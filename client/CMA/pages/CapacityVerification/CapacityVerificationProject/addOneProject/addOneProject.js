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