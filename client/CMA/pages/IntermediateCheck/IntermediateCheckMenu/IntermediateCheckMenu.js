// pages/IntermediateCheckMenu/IntermediateCheckMenu.js
Page({
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  gotoCheck(e) {
    wx.navigateTo({
      url: '../IntermediateCheck/IntermediateCheck'
    })
  },
  gotoRecord(e) {
    wx.navigateTo({
      url: '../IntermediateChecksRecord/IntermediateChecksRecord'
    })
  }
})