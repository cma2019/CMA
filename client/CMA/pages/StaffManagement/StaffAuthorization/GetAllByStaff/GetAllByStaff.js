// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    "mess": null
  },

  onLoad: function (options) {
    //console.log(this.data.planId)
    this.setData({
      id: options.id
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffAuthorization/getAllByStaff'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      //console.log('success')
      console.log(res)
      console.log('success')
      console.log(res.code)
      //console.log(res.msg)
      console.log(res.data)
      //var temp = res.data
      //this.temp = temp
      this.setData({
        mess: res.data
      })

      console.log(this.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: 'getallbystaff error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  onShow: function (options) {
  },

  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})