<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <h2>대시보드</h2>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {}
  },
  created() {
    console.log(process.env.BASE_URL)
    console.log(process.env.BASE_PORT)
  },
}
</script>
