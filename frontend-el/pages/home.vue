<template>
  <el-main>
    <el-card>
      <el-row text-right>
        <el-button type="primary" round @click="clearFilter"
          >필터 초기화</el-button
        >
        <el-button type="primary" round @click="AddListRow()"
          >내용 추가</el-button
        >
      </el-row>
      <el-table
        ref="workTable"
        show-summary
        :data="
          tableData.filter(
            (data) =>
              !search || data.name.toLowerCase().includes(search.toLowerCase())
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
        <el-table-column prop="type" label="구분" sortable>
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.type"
              :default-first-option="true"
              placeholder="구분"
            >
              <el-option
                v-for="item in typeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="company" label="거래처" sortable>
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.company"
              :fetch-suggestions="querySearch"
              :trigger-on-focus="false"
              style="display: block"
              placeholder="거래처를 입력해주세요"
              size="medium"
              @select="handleSelect"
            ></el-autocomplete>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="작업자" sortable>
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.name"
              :fetch-suggestions="querySearch"
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
              :fetch-suggestions="querySearch"
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
        <el-table-column prop="paymentStatus" label="결제상태" sortable>
          <template slot-scope="scope">
            <el-select
              v-model="scope.row.paymentStatus"
              :default-first-option="true"
              placeholder="구분"
            >
              <el-option
                v-for="item in typeOptions"
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
          :filters="[
            { text: '완료', value: '완료' },
            { text: '부분완료', value: '부분완료' },
            { text: '미완료', value: '미완료' },
          ]"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="
                scope.row.expenditureStatus === '완료' ? 'primary' : 'success'
              "
              disable-transitions
              >{{ scope.row.expenditureStatus }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="taxStatus" label="세금계산서" sortable />
        <el-table-column
          prop="communicator"
          label="일반/통신"
          sortable
          :filters="[
            { text: '일반', value: '일반' },
            { text: '통신', value: '통신' },
          ]"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.communicator === '일반' ? 'primary' : 'success'"
              disable-transitions
              >{{ scope.row.communicator }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="비고" sortable />
        <el-table-column prop="" label="기능">
          <template slot="header" slot-scope="scope">
            <el-input v-model="search" size="mini" :placeholder="scope.row" />
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >Edit</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >Delete</el-button
            >
          </template>
          <!-- <template slot-scope="scope">
            <el-row :gutter="20">
              <el-col :span="11">
                <el-button @click="dialogFormVisible = true">수정</el-button>
              </el-col>
              <el-col :span="9">
                <el-button>삭제</el-button>
              </el-col>
            </el-row>
          </template> -->
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="작업내역서"
      :visible.sync="dialogFormVisible"
      width="60%"
      custom-class="borderRadius10"
    >
      <WorkForm :work="{ content: 'ihihi' }" />
      <span slot="footer" class="dialog-footer">
        <el-popconfirm
          title="저장하시겠습니까?"
          confirm-button-text="저장"
          cancel-button-text="취소"
          icon="el-icon-warning"
          icon-color="rgb(31, 59, 179)"
        >
          <el-button slot="reference" type="primary">저장</el-button>
        </el-popconfirm>
        <el-popconfirm
          title="창을 닫으시겠습니까? 작성한 내용이 저장되지 않습니다."
          confirm-button-text="확인"
          confirm-button-type="danger"
          cancel-button-text="취소"
          icon="el-icon-warning"
          icon-color="rgb(31, 59, 179)"
          @confirm="dialogFormVisible = false"
        >
          <el-button slot="reference" type="danger">취소</el-button>
        </el-popconfirm>
      </span>
    </el-dialog>
  </el-main>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      links: [],
      dialogFormVisible: false,
      search: '',
      typeOptions: [
        {
          value: '일반',
          label: '일반',
        },
        {
          value: '지출',
          label: '지출',
        },
        {
          value: '비용',
          label: '비용',
        },
      ],
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      const testData = new Array(3)
      const item1 = {
        date: '2016-05-02',
        type: '일반',
        name: '홍길동',
        company: 'EACCompany',
        address: 'No. 189, Grove St, Los Angeles',
        content: '작업 내용입니다.',
        dispatcher: '-',
        price: '200000',
        paymentStatus: '완료',
        expenditureStatus: '부분완료',
        taxStatus: '미완료',
        communicator: '일반',
        remark: '-',
      }
      const item2 = {
        date: '2021-05-02',
        type: '지출',
        name: '홍길동',
        company: 'EACCompany',
        address: 'No. 189, Grove St, Los Angeles',
        content: '-',
        dispatcher: '-',
        price: '200000',
        paymentStatus: '완료',
        expenditureStatus: '부분완료',
        taxStatus: '미완료',
        communicator: '통신',
        remark: '-',
      }
      testData.push(item1)
      testData.push(item2)
      testData.push(item1)
      this.tableData = testData
      this.links = this.loadAll()
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
      return row[property] === value
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
    querySearch(queryString, cb) {
      const links = this.links
      const results = queryString
        ? links.filter(this.createFilter(queryString))
        : links
      // call callback function to return suggestions
      cb(results)
    },
    createFilter(queryString) {
      return (link) => {
        return link.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
      }
    },
    AddListRow() {
      this.tableData.push({
        date: '',
        type: '지출',
        name: '홍길동',
        company: 'EACCompany',
        address: 'No. 189, Grove St, Los Angeles',
        content: '-',
        dispatcher: '-',
        price: '200000',
        paymentStatus: '완료',
        expenditureStatus: '부분완료',
        taxStatus: '미완료',
        communicator: '통신',
        remark: '-',
      })
    },
    loadAll() {
      return [
        { value: 'vue', link: 'https://github.com/vuejs/vue' },
        { value: 'element', link: 'https://github.com/ElemeFE/element' },
        { value: 'cooking', link: 'https://github.com/ElemeFE/cooking' },
        { value: 'mint-ui', link: 'https://github.com/ElemeFE/mint-ui' },
        { value: 'vuex', link: 'https://github.com/vuejs/vuex' },
        { value: 'vue-router', link: 'https://github.com/vuejs/vue-router' },
        { value: 'babel', link: 'https://github.com/babel/babel' },
      ]
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
</style>
