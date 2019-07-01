// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "planId": "null",
    "name": "null",
    "organizer": "null",
    "state": "null",
    "year": "null",
    "note": "null",
    "analysis": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      planId: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      this.setData({
        name: res.data[0].name,
        organizer: res.data[0].organizer,
        state: res.data[0].state,
        year: res.data[0].year,
        note: res.data[0].note,
        analysis: res.data[0].analysis
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../modifyOne/modifyOne?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOne'
    let data = {
      "planId": this.data.planId
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
    }, (err) => {
      console.log('delete failed')
    })
  },

  getProjects(e){
    console.log("get projects")
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../../CapacityVerificationProject/showProjects/showProjects?id=' + target
    })
  }
})