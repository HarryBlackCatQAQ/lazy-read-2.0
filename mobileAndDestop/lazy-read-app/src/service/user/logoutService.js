/*
 * @Author: Harry 
 * @Date: 2020-01-21 02:57:43 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 03:03:13
 */

import _this from "@/main"
import api from "@/service/api"
import localStorageHelper from "@/model/localStorageHelper"

var that = _this
console.log(that);
//本地存储类
var storage1 = new localStorageHelper();

 export default {
    async logout(user){
        let res;

        await that.$post("user/logout",user)
        .then(response =>{
            console.log(response);

            res = response;
        })

        return res;
    }
 }