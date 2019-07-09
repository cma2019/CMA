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
    console.log('SelfInspection发生了getAll事件，携带数据为：', options)
    let url = app.globalData.url + 'SelfInspection/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
      if (res.code == 200) {
        this.setData({
          mess: res.data,
          flag: 1
        })
        console.log("SelfInspection-getAll成功")
      }
      else { //210
        this.setData({
          mess: null,
          flag: 0
        })
        console.log("SelfInspection-getAll无有效信息")
      }
    }, (err) => {
      wx.showToast({
        title: 'SelfInspection-getAll error',
        duration: 1500
      })
      console.log('SelfInspection-getAll error')
    })
  },
  gotoFile: function (e) {
    let id = e.currentTarget.id
    wx.navigateTo({
      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + id  
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
    console.log('SelfInspection发生了deleteOne事件:', id)
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
            image: '/icons/ok/ok.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
                wx.redirectTo({
                  url: '/pages/SelfInspection/SelfInspection/SelfInspection'
                })
              }, 500)
            }
          })
        }
        else {
          wx.showToast({
            title: '删除失败',
            image: '/icons/warning/warning.png',
            duration: 500
          })
          console.log('删除失败')
        }
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