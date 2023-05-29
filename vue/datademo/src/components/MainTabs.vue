<template>
  <div>
    <el-tabs @tab-click="handleClick">
      <el-tab-pane label="Answer" name="/answer"></el-tab-pane>
      <el-tab-pane label="Accepted Answer" name="/accepted-answer"></el-tab-pane>
      <el-tab-pane label="Tags" name="second"></el-tab-pane>
      <el-tab-pane label="Users" name="third"></el-tab-pane>
      <el-tab-pane label="Data Analysis" name="fourth"></el-tab-pane>
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