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
    let url = app.globalData.url + 'CapacityVerification/getOne'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan get one project success')
      this.setData({
        projectId: res.data[0].projectId,
        planId: res.data[0].planId,
        name: res.data[0].name,
        method: res.data[0].method,
        state: res.data[0].state,
        note: res.data[0].note
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
  }
})