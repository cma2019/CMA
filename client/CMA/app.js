//app.js
const CryptoJS = require("/utils/cryptojs.js")

//全局app
App({
  //全局预设数据
  globalData: {
    userInfo: null,
<<<<<<< HEAD
    url: 'http://192.168.1.110:8004/cma/',
=======
    url: 'http://192.168.1.100:8004/cma/',
>>>>>>> 374e408ef07038baca8fbc3cf83041fec10c8c31
    username1: "admin"
  },
  //wx.request的封装接口
  wxRequest(url, method, data, callback, errfun) {
    //data = CryptoJS.Encrypt(data)
    wx.request({
      url: url,
      method: method,
      data: data,
      header: {
        'content-type': method == 'GET' ? 'application/json' : 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      success: function (res) {
        //var resdata = CryptoJS.Decrypt(res.data)
        //callback(resdata)
        callback(res.data);
      },
      fail: function (err) {
        errfun(err);
      }
    })
  },
  //wx.uploadFile的封装接口
  wxUploadFile(url, filePath, data, callback, errfun) {
    wx.uploadFile({
      url: url,
      filePath: filePath,
      name: 'file',
      header: {
        'content-type': 'multipart/form-data'
      },
      formData: data,
      success: function (res) {
        callback(res.data)
      },
      fail: function (err) {
        errfun(res);
      }
    })
  },
  //wx.downloadFile的封装接口
  wxDownloadFile(url, callback, errfun) {
    wx.downloadFile({
      url: url,
      success: function (res) {
        callback(res)
      },
      fail: function (res) {
        errfun(res)
      }
    })
  },


  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    /*
    console.log("now login")
    wx.login({
      success: res => {
        console.log("res code")
        console.log(res.code)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    */
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  }
})