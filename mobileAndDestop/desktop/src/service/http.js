/*
 * @Author: Harry 
 * @Date: 2019-06-21 02:53:14 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-30 17:42:26
 */
import axios from 'axios';
import localStorageHelper from "@/model/localStorageHelper"
import _this3 from "@/main"
// import localStorageHelper from '@/servers/js/localStorageHelper'

// axios.defaults.timeout = 5000;
// axios.defaults.baseURL ='https://hovees.cn/LazyReadProject';
// axios.defaults.baseURL = 'http://129.211.127.106/LazyReadProject'
// axios.defaults.baseURL = 'http://127.0.0.1:8300'
axios.defaults.baseURL = "http://lazyread.free.idcfengye.com"
axios.defaults.withCredentials = true

//本地存储类
var storage1 = new localStorageHelper();
// var that = _this3;

//http request 拦截器
axios.interceptors.request.use(
  config => {
    // const token = getCookie('名称');注意使用的时候需要引入cookie方法，推荐js-cookie
    config.data = JSON.stringify(config.data);

    config.headers = {
      // 'Content-Type':'application/x-www-form-urlencoded'
      'Content-Type': 'application/json;charset=utf-8'
    }

    let token = storage1.get("token");
    if (token != undefined) {
      config.headers.token = token;
    }

    //从localStorageHelper中获取token，但是现在已经给客户端token了所以现在这个没有用了
    //  if(localStorageHelper.get("token")){
    //   //从localStorageHelper中获取token，但是现在已经不给客户端token了所以现在这个没有用了
    //   config.headers.token = localStorageHelper.get("token");
    //   config.headers.userName = localStorageHelper.get("userName");
    // }


    // if(token){
    //   config.params = {'token':token}
    // }

    return config;
  },
  error => {
    return Promise.reject(err);
  }
);


//http response 拦截器
axios.interceptors.response.use(
  response => {
    if (response.data.errCode == 2) {
      router.push({
        path: "/login",
        querry: { redirect: router.currentRoute.fullPath }//从哪个页面跳转
      })
    }



    return response;
  },
  error => {
    let response = error.response;
    console.log(response)
    if (response.status === 401 && response.statusText === "Unauthorized") {
      let that = _this3._this
      that.$q.notify({
        message: "token过期,请重新登录!",
        position: "top",
        color: "negative",
        timeout: 1000,
        icon: "ion-close-circle-outline"
      });
      that.$router.push("/bookcase");
      localStorage.clear();
      localStorage.setItem("isLogin",false);
    }
    return Promise.reject(error)
  }
)


/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */

export function fetch(url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: params
    })
      .then(response => {
        resolve(response.data);
      })
      .catch(err => {
        reject(err)
      })
  })
}

/**
 * 封装get方法 用于下载文件
 * @param url
 * @param data
 * @returns {Promise}
 */

export function fetchDownLoad(url, params = {}, that = 1) {
  return new Promise((resolve, reject) => {
    // console.log(that)
    if (that == 1) {
      a(url, params, resolve, reject);
    }
    else {
      b(url, params, resolve, reject, that);
    }
  })
}

function b(url, params, resolve, reject, that) {
  axios.get(url, {
    params: params
    , responseType: "blob",
    onDownloadProgress(a) {
      // console.log("load:" + a.loaded + "  total:" + a.total);
      let percent = (a.loaded / a.total) * 100;
      percent = percent.toFixed(0);
      // console.log(percent);
      // console.log(that.progressVal);
      percent = Number(percent);
      that.progressVal = Number(percent);
      // console.log(percent);
    }
  })
    .then(response => {
      resolve(response.data);
    })
    .catch(err => {
      reject(err)
    })
}

function a(url, params, resolve, reject) {
  axios.get(url, {
    params: params
    , responseType: "blob",
    onDownloadProgress(a) {
      console.log(a);
      console.log("load:" + a.loaded + "  total:" + a.total);
      let percent = (a.loaded / a.total) * 100;
      percent = percent.toFixed(2);
      console.log(percent);
      // console.log(that.progressVal);
      percent = Number(percent);
      // that.progressVal = Number(percent);
      console.log(percent);
    }
  })
    .then(response => {
      resolve(response.data);
    })
    .catch(err => {
      reject(err)
    })
}


/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function post(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        resolve(response.data);
      }, err => {
        reject(err)
      })
  })
}

/**
* 封装patch请求
* @param url
* @param data
* @returns {Promise}
*/

export function patch(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.patch(url, data)
      .then(response => {
        resolve(response.data);
      }, err => {
        reject(err)
      })
  })
}

/**
* 封装put请求
* @param url
* @param data
* @returns {Promise}
*/

export function put(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.put(url, data)
      .then(response => {
        resolve(response.data);
      }, err => {
        reject(err)
      })
  })
}