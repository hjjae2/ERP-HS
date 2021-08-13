<template>
  <el-form :model="form" label-position="top">
    <el-row type="flex" :gutter="20">
      <el-col>
        <el-form-item label="구분">
          <el-radio-group v-model="form.type" size="medium">
            <el-radio-button label="NORMAL">일반</el-radio-button>
            <el-radio-button label="EXPENDITURE">지출</el-radio-button>
            <el-radio-button label="EXPENSE">비용</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col>
        <el-form-item label="작업 날짜">
          <el-input
            type="date"
            autocomplete="on"
            maxlength="10"
            show-word-limit
            size="medium"
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row type="flex" :gutter="20">
      <el-col>
        <el-form-item label="거래처">
          <el-autocomplete
            v-model="state1"
            :fetch-suggestions="querySearch"
            :trigger-on-focus="false"
            style="display: block"
            placeholder="거래처를 입력해주세요"
            size="medium"
            @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
      </el-col>
      <el-col>
        <el-form-item label="작업자">
          <el-autocomplete
            v-model="state1"
            :fetch-suggestions="querySearch"
            :trigger-on-focus="false"
            style="display: block"
            placeholder="작업자를 입력해주세요"
            size="medium"
            @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
      </el-col>
    </el-row>

    <el-row type="flex" :gutter="20">
      <el-col>
        <el-form-item label="배차자">
          <el-autocomplete
            v-model="state1"
            :fetch-suggestions="querySearch"
            :trigger-on-focus="false"
            style="display: block"
            size="medium"
            placeholder="배차자를 입력해주세요"
            @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
      </el-col>
      <el-col>
        <el-form-item label="주소">
          <el-input
            v-model="form.content"
            size="medium"
            placeholder="주소를 입력해주세요"
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-form-item label="작업 내용">
      <el-input
        v-model="form.content"
        type="textarea"
        :rows="4"
        placeholder="작업내용을 입력해주세요"
      ></el-input>
    </el-form-item>
    <el-form-item label="작업 금액">
      <el-input v-model="form.price" size="medium" placeholder="ex) 400000">
        <template slot="prepend">₩</template>
        <template slot="append">원</template>
      </el-input>
    </el-form-item>
    <el-row type="flex" :gutter="20">
      <el-col>
        <el-form-item label="결제상태">
          <el-radio-group v-model="form.priceStatus" size="small">
            <el-radio-button label="NONE">없음</el-radio-button>
            <el-radio-button label="NOT_FINISH">미완료</el-radio-button>
            <el-radio-button label="FINISH">완료</el-radio-button>
            <el-radio-button label="PARTIAL_FINISH">부분완료</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col>
        <el-form-item label="지출상태">
          <el-radio-group v-model="form.expenditureStatus" size="small">
            <el-radio-button label="NONE">없음</el-radio-button>
            <el-radio-button label="NOT_FINISH">미완료</el-radio-button>
            <el-radio-button label="FINISH">완료</el-radio-button>
            <el-radio-button label="PARTIAL_FINISH">부분완료</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row type="flex" :gutter="20">
      <el-col>
        <el-form-item label="세금계산서">
          <el-radio-group v-model="form.taxStatus" size="small">
            <el-radio-button label="NONE">없음</el-radio-button>
            <el-radio-button label="NOT_FINISH">발행 미완료</el-radio-button>
            <el-radio-button label="FINISH">발행 완료</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col>
        <el-form-item label="일반/통신">
          <el-radio-group v-model="form.communicatorStatus" size="small">
            <el-radio-button label="NORMAL">일반</el-radio-button>
            <el-radio-button label="COMMUNICATOR">통신</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-col>
    </el-row>
    <el-form-item label="비고">
      <el-input
        v-model="form.content"
        type="textarea"
        :rows="4"
        placeholder="메모할 내용을 입력해주세요"
      ></el-input>
    </el-form-item>
  </el-form>
</template>

<script>
import { defineComponent } from '@vue/composition-api'

export default defineComponent({
  props: {
    work: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      form: {
        type: 'NORMAL',
        name: '',
        content: 'z',
        price: '',
        priceStatus: 'FINISH',
        expenditureStatus: 'NONE',
        taxStatus: 'NONE',
        communicatorStatus: 'NORMAL',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        resource: '',
        desc: '',
      },
      formLabelWidth: '120px',
      links: [],
      state1: '',
      state2: '',
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.form.content = this.work.content
      this.links = this.loadAll()
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
    handleSelect(item) {
      // todo
      console.log(item)
    },
  },
})
</script>

<style>
.el-input-group__prepend,
.el-input-group__append {
  color: rgb(255, 255, 255);
  background-color: rgb(31, 59, 179);
}
</style>
