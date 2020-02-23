/*
 * @Author: Harry 
 * @Date: 2020-01-17 13:30:39 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-23 01:11:02
 */

<template>
  <div id="app">
    <!-- <q-slide-transition> -->
    <router-view />
    <!-- </q-slide-transition> -->
  </div>
</template>

<script>
export default {
  name: "LayoutDefault",
  created() {
    var that = this;
    document.addEventListener("plusready", function() {
      // console.log(that.$route.path)
      var webview = plus.webview.currentWebview();
      plus.key.addEventListener("backbutton", function() {
        webview.canBack(function(e) {
          if (e.canBack && !(that.$route.path === "/" || that.$route.path === "/bookcase")
          ) {
            webview.back();
          } else {
            //webview.close(); //hide,quit
            //plus.runtime.quit();
            //首页返回键处理
            //处理逻辑：1秒内，连续两次按返回键，则退出应用；
            var first = null;
            plus.key.addEventListener(
              "backbutton",
              function() {
                //首次按键，提示‘再按一次退出应用’
                if (!first) {
                  first = new Date().getTime();
                  console.log("再按一次退出应用");

                  setTimeout(function() {
                    first = null;
                  }, 3000);
                } else {
                  if (new Date().getTime() - first < 3500) {
                    plus.runtime.quit();
                  }
                }
              },
              false
            );
          }
        });
      });
    });
  }
};
</script>

<style>
</style>
