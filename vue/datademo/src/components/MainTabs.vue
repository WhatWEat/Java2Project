<template>
  <div>
    <el-tabs @tab-click="handleClick">
      <el-tab-pane label="Answer" name="/answer">用户管理</el-tab-pane>
      <el-tab-pane label="Accepted Answer" name="first">用户管理</el-tab-pane>
      <el-tab-pane label="Tags" name="second">配置管理</el-tab-pane>
      <el-tab-pane label="Users" name="third">角色管理</el-tab-pane>
      <el-tab-pane label="Data Analysis" name="fourth">定时任务补偿</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "MainTabs",
  methods: {
    handleClick(tab, event) {
      this.$router.push(tab.name);
      console.log(tab.name);
    }
  }
}
</script>

<style scoped>

</style>