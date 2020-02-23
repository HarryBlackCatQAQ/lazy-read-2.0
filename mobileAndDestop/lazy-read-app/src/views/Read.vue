/*
 * @Author: Harry 
 * @Date: 2020-01-17 14:01:30 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-22 16:16:11
 */

<template>
  <div class="read-page">
    <!-- 阅读页面 -->
    <q-layout view="lHh Lpr lFf">
      <q-slide-transition>
        <!--呼出头部动画-->
        <!--头部-->
        <q-header v-show="openHeadAndFoot" class="bg-teal-4">
          <q-toolbar>
            <!--返回按钮-->
            <q-btn @click="goBackClick" flat dense round aria-label="Back" icon="ion-arrow-back" />
            <q-separator style="margin-left:5px;" vertical inset color="white" />
            <q-toolbar-title style="font-size:18px;">
              {{this.bookName}}
              <!--用于间隔 长度可以使用span中的--代替  -->
              <span style="color:#4db6ac; height:10px;width:10px;">------</span>
              <span style="font-size:15px;">{{this.title}}</span>
            </q-toolbar-title>

            <!--收藏爱心-->
            <van-goods-action-icon
              icon="like"
              style="font-size:25px;"
              :color="this.isCollection ? 'red' : 'black'"
              @click="collectionClick"
            />
          </q-toolbar>
        </q-header>
      </q-slide-transition>

      <q-drawer v-model="leftDrawerOpen" :width="300" :breakpoint="1800">
        <!-- 头部内容 -->
        <div class="absolute-top header-top" style="height: 100px">
          <!-- src="https://cdn.quasar.dev/img/material.png" -->
          <!--头部的list-->
          <q-list class="rounded-borders" style="max-width: 250px; width: 100%;">
            <p />
            <q-item>
              <q-item-section>
                <span style="font-size:18px;">
                  {{this.calBookName(this.bookName)}}
                </span>
              </q-item-section>
            </q-item>

            <q-item style="width:400px;">
              <q-item-section>
                <span>目录</span>
              </q-item-section>

              <!--章节升降序排列 默认升序-->
              <q-item-section>
                <q-icon
                  class="q-ml-xl"
                  style="font-size:24px;"
                  @click="bookListSort"
                  :name="isBookListSort ? 'ion-ios-arrow-dropdown' :'ion-ios-arrow-dropup'"
                />
              </q-item-section>
            </q-item>

            <q-separator class="line" inset="item" color="grey" />
          </q-list>
        </div>

        <!-- 章节列表 -->
        <q-scroll-area style="height: calc(100% - 150px); margin-top: 120px; max-width: 300px;">
          <q-scroll-observer @scroll="onScroll" />
          <q-list class style="width: 100%;">
            <template v-for="(item,i) in this.$store.getters['chapter/getChapterList']">
              <q-item :key="i">
                <span @click="chapterClick(i)">{{(i + 1) + ". " + item.title}}</span>
              </q-item>
            </template>
          </q-list>
        </q-scroll-area>
      </q-drawer>

      <q-page-container>
        <q-scroll-observer @scroll="onScroll2" />
        <!--书的内容-->
        <div class="read text-wrapper">
          <div class="chapter-title">
            <span v-html="kongge"></span>
            {{this.title}}
          </div>
          <div class="chapter-content">{{this.content}}</div>
          <!-- 按钮组 上下翻页-->
          <div v-show="!this.isBtnLoginShow" class="btn-group">
            <template v-if="this.judgeBtnGroup()">
              <span class="preBtn">
                <q-btn @click="preBtnClick" round color="secondary" icon="ion-ios-arrow-dropleft" />
              </span>
              <span class="nextBtn">
                <q-btn
                  @click="nextBtnClick"
                  round
                  color="secondary"
                  icon="ion-ios-arrow-dropright"
                />
              </span>
            </template>

            <template v-else>
              <div v-show="!this.isFirst" class="spBtn">
                <q-btn @click="preBtnClick" round color="secondary" icon="ion-ios-arrow-dropleft" />
              </div>
              <div v-show="this.isFirst" class="spBtn">
                <q-btn
                  @click="nextBtnClick"
                  round
                  color="secondary"
                  icon="ion-ios-arrow-dropright"
                />
              </div>
            </template>
          </div>
        </div>
      </q-page-container>

      <!--底部-->
      <q-slide-transition>
        <q-footer v-show="openHeadAndFoot" class="bg-teal-4">
          <q-tabs v-model="tab" inline-label class="text-white shadow-2">
            <q-tab
              name="bookcase"
              icon="ion-list-box"
              label="章节"
              @click="leftDrawerOpen = !leftDrawerOpen"
            />
            <!-- <q-route-tab name="bookcity" icon="ion-bookmarks" label="书城" to="/bookcity" /> -->
            <!-- <q-tab name="movies" icon="alarm" label="Movies" /> -->
          </q-tabs>
        </q-footer>
      </q-slide-transition>

      <!--居中-->
      <div @click="callService" id="fd">
        <!-- <span>
              服务好帮手
        </span>-->
      </div>
    </q-layout>
  </div>
</template>

<script>
import { mapState } from "vuex";

import chapterService from "@/service/chapter/chapterService";
import localStorageHelper from "@/model/localStorageHelper"
//本地存储类
var storage2 = new localStorageHelper();

export default {
  name: "read",
  data() {
    return {
      heavyList: [],
      leftDrawerOpen: false,
      isBookListSort: false, //书本排序，默认false为升序，true为降序
      openHeadAndFoot: false,
      tab: "",
      scrollInfo: {},
      isCollection: false,
      //这个页面的 书的属性
      content: "",
      title: "",
      bookName: "",
      //用于添加空格的
      kongge: "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
      //用于加载页面的时候隐藏这个按钮组
      isBtnLoginShow: false,
      //用于第一章和最后一张的判断 按钮显示的
      isFirst: true
    };
  },
  methods: {
    calBookName(val){
      if(val.length > 10){
        val = val.substring(0,10) + "...";
      }

      return val;
    },
    //判断此书是否有收藏
    isCollectionJudge() {
      let bookList = this.$store.getters["userCollectionBooks/getList"];
      let book = this.$store.getters["book/getBook"];

      if (book == undefined) {
        return false;
      }

      for (let i = 0; i < bookList.length; i++) {
        let b = bookList[i];
        if (b.bookUrl === book.bookUrl) {
          return true;
        }
      }

      return false;
    },
    chapterClick(idx) {
      let tp = this.$store.getters["chapter/getChapterList"][idx].content;
      if (tp === null) {
        this.$q.loading.show({
          message: "加载中，请稍后",
          spinnerSize: 70 // 像素
        });

        let res = chapterService.getChapterContent(
          this,
          this.$store.getters["chapter/getChapterList"][idx]
        );

        res.then(
          response => {
            let chapter = this.$store.getters["chapter/getChapterList"][idx];
            this.content = chapter.content;
            this.title = chapter.title;
            this.$store.commit("chapter/setChapterIdx", idx);

            let book = this.$store.getters["book/getBook"];
            storage2.set(book.bookUrl,chapter.title);

            this.$q.loading.hide();
          },
          error => {
            this.$q.loading.hide();
          }
        );
      } else {
        let chapter = this.$store.getters["chapter/getChapterList"][idx];
        this.content = chapter.content;
        this.title = chapter.title;
        this.$store.commit("chapter/setChapterIdx", idx);

        let book = this.$store.getters["book/getBook"];
        storage2.set(book.bookUrl,chapter.title);
      }

      window.scrollTo(0, 0);
    },
    //上一页按钮点击
    preBtnClick() {
      let tp = this.$store.getters["chapter/getChapterList"][
        this.$store.getters["chapter/getChapterIdx"] - 1
      ].content;
      let idx = this.$store.getters["chapter/getChapterIdx"];
      if (tp === null) {
        this.$q.loading.show({
          message: "加载中，请稍后",
          spinnerSize: 70 // 像素
        });

        let res = chapterService.getChapterContent(
          this,
          this.$store.getters["chapter/getChapterList"][idx - 1]
        );

        res.then(
          response => {
            let chapter = this.$store.getters["chapter/getChapterList"][
              idx - 1
            ];
            this.content = chapter.content;
            this.title = chapter.title;
            this.$store.commit("chapter/setChapterIdx", idx - 1);

            let book = this.$store.getters["book/getBook"];
            storage2.set(book.bookUrl,chapter.title);
            this.$q.loading.hide();
          },
          error => {
            this.$q.loading.hide();
          }
        );
      } else {
        let chapter = this.$store.getters["chapter/getChapterList"][idx - 1];
        this.content = chapter.content;
        this.title = chapter.title;
        this.$store.commit("chapter/setChapterIdx", idx - 1);

        let book = this.$store.getters["book/getBook"];
        storage2.set(book.bookUrl,chapter.title);
      }

      window.scrollTo(0, 0);
    },
    //下一页按钮点击
    nextBtnClick() {
      let tp = this.$store.getters["chapter/getChapterList"][
        this.$store.getters["chapter/getChapterIdx"] + 1
      ].content;
      let idx = this.$store.getters["chapter/getChapterIdx"];
      if (tp === null) {
        this.$q.loading.show({
          message: "加载中，请稍后",
          spinnerSize: 70 // 像素
        });

        let res = chapterService.getChapterContent(
          this,
          this.$store.getters["chapter/getChapterList"][idx + 1]
        );

        res.then(
          response => {
            let chapter = this.$store.getters["chapter/getChapterList"][
              idx + 1
            ];
            this.content = chapter.content;
            this.title = chapter.title;
            this.$store.commit("chapter/setChapterIdx", idx + 1);

            let book = this.$store.getters["book/getBook"];
            storage2.set(book.bookUrl,chapter.title);
            this.$q.loading.hide();
          },
          error => {
            this.$q.loading.hide();
          }
        );
      } else {
        let chapter = this.$store.getters["chapter/getChapterList"][idx + 1];
        this.content = chapter.content;
        this.title = chapter.title;
        this.$store.commit("chapter/setChapterIdx", idx + 1);

        let book = this.$store.getters["book/getBook"];
        storage2.set(book.bookUrl,chapter.title);
      }

      window.scrollTo(0, 0);
    },
    //用于判断按钮组的 是第一章还是最后一章
    judgeBtnGroup() {
      let list = this.$store.getters["chapter/getChapterList"];
      let idx = this.$store.getters["chapter/getChapterIdx"];

      let length = list.length;
      if (idx > 0 && idx < length - 1) {
        return true;
      }

      if (idx === 0) {
        this.isFirst = true;
      } else if (idx === length - 1) {
        this.isFirst = false;
      }

      return false;
    },
    goBackClick() {
      this.$router.push(this.prePath);
    },
    callService() {
      //   console.log("@");
      this.openHeadAndFoot = !this.openHeadAndFoot;
    },
    //章节排序
    bookListSort() {
      let that = this;
      let list = this.$store.getters["chapter/getChapterList"];
      list.sort(function(a, b) {
          return b.id - a.id;
      });

      for(let i = 0;i < list.length;i++){
        list[i].id = i;
      }

      this.$store.commit("chapter/setChapterList", list);
      this.isBookListSort = !this.isBookListSort;
    },
    collectionClick() {
      console.log("collectionClick");
      let book = this.$store.getters["book/getBook"];
      console.log(book);
      let user = this.$store.getters["user/getUser"];
      console.log(user);
      let bookList = this.$store.getters["userCollectionBooks/getList"];
      if (!this.isCollection) {
        this.$post("/user/collectionBook", {
          user: user,
          book: book
        }).then(
          response => {
            console.log(response);
            if (response) {
              bookList = this.$store.getters["userCollectionBooks/getList"];
              // console.log(book);
              bookList.push(book);
              this.$store.commit("userCollectionBooks/setList", bookList);
              this.$q.notify({
                message: "收藏成功",
                position: "top",
                color: "teal-5",
                timeout: 1000,
                icon: "ion-checkmark-circle-outline"
              });
              this.isCollection = !this.isCollection;
            } else {
              this.$q.notify({
                message: "收藏失败!系统繁忙请稍后再试！",
                position: "top",
                color: "negative",
                timeout: 1000,
                icon: "ion-close-circle-outline"
              });
            }
          },
          error => {
            this.$q.notify({
              message: "收藏失败!系统繁忙请稍后再试！",
              position: "top",
              color: "negative",
              timeout: 1000,
              icon: "ion-close-circle-outline"
            });
          }
        );
      } else {
        this.$post("/user/deleteCollectionBook", {
          user: user,
          book: book
        }).then(
          response => {
            console.log(response);
            if (response.flag) {
              for (let i = 0; i < bookList.length; i++) {
                if (book.bookUrl === bookList[i].bookUrl) {
                  bookList.splice(i, 1);
                  break;
                }
              }
              this.$store.commit("userCollectionBooks/setList",bookList);
              this.isCollection = !this.isCollection;
              this.$q.notify({
                message: "取消收藏成功！",
                position: "top",
                color: "teal-5",
                timeout: 1000,
                icon: "ion-checkmark-circle-outline"
              });
            } else {
              this.$q.notify({
                message: "取消收藏失败!系统繁忙请稍后再试！",
                position: "top",
                color: "negative",
                timeout: 1000,
                icon: "ion-close-circle-outline"
              });
            }
          },
          error => {
            this.$q.notify({
              message: "取消收藏失败!系统繁忙请稍后再试！",
              position: "top",
              color: "negative",
              timeout: 1000,
              icon: "ion-close-circle-outline"
            });
          }
        );

        console.log(bookList);
      }
    },
    onScroll(info) {
      this.scrollInfo = info;
      //   console.log(info.position);
    },
    onScroll2(info) {
      this.openHeadAndFoot = false;
      //   console.log(info);
    }
  },
  created() {
    // var maxSize = 1000;

    // for (let i = 0; i < maxSize; i++) {
    //   this.heavyList.push({
    //     label: "Option " + (i + 1),
    //     id: i
    //   });
    // }

    this.$q.loading.show({
      message: "加载中，请稍后",
      spinnerSize: 70 // 像素
    });

    this.isCollection = this.isCollectionJudge();

    let that = this;
    setTimeout(function() {
      let chapter =
        that.$store.getters["chapter/getChapterList"][
          that.$store.getters["chapter/getChapterIdx"]
        ];

      if (chapter != undefined) {
        that.content = chapter.content;
        that.title = chapter.title;
        that.bookName = that.$store.getters["book/getBook"].name;
        that.isBtnLoginShow = false;
      }
      that.$q.loading.hide();
    }, 1500);
  },
  computed: mapState({
    prePath: state => state.readGoBackHomePage.prePath
  })
};
</script>


<style scoped>
#fd {
  width: 150px;
  height: 150px;
  /* background-color: #1e90ff; */
  position: fixed;
  *position: absolute;
  width: 100px;
  height: 100px;
  top: 50%;
  left: 50%;
  margin: -50px 0 0 -50px;
}

.chapter-info {
}

.btn1 {
  background-color: red;
}

.line {
  margin-left: 0px !important;
  width: 300px;
}

.header-top {
  /* background-color: #4db6ac; */
}

.text-wrapper {
  white-space: pre-wrap;
}

.chapter-title {
  margin-top: 45px;
  font-size: 25px;
}

.chapter-content {
  font-size: 20px;
}

.btn-group {
  margin-top: 30px;
}

.preBtn {
  margin-left: 20%;
}

.nextBtn {
  margin-left: 35%;
}

.spBtn {
  text-align: center;
}
</style>
