// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "projectId": null,
  },

  onLoad: function (options) {
    this.setData({
      projectId: options.id
    })
  },
  bindDateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },

  AddNewRecord(e) {
    if (e.detail.value.date == "" ||
      e.detail.value.methodId == "" ||
      e.detail.value.equipmentName == ""||
      e.detail.value.equipmentId == "" ||
      e.detail.value.experimenter == "" ||
      e.detail.value.result == "" ||
      e.detail.value.resultDeal == "" ||
      e.detail.value.note == "") {
      console.log("message error")
    }
    else {
      let url = app.globalData.url + 'CapacityVerification/addOneRecord'
      console.log("add one record id")
      console.log(this.data.projectId)
      let data = {
        "projectId": this.data.projectId,
        "date": e.detail.value.date,
        "methodId": e.detail.value.methodId,
        "equipmentName": e.detail.value.equipmentName,
        "equipmentId": e.detail.value.equipmentId,
        "experimenter": e.detail.value.experimenter,
        "result": e.detail.value.result,
        "resultDeal": e.detail.value.resultDeal,
        "note": e.detail.value.note
      }
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('send CapacityVerificationPlan message successfully')
      }, (err) => {
        console.log('fail CapacityVerificationPlan register')
      })
    }
  }
})