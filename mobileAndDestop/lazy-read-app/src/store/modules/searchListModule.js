/*
 * @Author: Harry 
 * @Date: 2020-01-01 22:53:38 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 14:40:21
 */

export default {
    namespaced:true,
    state: {
        bookList:[]
    },
    getters:{
        getBookList(state){
            return state.bookList;
        }
    },
    mutations: {
        setBookList(state,val){
            state.bookList = val;
        }
    },
    actions: {
  
    }
 }