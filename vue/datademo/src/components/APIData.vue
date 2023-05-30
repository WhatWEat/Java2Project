<template>
  <el-container style="display: flex; flex-direction: column; align-items: center;">
    <el-row>
      <APICloud></APICloud>
    </el-row>
    <el-row>
      <APIPercentage></APIPercentage>
    </el-row>
  </el-container>
</template>

<script>
import APICloud from "@/components/API/APICloud.vue";
import APIPercentage from "@/components/API/APIPercentage.vue";

export default {
  name: "APIData",
  components: {APIPercentage, APICloud}
}
</script>

<style scoped>

</style>