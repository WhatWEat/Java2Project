import Vue from 'vue'
import VueRouter from 'vue-router'
import AnswerData from "@/components/AnswerData.vue";
import AcceptedAnserData from "@/components/AcceptedAnserData.vue";
import TagData from "@/components/TagData.vue";
import UserData from "@/components/UserData.vue";

Vue.use(VueRouter)
export const router = new VueRouter({
      routes: [
        {path: '/', redirect: "/answer"},
        {path: '/answer', component: AnswerData},
        {path: '/accepted-answer', component: AcceptedAnserData},
        {path: '/tag', component: TagData},
        {path: '/user', component: UserData},
      ],
      mode: 'history'
    }
)
export default router