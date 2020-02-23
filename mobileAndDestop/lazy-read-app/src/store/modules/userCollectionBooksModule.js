/*
 * @Author: Harry 
 * @Date: 2020-01-01 17:51:39 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 20:17:48
 */
import localStorageHelper from "@/model/localStorageHelper"
import ReflectHelper from "@/model/ReflectHelper"
import Book from "@/model/Book"

var reflectHelper = new ReflectHelper();
//本地存储类
var storage1 = new localStorageHelper();

 export default {
    namespaced:true,
    state: {
        list:undefined
    },
    getters:{
        getList(state){
            let list = state.list;
            if(list === undefined || list === null){
                let jsonstr = storage1.get("userCollectionBooks")
                // console.log(jsonstr)
                if(jsonstr != undefined){
                    list = listChange(JSON.parse(jsonstr));
                }
            }
            return list;
        }
    },
    mutations: {
        setList(state,val){
            if(val != undefined){
                let jsonstr = JSON.stringify(val);
                storage1.set("userCollectionBooks", jsonstr);
                state.list = val;
            }
        }
    },
    actions: {
  
    }
 }

 function listChange(list){
    for(let i = 0;i < list.length;i++){
        if(list[i] === null){
            continue;
        }
        list[i] = reflectHelper.getOwnObject(Book,list[i]);
    }

    return list;
}