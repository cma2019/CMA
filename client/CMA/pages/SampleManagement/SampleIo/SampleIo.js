const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess":null,
    "flag":0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    let url = app.globalData.url + 'SampleIo/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      if(res.code == 522){
        this.setData({
          mess : ""
        })
      }
      else{
        this.setData({
          mess: res.data,
          flag: 1
        })
        console.log('success')
      }
    }, (err) => {
      wx.showToast({
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  gotoOne: function (e) {
    console.log(e)
    let target = e.currentTarget.id
    wx.navigateTo({
      url: 'ioGetOne/ioGetOne?id=' + target
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'ioAddOne/ioAddOne',
    })
  }
})