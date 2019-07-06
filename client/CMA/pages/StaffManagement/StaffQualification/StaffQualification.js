// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    mess: []
  },

  onLoad: function (options) {
   
  },
  onShow: function (options) {
    let url = app.globalData.url + 'StaffQualification/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        mess: res.data
      })

      console.log(this.data.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: '失败!',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
      console.log('getone error')
    })
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'AddOneStaffQualification/AddOneStaffQualification',
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'PrintOneStaffQualification/PrintOneStaffQualification?id=' + target
    })
  }
})