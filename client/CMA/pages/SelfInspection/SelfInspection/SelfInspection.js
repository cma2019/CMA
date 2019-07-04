// pages/SelfInspection/SelfInspection/SelfInspection.js
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
    let url = app.globalData.url + 'SelfInspection/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      if (res.code == 522) {
        this.setData({
          mess: ""
        })
      }
      else {
        this.setData({
          mess: res.data,
          flag: 1
        })
        console.log(this.data.mess)
        console.log("abcdefg")
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
    let target = e.currentTarget.id
    console.log(target)
    console.log("fhsdjkhfk")
    wx.navigateTo({
      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + target  
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: '/pages/SelfInspection/SelfInspection/SelfInspectionAddOne/SelfInspectionAddOne',
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  SelfInspectionDelete: function (e) {
    let id = e.currentTarget.id
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SelfInspection/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "id": id
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '/pages/SelfInspection/SelfInspection/SelfInspection'
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