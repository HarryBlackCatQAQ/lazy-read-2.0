/*
 * @Author: Harry 
 * @Date: 2020-01-22 22:22:07 
 * @Last Modified by:   Harry-mac 
 * @Last Modified time: 2020-01-22 22:22:07 
 */

document.addEventListener('plusready', function () {
    var webview = plus.webview.currentWebview();
    plus.key.addEventListener('backbutton', function () {
        webview.canBack(function (e) {
            if (e.canBack) {
                webview.back(-1); //返回上一页  
            } else {
                webview.close(); //关闭应用  
            }
        })
    });
})