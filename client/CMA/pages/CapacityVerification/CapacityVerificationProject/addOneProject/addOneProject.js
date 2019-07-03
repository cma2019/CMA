// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({
  
  data: {
    "planId": null,
  },

  onLoad: function (options) {
    this.setData({
      planId: options.id
    })
  },

  AddNewProject(e) {
    if (e.detail.value.name == "" ||
      e.detail.value.method == "" ||
      e.detail.value.note == "") {
      console.log("message error")
    }
    else {
      let url = app.globalData.url + 'CapacityVerification/addOneProject'
      console.log("add one project id")
      console.log(this.data.planId)
      let data = {
        "planId": this.data.planId,
        "name": e.detail.value.name,
        "method": e.detail.value.method,
        "note": e.detail.value.note,
      }
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        if (res.code == 200) {
          console.log('add one project successfully')
          wx.navigateBack({
            delta: 1
          })
        }      }, (err) => {
        console.log('fail CapacityVerificationPlan register')
      })
    }
  }
})