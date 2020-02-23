/*
 * @Author: Harry 
 * @Date: 2020-01-17 23:13:26 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 03:19:00
 */
import localStorageHelper from "@/model/localStorageHelper"
import ReflectHelper from "@/model/ReflectHelper"
import User from "@/model/User"

var reflectHelper = new ReflectHelper();
//本地存储类
var storage1 = new localStorageHelper();


export default {
    namespaced:true,
    state: {
        isLogin:undefined,
        info:undefined
    },
    getters:{
        getIsLogin(state){
            let isLogin = state.isLogin;
            if(isLogin === undefined || isLogin === null){
                isLogin = storage1.get("isLogin");
            }
            return isLogin;
        },
        getUser(state){
            let user = state.info;
            if(user === undefined || user === null){
                let jsonstr = storage1.get("user");
                if(jsonstr != undefined){
                    user = reflectHelper.getOwnObject(User,JSON.parse(jsonstr));
                }
            }
            return user;
        }
    },
    mutations: {
        setIsLogin(state,val){
            storage1.set("isLogin",val);
            state.isLogin = val;
        },
        changeIsLogin(state){
            state.isLogin = !state.isLogin;
            storage1.set("isLogin",state.isLogin);
        },
        setUser(state,val){
            console.log(val);
            if(val != undefined){
                let jsonstr = JSON.stringify(val);
                storage1.set("user", jsonstr);
                state.info = val;
            }
        }
    },
    actions: {
  
    }
 }