/*
 * @Author: Harry 
 * @Date: 2020-01-21 21:40:06 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 22:28:20
 */
import _this from "@/main"
import api from "@/service/api"
import sha1 from "sha1";

import User from "@/model/User"
import ReflectHelper from "@/model/ReflectHelper"
import localStorageHelper from "@/model/localStorageHelper"

var that = _this._this;
var reflectHelper = new ReflectHelper();
//本地存储类
var storage1 = new localStorageHelper();

 export default {
    async register(userName,nickName,password,phone,email){
        let res;
        phone = judge(phone);
        email = judge(phone);
        password = sha1(password);
        console.log(password);
        let user = new User(null,userName,nickName,password,phone,email);
        console.log(user);

        await that.$post(api.user.register,user)
        .then(response =>{
            console.log(response);

            res = response;
        })

        return res;
    },
    async checkUserName(userName,ff){
        let res;
        await that.$get("/user/checkUserName",{
            userName:userName
        })
        .then(response =>{
            // console.log(response);
            if(response.flag){
                if(ff != true){
                    that.$q.notify({
                        message: "用户名可用!",
                        position: "top",
                        color:"teal-5",
                        timeout:1000,
                        icon: "ion-checkmark-circle-outline"
                    })
                }
            }
            else{
                that.$q.notify({
                    message: "用户名已存在!",
                    position: "top",
                    color:"negative",
                    timeout:1000,
                    icon: "ion-close-circle-outline"
                })
            }
            res = response;
        },
        error =>{
            that.$q.notify({
                message: "系统繁忙请稍后再试！",
                position: "top",
                color:"negative",
                timeout:1000,
                icon: "ion-close-circle-outline"
            })
        })

        return res;
    }
 }


 function judge(val){
     if(val === undefined || val === null || val.length <= 0){
         val = null;
     }

     return val;
 }