// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },

  bindCheckDateChange(e) {
    console.log(e)
    this.setData({
      checkDate: e.detail.value
    })
  },

  bindDateChange1(e) {
    console.log(e)
    this.setData({
      processRecordDate: e.detail.value
    })
  },

  bindDateChange2(e) {
    console.log(e)
    this.setData({
      resultRecordDate: e.detail.value
    })
  },

  InterCheckRegister(e) {
    if (e.detail.value.object == "" ||
      e.detail.value.date == "" ||
      e.detail.value.content == "" ||
      e.detail.value.personInCharge == "") {
      console.log("message error")
      console.log(e.detail.value.object)
      console.log(e.detail.value.content)
      console.log(e.detail.value.date)
      console.log(e.detail.value.personInCharge)
    }
    else {
      let url = app.globalData.url + 'IntermediateChecksRecord/addOne'
      let data = {
        "planId": e.detail.value.planId,
        "object": e.detail.value.object,
        "checkDate": e.detail.value.checkDate,
        "processRecord": e.detail.value.processRecord,
        "processRecordPerson": e.detail.value.processRecordPerson,
        "processRecordDate": e.detail.value.processRecordDate,
        "resultRecord": e.detail.value.resultRecord,
        "resultRecordPerson": e.detail.value.resultRecordPerson,
        "resultRecordDate": e.detail.value.resultRecordDate,
        "note": e.detail.value.note
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        if (res.code == 200) {
          console.log('add one record successfully')
          wx.navigateBack({
            delta: 1
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
      })
    }
  }
})