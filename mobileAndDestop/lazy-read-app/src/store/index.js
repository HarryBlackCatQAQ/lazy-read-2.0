import Vue from 'vue'
import Vuex from 'vuex'

import userCollectionBooks from "./modules/userCollectionBooksModule"
import searchList from "./modules/searchListModule"
import readGoBackHomePage from "./modules/readGoBackHomePageModule"
import user from "./modules/userBaseModel"
import chapter from "./modules/chapterModule"
import book from "./modules/bookModule"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    userCollectionBooks,
    searchList,
    readGoBackHomePage,
    user,
    chapter,
    book
  }
})
