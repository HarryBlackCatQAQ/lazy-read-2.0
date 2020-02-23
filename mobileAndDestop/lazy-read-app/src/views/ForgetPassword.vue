/*
 * @Author: Harry 
 * @Date: 2020-01-18 13:41:08 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-18 16:29:46
 */
<template>
  <div class="forget-password-page">
    forgetPassword 页面
    <q-layout view="lHh Lpr lFf">
      <!-- 头部 -->
      <q-header reveal elevated class="bg-teal-4">
        <q-toolbar>
          <q-btn flat dense round aria-label="Menu" icon="ion-arrow-back" @click="backToLogin" />
          <q-separator style="margin-left:5px;" vertical inset color="white" />

          <q-toolbar-title style="font-size:18px;">重 置 密 码</q-toolbar-title>
        </q-toolbar>
      </q-header>

      <!--主页内容-->
      <q-page-container>
        <div class="q-pa-md">
          <q-stepper v-model="step" vertical color="secondary" animated>
            <!-- 阶段一 选择找回密码的方式-->
            <q-step color="secondary" :name="1" title="请选择找回密码的方式" icon="settings">
              <p>📱你可以根据你绑定的手机找回密码</p>
              <p>📮你可以根据你绑定的邮箱找回密码</p>
              <q-stepper-navigation>
                <q-btn @click="typeOfFindPassword('phone')" color="teal-4" label="手机找回" />
                <q-btn
                  @click="typeOfFindPassword('email')"
                  color="teal-4"
                  label="邮箱找回"
                  class="q-ml-sm"
                />
              </q-stepper-navigation>
            </q-step>

            <!--阶段2 输入手机或者邮箱 并且验证-->
            <q-step
              :name="2"
              title="密 码 找 回"
              icon="ion-ios-lock"
              color="secondary"
              :disable="this.isDisable[2]"
            >
              {{stepTwoTip}}
              <q-form @submit="onSubmit">
                <!-- 手机号码输入框-->
                <q-input
                  v-if="this.findPasswordType === 'phone'"
                  v-model="phone"
                  filled
                  type="phone"
                  hint="手机号码"
                  color="teal-4"
                  :rules="[
          val => phoneJudge() || this.phoneTip]"
                >
                  <template v-slot:before>
                    <q-icon name="ion-phone-portrait" />
                  </template>
                </q-input>

                <!-- 邮箱地址输入框-->
                <q-input
                  v-else
                  v-model="email"
                  filled
                  type="email"
                  hint="邮箱地址"
                  color="teal-4"
                  :rules="[
          val => emailJudge() || this.emailTip]"
                >
                  <template v-slot:before>
                    <q-icon name="mail" />
                  </template>
                </q-input>

                <!-- 按钮组-->
                <q-stepper-navigation>
                  <q-btn @click="goToStepThree" color="secondary" label="Continue" type="submit" />
                  <q-btn
                    flat
                    @click="backToPreStep"
                    color="secondary"
                    label="Back"
                    class="q-ml-sm"
                  />
                </q-stepper-navigation>
              </q-form>
            </q-step>

            <!--阶段3 输入验证码 并且验证-->
            <q-step
              :name="3"
              title="输入验证码"
              icon="assignment"
              color="secondary"
              :disable="this.isDisable[3]"
            >
              <!-- 验证码输入框-->
              <q-input
                v-model="verificationCode"
                filled
                type="verificationCode"
                hint="验证码"
                color="teal-4"
              >
                <template v-slot:before>
                  <q-icon name="ion-barcode" />
                </template>
                <template v-sloat:append>
                  <div class="verificationCodeBtn-te">
                    <q-btn
                      :disable="verificationCodeDisable"
                      class="verificationCodeBtn"
                      outline
                      color="secondary"
                      :label="this.verificationCodeTip"
                      @click="verificationCodeBtnClick"
                    />
                  </div>
                </template>
              </q-input>

              <!-- 按钮组-->
              <q-stepper-navigation>
                <q-btn @click="goToStepFour" color="secondary" label="Continue" />
                <q-btn flat @click="backToPreStep" color="secondary" label="Back" class="q-ml-sm" />
              </q-stepper-navigation>
            </q-step>

            <q-step
              :name="4"
              title="重 置 成 功"
              icon="ion-checkmark-circle-outline"
              color="secondary"
              :disable="this.isDisable[4]"
            >
            <p>请到您的手机或者邮箱查收重新设置的密码👌！</p>
            <p>若要重新设置密码请登录账号后重新设置😄！</p>
            </q-step>
          </q-stepper>
        </div>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script>
import routerApi from "@/router/routerApi";

export default {
  name: "forgetPassword",
  data() {
    return {
      step: 1, //阶段 从1开始
      isDisable: [false, false, true, true, true, true], //每一个step开启disable的数组
      stepTwoTip: "", //step 2 的提示
      email: "", //邮箱
      findPasswordType: "", //找回密码的方式
      emailTip: "请输入您的邮箱地址", //邮箱提示
      phone: "", //手机号码
      phoneTip: "请输入您的手机号码", //手机提示
      stepTwoOk: false, //表示step 2是否验证成功
      verificationCode: "", //验证码
      verificationCodeTime: 60, //验证码重置时间
      verificationCodeDisable: true, //是否可以重新发送验证码 一开始默认不可以
      verificationCodeTip: "60s", //验证码提示
      verificationCodeCountDown: true //验证码是否倒计时 默认是
    };
  },
  methods: {
    goToStepFour() {
      this.step++;
      this.isDisable[this.step] = false;
    },
    verificationCodeBtnClick() {
      if (this.verificationCodeTime === 60) {
        console.log("重新发送验证码");
      }

      this.verificationCodeDisable = true;
      this.verificationCodeTime--;
      this.verificationCodeTip = this.verificationCodeTime + "s";
      if (this.verificationCodeTime === 0) {
        this.verificationCodeTip = "rsend";
        this.verificationCodeTime = 60;
        this.verificationCodeCountDown = false;
        this.verificationCodeDisable = false;
        return;
      }

      let that = this;
      setTimeout(function() {
        that.verificationCodeBtnClick();
      }, 1000);
    },
    //step 2 去step 3的判断方法 先要判断step2 中用户是否已经完成
    goToStepThree() {
      if (this.stepTwoOk) {
        this.step++;
        this.isDisable[this.step] = false;
        this.verificationCodeBtnClick();
      }
    },
    //step2提交方法
    onSubmit() {
      console.log("@@@@@");
    },
    //手机号码验证判断
    phoneJudge() {
      if (!this.inputJudge(this.phone)) {
        this.phoneTip = "请输入您的手机号码";
        this.stepTwoOk = false;
        return false;
      }

      if (!/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(this.phone)) {
        this.phoneTip = "请输入有效的手机号码";
        this.stepTwoOk = false;
        return false;
      } else {
        this.stepTwoOk = true;
        return true;
      }
    },
    //邮箱验证判断
    emailJudge() {
      if (!this.inputJudge(this.email)) {
        this.emailTip = "请输入您的邮箱地址";
        this.stepTwoOk = false;
        return false;
      }

      //   let re = /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
      let re = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (!re.test(this.email)) {
        this.emailTip = "请输入有效的邮箱地址";
        this.stepTwoOk = false;
        return false;
      } else {
        this.stepTwoOk = true;
        return true;
      }
    },
    //输入框通用判断 不能为空和长度必须大于0
    inputJudge(val) {
      return val && val.length > 0;
    },
    //返回到登录界面
    backToLogin() {
      this.$router.push(routerApi.login.path);
    },
    //根据用户的选择显示step2使用哪个一个提示 现在有有手机和邮箱
    typeOfFindPassword(types) {
      this.findPasswordType = types;
      if (types === "phone") {
        this.stepTwoTip = "请输入您绑定的的手机号码📱";
      } else {
        this.stepTwoTip = "请输入您绑定的的邮箱地址📮";
      }
      this.step = 2;
      this.isDisable[this.step] = false;
    },
    //返回上一步 step
    backToPreStep() {
      this.isDisable[this.step] = true;
      this.step--;
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
.verificationCodeBtn-te {
  padding-top: 16px;
}

.verificationCodeBtn {
  height: 32px;
  width: 55px;
}
</style>
