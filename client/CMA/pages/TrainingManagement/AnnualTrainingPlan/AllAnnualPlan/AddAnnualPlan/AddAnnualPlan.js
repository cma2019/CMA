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
      createDate: e.detail.value
    })
  },
  
  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'AnnualTrainingPlan/addAnnualPlan'
      let data = {
        "year": e.detail.value.year,
        "author": e.detail.value.author,
        "createDate": e.detail.value.createDate
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