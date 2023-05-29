<template>
  <el-container direction="vertical">
    <el-header>
      <AnserStatic></AnserStatic>
    </el-header>
    <el-main>
      <el-row type="flex" justify="space-between">
        <el-col :span="14">
          <NoAnswer></NoAnswer>
        </el-col>
        <el-col :span="10">
          <AnswerDistri></AnswerDistri>
        </el-col>
      </el-row>
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