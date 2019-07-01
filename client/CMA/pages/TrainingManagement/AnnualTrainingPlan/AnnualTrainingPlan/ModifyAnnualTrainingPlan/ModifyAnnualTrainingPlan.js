// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'AnnualTrainingPlan/getOne'
    let postdata = {
      "planId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      console.log(res.data.trainProject)
      this.setData({
        planId: res.data.planId,
        trainProject: res.data.trainProject,
        people: res.data.people,
        method: res.data.method,
        trainingTime: res.data.trainingTime,
        startTime: res.data.startTime,
        endTime: res.data.endTime,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  bindDateChange1: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      startTime: e.detail.value
    })
  },
  bindDateChange2: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      endTime: e.detail.value
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      let url = app.globalData.url + 'AnnualTrainingPlan/modifyOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "planId": e.detail.value.planId,
        "trainProject": e.detail.value.trainProject,
        "people": e.detail.value.people,
        "method": e.detail.value.method,
        "trainingTime": e.detail.value.trainingTime,
        "startTime": e.detail.value.startTime,
        "endTime": e.detail.value.endTime,
        "note": e.detail.value.note,


      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        /*
        if (res.data == "modify successfully.") {
          wx.navigateBack({
            delta: 1
          })
        }
        */
      }, (err) => {
        console.log('fail modify')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})