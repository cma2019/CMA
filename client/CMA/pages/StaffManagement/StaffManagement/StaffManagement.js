// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    "mess": null
  },

  onLoad: function (options) {

  },
  onShow: function (options) {
    //console.log(this.data.planId)
    let url = app.globalData.url + 'StaffManagement/getAll'
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
      
      console.log('getone error')
    })
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'AddStaff/AddStaff',
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'PrintOneStaff/PrintOneStaff?id=' + target
    })
  }
})