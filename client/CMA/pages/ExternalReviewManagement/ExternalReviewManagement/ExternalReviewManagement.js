// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagement.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id": 1,
      "year": 2019,
      "date": "2019-7-5",
    },
    {
      "id": 2,
      "year": 2020,
      "date": "2019-7-5",
    }]
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementAddOne/ExternalReviewManagementAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var myurl = app.globalData.url + 'ExternalReviewManagement/getAll';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.data
      })
      console.log(that.data.array)
    }, (err) => {
      console.log(err)
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
    var that = this
    var myurl = app.globalData.url + 'ExternalReviewManagement/getAll';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.data
      })
      console.log(that.data.array)
    }, (err) => {
      console.log(err)
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