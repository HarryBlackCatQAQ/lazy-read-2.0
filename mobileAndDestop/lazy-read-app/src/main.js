import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import './quasar'

import {post,fetch,patch,put} from '@/service/http'

import { Icon,GoodsActionIcon } from 'vant';
import 'vant/lib/button/style';

import Es6Promise from 'es6-promise'
require('es6-promise').polyfill()
Es6Promise.polyfill()

// import WebSql from "./db/WebSql"

// Vue.use(WebSql);

Vue.use(Icon)
    .use(GoodsActionIcon);

Vue.config.productionTip = false

//定义全局变量
Vue.prototype.$post=post;
Vue.prototype.$get=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;

var _this = new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

export default {
  _this
}
