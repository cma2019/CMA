const app = getApp()
const CryptoJS = require("../../../utils/cryptojs.js")
Page({

  data: {
      username2:null
  },

  onLoad: function (options) {
    this.setData({

      username2: app.globalData.username1
    })
    console.log(this.data.username2)
    console.log(app.globalData)
  },
  onShow: function (options) {

  },

  fun1: function (e) {
    //修改密码的流程，与注册基本相同，此处不做过多注释
    let codeurl = app.globalData.url + 'user/getCodeModify'
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
    let url = app.globalData.url + 'user/modifyPassword'


    var data3 = CryptoJS.Encrypt(CryptoJS.username)
    console.log("data3")
    console.log(data3)
    if (e.detail.value.password2 != e.detail.value.password3)
    {
      wx.showToast({
        title: '两次密码不匹配',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else{
    let tempdata = {
      "password": e.detail.value.password,
      "newPassword": e.detail.value.password2
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

    app.wxRequest(url, 'POST', adddata, (res) => {

      console.log(res)
      if (res.msg == "密码错误") {
        wx.showToast({
          title: '旧密码错误',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      else {
        wx.showToast({
          title: '密码修改成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '/pages/login/login',
              })
            }, 1000);
          }
        })
      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
    }
  },

  ApplicationAdd: function (e) {
    console.log('form发生了add事件，携带数据为：', e.detail.value)

    if (CryptoJS.SetKey("123456789abcdef1")) {
      console.log("usercode")
      console.log(CryptoJS.key)
      this.fun1(e)
    }
    //this.fun1(e)
  },

  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})