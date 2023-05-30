import Vue from 'vue'
import VueRouter from 'vue-router'
import AnswerData from "@/components/AnswerData.vue";
import AcceptedAnserData from "@/components/AcceptedAnserData.vue";
import TagData from "@/components/TagData.vue";

Vue.use(VueRouter)
export const router = new VueRouter({
      routes: [
        {path: '/', redirect: "/main"},
        {path: '/answer', component: AnswerData},
        {path: '/accepted-answer', component: AcceptedAnserData},
        {path: '/tag', component: TagData},
      ],
      mode: 'history'
    }
)
export default router