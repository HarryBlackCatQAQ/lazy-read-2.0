'use strict'

import { app, protocol, BrowserWindow,Tray,dialog } from 'electron'
import {
  createProtocol,
  installVueDevtools
} from 'vue-cli-plugin-electron-builder/lib'
const isDevelopment = process.env.NODE_ENV !== 'production'

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let win

// Scheme must be registered before the app is ready
protocol.registerSchemesAsPrivileged([{scheme: 'app', privileges: { secure: true, standard: true } }])

const electron = require('electron')
/*获取electron窗体的菜单栏*/
const Menu = electron.Menu

var tray;
var contextMenu;
var isHide;

function createWindow () {
  /*隐藏electron创听的菜单栏*/
  Menu.setApplicationMenu(null)


  // Create the browser window.
  win = new BrowserWindow({ width: 1100, height: 750, webPreferences: {
    nodeIntegration: true
  } })

  if(tray === undefined){
    tray = new Tray('./src/assets/logo3.png');//app.ico是app目录下的ico文件

  //图标的上下文菜单
  contextMenu = Menu.buildFromTemplate([
    {
      label: '打开',
      click: () => {
        isHide = false;
        win.show();
      }
    }, {
      label: '退出',
      click: () => {
        app.exit();
      }
    }
  ]);

  //设置此托盘图标的悬停提示内容
  tray.setToolTip('我的托盘图标');

  //设置此图标的上下文菜单
  // tray.setContextMenu(contextMenu);
  //单击右下角小图标显示应用
  tray.on('click', function () {
    win.show();
  })

  tray.on('right-click', () => {
    tray.popUpContextMenu(contextMenu);
  });
  }

  win.on('close', (e) => {
    //回收BrowserWindow对象
    // if (isHide) {
    //   win = null;
    // } else {
    //   e.preventDefault();
    //   win.hide();
    //   isHide = true;
    // }

    dialog.showMessageBox({
      type: 'info',
      title: 'Information',
      defaultId: 0,
      message: '确定要关闭吗？',
      buttons: ['最小化','直接退出']
    },(index)=>{
      if(index===0){
        e.preventDefault();		//阻止默认行为，一定要有
        win.hide();	//调用 最小化实例方法
      } else {
        win = null;
        //app.quit();	//不要用quit();试了会弹两次
        app.exit();		//exit()直接关闭客户端，不会执行quit();
      }
    }) 


  });
  
  

  if (process.env.WEBPACK_DEV_SERVER_URL) {
    // Load the url of the dev server if in development mode
    win.loadURL(process.env.WEBPACK_DEV_SERVER_URL)
    if (!process.env.IS_TEST) win.webContents.openDevTools()
  } else {
    createProtocol('app')
    // Load the index.html when not in development
    win.loadURL('app://./index.html')
  }
}

// Quit when all windows are closed.
app.on('window-all-closed', () => {
  // On macOS it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  // On macOS it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (win === null) {
    createWindow()
  }
  else{
    win.show();
  }
})

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', async () => {
  if (isDevelopment && !process.env.IS_TEST) {
    // Install Vue Devtools
    // Devtools extensions are broken in Electron 6.0.0 and greater
    // See https://github.com/nklayman/vue-cli-plugin-electron-builder/issues/378 for more info
    // Electron will not launch with Devtools extensions installed on Windows 10 with dark mode
    // If you are not using Windows 10 dark mode, you may uncomment these lines
    // In addition, if the linked issue is closed, you can upgrade electron and uncomment these lines
    // try {
    //   await installVueDevtools()
    // } catch (e) {
    //   console.error('Vue Devtools failed to install:', e.toString())
    // }

  }
  createWindow()
})

// Exit cleanly on request from parent process in development mode.
if (isDevelopment) {
  if (process.platform === 'win32') {
    process.on('message', data => {
      if (data === 'graceful-exit') {
        app.quit()
      }
    })
  } else {
    process.on('SIGTERM', () => {
      app.quit()
    })
  }
}
