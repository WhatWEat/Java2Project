<template>
  <el-container style="display: flex; flex-direction: column; align-items: center;">
    <el-row>
      <TagsAppearJava></TagsAppearJava>
    </el-row>
    <el-row>
      <el-carousel indicator-position="outside" style="width: 90vw;height: 90vh;">
        <el-carousel-item style="height: 90vh">
          <TagsCombine></TagsCombine>
        </el-carousel-item>
        <el-carousel-item style="height: 90vh;">
          <TagsView></TagsView>
        </el-carousel-item>
      </el-carousel>
    </el-row>
  </el-container>
</template>

<script>
import TagsAppearJava from "@/components/Tag/TagsAppearJava.vue";
import TagsCombine from "@/components/Tag/TagsCombine.vue";
import TagsView from "@/components/Tag/TagsView.vue";

export default {
  name: "TagData",
  components: {TagsView, TagsCombine, TagsAppearJava}
}
</script>

<style>
</style>