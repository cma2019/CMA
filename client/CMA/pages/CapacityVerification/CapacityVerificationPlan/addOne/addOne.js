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
    console.log("plan")
    console.log(e.detail)
    if (e.detail.value.name == null ||
      e.detail.value.organizer == null ||
      e.detail.value.year == null ||
      e.detail.value.note == null || 
      e.detail.value.name == "" ||
      e.detail.value.organizer == "" ||
      e.detail.value.year == "" ||
      e.detail.value.note == "") {
      console.log("message error")
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
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
          wx.showToast({
            title: '添加成功',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
                wx.navigateBack({
                  delta: 1
                })
              }, 1000);
            }
          })
        }else{
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail CapacityVerificationPlan register')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
})