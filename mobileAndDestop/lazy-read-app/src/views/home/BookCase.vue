/*
 * @Author: Harry 
 * @Date: 2020-01-01 22:32:17 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-21 22:33:24
 */


<template>
  <div class="book-case">
    <!-- 书架 -->
    <q-pull-to-refresh @refresh="refresh">
      <q-slide-transition>
        <div v-show="visible">
          <img class="responsive" src="https://cdn.quasar.dev/img/quasar.jpg" />
        </div>
      </q-slide-transition>
      <q-slide-transition>
        <div v-show="visible1">
          <q-list class="shadow-2 rounded-borders" style="width: 100%;">
            <div v-if="!this.$store.getters['user/getIsLogin'] || this.$store.getters['userCollectionBooks/getList'].length <= 0">
              <br/>
              "你还没有收藏书籍哦！或者你还没有进行登录！Σ(ŎдŎ|||)ﾉﾉ"
            </div>
            <template v-else v-for="(b,i) in this.$store.getters['userCollectionBooks/getList']">
              <div :key="i">
                <q-separator  v-if="i != 0" inset="item-thumbnail" />
              <BookCard  :book="b"/>
              </div>
            </template>

            <!-- <BookCard :book="this.b1" />
            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" />

            <q-separator inset="item-thumbnail" />
            <BookCard :book="this.b1" /> -->
          </q-list>
        </div>
      </q-slide-transition>
      <!-- <HelloWorld />
          <HelloWorld />
          <HelloWorld />
      <HelloWorld />-->
    </q-pull-to-refresh>
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld.vue";
import BookCard from "@/components/BookCard.vue";
import Book from "@/model/Book";
import Chapter from "@/model/Chapter";
import User from "@/model/User";

import bookService from "@/service/book/bookService"

export default {
  name: "bookcase",
  components: {
    HelloWorld,
    BookCard
  },
  data() {
    return {
      items: [{}, {}, {}, {}, {}, {}, {}, {}, {}],
      b1: {
        name: "aaa",
        author: "23232"
      },
      visible: false,
      visible1: true,
      bookList:[],
      isLogin:false
    };
  },
  methods: {
    refresh(done) {
      // this.$q.loading.show({
      // });
      let res = bookService.getUserCollectionsBooks(this.$store.getters["user/getUser"]);
      let that = this;
      res.then(response =>{
        if(response.flag){
          that.bookList = that.$store.getters["userCollectionBooks/getList"];
          
          this.$q.notify({
              message: "刷新成功!",
              position: "top",
              color:"teal-5",
              timeout:1000,
              icon: "ion-checkmark-circle-outline"
            })
        }
        else{
          this.$q.notify({
              message: "系统繁忙请稍后再试!",
              position: "top",
              color:"negative",
              timeout:1000,
              icon: "ion-close-circle-outline"
            });
        }

        done();
      })

      // setTimeout(() => {
      //   this.items.push({}, {}, {}, {}, {}, {}, {});
      //   done();
      //   this.$q.loading.hide();
      // }, 1000);
    }
  },
  created(){
    this.bookList = this.$store.getters["userCollectionBooks/getList"];
    if(this.bookList === undefined || this.bookList === null){
      this.bookList = [];
    }
    console.log(this.bookList)
    this.isLogin = this.$store.getters["user/getIsLogin"];
    if(this.isLogin === undefined){
      this.isLogin = false;
    }
  }
};
</script>


<style>
</style>
