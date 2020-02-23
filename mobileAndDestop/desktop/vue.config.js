module.exports = {
  publicPath: "./",
  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: true
    },
    electronBuilder: {
      builderOptions: {
          "appId": "com.hhr.lazyread",
          "productName":"懒 读",//项目名，也是生成的安装文件名，即aDemo.exe
          "copyright":"Copyright © 2020",//版权信息
          "directories":{
              "output":"./dist_11"//输出文件路径
          },
          "win":{//win相关配置
              "icon":"./public/win.ico",//图标，当前图标在根目录下，注意这里有两个坑
          },
          "mac":{
              "icon":"./public/mac.icns"
          }
          }
      }
  },
  transpileDependencies: [
    'quasar'
  ]
}
