/*
 * @Author: Harry 
 * @Date: 2020-01-17 13:30:33 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 23:36:12
 */

<template>
  <div class="home">
    <q-layout view="lHh Lpr lFf">
      <!-- 头部 -->
      <q-header reveal elevated class="bg-teal-4">
        <q-toolbar>
          <q-btn
            flat
            dense
            round
            @click="leftDrawerOpen = !leftDrawerOpen"
            aria-label="Menu"
            icon="menu"
          />
          <q-separator style="margin-left:5px;" vertical inset color="white" />

          <q-toolbar-title>懒 读</q-toolbar-title>
          <q-btn @click="search" flat round dense icon="ion-search" class="q-mr-md" />
          <img class="logo" src="../assets/lazyread-touming-logo.png" />
          <!-- <div>version v2.0</div> -->
        </q-toolbar>
      </q-header>

      <!-- 左滑菜单栏 -->
      <q-drawer show-if-above v-model="leftDrawerOpen" :width="200" :breakpoint="100">
        
        <!-- 头部内容 -->
        <q-img
          class="absolute-top"
          src="http://pic1.win4000.com/wallpaper/c/555eee236e919.jpg"
          style="height: 150px"
        >
        
          <div class="absolute-bottom bg-transparent">
            <q-avatar size="56px" class="q-mb-sm">
              <!--  ../assets/boy-avatar.png已登录的头像logo -->
              <img v-if="!this.$store.getters['user/getIsLogin']" @click="goToLoginPage" src="../assets/unlogin.jpg" />
              <img v-else src="../assets/boy-avatar.png">
            </q-avatar>
            <div class="text-weight-bold">
              <span v-if="!this.$store.getters['user/getIsLogin']">
                点击头像登录
              </span>
              <span v-else>
                  {{this.user.nickName}}
              </span>
            </div>
            <div>@lazyread!</div>
          </div>
        </q-img>

        <!-- 列表内容 -->
        <q-scroll-area
          style="height: calc(100% - 150px); margin-top: 150px; border-right: 1px solid #ddd"
        >
          <q-list padding>
            <!-- 首页 -->
            <q-item @click="gotoHome" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="home" />
              </q-item-section>

              <q-item-section>首页</q-item-section>
            </q-item>

            <!-- 历史记录 -->
            <q-item clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="ion-ios-time" />
              </q-item-section>

              <q-item-section>历史记录</q-item-section>
            </q-item>

            <!-- 历史记录 -->
            <q-item v-show="this.$store.getters['user/getIsLogin']" clickable v-ripple @click="logout">
              <q-item-section avatar>
                <q-icon name="ion-ios-exit" />
              </q-item-section>

              <q-item-section>退出登录</q-item-section>
            </q-item>

            <!-- <q-item clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="send" />
              </q-item-section>

              <q-item-section>
                Send
              </q-item-section>
            </q-item>

            <q-item clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="drafts" />
              </q-item-section>

              <q-item-section>
                Drafts
              </q-item-section>
            </q-item>-->
          </q-list>
        </q-scroll-area>
      </q-drawer>

      <!-- 主题页面 -->
      <q-page-container>
        <router-view />
      </q-page-container>

      <!-- 底部页脚 -->
      <q-footer elevated class="bg-teal-4">
        <q-tabs v-model="tab" inline-label class="text-white shadow-2">
          <q-route-tab name="bookcase" icon="ion-ios-bookmarks" label="书架" to="/bookcase" />
          <q-route-tab name="bookcity" icon="ion-bookmarks" label="书城" to="/bookcity" />
          <!-- <q-tab name="movies" icon="alarm" label="Movies" /> -->
        </q-tabs>
      </q-footer>
    </q-layout>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import BookCard from "@/components/BookCard.vue";
import Book from "@/model/Book";
import Chapter from "@/model/Chapter";
import User from "@/model/User";
import routerApi from "@/router/routerApi";

import { mapState } from "vuex";

export default {
  name: "home",
  components: {
    HelloWorld,
    BookCard
  },
  data() {
    return {
      leftDrawerOpen: false,
      tab: "bookcase",
      items: [{}, {}, {}, {}, {}, {}, {}, {}, {}],
      b1: {
        name: "aaa",
        author: "23232"
      },
      visible: false,
      visible1: true,

      //从state 和storage中获取
      isLogin:undefined,
      user:undefined,
    };
  },
  methods: {
    logout(){
      this.$post("/user/logout",this.user)
      .then(response =>{
        console.log(response);

        if(response){
          localStorage.removeItem("user");
          localStorage.removeItem("userCollectionBooks");
          localStorage.clear();
          localStorage.setItem("isLogin",false);

          this.$store.commit("user/setIsLogin",false);
          this.$store.commit("user/setUser",undefined);
          this.$store.commit("userCollectionBooks/setList",undefined);


          this.$q.notify({
              message: "登出成功!",
              position: "top",
              color:"teal-5",
              timeout:1000,
              icon: "ion-checkmark-circle-outline"
            })
        }
        else{
          this.$q.notify({
              message: "系统繁忙请稍后再试!",
              position: "top",
              color:"negative",
              timeout:1000,
              icon: "ion-close-circle-outline"
            });
        }
      })
    },
    gotoHome() {
      console.log("gotoHome");
      this.$router.push(routerApi.bookcase.path);
      // this.leftDrawerOpen = !this.leftDrawerOpen;
    },
    goToLoginPage(){
      this.$router.push(routerApi.login.path)
    },
    search() {
      console.log("@");
      // setTimeout(() => {
      //   this.visible1 = !this.visible1
      // }, 1000);
      this.visible = !this.visible;
      this.visible1 = !this.visible1;

      //这里设置时间是让动画看起来好看一些
      setTimeout(() => {
        //跳转到search页面
        this.$router.push(routerApi.search.path);
      }, 300);
    }
  },
  created() {
    this.b1 = new Book();
    this.b1.name = "312312harry";
    this.b1.author = "harry";
    // console.log(this.prePath);

    //获取数据
    this.isLogin = this.$store.getters["user/getIsLogin"];
    this.user = this.$store.getters["user/getUser"];
  },
  computed: mapState({
    list222: state => state.userCollectionBooks.list
  })
};
</script>

<style scoped>
.logo {
  width: 55px;
}
</style>
