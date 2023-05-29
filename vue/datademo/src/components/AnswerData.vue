<template>
  <el-container direction="vertical">
    <el-header>
      <AnserStatic></AnserStatic>
      <el-divider></el-divider>
    </el-header>
    <el-main style="display: flex; flex-direction: column; align-items: center;">
          <NoAnswer></NoAnswer>
          <AnswerDistri></AnswerDistri>
    </el-main>
  </el-container>

</template>

<script>
import NoAnswer from "@/components/Answer/NoAnswer.vue";
import AnserStatic from "@/components/Answer/AnserStatic.vue";
import AnswerDistri from "@/components/Answer/AnswerDistri.vue";

export default {
  name: "AnswerData",
  components: {AnswerDistri, AnserStatic, NoAnswer}
}
</script>

<style scoped>

</style>