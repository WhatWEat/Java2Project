<template>
  <el-container>
    <el-header>
      <MainTabs></MainTabs>
    </el-header>
    <el-main style="width: 100%; display: flex; justify-content: center; align-items: center;" >
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </el-main>
  </el-container>
</template>

<script>

import AnswerData from "@/components/AnswerData.vue";
import MainTabs from "@/components/MainTabs.vue";

export default {
  name: 'App',
  components: {MainTabs, AnswerData},
  methods: {

  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>
