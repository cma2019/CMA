// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "mess": null,
    "temp":
      [{
        "fileId": 32,
        "fileName": "file1"
      },
      {
        "fileId": 33,
        "fileName": "file2"
      }]
  },

  onLoad: function (options) {
  },

  onShow: function (options) {
    let url = app.globalData.url + 'StandardManagement/getAll'
    let postdata = ''

    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      if(res.code == 200){
        this.setData({
          mess: res.data
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      console.log('standard get all success')
    }, (err) => {
      console.err('standard get all error')
    })
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'StandardManagementAddone/StandardManagementAddone',
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'StandardManagementdownload/StandardManagementdownload?id=' + target
    })
  }
})
