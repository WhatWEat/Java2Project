import Vue from 'vue'
import VueRouter from 'vue-router'
import AnswerData from "@/components/AnswerData.vue";

Vue.use(VueRouter)
export const router = new VueRouter({
      routes: [
        {path: '/', redirect: "/main"},
        {path: '/answer', component: AnswerData}
      ],
      mode: 'history'
    }
)
export default router