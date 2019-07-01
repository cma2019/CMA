const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },
  bindDateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },

  ApplicationAdd: function (e) {
{
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'TrainingApplication/addOne'
      let data = {
        "name": e.detail.value.name,
        "people": e.detail.value.people,
        "department": e.detail.value.department,
        "trainingUnit": e.detail.value.trainingUnit,
        "expense": e.detail.value.expense,
        "reason": e.detail.value.reason,
        "createDate": e.detail.value.date
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('send intermediate check message successfully')
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