// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    mess: []
  },

  onLoad: function (options) {
    //console.log(this.data.planId)
    this.setData({
      id: options.id
    })
    
  },
  onShow: function () {
    let url = app.globalData.url + 'StaffQualification/getAllByStaff'
    let postdata = {
      "staffId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res.data)
      this.setData({
        mess: res.data
      })

      console.log(this.data.mess)
    }, (err) => {
      console.log('getone error')
    })
  },
  gotoAdd(e) {
    wx.navigateBack({
      delta: 1
    })
  }
})