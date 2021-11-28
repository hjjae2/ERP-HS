<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <h2>대시보드</h2>
      </div>
      <el-row :gutter="20" type="flex" align="middle" justify="center">
        <el-col :span="12">
          <div id="monthlySummary" class="monthlySummary" style="width: 100%; height: 300px"></div>
        </el-col>
        <el-col :span="12">
          <el-card
            shadow="never"
            style="border-radius: 10px; background-color: #1f3bb3; color: white; opacity: 0.9"
          >
            <div slot="header" class="clearfix" style="text-align: center">
              <strong>{{ new Date().getMonth() + 1 }}월 작업 요약</strong>
            </div>
            <div style="padding: 14px; text-align: center">
              <strong>총 작업 건수</strong>
              <div>
                <strong>{{ workStatisticsOfThisMonth.totalNumberOfWorks.toLocaleString() }} 건</strong>
              </div>
            </div>
            <div style="padding: 14px; text-align: center">
              <strong>총 매출</strong>
              <div>
                <strong>{{ workStatisticsOfThisMonth.totalSummationOfPrices.toLocaleString() }} 원</strong>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import * as am5 from '@amcharts/amcharts5';
import * as am5xy from '@amcharts/amcharts5/xy';
import am5themesAnimated from '@amcharts/amcharts5/themes/Animated';
import WorkStatisticsAPI from '~/api/workStatistics';

const workStatisticsAPI = new WorkStatisticsAPI();

export default {
  data() {
    return {
      // About data
      workStatisticsOfThisMonth: {
        totalSummationOfPrices: 0,
        totalNumberOfWorks: 0,
        works: [],
      },
      workStatisticsOfMonthly: [
        {
          month: '',
          totalSummationOfPrices: 0,
          totalNumberOfWorks: 0,
        },
      ],

      // About flag
      isError: false,
    };
  },
  async mounted() {
    await this.initData();
    this.showWorkStatisticsOfMonthly();
  },
  methods: {
    async initData() {
      await this.initWorkStatisticsOfThisMonth();
      await this.initWorkStatisticsOfMonthly(this.getLast5Months());
    },
    async initWorkStatisticsOfThisMonth() {
      try {
        const response = await workStatisticsAPI.getWorkStatisticsOfThisMonth();
        this.workStatisticsOfThisMonth = response.data.data;
      } catch (error) {
        this.occurError('데이터 조회 중 오류가 발생하였습니다.');
      }
    },
    async initWorkStatisticsOfMonthly(months) {
      /**
       * columnSettings: {
            strokeWidth: 1,
            strokeDasharray: [5],
            fillOpacity: 0.2,
          },
          info: '(진행중)',
       */
      try {
        const thisMonth = this.getThisMonth();
        this.workStatisticsOfMonthly.month = thisMonth;
        const payload = {
          months,
        };
        const response = await workStatisticsAPI.getWorkStatisticsOfMonthly(payload);
        this.workStatisticsOfMonthly = response.data.data;

        this.workStatisticsOfMonthly.forEach((value) => {
          if (value.month === thisMonth) {
            value.columnSettings = {
              strokeWidth: 1,
              strokeDasharray: [5],
              fillOpacity: 0.2,
            };
            value.info = '(진행중)';
          }
        });
      } catch (error) {
        this.occurError('데이터 조회 중 오류가 발생하였습니다.');
      }
    },
    showWorkStatisticsOfMonthly() {
      const root = am5.Root.new('monthlySummary');
      root.setThemes([am5themesAnimated.new(root)]);

      const chart = root.container.children.push(
        am5xy.XYChart.new(root, {
          panX: false,
          panY: false,
          wheelX: 'panX',
          wheelY: 'zoomX',
          layout: root.verticalLayout,
        })
      );
      chart.set('cursor', am5xy.XYCursor.new(root, {}));
      chart.get('colors').set('step', 2);
      chart.appear(1000, 10);

      const legend = chart.children.push(
        am5.Legend.new(root, {
          centerX: am5.p50,
          x: am5.p50,
        })
      );
      legend.data.setAll(chart.series.values);

      const priceAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          min: 0,
          renderer: am5xy.AxisRendererY.new(root, {}),
        })
      );
      const countAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          min: 0,
          renderer: am5xy.AxisRendererY.new(root, { opposite: true }),
        })
      );
      const xAxis = chart.xAxes.push(
        am5xy.CategoryAxis.new(root, {
          renderer: am5xy.AxisRendererX.new(root, {}),
          categoryField: 'month',
          tooltip: am5.Tooltip.new(root, {}),
        })
      );

      const priceSeries = chart.series.push(
        this.getColumnSeries(root, xAxis, priceAxis, '매출', 'totalSummationOfPrices', 'month', {
          labelText: '{name} : {valueY} 원 {info}',
        })
      );
      const countSeries = chart.series.push(
        this.getLineSeries(root, xAxis, countAxis, '작업 건수', 'totalNumberOfWorks', 'month', {
          labelText: '{name} : {valueY} 건 {info}',
        })
      );

      priceSeries.columns.template.setAll({
        templateField: 'columnSettings',
        cornerRadiusTL: 10,
        cornerRadiusTR: 10,
      });
      priceSeries.columns.template.adapters.add('fill', (fill, target) => {
        return chart.get('colors').getIndex(priceSeries.columns.indexOf(target));
      });
      priceSeries.columns.template.adapters.add('stroke', (stroke, target) => {
        return chart.get('colors').getIndex(priceSeries.columns.indexOf(target));
      });
      countSeries.bullets.push(function () {
        return am5.Bullet.new(root, {
          sprite: am5.Circle.new(root, {
            radius: 5,
            strokeWidth: 3,
            stroke: countSeries.get('stroke'),
            fill: root.interfaceColors.get('background'),
          }),
        });
      });

      priceSeries.data.setAll(this.workStatisticsOfMonthly);
      countSeries.data.setAll(this.workStatisticsOfMonthly);
      xAxis.data.setAll(this.workStatisticsOfMonthly);
    },
    getColumnSeries(root, xAxis, yAxis, name, valueYField, categoryXField, options) {
      const labelText = options.labelText ? options.labelText : '{valueY}';
      const pointerOrientation = options.pointerOrientation ? options.pointerOrientation : 'horizontal';
      const sequencedInterpolation = options.sequencedInterpolation ? options.sequencedInterpolation : false;

      return am5xy.ColumnSeries.new(root, {
        name,
        xAxis,
        yAxis,
        valueYField,
        sequencedInterpolation,
        categoryXField,
        tooltip: am5.Tooltip.new(root, {
          pointerOrientation,
          labelText,
        }),
      });
    },
    getLineSeries(root, xAxis, yAxis, name, valueYField, categoryXField, options) {
      const labelText = options.labelText ? options.labelText : '{valueY}';
      const pointerOrientation = options.pointerOrientation ? options.pointerOrientation : 'horizontal';
      const sequencedInterpolation = options.sequencedInterpolation ? options.sequencedInterpolation : false;

      return am5xy.LineSeries.new(root, {
        name,
        xAxis,
        yAxis,
        valueYField,
        sequencedInterpolation,
        categoryXField,
        tooltip: am5.Tooltip.new(root, {
          pointerOrientation,
          labelText,
        }),
      });
    },
    getMonthAsYYYYMM(year, month) {
      return `${year}-${month >= 10 ? month : '0' + month}`;
    },
    getThisMonth() {
      const now = new Date();
      const year = now.getFullYear();
      const month = now.getMonth() + 1;

      return this.getMonthAsYYYYMM(year, month);
    },
    getLast5Months() {
      const now = new Date();
      const thisYear = now.getFullYear();
      const thisMonth = now.getMonth();

      const last5Months = [];
      for (let i = 5; i >= 0; i--) {
        const date = new Date(thisYear, thisMonth - i, 1);
        last5Months.push(this.getMonthAsYYYYMM(date.getFullYear(), date.getMonth() + 1));
      }

      return last5Months;
    },
    occurError(message) {
      this.isError = true;
      this.errorMessage(message);
    },
    errorMessage(message) {
      this.$message({
        showClose: true,
        message,
        type: 'error',
      });
    },
  },
};
</script>

<style scoped>
body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif,
    'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
}
</style>
