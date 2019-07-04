const app = getApp()
const CryptoJS = require("../../utils/cryptojs.js")
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },
  /*
  ApplicationAdd: function (e) {
    {
      //console.log(res)
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'user/find'
      let data = {
        "username": e.detail.value.username,
        "password": e.detail.value.password
      }
     // wx.clearStorage()
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'GET', data, (res) => {
        console.log('successfully')
        
        console.log(res.msg)
        console.log(res.code)
        if (res.code == 200) {
          
          app.globalData.username1=e.detail.value.username
          wx.switchTab({
            url: '../home/home',
          })
        }
        else {
          wx.showToast({
            title: '登录失败',
            icon: 'none',
            duration: 2000
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
      })

    }

  },
*/  
  goback: function () {
    wx.navigateTo({
      url: '/pages/register/register',
    })
  },

  fun1: function (e) {
    let codeurl = app.globalData.url + 'user/logingetCode'
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
        CryptoJS.username = e.detail.value.username

        var mykey = CryptoJS.Encrypt(CryptoJS.username)
        var endkey = mykey.slice(0, 16)
        console.log(mykey)
        console.log(endkey)
        console.log(CryptoJS.key)

        var data2 = CryptoJS.Encrypt(CryptoJS.username)
        console.log("data2")
        console.log(data2)

        //CryptoJS.selfkey = endkey

        if (CryptoJS.SetKey(endkey)) {
          console.log("usercode")
          console.log(CryptoJS.key)
          this.fun2(e)
        }

      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
  },

  fun2: function (e) {
    console.log("wx login end")
    let url = app.globalData.url + 'user/login'


    var data3 = CryptoJS.Encrypt(CryptoJS.username)
    console.log("data3")
    console.log(data3)

    let tempdata = {
      "password": e.detail.value.password,
    }

    var lastdata = JSON.stringify(tempdata)
    console.log("last")
    console.log(lastdata)
    console.log(CryptoJS.username)

    var data = CryptoJS.Encrypt(lastdata)
    let adddata = {
      "data": data,
    }
    console.log("data secret")
    console.log(adddata)
    console.log(data)
    var resdata = CryptoJS.Decrypt(data)
    console.log("data result")
    console.log(url)
    console.log(resdata)

    app.wxRequest(url, 'GET', adddata, (res) => {

      console.log(res)
      if (res.code == 200) {
        app.globalData.username1 = e.detail.value.username
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
    if (CryptoJS.SetKey("123456789abcdef1")) {
      console.log("usercode")
      console.log(CryptoJS.key)
      this.fun1(e)
    }
    //this.fun1(e)
  }

})