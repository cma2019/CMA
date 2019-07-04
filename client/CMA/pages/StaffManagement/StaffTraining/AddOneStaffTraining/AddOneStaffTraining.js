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
      trainingDate: e.detail.value
    })
  },
  StaffAdd: function (e) {

    if (e.detail.value.program == ""
      || e.detail.value.trainingDate == ""
      || e.detail.value.place == "" || e.detail.value.presenter == ""
      ) {
      wx.showToast({
        title: '错误(空白输入)',
        icon: 'none',
        duration: 2000
      })
      console.log('错误(空白输入)')
    }
    else {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffTraining/addOne'
      let data = {
        "program": e.detail.value.program,
        "trainingDate": e.detail.value.trainingDate,
        "place": e.detail.value.place,
        "presenter": e.detail.value.presenter,
        "content": e.detail.value.content,
        "note": e.detail.value.note
       // "file": e.detail.value.file
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('send intermediate check message successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        wx.redirectTo({
          url: '/pages/StaffManagement/StaffTraining/StaffTraining',
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