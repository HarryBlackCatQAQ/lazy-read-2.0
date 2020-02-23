/*
 * @Author: Harry 
 * @Date: 2020-01-21 01:31:43 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 02:55:32
 */

import _this2 from "@/main"
import api from "@/service/api"
import localStorageHelper from "@/model/localStorageHelper"
import ReflectHelper from "@/model/ReflectHelper"
import Book from "@/model/Book"

var that = _this2._this;
var reflectHelper = new ReflectHelper();
//本地存储类
var storage1 = new localStorageHelper();

export default {
    async getUserCollectionsBooks(user) {
        let res;

        await that.$post(api.book.getUserCollectionBooks, user)
            .then(response => {
                console.log(response);
                if(response.flag){
                    //获取响应中的书籍
                    let list = listChange(response.data);
                    //提交到store中
                    that.$store.commit("userCollectionBooks/setList",list);
                }
                else{
                    that.$q.notify({
                        message: "获取书籍失败请稍后再试!",
                        position: "top",
                        color:"negative",
                        timeout:750,
                        icon: "ion-close-circle-outline"
                    });
                }
                res = response;
        })

        return res;
    }
}


function listChange(list){
    for(let i = 0;i < list.length;i++){
        list[i] = reflectHelper.getOwnObject(Book,list[i]);
    }

    return list;
}