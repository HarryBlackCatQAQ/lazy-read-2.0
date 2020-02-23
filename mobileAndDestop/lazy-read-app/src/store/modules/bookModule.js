/*
 * @Author: Harry 
 * @Date: 2020-01-21 17:41:49 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 17:46:26
 */

export default {
    namespaced:true,
    state: {
        book:undefined
    },
    getters:{
        getBook(state){
            return state.book;
        }
    },
    mutations: {
        setBook(state,val){
            state.book = val;
        }
    },
    actions: {
  
    }
 }