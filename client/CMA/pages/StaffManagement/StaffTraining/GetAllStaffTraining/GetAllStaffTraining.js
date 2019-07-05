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
    let url = app.globalData.url + 'StaffTraining/getAllByStaff'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        mess: res.data
      })

      console.log(this.data.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: '失败',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  onShow: function () {
    let url1 = app.globalData.url + 'StaffTraining/getAllByStaff'
    let postdata1 = {
      "id": this.data.id
    }
    app.wxRequest(url1, 'GET', postdata1, (res) => {
      this.setData({
        mess: res.data
      })

      console.log(this.data.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: '失败',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  gotoAdd(e)
  {
    wx.navigateBack({
      delta:1  
    })
  }
})