// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    "mess": null
  },

  onLoad: function (options) {
    //console.log(this.data.planId)
    this.setData({
      id: options.id
    })
    
  },
  onShow: function () {
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffAuthorization/GetAllByStaff'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        mess: res.data
      })

    /*  wx.showToast({
        title: '成功',
        //icon: 'success',
        image: '/icons/ok/ok.png',
        duration: '1000',
        success: function () {
          setTimeout(function () {
            wx.navigateTo({
              url: '../StaffAuthorization',
            })
          }, 1000);
        }
      })*/
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: '失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
      console.log('getone error')
    })
  },

  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})