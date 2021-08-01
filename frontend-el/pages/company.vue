<template>
  <el-main>
    <el-table :highlight-current-row="true" show-summary>
      <el-table-column prop="date" label="Date" width="140"> </el-table-column>
      <el-table-column prop="name" label="Name" width="120"> </el-table-column>
      <el-table-column prop="address" label="Address"> </el-table-column>
      <el-table-column prop="address" label="Address"> </el-table-column>
    </el-table>
  </el-main>
</template>

<script>
export default {}
</script>

<style scoped>
#content {
  background-color: grey;
}
</style>
