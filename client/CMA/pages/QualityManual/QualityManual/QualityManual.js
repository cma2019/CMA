// pages/QualityManual/QualityManual/QualityManual.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id": 1,
      "fileId": 1,
      "fileName": "1.pdf",
      "state": "0",
      "current": "0",
      "modifyTime": "2019-7-4",
      "modifier": "admin",
      "modifyContent": "test"
    },
    {
      "id": 2,
      "fileId": 2,
      "fileName": "2.pdf",
      "state": "1",
      "current": "0",
      "modifyTime": "2019-7-4",
      "modifier": "admin",
      "modifyContent": "test"
    }],
    current:{
      "id": 3,
      "fileId": 3,
      "fileName": "3.pdf",
      "state": "1",
      "current": "1",
      "modifyTime": "2019-7-4",
      "modifier": "admin",
      "modifyContent": "test"
    }
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/QualityManual/QualityManual/QualityManualAddOne/QualityManualAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var myurl1 = app.globalData.url + 'QualityManual/getAllHistory';
    var myurl2 = app.globalData.url + 'QualityManual/getCurrent';
    app.wxRequest(myurl1, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.data
      })
      console.log(that.data)
      app.wxRequest(myurl2,'GET',null,(res) => {
        console.log(res)
        that.setData({
          current:res.data
        })
        console.log(that.data) 
      },(err) => {
        console.log(err)
      })
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
    var myurl1 = app.globalData.url + 'QualityManual/getAllHistory';
    var myurl2 = app.globalData.url + 'QualityManual/getCurrent';
    app.wxRequest(myurl1, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.data
      })
      console.log(that.data)
      app.wxRequest(myurl2, 'GET', null, (res) => {
        console.log(res)
        that.setData({
          current: res.data
        })
        console.log(that.data)
      }, (err) => {
        console.log(err)
      })
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