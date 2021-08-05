<template>
  <div>
    <el-card>
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
        <el-table-column prop="name" label="이름" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.name"
              size="small"
              style="text-align: center"
              controls-position="right"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column prop="tel" label="전화번호" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.tel"
              size="small"
              style="text-align: center"
              controls-position="right"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column prop="email" label="이메일" sortable>
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.email"
              size="small"
              style="text-align: center"
              controls-position="right"
            ></el-input>
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
      searchValue: '',
      searchColumn: 'name',
      seacrhColumns: [
        { value: 'name', label: '이름' },
        { value: 'email', label: '이메일' },
        { value: 'tel', label: '전화번호' },
        { value: 'address', label: '주소' },
        { value: 'remark', label: '비고' },
      ],
    }
  },
  created() {
    this.initData()
    this.initSearchColumn()
  },
  methods: {
    initData() {
      const testData = new Array(3)
      const item1 = {
        id: '1',
        name: '홍길동',
        email: '100033p@naver.com',
        tel: '010-2951-0468',
        address: '부천시 원미구 부일동 685번길 40-12',
        remark: '-',
      }
      const item2 = {
        id: '2',
        name: '이짱구',
        email: '100033p@naver.com',
        tel: '010-2951-0468',
        address: '부천시 원미구 부일동 685번길 40-13',
        remark: '-',
      }
      testData.push(item1)
      testData.push(item2)
      this.data = testData
    },
    initSearchColumn() {
      this.searchColumn = this.seacrhColumns[0].value
    },
    clearFilter() {
      this.$refs.workTable.clearFilter()
    },
    addListRow() {
      console.log(this.data.length + 1)
      this.data.push({
        id: this.data.length + 1,
        name: '',
        rep: '',
        email: '',
        tel: '',
        address: '',
        remark: '-',
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
