/*
 * @Author: Harry 
 * @Date: 2020-01-17 23:17:20 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-23 01:00:17
 */

<template>
  <div class="login-page">
    登录页面
    <q-layout view="lHh Lpr lFf">
      <!-- 头部 -->
      <q-header reveal elevated class="bg-teal-4">
        <q-toolbar>
          <q-btn flat dense round aria-label="Menu" icon="ion-arrow-back" @click="backToBookCase" />
          <q-separator style="margin-left:5px;" vertical inset color="white" />

          <q-toolbar-title style="font-size:18px;">用 户 登 录</q-toolbar-title>

          <div @click="goForgetPasswordClick" style="font-size:13px;">忘 记 密 码 ？</div>
        </q-toolbar>
      </q-header>

      <div class="logo-div">
        <img class="logo" src="../assets/logo.png" />
      </div>

      <q-page-container class="my-container">
        <div class="q-pa-md" style="max-width: 400px">
          <q-form @submit="login" @reset="onReset" class="q-gutter-md">
            <q-input
              filled
              outlined
              v-model="username"
              label="账 号 *"
              lazy-rules
              color="teal-4"
              :rules="[ val => userNameJudge(val) || '请输入您的账号']"
            >
              <template v-slot:prepend>
                <q-icon name="ion-person" />
              </template>
            </q-input>

            <q-input
              filled
              color="teal-4"
              v-model="password"
              :type="isPwd ? 'password' : 'text'"
              float-label="Password"
              label="密 码 *"
              lazy-rules
              :rules="[
          val => passwordJudge(val) || '请输入您的密码'
        ]"
            >
              <template v-slot:prepend>
                <q-icon name="ion-key" />
              </template>

              <template v-slot:append>
                <q-icon
                  :name="isPwd ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="isPwd = !isPwd"
                />
              </template>
            </q-input>

            <!--记住我选框太丑 先放着，现在默认30天重新token时间 -->
            <!-- <div class="remberMe-div">
              <q-checkbox class="remberMe-checkbox" v-model="remberMe" label="记住我" color="teal" />
            </div>-->

            <div class="btn-group">
              <q-btn
                @click="registerClick"
                label="注册账号"
                color="secondary"
                size="15px"
                class="btn-width"
                outline
              />
              <q-btn
                label="登 录"
                type="submit"
                color="secondary"
                size="15px"
                class="btn-width q-ml-xl"
              />
            </div>
          </q-form>
        </div>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script scoped>
import routerApi from "@/router/routerApi";
import loginService from "@/service/user/loginService";
import bookService from "@/service/book/bookService"

export default {
  name: "login",
  data() {
    return {
      username: null,//用户名
      password: null,//密码
      remberMe: false,//是否记住我  （暂时不用）
      isPwd: true, //用于密码显示的
      canLogin: false, //是否可以登录
      isUserName: false, //用于判断用户名是否合法
      isPassword: false //用于判断密码是否合法
    };
  },
  methods: {
    //登录点击
    login() {
      if (this.isUserName && this.isPassword) {
        this.$q.loading.show({
            message:"登录中..."
        });
        let res = loginService.login(this.username, this.password);

        //获取响应 
        res.then(response => {
          //如果登录成功提示登录成功 并跳转页面
          if (response.flag) {
            this.$q.notify({
              message: "登录成功",
              position: "top",
              color:"teal-5",
              timeout:750,
              icon: "ion-checkmark-circle-outline"
            })

            let that = this;
            //获取用户书籍
            bookService.getUserCollectionsBooks(this.$store.getters["user/getUser"]);
            //设置1秒后跳转页面 这样比较好看一点
            this.$q.loading.hide();
            setTimeout(function(){
              that.$router.push(routerApi.bookcase.path);
            },1000)
            

          } //如果登录不成功提示登录不成功
          else if(!response.flag) {
            this.$q.loading.hide();
            this.$q.notify({
              message: response.message,
              position: "top",
              color:"negative",
              timeout:750,
              icon: "ion-close-circle-outline"
            });
          }
        },
        error =>{
          this.$q.loading.hide();
            this.$q.notify({
              message: "系统繁忙请稍候再试!😢",
              position: "top",
              color:"negative",
              timeout:1000,
              icon: "ion-close-circle-outline"
            });
        });
      }
    },
    //用户名输入框判断
    userNameJudge(val) {
      if (!this.inputJudge(val)) {
        this.isUserName = false;
      } else {
        this.isUserName = true;
      }
      return this.isUserName;
    },
    //密码输入框判断
    passwordJudge(val) {
      if (!this.inputJudge(val)) {
        this.isPassword = false;
      } else {
        this.isPassword = true;
      }

      return this.isPassword;
    },
    //输入框值判断
    inputJudge(val) {
      return val && val.length > 0;
    },
    //注册账号按钮点击
    registerClick() {
      this.$router.push(routerApi.register.path);
    },
    //回到首页按钮
    backToBookCase() {
      this.$router.push(routerApi.bookcase.path);
    },
    //前往忘记密码页面点击
    goForgetPasswordClick() {
      //   console.log("goForgetPasswordClick");
      this.$router.push(routerApi.forgetPassword.path);
    },
    //输入框重置按钮
    onReset() {
      this.name = "";
      this.age = "";
      //   this.accept = false;
    },
    //页面滚动事件监听
    bodyScroll(event) {
      event.preventDefault();
    }
  },
  created() {
    //页面创建时禁止页面滚动
    document.body.addEventListener("touchmove", this.bodyScroll, {
      passive: false
    });

    // loginService.test();
  },
  destroyed() {
    //改页面销毁时恢复滚动
    document.body.removeEventListener("touchmove", this.bodyScroll, {
      passive: false
    });
  }
};
</script>


<style scoped>
.logo {
  width: 65px;
  border-radius: 20px;
}

.logo-div {
  text-align: center;
  margin-top: 40px;
}

.my-container {
  padding-top: 1px !important;
}

.btn-group {
  text-align: center;
  margin-top: 30px;
}

.btn-width {
  width: 106px;
}

.remberMe-checkbox {
  margin-left: 70%;
}

.remberMe-div {
  margin-top: 0px;
}
</style>
