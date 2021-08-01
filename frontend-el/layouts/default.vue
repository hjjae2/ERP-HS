<template>
  <el-container
    direction="vertical"
    style="width: 100%; height: 100%; padding: 0% 1%"
  >
    <common-header />
    <el-container style="height: 80%">
      <common-navigator />
      <nuxt />
    </el-container>
    <common-footer />
  </el-container>
</template>

<script>
import CommonHeader from '~/layouts/header.vue'
import CommonNavigator from '~/layouts/navigator.vue'
import CommonFooter from '~/layouts/footer.vue'
export default {
  components: {
    CommonHeader,
    CommonNavigator,
    CommonFooter,
  },
}
</script>

<style scoped>
.text-primary {
  color: #409eff;
}
</style>
