import Vue from 'vue'
import VueRouter from 'vue-router'
import AnswerData from "@/components/AnswerData.vue";
import AcceptedAnserData from "@/components/AcceptedAnserData.vue";

Vue.use(VueRouter)
export const router = new VueRouter({
      routes: [
        {path: '/', redirect: "/main"},
        {path: '/answer', component: AnswerData},
        {path: '/accepted-answer', component: AcceptedAnserData},
        {path: '/accepted-answer', component: AcceptedAnserData},
      ],
      mode: 'history'
    }
)
export default router