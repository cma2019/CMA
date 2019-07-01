// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },

  bindDateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },

  AddNewPlan(e) {
    if (e.detail.value.object == "" ||
      e.detail.value.date == "" ||
      e.detail.value.content == "" ||
      e.detail.value.personInCharge == "") {
      console.log("message error")
    }
    else {
      let url = app.globalData.url + 'CapacityVerification/addOne'
      let data = {
        "name": e.detail.value.name,
        "organizer": e.detail.value.organizer,
        "state": e.detail.value.state,
        "year": e.detail.value.year,
        "note": e.detail.value.note,
        "analysis": e.detail.value.analysis
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