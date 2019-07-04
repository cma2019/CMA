// pages/QualityManual/QualityManualApprove/QualityManualApproveGetOne/QualityManualApproveGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    manual: {}
  },
  myapprove:function(e){
    var that = this
    var mydata = {
      id: that.data.manual.id,
      state: 2
    }
    var myurl = app.globalData.url + 'QualityManual/approve'
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
    })
  },
  myreject: function (e) {
    var that = this
    var mydata = {
      id: that.data.manual.id,
      state: 1
    }
    var myurl = app.globalData.url + 'QualityManual/approve'
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
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