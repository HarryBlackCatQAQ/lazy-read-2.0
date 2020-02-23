/*
 * @Author: Harry 
 * @Date: 2020-01-21 14:10:28 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 14:48:23
 */

import _this2 from "@/main"
import api from "@/service/api"
// import localStorageHelper from "@/model/localStorageHelper"
import ReflectHelper from "@/model/ReflectHelper"
import Book from "@/model/Book"

var that = _this2._this;
var reflectHelper = new ReflectHelper();
//本地存储类
// var storage1 = new localStorageHelper();

 export default {
    async search(content){
        let res;
        console.log(content)
        await that.$get(api.book.search,{
            content:content
        })
        .then(response =>{
            let list = response;
            list = listChange(list);
            that.$store.commit("searchList/setBookList",list);
            res = response;
        },
        error =>{
            that.$q.notify({
                message: "系统出错请稍后再试!😢",
                position: "top",
                color:"negative",
                timeout:1000,
                icon: "ion-close-circle-outline"
              });
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