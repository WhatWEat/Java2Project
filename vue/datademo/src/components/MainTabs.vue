<template>
  <div>
    <el-tabs v-model="activeTab">
      <el-tab-pane label="Answer" name="/answer"></el-tab-pane>
      <el-tab-pane label="Accepted Answer" name="/accepted-answer"></el-tab-pane>
      <el-tab-pane label="Tags" name="/tag"></el-tab-pane>
      <el-tab-pane label="Users" name="third"></el-tab-pane>
      <el-tab-pane label="Data Analysis" name="fourth"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "MainTabs",
  data() {
    return {
      activeTab: '/answer'
    }
  },
  methods: {
    handleClick(tab, event) {
      this.$router.push(tab.name);
      console.log(tab.name);
    }
  },
  watch: {
    activeTab(newTab) {
      this.$router.push(newTab).catch(err => {
        if (err.name !== 'NavigationDuplicated') {
          throw err;
        }
      });
    }
  },
  created() {
    this.activeTab = this.$route.path;
    this.$router.afterEach((to) => {
      this.activeTab = to.path;
    })
  }
}
</script>

<style scoped>

</style>