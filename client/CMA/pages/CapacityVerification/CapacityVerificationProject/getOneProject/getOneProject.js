// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": "null",
    "projectId": "null",
    "planId": "null",
    "name": "null",
    "method": "null",
    "state": "null",
    "note": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      id: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOneProject'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan get one project success')
      this.setData({
        projectId: res.data.projectId,
        planId: res.data.planId,
        name: res.data.name,
        method: res.data.method,
        state: res.data.state,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one project error')
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../modifyOneProject/modifyOneProject?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOneProject'
    let data = {
      "id": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
    }, (err) => {
      console.log('delete failed')
    })
  },
  getPlans(e) {
    console.log("get projects")
    let target = this.data.projectId
    console.log(target)
    wx.navigateTo({
      url: '../../CapacityVerificationRecord/getRecordByProjectId/getRecordByProjectId?id=' + target
    })
  }
})