/*
 * @Author: Harry 
 * @Date: 2020-01-18 16:46:20 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 01:39:31
 */

import _this from "@/main"
import api from "@/service/api"
import sha1 from "sha1";

import User from "@/model/User"
import ReflectHelper from "@/model/ReflectHelper"
import localStorageHelper from "@/model/localStorageHelper"
import UserDao from "@/db/UserDao"

var that = _this._this;
var reflectHelper = new ReflectHelper();
var userDao = new UserDao();
//本地存储类
var storage1 = new localStorageHelper();

export default {
    test() {
        let storage1 = new localStorageHelper();
        let tp = storage1.get("token");
        console.log(tp);
    },
    async login(username, password) {
        let res;

        await that.$post(api.user.loginApi, {
            userName: username,
            password: sha1(password)
        })
            .then(response => {
                console.log(response)
                if (response.flag) {
                    //获取 user
                    let user = response.data.user;
                    //获取token
                    let token = response.data.token;

                    //将token保存 过期时间为7天
                    storage1.set("token", token, storage1.day * 7);

                    //反射获取到 User类
                    let t = reflectHelper.getOwnObject(User, user);

                    that.$store.commit("user/setIsLogin",true);
                    that.$store.commit("user/setUser",t);
                }
                res = response;
            })

        return res;
    }
}