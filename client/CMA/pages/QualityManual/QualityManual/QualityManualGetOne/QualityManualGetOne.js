// pages/QualityManual/QualityManual/QualityManualGetOne/QualityManualGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    manual:{}
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/QualityManual/QualityManual/QualityManual',
    })
  },
  mydelete: function (e) {
    var that = this
    var myurl = app.globalData.url + 'QualityManual/deleteOne/' + that.data.manual.id;
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      wx.showToast({
        title: '删除成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/QualityManual/QualityManual/QualityManual',
            })
          }, 1000);
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  mydownload:function(e){
    var that = this
    var myurl = app.globalData.url + 'QualityManual/getFileById/' + that.data.manual.id;
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          myFilePath = res.savedFilePath
          console.log(myFilePath)
          wx.showToast({
            title: '下载成功',
            icon: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
              }, 1000);
            }
          })
        },
        fail: function (err) {
          console.log(err)
        }
      })
    }, (err) => {
      console.log(err)
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this;
    console.log(option)
    that.setData({
      manual: option
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