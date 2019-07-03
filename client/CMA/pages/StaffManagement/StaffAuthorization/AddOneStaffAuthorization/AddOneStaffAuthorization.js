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
      authorizerDate: e.detail.value
    })
  },

  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffAuthorization/addOne'
      let data = {
        "id": e.detail.value.id,
        "authorizerId": e.detail.value.authorizerId,
        "content": e.detail.value.content,
        "authorizerDate": e.detail.value.authorizerDate
      }
      console.log(url)
      console.log(data)
      if(id==""||authorizerId==""||content==""||authorizerDate=="")
      {
        wx.showToast({
          title: '错误，空白输入',
          icon:'none',
          duration:'2000'
        })
      }
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        wx.redirectTo({
          url: '../StaffAuthorization',
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