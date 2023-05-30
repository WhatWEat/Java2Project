<template>
  <el-container style="display: flex; flex-direction: column; align-items: center;">
    <el-row>
      <TagsAppearJava></TagsAppearJava>
    </el-row>
    <el-row>
      <TagsCombine></TagsCombine>
    </el-row>
  </el-container>
</template>

<script>
import TagsAppearJava from "@/components/Tag/TagsAppearJava.vue";
import TagsCombine from "@/components/Tag/TagsCombine.vue";

export default {
  name: "TagData",
  components: {TagsCombine, TagsAppearJava}
}
</script>

<style scoped>

</style>