<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <h2>작업자/거래인 관리</h2>
      </div>
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
      <br />
      <el-row type="flex" justify="end">
        <el-button plain @click="saveAll">전체 내용 저장</el-button>
        <el-button plain @click="addRow">내용 추가</el-button>
        <el-button plain @click="clearFilter">필터 초기화</el-button>
      </el-row>
      <br />
      <el-table
        ref="mainTable"
        show-summary
        :data="
          people.filter((person) => {
            if (!searchValue) {
              return true;
            }
            return (
              person[searchColumn] && person[searchColumn].toLowerCase().includes(searchValue.toLowerCase())
            );
          })
        "
      >
        <el-table-column
          prop="name"
          label="이름"
          sortable
          :filters="nameOptions"
          :filter-method="filterHandler"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.name"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column
          prop="company"
          label="회사"
          sortable
          :filters="companyOptions"
          :filter-method="filterHandler"
        >
          <template slot-scope="scope">
            <el-autocomplete
              v-model="scope.row.company"
              :fetch-suggestions="queryCompanySuggestions"
              style="display: block"
              size="medium"
              @change="saveOrUpdateRow(scope.row)"
              @select="saveOrUpdateRow(scope.row)"
            ></el-autocomplete>
          </template>
        </el-table-column>

        <el-table-column
          prop="phone"
          label="전화번호"
          sortable
          :filters="phoneOptions"
          :filter-method="filterHandler"
        >
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.phone"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column
          prop="email"
          label="이메일"
          sortable
          :filters="emailOptions"
          :filter-method="filterHandler"
        >
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.email"
              size="small"
              style="text-align: center"
              controls-position="right"
              @change="saveOrUpdateRow(scope.row)"
            ></el-input>
          </template>
        </el-table-column>

        <el-table-column
          prop="address"
          label="주소"
          sortable
          :filters="addressOptions"
          :filter-method="filterHandler"
        >
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
      <br />
      <el-row type="flex" justify="end">
        <el-button plain @click="saveAll">전체 내용 저장</el-button>
        <el-button plain @click="addRow">내용 추가</el-button>
        <el-button plain @click="clearFilter">필터 초기화</el-button>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import PersonAPI from '~/api/person';
import CompanyAPI from '~/api/company';

const personAPI = new PersonAPI();
const companyAPI = new CompanyAPI();

export default {
  data() {
    return {
      // About data
      people: [],
      companies: [],
      nameOptions: [],
      companyOptions: [],
      phoneOptions: [],
      emailOptions: [],
      addressOptions: [],
      searchValue: '',
      searchColumn: 'name',
      seacrhColumns: [
        { value: 'name', label: '이름' },
        { value: 'company', label: '회사' },
        { value: 'phone', label: '전화번호' },
        { value: 'email', label: '이메일' },
        { value: 'address', label: '주소' },
        { value: 'remark', label: '비고' },
      ],

      // About flag
      isError: false,
    };
  },
  created() {
    this.initList();
    this.initCompanyList();
    this.initSearchColumn();
    this.resetError();
  },
  methods: {
    async initList() {
      let people = [];
      try {
        const response = await personAPI.getPeople();
        people = response.data.data;
      } catch (error) {
        this.occurError('데이터 조회 중 오류가 발생하였습니다.');
      } finally {
        this.people = people;
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
    initSearchColumn() {
      this.searchColumn = this.seacrhColumns[0].value;
    },
    initOptions() {
      if (Array.isArray(this.people)) {
        this.people.forEach((person) => {
          if (this.nameOptions.filter((nameOption) => nameOption.value === person.name).length === 0) {
            this.nameOptions.push({
              value: person.name,
              label: person.name,
              text: person.name,
            });
          }

          if (
            this.companyOptions.filter((companyOption) => companyOption.value === person.company).length === 0
          ) {
            this.companyOptions.push({
              value: person.company,
              label: person.company,
              text: person.company,
            });
          }

          if (this.phoneOptions.filter((phoneOption) => phoneOption.value === person.phone).length === 0) {
            this.phoneOptions.push({
              value: person.phone,
              label: person.phone,
              text: person.phone,
            });
          }

          if (this.emailOptions.filter((emailOption) => emailOption.value === person.email).length === 0) {
            this.emailOptions.push({
              value: person.email,
              label: person.email,
              text: person.email,
            });
          }

          if (
            this.addressOptions.filter((addressOption) => addressOption.value === person.address).length === 0
          ) {
            this.addressOptions.push({
              value: person.address,
              label: person.address,
              text: person.address,
            });
          }
        });
      }
    },
    addRow() {
      const newRow = {
        id: 'new',
        name: '',
        rep: '',
        email: '',
        phone: '',
        address: '',
        remark: '-',
      };
      this.people.push(newRow);
    },
    saveAll() {
      // 1. call api to save all
      // 1-1. 새롭게 생긴 item 만 save?
      // 업데이트 된 게 있을 수 있음.
      // 1-2. all item save?
      // 1-3. 단건 save vs list save
      console.log(JSON.stringify(this.people));
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
        await personAPI.savePerson(row);
        await this.initList();
        this.successMessage('데이터를 저장하였습니다.');
      } catch (error) {
        this.occurError('데이터 저장 중 오류가 발생하였습니다.');
      }
    },
    async updateRow(row) {
      try {
        await personAPI.updatePerson(row.id, row);
        await this.initList();
        this.successMessage('데이터를 수정하였습니다.');
      } catch (error) {
        this.occurError('데이터 업데이트 중 오류가 발생하였습니다.');
      }
    },
    async deleteRow(row) {
      try {
        await personAPI.deletePerson(row.id);
        await this.initList();
        this.successMessage('데이터를 삭제하였습니다.');
      } catch (error) {
        this.occurError('데이터 삭제 중 오류가 발생하였습니다.');
      }
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
