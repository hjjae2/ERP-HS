<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <h2>대시보드</h2>
      </div>
      <el-row :gutter="20" type="flex" align="middle" justify="center">
        <el-col :span="12">
          <div id="monthlyPriceSummary" class="monthlyPriceSummary" style="width: 100%; height: 300px"></div>
        </el-col>
        <el-col :span="12">
          <el-card
            shadow="never"
            style="border-radius: 10px; background-color: #1f3bb3; color: white; opacity: 0.9"
          >
            <div slot="header" class="clearfix">
              <strong>{{ new Date().getMonth() + 1 }}월 작업 요약</strong>
            </div>
            <div style="padding: 14px">
              <strong>총 작업 건수</strong>
              <div>
                <strong>{{ workStatisticsOfThisMonth.totalNumberOfWorks.toLocaleString() }} 건</strong>
              </div>
            </div>
            <div style="padding: 14px">
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

export default {
  data() {
    return {
      workStatisticsOfThisMonth: {
        totalSummationOfPrices: 1086500,
        totalNumberOfWorks: 10,
        works: [
          {
            id: 5,
            workDate: '2021-11-14',
            workType: '일반',
            worker: '이현재',
            company: '화성스카이차',
            customer: '김포공항',
            dispatcher: null,
            address: '경기도 수원시 권선구 평동 106 801',
            content: null,
            remark: '비고!',
            paymentStatus: '완료',
            expenditureStatus: '미완료',
            taxStatus: '발급완료',
            price: 10500,
            communicator: 'N',
          },
          {
            id: 6,
            workDate: '2021-11-14',
            workType: '일반',
            worker: '이현재',
            company: '화성스카이차',
            customer: '김포공항',
            dispatcher: null,
            address: '경기도 수원시 권선구 평동 106 801',
            content: null,
            remark: '비고!',
            paymentStatus: '완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 10500,
            communicator: 'N',
          },
          {
            id: 7,
            workDate: '2021-11-14',
            workType: '일반',
            worker: '이현재',
            company: '화성스카이차',
            customer: '김포공항',
            dispatcher: null,
            address: '경기도 수원시 권선구 평동 106 801',
            content: '비고!',
            remark: '비고!',
            paymentStatus: '완료',
            expenditureStatus: '완료',
            taxStatus: '발급미완료',
            price: 10500,
            communicator: 'Y',
          },
          {
            id: 8,
            workDate: '2021-11-28',
            workType: '일반',
            worker: '이현재',
            company: '화성스카이차',
            customer: '김포공항',
            dispatcher: '김리리',
            address: '경기도 수원시 권선구 평동 106 801',
            content: '콘텐츠!!!',
            remark: '비고!',
            paymentStatus: '완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 1050000,
            communicator: 'N',
          },
          {
            id: 10,
            workDate: '2021-11-26',
            workType: '일반',
            worker: null,
            company: '화성스카이차',
            customer: '화성스카이차',
            dispatcher: null,
            address: '',
            content: '',
            remark: '',
            paymentStatus: '부분완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 5000,
            communicator: 'N',
          },
          {
            id: 11,
            workDate: '2021-11-26',
            workType: '일반',
            worker: null,
            company: '화성스카이차',
            customer: '화성스카이',
            dispatcher: '이현재',
            address: '',
            content: '',
            remark: '',
            paymentStatus: '완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 0,
            communicator: 'N',
          },
          {
            id: 16,
            workDate: '2021-11-26',
            workType: '일반',
            worker: null,
            company: '화성스카이',
            customer: null,
            dispatcher: null,
            address: '',
            content: '',
            remark: '',
            paymentStatus: '미완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 0,
            communicator: 'N',
          },
          {
            id: 17,
            workDate: '2021-11-26',
            workType: '일반',
            worker: null,
            company: '화성스카이',
            customer: null,
            dispatcher: null,
            address: '',
            content: '',
            remark: '',
            paymentStatus: '미완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 0,
            communicator: 'N',
          },
          {
            id: 18,
            workDate: '2021-11-26',
            workType: '일반',
            worker: null,
            company: null,
            customer: null,
            dispatcher: null,
            address: '',
            content: '',
            remark: '',
            paymentStatus: '미완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 0,
            communicator: 'N',
          },
          {
            id: 19,
            workDate: '2021-11-26',
            workType: '지출',
            worker: null,
            company: null,
            customer: null,
            dispatcher: null,
            address: '',
            content: '',
            remark: '',
            paymentStatus: '미완료',
            expenditureStatus: '미완료',
            taxStatus: '발급미완료',
            price: 0,
            communicator: 'N',
          },
        ],
      },
      workStatisticsOfMonthlyPrice: [
        {
          month: '2021-05',
          totalSummationOfPrices: 500000,
          totalNumberOfWorks: 5,
        },
        {
          month: '2021-06',
          totalSummationOfPrices: 500000,
          totalNumberOfWorks: 5,
        },
        {
          month: '2021-07',
          totalSummationOfPrices: 500000,
          totalNumberOfWorks: 5,
        },
        {
          month: '2021-09',
          totalSummationOfPrices: 101000,
          totalNumberOfWorks: 7,
        },
        {
          month: '2021-10',
          totalSummationOfPrices: 70000,
          totalNumberOfWorks: 8,
        },
        {
          month: '2021-11',
          totalSummationOfPrices: 1086500,
          totalNumberOfWorks: 10,
          columnSettings: {
            strokeWidth: 1,
            strokeDasharray: [5],
            fillOpacity: 0.2,
          },
          info: '(진행중)',
        },
      ],
    };
  },
  mounted() {
    this.showWorkStatisticsOfThisMonth();
    this.showWorkStatisticsOfMonthlyPrice();
  },
  methods: {
    showWorkStatisticsOfThisMonth() {},
    showWorkStatisticsOfMonthlyPrice() {
      const root = am5.Root.new('monthlyPriceSummary');
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
      chart.appear(1000, 10);

      const legend = chart.children.push(
        am5.Legend.new(root, {
          centerX: am5.p50,
          x: am5.p50,
        })
      );
      legend.data.setAll(chart.series.values);

      const yAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          logarithmic: true,
          renderer: am5xy.AxisRendererY.new(root, {}),
        })
      );
      const xAxis = chart.xAxes.push(
        am5xy.CategoryAxis.new(root, {
          renderer: am5xy.AxisRendererX.new(root, {}),
          categoryField: 'month',
        })
      );

      const priceSeries = chart.series.push(
        this.getColumnSeries(root, xAxis, yAxis, '매출', 'totalSummationOfPrices', 'month', {
          labelText: '{name} : {valueY} 원 {info}',
        })
      );
      const countSeries = chart.series.push(
        this.getLineSeries(root, xAxis, yAxis, '작업 건수', 'totalNumberOfWorks', 'month', {
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

      priceSeries.data.setAll(this.workStatisticsOfMonthlyPrice);
      countSeries.data.setAll(this.workStatisticsOfMonthlyPrice);
      xAxis.data.setAll(this.workStatisticsOfMonthlyPrice);
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
  },
};
</script>

<style scoped>
body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif,
    'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
}
</style>
