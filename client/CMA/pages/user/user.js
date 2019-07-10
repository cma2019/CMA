const app = getApp()
Page({
  data: {
    "mess": null
  },
  onLoad: function (options) {
    this.setData({
      id: app.globalData.username1
    })
    let url = app.globalData.url + 'user/getOne'
    let postdata = {
      "username": this.data.id
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      this.setData({
        username: this.data.id,
        mess: res.data1
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  gotoModify(e) {
    wx.navigateTo({
      url: '../login/modifypassword/modifypassword',
    })
  } ,
  goto(e) {
    wx.redirectTo({
      url: '../login/login',
    })
  }
})
