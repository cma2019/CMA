// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
    "mess":null
  },
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
  },
  onShow:function()
  {
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
  ModifyStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../modifyadmin/modifyadmin?id=' + target
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})