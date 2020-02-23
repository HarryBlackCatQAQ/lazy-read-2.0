/*
 * @Author: Harry 
 * @Date: 2020-01-21 21:05:57 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-23 01:25:36
 */

<template>
  <div class="register-page">
    注册页面
    <q-layout view="lHh Lpr lFf">
      <q-header reveal elevated class="bg-teal-4">
        <q-toolbar>
          <q-btn flat dense round aria-label="Menu" icon="ion-arrow-back" @click="backToLogin" />
          <q-separator style="margin-left:5px;" vertical inset color="white" />

          <q-toolbar-title style="font-size:18px;">用 户 注 册</q-toolbar-title>
        </q-toolbar>
      </q-header>

      <q-page-container class="my-container">
        <div class="q-pa-md" style="max-width: 400px">
          <q-form @submit="registerClick" @reset="onReset" class="q-gutter-md">

            <!-- 账号输入框-->
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

              <template v-slot:append>
                <q-icon
                  name="ion-color-wand"
                  class="cursor-pointer"
                  @click="checkUserName"
                />
              </template>
            </q-input>

            <!-- 昵称输入框-->
            <q-input
              filled
              outlined
              v-model="nickName"
              label="昵 称 *"
              lazy-rules
              color="teal-4"
              :rules="[ val => nickNameJudge(val) || this.nickNameTip]"
            >
              <template v-slot:prepend>
                <q-icon name="ion-person" />
              </template>
            </q-input>

            <!-- 密码输入框-->
            <q-input
              filled
              color="teal-4"
              v-model="password"
              :type="isPwd ? 'password' : 'text'"
              float-label="Password"
              label="密 码 *"
              lazy-rules
              :rules="[
          val => passwordJudge(val) || '请输入您的密码']"
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

            <!-- 确认密码输入框-->
            <q-input
              filled
              color="teal-4"
              v-model="passwordConfirm"
              :type="isPwd2 ? 'password' : 'text'"
              float-label="Password"
              label="确 认 密 码 *"
              lazy-rules
              :rules="[
          val => passwordConfirmJudge(val) || '请确认您两次输入的密码一致']"
            >
              <template v-slot:prepend>
                <q-icon name="ion-key" />
              </template>

              <template v-slot:append>
                <q-icon
                  :name="isPwd2 ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="isPwd2 = !isPwd2"
                />
              </template>
            </q-input>

            <!-- 手机号码输入框-->
            <q-input
                filled
                outlined
                v-model="phone"
                label="手 机 号 码*(选填)"
                lazy-rules
                color="teal-4"
                    :rules="[
            val => phoneJudge() || this.phoneTip]"
            >
                <template v-slot:prepend>
                <q-icon name="ion-phone-portrait" />
                </template>
            </q-input>

            <!-- 邮箱地址输入框-->
            <q-input
                filled
                outlined
                v-model="email"
                label="邮 箱 地 址 *(选填)"
                lazy-rules
                color="teal-4"
                    :rules="[
            val => emailJudge() || this.emailTip]"
            >
                <template v-slot:prepend>
                <q-icon name="mail" />
                </template>
            </q-input>

            <div class="btn-group">
              <q-btn
              type="sumbit"
                label="注册账号"
                color="secondary"
                size="15px"
                class="btn-width"
              />
              <q-btn
                label="重 置"
                type="reset"
                color="secondary"
                size="15px"
                class="btn-width q-ml-xl"
                outline
              />
            </div>
          </q-form>
        </div>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script>
import registerService from "@/service/user/registerService";

export default {
  name: "register",
  data() {
    return {
      username: null, //用户名
      password: null, //密码
      passwordConfirm:null, //密码确认
      phone:null,//电话
      email:null,//邮箱
      nickName:null,//昵称
      isPwd: true, //用于密码显示的
      isPwd2: true, //用于确认密码显示的
    //   canLogin: false, //是否可以登录
      isUserName: false, //用于判断用户名是否合法
      isPassword: false, //用于判断密码是否合法
      isPasswordConfirm:false, //用于判断确认密码是否合法
      isNickName: false,
      phoneTip:"",//手机号码输入框提示
      emailTip:"",//邮箱地址输入框提示
      nickNameTip:"",
    };
  },
  methods: {
    backToLogin() {
      this.$router.go(-1);
    },
    checkUserName(){
        console.log("checkUserName");
        if(this.username != null && this.username != undefined){
            if(this.username.length > 0)
                registerService.checkUserName(this.username);
        }
    },
    registerClick() {
      console.log("register");

      if(this.isUserName && this.isPassword && this.isPasswordConfirm && this.isNickName){
          
            let res = registerService.checkUserName(this.username,true);
            let that = this;
            res.then(response =>{
              if(response.flag){
                  let res2 = registerService.register(this.username,this.nickName,
                    this.password,this.phone,this.email);

                    res2.then(response2 =>{
                        if(response2){
                            that.$q.notify({
                                message: "注册成功!",
                                position: "top",
                                color:"teal-5",
                                timeout:1000,
                                icon: "ion-checkmark-circle-outline"
                            })

                            setTimeout(function(){
                                that.$router.go(-1);
                            },1000)
                        }
                        else{
                            that.$q.notify({
                                message: "注册失败!系统繁忙请稍后再试！",
                                position: "top",
                                color:"negative",
                                timeout:1000,
                                icon: "ion-close-circle-outline"
                            })
                        }
                    },
                    error=>{
                        that.$q.notify({
                                message: "注册失败!系统繁忙请稍后再试！",
                                position: "top",
                                color:"negative",
                                timeout:1000,
                                icon: "ion-close-circle-outline"
                            })
                    })
              }
          })
      }
      else{
          console.log("no!");
      }
    },
    onReset() {
      this.phone = "";
      this.password = "";
      this.username = "";
      this.passwordConfirm = "";
      this.email = "";
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
    //昵称输入框判断
    nickNameJudge(val){
        if (!this.inputJudge(val)) {
            this.nickNameTip = "请输入您的昵称!";
        this.isNickName = false;
      } else {
          if(this.nickName.length > 12){
              this.nickNameTip = "输入长度不可超过12";
        this.isNickName = false;
          }
          else{
              this.isNickName = true;
          }
      }
      return this.isNickName;
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
    //确认密码输入判断
    passwordConfirmJudge(val){
        if(!this.inputJudge(val)){
            this.isPasswordConfirm = false;
        }
        else{
            if(val === this.password){
                this.isPasswordConfirm = true;
            }
            else{
                this.isPasswordConfirm = false;
            }
        }

        return this.isPasswordConfirm;
    },
    //手机号码验证判断
    phoneJudge() {
        //选填可以为空
        if(this.phone === undefined || this.phone === null || this.phone.length <= 0){
            return true;
        }

      if (!/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(this.phone)) {
        this.phoneTip = "请输入有效的手机号码";
        return false;
      } else {
        return true;
      }
    },
    emailJudge() {
        //选填可以为空
      if(this.email === undefined || this.email === null || this.email.length <= 0){
            return true;
        }

      //   let re = /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
      let re = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (!re.test(this.email)) {
        this.emailTip = "请输入有效的邮箱地址";
        return false;
      } else {
        return true;
      }
    },
    //输入框值判断
    inputJudge(val) {
      return val && val.length > 0;
    },
  }
};
</script>


<style scoped>
.btn-group {
  text-align: center;
  margin-top: 30px;
}
</style>
