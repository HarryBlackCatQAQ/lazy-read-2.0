/*
 * @Author: Harry 
 * @Date: 2020-01-21 14:51:50 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-22 01:07:42
 */

import api from "@/service/api"
import localStorageHelper from "@/model/localStorageHelper"
import ReflectHelper from "@/model/ReflectHelper"
import Book from "@/model/Book"
import Chapter from "@/model/Chapter"

var reflectHelper = new ReflectHelper();
//本地存储类
var storage1 = new localStorageHelper();

export default {
    async readBook(that,book){
        let res;
        console.log(book)

        await that.$post("/book/getChapterList",book)
        .then(response =>{
            console.log(response);
            let list = listChange(response);
            that.$store.commit("chapter/setChapterList",list);

            res = response;
        })

        return res;
        
    },
    async getChapterContent(that,chapter){
        let res;

        console.log(chapter);

        await that.$post("/book/getChapterContent",chapter)
        .then(response =>{
            console.log(response)

            let tp = reflectHelper.getOwnObject(Chapter,response);
            tp.id = chapter.id;

            let list = that.$store.getters["chapter/getChapterList"];
            list[chapter.id] = tp;
            that.$store.commit("chapter/setChapterList",list);

            res = response;
        })

        return res;
    }
}

function listChange(list){
    for(let i = 0;i < list.length;i++){
        list[i] = reflectHelper.getOwnObject(Chapter,list[i]);
        list[i].id = i;
    }

    return list;
}