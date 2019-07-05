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
        "year": e.detail.value.year,
        "note": e.detail.value.note,
      }
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        if (res.code == 200) {
          console.log('add plan successfully')
          wx.navigateBack({
            delta: 1
          })
        }
      }, (err) => {
        console.log('fail CapacityVerificationPlan register')
      })
    }
  }
})