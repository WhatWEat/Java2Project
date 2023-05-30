<template>
  <el-container direction="vertical">
    <el-row>
      <el-col :span="10">
        <AcceptedPercent></AcceptedPercent>
      </el-col>
      <el-col :span="10">
        <OverUpvote></OverUpvote>
      </el-col>
    </el-row>
    <el-row style="display: flex; flex-direction: column; align-items: center;">
        <TimeInterval></TimeInterval>
    </el-row>
  </el-container>

</template>

<script>
import AcceptedPercent from "@/components/AcceptAnswer/AcceptedPercent.vue";
import OverUpvote from "@/components/AcceptAnswer/OverUpvote.vue";
import TimeInterval from "@/components/AcceptAnswer/TimeInterval.vue";

export default {
  name: "AcceptedAnserData",
  components: {TimeInterval, OverUpvote, AcceptedPercent}
}
</script>

<style scoped>

</style>