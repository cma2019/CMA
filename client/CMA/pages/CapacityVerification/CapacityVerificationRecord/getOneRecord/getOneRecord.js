// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "recordId": "null",
    "projectId": "null",
    "date": "null",
    "methodId": "null",
    "equipmentName": "null",
    "equipmentId": "null",
    "experimenter": "null",
    "result": "null",
    "resultDeal": "null",
    "note": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      recordId: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOneRecord'
    let postdata = {
      "id": this.data.recordId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan get one project success')
      this.setData({
        projectId: res.data[0].projectId,
        date: res.data[0].date,
        methodId: res.data[0].methodId,
        equipmentName: res.data[0].equipmentName,
        equipmentId: res.data[0].equipmentId,
        experimenter: res.data[0].experimenter,
        result: res.data[0].result,
        resultDeal: res.data[0].resultDeal,
        note: res.data[0].note
      })
    }, (err) => {
      console.err('get one project error')
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.recordId
    console.log(target)
    wx.navigateTo({
      url: '../modifyOneRecord/modifyOneRecord?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOneRecord'
    let data = {
      "id": this.data.recordId
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete plan successfully')
    }, (err) => {
      console.log('delete plan failed')
    })
  }
})