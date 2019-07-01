const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },
  bindDateChange1(e) {
    this.setData({
      startTime: e.detail.value
    })
  },
  bindDateChange2(e) {
    this.setData({
      endTime: e.detail.value
    })
  },
  ApplicationAdd: function (e) {
{
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'AnnualTrainingPlan/addOne'
      let data = {
        "year": e.detail.value.year,
        "trainProject": e.detail.value.trainProject,
        "people": e.detail.value.people,
        "method": e.detail.value.method,
        "trainingTime": e.detail.value.trainingTime,
        "note": e.detail.value.note,
        "startTime": e.detail.value.startTime,
        "endTime":e.detail.value.endTime
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
      }, (err) => {
        console.log('fail intermediate check register')
      })

    }

  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})