/*
 * @Author: Harry 
 * @Date: 2020-01-21 14:59:00 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 15:22:19
 */


export default {
    namespaced:true,
    state: {
        chapterList:[],
        chapterIdx:0,
    },
    getters:{
        getChapterList(state){
            return state.chapterList;
        },
        getChapterIdx(state){
            return state.chapterIdx;
        }
    },
    mutations: {
        setChapterList(state,val){
            state.chapterList = val;
        },
        setChapterIdx(state,val){
            state.chapterIdx = val;
        }
    },
    actions: {
  
    }
 }