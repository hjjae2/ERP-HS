<template>
  <div>
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <h2>작업 내역서</h2>
      </div>
      <el-row :gutter="20" type="flex">
        <el-col :span="12">
          <el-input
            v-model="searchValue"
            placeholder="검색할 내용을 입력해주세요"
            class="input-with-select"
          >
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
      <br />
      <el-row type="flex" justify="end">
        <el-button plain @click="saveAll">전체 내용 저장</el-button>
        <el-button plain @click="addListRow">내용 추가</el-button>
        <el-button plain @click="clearFilter">필터 초기화</el-button>
      </el-row>
      <br />
      <el-table
        ref="workTable"
        show-summary
        :data="
          data.filter(
            (data) =>
              !searchValue ||
              data[searchColumn]
                .toLowerCase()
                .includes(searchValue.toLowerCase())
          )
        "
      >
        <el-table-column prop="date" label="날짜" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.date"
              size="small"
              style="text-align: center"
              controls-position="right"
            ></el-input>
          </template>
        </el-table-column>
        <el-table-column
          prop="workType"
          label="구분"
          sortable
          :filters="workTypeOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.workType"
              :default-first-option="true"
              placeholder="구분"
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
          prop="business"
          label="우리사업자"
          sortable
          :filters="businessOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.business"
              :default-first-option="true"
              placeholder="우리사업자"
            >
              <el-option
                v-for="item in businessOptions"
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
          label="거래처"
          sortable
          :filters="companyOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.company"
              :fetch-suggestions="queryCompanySuggestion"
              :trigger-on-focus="false"
              style="display: block"
              placeholder="거래처를 입력해주세요"
              size="medium"
              @select="handleSelect"
            ></el-autocomplete>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="작업자"
          sortable
          :filters="nameOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.name"
              :fetch-suggestions="queryNameSuggestion"
              :trigger-on-focus="false"
              style="display: block"
              placeholder="작업자를 입력해주세요"
              size="medium"
              @select="handleSelect"
            ></el-autocomplete>
          </template>
        </el-table-column>
        <el-table-column prop="dispatcher" label="배차대상" sortable>
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.dispatcher"
              :fetch-suggestions="queryDispatcherSuggestion"
              :trigger-on-focus="false"
              style="display: block"
              placeholder="배차대상을 입력해주세요."
              size="medium"
              @select="handleSelect"
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
            ></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="" label="기능">
          <template slot-scope="scope">
            <el-row type="flex" :gutter="20">
              <el-col>
                <el-popconfirm
                  title="저장하시겠습니까?"
                  confirm-button-text="저장"
                  cancel-button-text="취소"
                  icon="el-icon-warning"
                  icon-color="rgb(31, 59, 179)"
                  style="display: inline-block"
                >
                  <el-button slot="reference" plain type="info" size="small"
                    >저장</el-button
                  >
                </el-popconfirm>
              </el-col>
              <el-col>
                <el-popconfirm
                  title="해당 내용을 삭제하시겠습니까? 삭제한 내용은 복구할 수 없습니다."
                  confirm-button-text="확인"
                  confirm-button-type="danger"
                  cancel-button-text="취소"
                  icon="el-icon-warning"
                  icon-color="rgb(31, 59, 179)"
                  @confirm="deleteRow(scope.$index, scope.row)"
                >
                  <el-button slot="reference" plain type="danger" size="small"
                    >삭제</el-button
                  >
                </el-popconfirm>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
      <br />
      <el-row type="flex" justify="end">
        <el-button plain @click="saveAll">전체 내용 저장</el-button>
        <el-button plain @click="addListRow">내용 추가</el-button>
        <el-button plain @click="clearFilter">필터 초기화</el-button>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [],
      nameList: [],
      dispatcherList: [],
      companyList: [],
      // Search data
      searchValue: '',
      searchColumn: 'date',
      seacrhColumns: [
        {
          value: 'date',
          label: '날짜',
        },
        {
          value: 'workType',
          label: '구분',
        },
        {
          value: 'business',
          label: '우리사업자',
        },
        {
          value: 'company',
          label: '거래처',
        },
        {
          value: 'name',
          label: '작업자',
        },
        {
          value: 'dispatcher',
          label: '배차대상',
        },
        {
          value: 'address',
          label: '주소',
        },
        {
          value: 'content',
          label: '내용',
        },
        {
          value: 'price',
          label: '금액',
        },
        {
          value: 'paymentStatus',
          label: '결제상태',
        },
        {
          value: 'expenditureStatus',
          label: '지출상태',
        },
        {
          value: 'taxStatus',
          label: '세금계산서',
        },
        {
          value: 'communicator',
          label: '일반/통신',
        },
        {
          value: 'remark',
          label: '비고',
        },
      ],
      // Option List
      nameOptions: [],
      companyOptions: [],
      businessOptions: [],
      workTypeOptions: [],
      paymentStatusOptions: [],
      expenditureStatusOptions: [],
      taxStatusOptions: [],
      communicatorOptions: [],
    }
  },
  created() {
    this.initData()
    this.initSearchColumn()
    this.initNameList()
    this.initDispatcherList()
    this.initCompanyList()

    this.initCompanyOptions()
    this.initNameOptions()
    this.initBusinessOptions()
    this.initWorkTypeOptions()
    this.initPaymentStatusOptions()
    this.initExpenditureStatusOptions()
    this.initTaxStatusOptions()
    this.initCommunicatorOptions()
  },
  methods: {
    initData() {
      const testData = new Array(3)
      const item1 = {
        id: '1',
        date: '2021-04-02',
        business: '화성스카이',
        workType: '일반',
        name: '홍길동',
        company: '가나다컴퍼니',
        address: 'No. 189, Grove St, Los Angeles',
        content: '작업 내용입니다.',
        dispatcher: '-',
        price: '200000',
        paymentStatus: '완료',
        expenditureStatus: '부분완료',
        taxStatus: '발급완료',
        communicator: '일반',
        remark: '-',
      }
      const item2 = {
        id: '2',
        date: '2021-05-02',
        business: '화성스카이차',
        workType: '지출',
        name: '홍길동',
        company: 'ABC컴퍼니',
        address: 'No. 189, Grove St, Los Angeles',
        content: '-',
        dispatcher: '-',
        price: '200000',
        paymentStatus: '완료',
        expenditureStatus: '부분완료',
        taxStatus: '발급미완료',
        communicator: '통신',
        remark: '-',
      }
      testData.push(item1)
      testData.push(item2)
      this.data = testData
    },
    initSearchColumn() {
      this.searchColumn = this.seacrhColumns[0].value
    },
    initNameList() {
      // todo
      // 1. api 호출
      this.nameList = [
        { value: '홍길동' },
        { value: '김철수' },
        { value: '이짱구' },
      ]
    },
    initDispatcherList() {
      // todo
      // 1. api 호출
      this.dispatcherList = [
        { value: '홍길동' },
        { value: '김철수' },
        { value: '이짱구' },
      ]
    },
    initCompanyList() {
      this.companyList = [{ value: '가나다컴퍼니' }, { value: 'ABC컴퍼니' }]
    },
    initNameOptions() {
      this.nameList.forEach((name) => {
        this.nameOptions.push({
          value: name.value,
          label: name.value,
          text: name.value,
        })
      })
    },
    initCompanyOptions() {
      this.companyList.forEach((company) => {
        this.companyOptions.push({
          value: company.value,
          label: company.value,
          text: company.value,
        })
      })
    },
    initWorkTypeOptions() {
      this.workTypeOptions = [
        { value: '일반', label: '일반', text: '일반' },
        { value: '지출', label: '지출', text: '지출' },
        { value: '비용', label: '비용', text: '비용' },
      ]
    },
    initBusinessOptions() {
      this.businessOptions = [
        { value: '화성스카이차', label: '화성스카이차', text: '화성스카이차' },
        { value: '화성스카이', label: '화성스카이', text: '화성스카이' },
      ]
    },
    initPaymentStatusOptions() {
      this.paymentStatusOptions = [
        { value: '미완료', label: '미완료', text: '미완료' },
        { value: '완료', label: '완료', text: '완료' },
        { value: '부분완료', label: '부분완료', text: '부분완료' },
      ]
    },
    initExpenditureStatusOptions() {
      this.expenditureStatusOptions = [
        { value: '미완료', label: '미완료', text: '미완료' },
        { value: '완료', label: '완료', text: '완료' },
        { value: '부분완료', label: '부분완료', text: '부분완료' },
      ]
    },
    initTaxStatusOptions() {
      this.taxStatusOptions = [
        { value: '발급미완료', label: '발급미완료', text: '발급미완료' },
        { value: '발급완료', label: '발급완료', text: '발급완료' },
      ]
    },
    initCommunicatorOptions() {
      this.communicatorOptions = [
        { value: '일반', label: '일반', text: '일반' },
        { value: '통신', label: '통신', text: '통신' },
      ]
    },
    clearFilter() {
      this.$refs.workTable.clearFilter()
    },
    formatter(row, column) {
      return row.address
    },
    filter(value, row) {
      return row.tag === value
    },
    filterExpenditureStatus(value, row) {
      return row.expenditureStatus === value
    },
    filterCommunicator(value, row) {
      return row.communicator === value
    },
    filterHandler(value, row, column) {
      const property = column.property
      console.log(value)
      console.log(row)
      console.log(row.property)
      console.log(row[property])
      console.log(column)
      return row[property] === value
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
    handleSelect(item) {
      console.log(item)
    },
    queryNameSuggestion(queryString, callback) {
      callback(
        queryString
          ? this.nameList.filter((name) =>
              name.value.toLowerCase().includes(queryString.toLowerCase())
            )
          : this.nameList
      )
    },
    queryDispatcherSuggestion(queryString, callback) {
      callback(
        queryString
          ? this.dispatcherList.filter((dispatcher) =>
              dispatcher.value.toLowerCase().includes(queryString.toLowerCase())
            )
          : this.dispatcherList
      )
    },
    queryCompanySuggestion(queryString, callback) {
      callback(
        queryString
          ? this.companyList.filter((company) =>
              company.value.toLowerCase().includes(queryString.toLowerCase())
            )
          : this.companyList
      )
    },
    addListRow() {
      console.log(this.data.length + 1)
      this.data.push({
        id: this.data.length + 1,
        date: '',
        workType: '',
        name: '',
        company: '',
        address: '',
        content: '',
        dispatcher: '',
        price: '',
        paymentStatus: '',
        expenditureStatus: '',
        taxStatus: '',
        communicator: '',
        remark: '',
      })
    },
    saveAll() {
      // 1. call api to save all
      // 1-1. 새롭게 생긴 item 만 save?
      // 업데이트 된 게 있을 수 있음.
      // 1-2. all item save?
      // 1-3. 단건 save vs list save
      console.log(JSON.stringify(this.data))
    },
    saveOne(index, row) {
      // 1. call api to save
      console.log(index)
      console.log(row)
    },
    deleteRow(index, row) {
      // 1. call api to delete
      // 2. refresh
      console.log(row)
    },
  },
}
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
