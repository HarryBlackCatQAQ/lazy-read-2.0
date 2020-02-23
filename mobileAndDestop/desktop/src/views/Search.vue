/*
 * @Author: Harry 
 * @Date: 2020-01-17 11:59:46 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-22 00:37:38
 */
<template>
  <div class="search-page">
    search
    <q-layout view="lHh Lpr lFf">

     <!--头部-->
      <q-header reveal elevated class="bg-teal-4">
        <q-toolbar>
          <q-btn flat dense round aria-label="Menu" icon="ion-arrow-back" @click="backToBookCase" />
          <q-separator style="margin-left:5px;" vertical inset color="white" />

          <q-input
            @keyup="show($event)"
            rounded
            dark
            dense
            standout
            v-model="content"
            input-class="text-left"
            class="q-ml-md"
          >
            <template v-slot:append>
              <q-icon v-if="content === ''" name="search" />
              <q-icon v-else name="clear" class="cursor-pointer" @click="content = ''" />
            </template>
          </q-input>

          <img class="logo q-ml-xl" src="../assets/lazyread-touming-logo.png" />
        </q-toolbar>
      </q-header>

    <!--主要内容-->
      <q-page-container>
          <q-list class="shadow-2 rounded-borders" style="width: 100%;">

            <div v-for="(b,i) in this.bookList" :key="i">
                <q-separator v-if="i != 0" inset="item-thumbnail" />
                <BookCard :book="b" />
            </div>
                
          </q-list>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script>
import routerApi from "@/router/routerApi";
import BookCard from "@/components/BookCard.vue";
import Book from "@/model/Book";

import { mapState } from "vuex";

import searchService from "@/service/book/searchService"

export default {
  name: "search",
  components:{
      BookCard
  },
  data() {
    return {
      content: "",
    };
  },
  methods: {
    backToBookCase() {
      this.$router.push(routerApi.bookcase.path);
    },
    show(ev) {
      if (ev.keyCode == 13) {
        console.log("你按回车键了");
        this.$q.loading.show({
          message: "搜索中,请稍后🔍",
          spinnerSize: 70 // 像素
        });

        let res = searchService.search(this.content);

        res.then(response =>{
          this.$q.loading.hide();
        },
        error =>{
          this.$q.loading.hide();
        })
      }
    }
  },
  created(){
    //   console.log(this.bookList)
  },
  computed: mapState({
    bookList: state => state.searchList.bookList
  })
};
</script>


<style scoped>
.logo {
  width: 55px;
}
</style>
