<template>
  <el-container style="display: flex; flex-direction: column; align-items: center;">
    <el-row>
      <UserPercent></UserPercent>
    </el-row>
    <el-row>
      <UserDis></UserDis>
    </el-row>
    <el-row style="display: flex; flex-direction: column; align-items: center;">
      <h2>用户活跃度</h2>
      <h1>活跃度 = 评论数 * 2 + 回答数 * 3 + 提问数 * 5</h1>
      <UserActive></UserActive>
    </el-row>
  </el-container>
</template>

<script>
import UserDis from "@/components/User/UserDis.vue";
import UserActive from "@/components/User/UserActive.vue";
import UserPercent from "@/components/User/UserPercent.vue";

export default {
  name: "UserData",
  components: {UserPercent, UserActive, UserDis}
}
</script>

<style scoped>

</style>