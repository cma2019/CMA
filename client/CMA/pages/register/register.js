const app = getApp()
const CryptoJS = require("../../utils/cryptojs.js")
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },

  fun1: function(e) {
    let codeurl = app.globalData.url + 'user/getCode'
    console.log("wx login")
    let userdata = {
      "username": e.detail.value.username,
    }
    console.log(userdata)
    app.wxRequest(codeurl, 'POST', userdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        console.log('code successfully')
        console.log(res)
        this.fun2(e)
      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
  },

  fun2:function(e){
    console.log("wx login end")
    let url = app.globalData.url + 'user/add'

    
    let tempdata = {
      "password": e.detail.value.password,
      "password2": e.detail.value.password2
    }
   
    var lastdata = JSON.stringify(tempdata)
    console.log("last")
    console.log(lastdata)

    var data = CryptoJS.Encrypt(lastdata)
    let adddata = {
      "data": data,
    }
    console.log("data secret")
    console.log(adddata)

    var resdata = CryptoJS.Decrypt(data)
    console.log("data result")
    console.log(url)
    console.log(resdata)

    app.wxRequest(url, 'POST', adddata, (res) => {
      
      console.log(res)
      if(res.code == 200){
        console.log('successfully')
        wx.redirectTo({
          url: '/pages/login/login',
        })
      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
  },

  ApplicationAdd: function (e) {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      this.fun1(e)
  },

  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})