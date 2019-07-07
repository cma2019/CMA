// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "recordId": "null",
    "planId": "null",
    "object": "null",
    "checkDate": "null",
    "processRecord": "null",
    "processRecordPerson": "null",
    "processRecordDate": "null",
    "resultRecord": "null",
    "resultRecordPerson": "null",
    "resultRecordDate": "null",
    "note": "null"
  },

  onLoad: function (options) {
    this.setData({
      recordId: options.id
    })
  },
  onShow: function (options){
    let url = app.globalData.url + 'IntermediateChecksRecord/getOneByRecordId'
    let postdata = {
      "recordId": this.data.recordId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('record getone success')
      console.log(res)
      this.setData({
        planId: res.data[0].planId,
        object: res.data[0].object,
        checkDate: res.data[0].checkDate,
        processRecord: res.data[0].processRecord,
        processRecordPerson: res.data[0].processRecordPerson,
        processRecordDate: res.data[0].processRecordDate,
        resultRecord: res.data[0].resultRecord,
        resultRecordPerson: res.data[0].resultRecordPerson,
        resultRecordDate: res.data[0].resultRecordDate,
        note: res.data[0].note
      })
    }, (err) => {
      console.err('getone error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  
  modifyData(e) {
    console.log(e)
    let target = this.data.recordId
    console.log(target)
    wx.navigateTo({
      url: '../IntermediateChecksRecordModify/IntermediateChecksRecordModify?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'IntermediateChecksRecord/deleteOne'
    let data = {
      "recordId": this.data.recordId
    }
    app.wxRequest(url, 'POST', data, (res) => {
      if (res.code == 200) {
        console.log('delete successfully')
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateBack({
                delta: 1
              })
            }, 1000);
          }
        })
      }else{
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.log('delete failed')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }

})