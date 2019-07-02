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
      leavingDate: e.detail.value
    })
  },

  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffLeaving/addOne'
      let data = {
        "id": e.detail.value.id,
        "leavingDate": e.detail.value.leavingDate
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log("data"+data)
        console.log("msg"+res.msg)
        if (res.msg =="不存在员工")
        {
          wx.showToast({
            title: 'id不存在',
            icon: 'none',
            duration: 1500
          })
        }
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