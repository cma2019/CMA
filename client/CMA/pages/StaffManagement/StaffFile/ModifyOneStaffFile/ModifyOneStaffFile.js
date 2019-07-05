// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffFile/getOne'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      
      this.setData({
        id: res.data.id,
        fileId: res.data.fileId,
        fileLocation: res.data.fileLocation
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      let url = app.globalData.url + 'StaffFile/modifyOne';
      console.log(url)
      console.log(this.data.id)
      let data = {
        //"id ":this.data.id,
        "id": e.detail.value.id,
        "fileId": e.detail.value.fileId,
        "fileLocation": e.detail.value.fileLocation
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
      }, (err) => {
        console.log('fail modify')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})