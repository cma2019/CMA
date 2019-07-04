// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    mess: [],
    year:null
  },

  onLoad: function (options) {
    this.setData({

      year: options.id
    })
    
    let url = app.globalData.url + 'ManagementReview/getAllFile'
    let postdata = {
      "year": this.data.year
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
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  onShow: function (options) {
   
    let url = app.globalData.url + 'ManagementReview/getAllFile'
    let postdata = {
      "year": this.data.year
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
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },

  gotoAdd(e) {
    console.log(e)
    let target = this.data.year
    wx.navigateTo({
      url: '../AddOneFile/AddOneFile?id='+target,
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: '../PrintOneFile/PrintOneFile?id=' + target
    })
  }
})