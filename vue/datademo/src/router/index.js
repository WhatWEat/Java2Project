import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
export const router = new VueRouter({
      routes: [
        {path: '/', redirect: "/main"},
      ],
      mode: 'history'
    }
)
export default router