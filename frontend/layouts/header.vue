<template>
  <el-row type="flex" style="border-bottom: solid 1px #e6e6e6" justify="space-between">
    <el-col style="width: 100px">
      <div class="title-area">
        <img src="@/static/logo.jpg" alt="" width="100px" height="61px" />
      </div>
    </el-col>
    <el-col>
      <el-menu default-active="1" :router="true" mode="horizontal">
        <el-menu-item index="1" route="home">
          <i class="el-icon-house"></i>
          <span>홈</span>
        </el-menu-item>
        <el-menu-item index="2" route="works">
          <i class="el-icon-document-copy"></i>
          <span>작업내역서 관리</span>
        </el-menu-item>
        <el-menu-item index="3" route="companies">
          <i class="el-icon-school"></i>
          <span>거래처 관리</span>
        </el-menu-item>
        <el-menu-item index="4" route="people">
          <i class="el-icon-user"></i>
          <span>작업자/거래인 관리</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
.title-area {
  width: 100%;
  height: 100%;
  /* text-align: center; */
  align-items: center;
}
.el-menu.el-menu--horizontal {
  border-bottom: 0px;
}
</style>
