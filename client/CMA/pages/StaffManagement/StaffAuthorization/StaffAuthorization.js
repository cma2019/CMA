// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    "mess": null
  },

  onLoad: function (options) {
    //console.log(this.data.planId)
    
  },
  onShow: function () {
    let url = app.globalData.url + 'StaffAuthorization/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      //console.log('success')
      console.log(res)
      console.log('success')
      console.log(res.code)
      //console.log(res.msg)
      console.log(res.data)
      //var temp = res.data
      //this.temp = temp
      this.setData({
        mess: res.data
      })

      console.log(this.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'AddOneStaffAuthorization/AddOneStaffAuthorization',
    })
  },
  ApplicationAdd: function (e) {
    if (e.detail.value.id == null) {
      /*wx.showToast({
        title: '错误(空白输入)',
        icon: 'none',
        duration: 2000
      })*/
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 1000
      })

      console.log('错误(空白输入)')
    }
    else {
      console.log(e)
      let target = e.detail.value.id
      console.log(e.detail.value)
      console.log('getall id')
      console.log(target)
      wx.navigateTo({
        url: 'GetAllByStaff/GetAllByStaff?id=' + target
      })
    }
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id//??id/planId
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'GetOneStaffAuthorization/GetOneStaffAuthorization?id=' + target
    })
  }
})