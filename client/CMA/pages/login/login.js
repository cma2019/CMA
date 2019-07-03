const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },

  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'user/find'
      let data = {
        "username": e.detail.value.username,
        "password": e.detail.value.password
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'GET', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if (res.code == 200) {
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
        console.log('fail intermediate check register')
      })

    }

  },
  goback: function () {
    wx.navigateTo({
      url: '/pages/register/register',
    })
  }
})