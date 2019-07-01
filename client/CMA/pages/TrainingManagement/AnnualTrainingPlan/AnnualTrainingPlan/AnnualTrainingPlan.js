// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    "mess": null
  },

  onLoad: function (options) {
    //console.log(this.data.planId)
    this.setData({
      year: options.year
    })
    console.log(this.data.year)
    let url = app.globalData.url + 'AnnualTrainingPlan/getAll'
    let postdata = {
      "year": this.data.year
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
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  onShow: function (options) {
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'AddAnnualTrainingPlan/AddAnnualTrainingPlan',
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id//??id/planId
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'PrintOneAnnualTrainingPlan/PrintOneAnnualTrainingPlan?id=' + target
    })
  }
})