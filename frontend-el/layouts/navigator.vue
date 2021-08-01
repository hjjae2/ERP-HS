<template>
  <el-aside width="200px">
    <el-menu default-active="1" :router="true">
      <el-menu-item index="1" route="home">
        <i class="el-icon-house"></i>
        <span>홈</span>
      </el-menu-item>
      <el-menu-item index="2" route="work">
        <i class="el-icon-document-copy"></i>
        <span>작업내역 관리</span>
      </el-menu-item>
      <el-menu-item index="3" route="company">
        <i class="el-icon-school"></i>
        <span>거래처 관리</span>
      </el-menu-item>
      <el-menu-item index="4" route="person">
        <i class="el-icon-user"></i>
        <span>작업자/거래인 관리</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: true,
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath)
    },
  },
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
