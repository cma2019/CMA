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
        projectId: res.data.projectId,
        date: res.data.date,
        methodId: res.data.methodId,
        equipmentName: res.data.equipmentName,
        equipmentId: res.data.equipmentId,
        experimenter: res.data.experimenter,
        result: res.data.result,
        resultDeal: res.data.resultDeal,
        note: res.data.note
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
      if (res.code == 200) {
        console.log('delete record successfully')
        wx.navigateBack({
          delta: 1
        })
      }
    }, (err) => {
      console.log('delete plan failed')
    })
  }
})