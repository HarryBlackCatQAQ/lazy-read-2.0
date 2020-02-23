import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import api from '@/router/routerApi.js'

Vue.use(VueRouter)

/**
 * 解决跳转相同路由地址报错
 */
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    redirect: api.bookcase.path,//从定向到login
    children: [
      {
        path: api.bookcase.path,
        name: api.bookcase.name,
        component: () => import('../views/home/BookCase.vue')
      },
      {
        path: api.bookcity.path,
        name:api.bookcity.name,
        component: () => import('../views/home/BookCity.vue')
      }
    ]
  },
  {
    path:api.search.path,
    name:api.search.name,
    component:() => import('../views/Search.vue')
  },
  {
    path:api.read.path,
    name:api.read.name,
    component:() => import('../views/Read.vue')
  },
  {
    path:api.login.path,
    name:api.login.name,
    component:() => import('../views/Login.vue')
  },
  {
    path:api.forgetPassword.path,
    name:api.forgetPassword.name,
    component:() => import('../views/ForgetPassword.vue')
  },
  {
    path:api.register.path,
    name:api.register.name,
    component:() => import('../views/Register.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
