const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {
    this.setData({
      year: options.id
    })
    let url = app.globalData.url + 'ManagementReview/getAllFile'
    let postdata = {
      "year": this.data.year
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        year:res.data.year
      })

      console.log(this.data.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  onShow: function (options) {

  },
  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'ManagementReview/addOneFile'
      let data = {
        "year": e.detail.value.year,
        "fileName": e.detail.value.fileName
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        wx.redirectTo({
          url: '../GetAllFileManagement/GetAllFileManagement?id='+e.detail.value.year,
        })
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