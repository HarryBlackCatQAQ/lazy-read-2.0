/*
 * @Author: Harry 
 * @Date: 2020-01-01 16:43:00 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-22 16:10:47
 */


<template>
  <div class="bookcard-com" @click="read">
    <q-item class="book-card">
      <q-item-section thumbnail>
        <img :src="book.imageUrl" />
      </q-item-section>
      <q-item-section class="info">
        <span>书名: {{book.name}}</span>
        <p />
        <span>作者: {{book.author}}</span>
        <p />
        <span class="latestChapter">最新章节: {{book.latestChapterName}}</span>
      </q-item-section>
    </q-item>
  </div>
</template>

<script>
import { mapState } from "vuex";
import routerApi from "@/router/routerApi"
import localStorageHelper from "@/model/localStorageHelper"
import chapterService from "@/service/chapter/chapterService"

//本地存储类
var storage2 = new localStorageHelper();

export default {
  name: "bookcard",
  props: {
    book: Object
  },
  methods: {
    read() {
      // console.log("router:" + this.$route.path)
      // console.log(this.prePath)

      this.$q.loading.show({
        message: "加载中，请稍后",
        spinnerSize: 70 // 像素
      });

      let res = chapterService.readBook(this,this.book);

      res.then(response =>{
        this.$store.commit('readGoBackHomePage/setPrePath',this.$route.path)
        this.$store.commit("book/setBook",this.book);
        
        console.log(this.book);
        let tp = storage2.get(this.book.bookUrl);
        console.log(this.book.bookUrl)
        console.log(tp);
        let idx = 0;
        if(tp != undefined){
          let chapterList = this.$store.getters["chapter/getChapterList"];
          for(let i = 0;i < chapterList.length;i++){
              if(chapterList[i].title === tp){
                idx = i;
                break;
              }
          }
        }
        this.$store.commit("chapter/setChapterIdx",idx);
        chapterService.getChapterContent(this,this.$store.getters["chapter/getChapterList"][idx]);
        this.$q.loading.hide();
        this.$router.push(routerApi.read.path)
      },
      error =>{
        this.$q.loading.hide();
      })

      // this.$store.commit('readGoBackHomePage/setPrePath',this.$route.path)
      // this.$q.loading.show({
      //   message: "加载中，请稍后",
      //   spinnerSize: 70 // 像素
      // });

      // setTimeout(() => {
      //   this.$q.loading.hide();
      //   // console.log(this.prePath)
      //   this.$router.push(routerApi.read.path)
      // }, 1000);
    }
  },
  created(){
    // console.log(this.prePath)
    // console.log(routerApi)
  },
  computed: mapState({
    prePath: state => state.readGoBackHomePage.prePath
  })
  
};
</script>


<style scoped>
.book-card {
  padding-left: 25px;
}

.book-card img {
  height: 130px;
  border-radius: 5px;
}

.info {
  font-size: 16px;
}

.latestChapter {
  font-size: 10px;
  color: gray;
}
</style>
