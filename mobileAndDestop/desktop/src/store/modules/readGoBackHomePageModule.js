/*
 * @Author: Harry 
 * @Date: 2020-01-17 21:57:00 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-17 22:09:31
 */
import routerApi from "@/router/routerApi"

export default {
    namespaced:true,
    state: {
        prePath:routerApi.bookcase.path//默认使用书架上的路径
    },
    getters:{
        getPrePath(state){
            return state.prePath;
        }
    },
    mutations: {
        setPrePath(state,val){
            state.prePath = val;
        }
    },
    actions: {
  
    }
 }