const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },

  fun1: function (e) {
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
        CryptoJS.username = e.detail.value.username
        this.fun2(e)
      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
  },

  fun2: function (e) {
    console.log("wx login end")
    let url = app.globalData.url + 'user/find'


    let tempdata = {
      "password": e.detail.value.password,
    }

    var lastdata = JSON.stringify(tempdata)
      console.log("last")
      console.log(lastdata)
      console.log(CryptoJS.username)
    var mykey = CryptoJS.Encrypt(CryptoJS.username)
    var endkey = mykey.slice(0, 16)
      console.log(mykey)
      console.log(endkey)
    CryptoJS.SetKey(endkey)

      console.log("usercode")
      console.log(mykey)
      console.log(CryptoJS.key)
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
    app.wxRequest(url, 'GET', adddata, (res) => {
      console.log(res.code)
      if (res.code == 200) {
        console.log('successfully')
        wx.setStorage({
          key: "key",
          data: e.detail.value.username
        })
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
      console.log('fail')
    })
  },
  ApplicationAdd: function (e) {
    console.log('form发生了add事件，携带数据为：', e.detail.value)
    this.fun1(e)
  },
  goback: function () {
    wx.navigateTo({
      url: '/pages/register/register',
    })
  }
})