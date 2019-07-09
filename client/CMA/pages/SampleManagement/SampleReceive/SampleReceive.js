const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess": null,
    "flag":0 //0:database empty;  1:database not empty
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    let url = app.globalData.url + 'SampleReceive/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        this.setData({
          mess: res.data,
          flag: 1
        })
        console.log("SampleReceive-getAll成功")
      }
      else { //210
        this.setData({
          mess: null,
          flag: 0
        })
        console.log("SampleReceive-getAll无有效信息")
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
    let id = e.currentTarget.id
    wx.navigateTo({
      url: 'receiveGetOne/receiveGetOne?id=' + id
    })
  },
  gotoAdd: function () {
    wx.redirectTo({
      url: 'receiveAddOne/receiveAddOne',
    })
  }
})