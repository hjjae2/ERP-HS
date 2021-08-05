<template>
  <el-container
    direction="vertical"
    style="width: 100%; height: 100%; padding: 0% 5%"
  >
    <common-header />
    <br />
    <nuxt />
    <common-footer />
  </el-container>
</template>

<script>
import CommonHeader from '~/layouts/header.vue'
import CommonFooter from '~/layouts/footer.vue'
export default {
  components: {
    CommonHeader,
    CommonFooter,
  },
}
</script>

<style scoped>
.text-primary {
  color: #409eff;
}
</style>
