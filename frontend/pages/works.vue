<template>
  <div>
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <h2>작업 내역서</h2>
      </div>

      <!-- 검색 레이어 -->
      <el-row :gutter="20" type="flex">
        <el-col :span="12">
          <el-input v-model="searchValue" placeholder="검색할 내용을 입력해주세요" class="input-with-select">
            <el-select
              slot="prepend"
              v-model="searchColumn"
              :default-first-option="true"
              placeholder="검색열"
            >
              <el-option
                v-for="item in seacrhColumns"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <!-- //검색 레이어 -->

      <br />

      <!-- 테이블 상단 버튼 레이어 -->
      <el-row type="flex" justify="end">
        <el-button plain @click="addRow">내용 추가</el-button>
        <el-button plain @click="clearFilter">필터 초기화</el-button>
      </el-row>
      <!-- //테이블 상단 버튼 레이어 -->

      <br />

      <!-- 테이블 레이어 -->
      <el-table
        ref="mainTable"
        show-summary
        :summary-method="getSummaries"
        :data="
          works.filter((work) => {
            if (!searchValue) {
              return true;
            }
            return work[searchColumn] && work[searchColumn].toLowerCase().includes(searchValue.toLowerCase());
          })
        "
        :max-height="tableMaxHeight"
      >
        <el-table-column prop="id" label="No" sortable> </el-table-column>

        <el-table-column
          prop="workDate"
          label="날짜"
          sortable
          :filters="workDateOptions"
          :filter-method="filterHandler"
        >
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.workDate"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column
          prop="workType"
          label="구분"
          sortable
          :filters="workTypeOptions"
          :filter-method="filterHandler"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.workType"
              :default-first-option="true"
              placeholder="구분"
              @change="saveOrUpdateRow(scope.row)"
            >
              <el-option
                v-for="item in workTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column
          prop="company"
          label="우리사업자"
          sortable
          :filters="companyOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.company"
              :default-first-option="true"
              placeholder="우리사업자"
              @change="saveOrUpdateRow(scope.row)"
            >
              <el-option
                v-for="item in companyOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column
          prop="customer"
          label="거래처"
          sortable
          :filters="customerOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.customer"
              :fetch-suggestions="queryCompanySuggestions"
              style="display: block"
              placeholder="거래처를 입력해주세요"
              size="medium"
              @change="saveOrUpdateRow(scope.row)"
              @select="saveOrUpdateRow(scope.row)"
            ></el-autocomplete>
          </template>
        </el-table-column>

        <el-table-column
          prop="worker"
          label="작업자"
          sortable
          :filters="workerOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.worker"
              :fetch-suggestions="queryPersonSuggestions"
              style="display: block"
              placeholder="작업자를 입력해주세요"
              size="medium"
              @change="saveOrUpdateRow(scope.row)"
              @select="saveOrUpdateRow(scope.row)"
            ></el-autocomplete>
          </template>
        </el-table-column>

        <el-table-column prop="dispatcher" label="배차대상" sortable>
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.dispatcher"
              :fetch-suggestions="queryPersonSuggestions"
              style="display: block"
              placeholder="배차대상을 입력해주세요."
              size="medium"
              @change="saveOrUpdateRow(scope.row)"
              @select="saveOrUpdateRow(scope.row)"
            ></el-autocomplete>
          </template>
        </el-table-column>

        <el-table-column prop="address" label="주소" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.address"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column prop="content" label="내용" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.content"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column prop="price" label="금액" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.price"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column
          prop="paymentStatus"
          label="결제상태"
          sortable
          :filters="paymentStatusOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.paymentStatus"
              :default-first-option="true"
              placeholder="결제상태"
              @change="saveOrUpdateRow(scope.row)"
            >
              <el-option
                v-for="item in paymentStatusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column
          prop="expenditureStatus"
          label="지출상태"
          sortable
          :filters="expenditureStatusOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.expenditureStatus"
              :default-first-option="true"
              placeholder="지출상태"
              @change="saveOrUpdateRow(scope.row)"
            >
              <el-option
                v-for="item in expenditureStatusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column
          prop="taxStatus"
          label="세금계산서"
          sortable
          :filters="taxStatusOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.taxStatus"
              :default-first-option="true"
              placeholder="세금계산서"
              @change="saveOrUpdateRow(scope.row)"
            >
              <el-option
                v-for="item in taxStatusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column
          prop="communicator"
          label="일반/통신"
          sortable
          :filters="communicatorOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.communicator"
              :default-first-option="true"
              placeholder="일반/통신"
              @change="saveOrUpdateRow(scope.row)"
            >
              <el-option
                v-for="item in communicatorOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="remark" label="비고" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.remark"
              type="textarea"
              rows="2"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column prop="" label="기능">
          <template slot-scope="scope">
            <el-row type="flex" :gutter="20">
              <el-popconfirm
                title="저장하시겠습니까?"
                confirm-button-text="저장"
                confirm-button-type="info"
                cancel-button-text="취소"
                cancel-button-type=""
                icon="el-icon-warning-outline"
                icon-color="#5ACCFB"
                @confirm="saveOrUpdateRow(scope.row)"
              >
                <el-button slot="reference" plain type="info" size="small">저장</el-button>
              </el-popconfirm>
              &nbsp;
              <el-popconfirm
                title="해당 내용을 삭제하시겠습니까? 삭제한 내용은 복구할 수 없습니다."
                confirm-button-text="삭제"
                confirm-button-type="danger"
                cancel-button-text="취소"
                cancel-button-type=""
                icon="el-icon-warning-outline"
                icon-color="red"
                @confirm="deleteRow(scope.row)"
              >
                <el-button slot="reference" plain type="danger" size="small">삭제</el-button>
              </el-popconfirm>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
      <!-- //테이블 레이어 -->

      <br />

      <!-- 테이블 하단 버튼 레이어 -->
      <el-row type="flex" justify="end">
        <el-button plain @click="addRow">내용 추가</el-button>
        <el-button plain @click="clearFilter">필터 초기화</el-button>
      </el-row>
      <!-- //테이블 하단 버튼 레이어 -->
    </el-card>
  </div>
</template>

<script>
import WorkAPI from '~/api/work';
import PersonAPI from '~/api/person';
import CompanyAPI from '~/api/company';

const workAPI = new WorkAPI();
const personAPI = new PersonAPI();
const companyAPI = new CompanyAPI();

export default {
  data() {
    return {
      // About data
      works: [],
      people: [],
      companies: [],
      workDateOptions: [],
      workTypeOptions: [],
      companyOptions: [
        { value: '화성스카이차', label: '화성스카이차', text: '화성스카이차' },
        { value: '화성스카이', label: '화성스카이', text: '화성스카이' },
      ],
      customerOptions: [],
      workerOptions: [],
      dispatcherOptions: [],
      addressOptions: [],
      priceOptions: [],
      paymentStatusOptions: [],
      expenditureStatusOptions: [],
      taxStatusOptions: [],
      communicatorOptions: [],

      // About flag
      isError: false,

      // About search
      searchValue: '',
      searchColumn: 'date',
      seacrhColumns: [
        { value: 'date', label: '날짜' },
        { value: 'workType', label: '구분' },
        { value: 'company', label: '우리사업자' },
        { value: 'customer', label: '거래처' },
        { value: 'worker', label: '작업자' },
        { value: 'dispatcher', label: '배차대상' },
        { value: 'address', label: '주소' },
        { value: 'content', label: '내용' },
        { value: 'price', label: '금액' },
        { value: 'paymentStatus', label: '결제상태' },
        { value: 'expenditureStatus', label: '지출상태' },
        { value: 'taxStatus', label: '세금계산서' },
        { value: 'communicator', label: '일반/통신' },
        { value: 'remark', label: '비고' },
      ],
    };
  },
  computed: {
    tableMaxHeight() {
      const windowHeight =
        (window && window.innerHeight) ||
        (document && document.documentElement.clientHeight) ||
        (document && document.body.clientHeight);

      return windowHeight ? windowHeight * 0.6 : -1;
    },
  },
  created() {
    this.initList();
    this.initCompanyList();
    this.initPeopleList();
    this.initSearchColumn();
    this.resetError();
  },
  methods: {
    async initList() {
      let works = [];
      try {
        const response = await workAPI.getWorks();
        works = response.data.data;
      } catch (error) {
        this.occurError('데이터 조회 중 오류가 발생하였습니다.');
      } finally {
        this.works = works;
        this.initOptions();
      }
    },
    async initCompanyList() {
      let companies = [];
      try {
        const response = await companyAPI.getCompanies();
        companies = response.data.data;
        companies = companies.map((company) => {
          return {
            value: company.name,
          };
        });
      } catch (error) {
        this.occurError('거래처 데이터 조회 중 오류가 발생하였습니다.');
      } finally {
        this.companies = companies;
      }
    },
    async initPeopleList() {
      let people = [];
      try {
        const response = await personAPI.getPeople();
        people = response.data.data;
        people = people.map((person) => {
          return {
            value: person.name,
          };
        });
      } catch (error) {
        this.occurError('작업자/거래인 데이터 조회 중 오류가 발생하였습니다.');
      } finally {
        this.people = people;
      }
    },
    initOptions() {
      if (Array.isArray(this.works)) {
        this.works.forEach((work) => {
          if (
            work.workDate &&
            this.workDateOptions.filter((workDateOption) => workDateOption.value === work.workDate).length ===
              0
          ) {
            this.workDateOptions.push({
              value: work.workDate,
              label: work.workDate,
              text: work.workDate,
            });
          }

          if (
            work.customer &&
            this.customerOptions.filter((customerOption) => customerOption.value === work.customer).length ===
              0
          ) {
            this.customerOptions.push({
              value: work.customer,
              label: work.customer,
              text: work.customer,
            });
          }

          if (
            work.worker &&
            this.workerOptions.filter((workerOption) => workerOption.value === work.worker).length === 0
          ) {
            console.log(work);
            this.workerOptions.push({
              value: work.worker,
              label: work.worker,
              text: work.worker,
            });
          }

          if (
            work.dispatcher &&
            this.dispatcherOptions.filter((dispatcherOption) => dispatcherOption.value === work.dispatcher)
              .length === 0
          ) {
            this.dispatcherOptions.push({
              value: work.dispatcher,
              label: work.dispatcher,
              text: work.dispatcher,
            });
          }

          if (
            work.address &&
            this.addressOptions.filter((addressOption) => addressOption.value === work.address).length === 0
          ) {
            this.addressOptions.push({
              value: work.address,
              label: work.address,
              text: work.address,
            });
          }

          if (
            work.price &&
            this.priceOptions.filter((priceOption) => priceOption.value === work.price).length === 0
          ) {
            this.priceOptions.push({
              value: work.price,
              label: work.price,
              text: work.price,
            });
          }
        });
      }

      this.initWorkTypeOptions();
      this.initPaymentStatusOptions();
      this.initExpenditureStatusOptions();
      this.initTaxStatusOptions();
      this.initCommunicatorOptions();
    },
    initWorkTypeOptions() {
      this.workTypeOptions = [
        { value: '일반', label: '일반', text: '일반' },
        { value: '지출', label: '지출', text: '지출' },
        { value: '비용', label: '비용', text: '비용' },
      ];
    },
    initPaymentStatusOptions() {
      this.paymentStatusOptions = [
        { value: '미완료', label: '미완료', text: '미완료' },
        { value: '완료', label: '완료', text: '완료' },
        { value: '부분완료', label: '부분완료', text: '부분완료' },
      ];
    },
    initExpenditureStatusOptions() {
      this.expenditureStatusOptions = [
        { value: '미완료', label: '미완료', text: '미완료' },
        { value: '완료', label: '완료', text: '완료' },
        { value: '부분완료', label: '부분완료', text: '부분완료' },
      ];
    },
    initTaxStatusOptions() {
      this.taxStatusOptions = [
        { value: '발급미완료', label: '발급미완료', text: '발급미완료' },
        { value: '발급완료', label: '발급완료', text: '발급완료' },
      ];
    },
    initCommunicatorOptions() {
      this.communicatorOptions = [
        { value: 'N', label: '일반', text: '일반' },
        { value: 'Y', label: '통신', text: '통신' },
      ];
    },
    initSearchColumn() {
      this.searchColumn = this.seacrhColumns[0].value;
    },
    addRow() {
      const now = new Date();
      const month = now.getMonth() + 1 >= 10 ? now.getMonth() + 1 : '0' + (now.getMonth() + 1);
      const date = now.getDate() >= 10 ? now.getDate() : '0' + now.getDate();
      const newRow = {
        id: 'new',
        workDate: `${now.getFullYear()}-${month}-${date}`,
        workType: '일반',
        company: '',
        customer: '',
        worker: '',
        dispatcher: '',
        address: '',
        content: '',
        price: '0',
        paymentStatus: '미완료',
        expenditureStatus: '미완료',
        taxStatus: '발급미완료',
        communicator: 'N',
        remark: '',
      };
      this.works.push(newRow);
    },
    saveOrUpdateRow(row) {
      if (row.id === 'new') {
        this.saveRow(row);
      } else {
        this.updateRow(row);
      }
    },
    async saveRow(row) {
      try {
        await workAPI.saveWork(row);
        await this.initList();
        this.successMessage('데이터를 저장하였습니다.');
      } catch (error) {
        this.occurError('데이터 저장 중 오류가 발생하였습니다.');
      }
    },
    async updateRow(row) {
      try {
        await workAPI.updateWork(row.id, row);
        await this.initList();
        this.successMessage('데이터를 수정하였습니다.');
      } catch (error) {
        this.occurError('데이터 업데이트 중 오류가 발생하였습니다.');
      }
    },
    async deleteRow(row) {
      try {
        await workAPI.deleteWork(row.id);
        await this.initList();
        this.successMessage('데이터를 삭제하였습니다.');
      } catch (error) {
        this.occurError('데이터 삭제 중 오류가 발생하였습니다.');
      }
    },
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];

      columns.forEach((column, index) => {
        if (column.property === 'id') {
          sums[index] = '총 ' + data.length + ' 건';
        }

        if (column.property === 'price') {
          const prices = data.map((item) => Number(item[column.property]));
          const priceSum = prices.reduce((acc, cur) => {
            return acc + cur;
          }, 0);
          sums[index] = priceSum.toLocaleString() + ' 원';
        }

        if (column.property === 'paymentStatus') {
          const statuses = data.map((item) => item[column.property]);
          const completion = statuses.filter((status) => status === '완료').length;
          const incompletion = statuses.filter((status) => status === '미완료').length;
          const partialCompletion = statuses.filter((status) => status === '부분완료').length;

          sums[index] = completion + ' / ' + incompletion + ' / ' + partialCompletion;
        }

        if (column.property === 'expenditureStatus') {
          const statuses = data.map((item) => item[column.property]);
          const completion = statuses.filter((status) => status === '완료').length;
          const incompletion = statuses.filter((status) => status === '미완료').length;
          const partialCompletion = statuses.filter((status) => status === '부분완료').length;

          sums[index] = completion + ' / ' + incompletion + ' / ' + partialCompletion;
        }

        if (column.property === 'taxStatus') {
          const statuses = data.map((item) => item[column.property]);
          const completion = statuses.filter((status) => status === '발급완료').length;
          const incompletion = statuses.filter((status) => status === '발급미완료').length;

          sums[index] = completion + ' / ' + incompletion;
        }

        if (column.property === 'communicator') {
          const statuses = data.map((item) => item[column.property]);
          const normal = statuses.filter((status) => status === 'N').length;
          const communicator = statuses.filter((status) => status === 'Y').length;

          sums[index] = normal + ' / ' + communicator;
        }
      });

      return sums;
    },
    queryPersonSuggestions(query, callback) {
      callback(
        query
          ? this.people.filter((person) => person.value.toLowerCase().includes(query.toLowerCase()))
          : this.people
      );
    },
    queryCompanySuggestions(query, callback) {
      callback(
        query
          ? this.companies.filter((company) => company.value.toLowerCase().includes(query.toLowerCase()))
          : this.companies
      );
    },
    filterHandler(value, row, column) {
      const property = column.property;

      return row[property] === value;
    },
    clearFilter() {
      this.$refs.mainTable.clearFilter();
    },
    resetError() {
      this.isError = false;
    },
    occurError(message) {
      this.isError = true;
      this.errorMessage(message);
    },
    successMessage(message) {
      this.$message({
        showClose: true,
        message,
        type: 'success',
      });
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

<style>
.borderRadius10 {
  border-radius: 10px;
}
.el-table__row .el-input .el-input__inner {
  border-style: none;
}
.hover-row .el-input .el-input__inner {
  border-style: solid;
}
.el-select .el-input {
  width: 110px;
}
.input-with-select .el-input-group__prepend {
  color: #909399;
  background-color: #fff;
}
</style>
