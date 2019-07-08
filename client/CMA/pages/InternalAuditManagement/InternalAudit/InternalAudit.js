const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess": null,
    "flag": 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('InternalAudit发生了getAll事件，携带数据为：', options)
    let url = app.globalData.url + 'InternalAuditManagement/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
      if (res.code == 200) {
        this.setData({
          mess: res.data,
          flag: 1
        })
      }
      else {
        this.setData({
          mess: null,
          flag: 0
        })
      }
    }, (err) => {
      wx.showToast({
        title: 'getall error',
        duration: 1500
      })
      console.log('getall error')
    })
  },
  gotoFile: function (e) {
    console.log(e)
    let year = e.currentTarget.id
    console.log(year)
    console.log("fhsdjkhfk")
    wx.navigateTo({
      url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocument?id=' + year  
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: '/pages/InternalAuditManagement/InternalAudit/InternalAuditAddOne/InternalAuditAddOne',
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  InternalAuditDelete: function (e) {
    let year = e.currentTarget.id
    console.log('InternalAudit发生了deleteOne事件，携带数据为：', year)
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'InternalAuditManagement/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "year": year
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            image: '/icons/ok/ok.png',
            duration: 1000
          })
          wx.navigateTo({
            url: '/pages/InternalAuditManagement/InternalAudit/InternalAudit'
          })
        }
        else {
          wx.showToast({
            title: '删除失败',
            duration: 1500
          })
          console.log('删除失败')
        }
        wx.navigateBack({
          delta: 1
        })
      },
      fail(err) {
        console.log('fail deleteone')
      },
      complete(fin) {
        console.log('final deleteone')
      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let url = app.globalData.url + 'InternalAuditManagement/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      if (res.code == 200) {
        this.setData({
          mess: res.data,
          flag: 1
        })
      }
      else {
        this.setData({
          mess: null,
          flag: 0
        })
      }
    }, (err) => {
      wx.showToast({
        title: 'getall error',
        duration: 1500
      })
      console.log('getall error')
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})