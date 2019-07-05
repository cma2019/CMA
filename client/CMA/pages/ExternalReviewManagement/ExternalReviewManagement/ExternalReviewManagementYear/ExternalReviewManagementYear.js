// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id": 1,
      "year": 2019,
      "fileId": "1",
      "fileName": "1.pdf"
    },
    {
      "id": 2,
      "year": 2020,
      "fileId": "2",
      "fileName": "2.pdf"
    }]
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYearAddOne/ExternalReviewManagementYearAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this
    var myurl = app.globalData.url + 'ExternalReviewManagement/getAllFile/' + option.year;
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
  onShow: function (option) {
    var that = this
    var myurl = app.globalData.url + 'ExternalReviewManagement/getAllFile/' + option.year;
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