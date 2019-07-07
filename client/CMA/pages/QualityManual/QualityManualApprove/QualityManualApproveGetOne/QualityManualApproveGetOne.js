// pages/QualityManual/QualityManualApprove/QualityManualApproveGetOne/QualityManualApproveGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    manual: {}
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
    })
  },
  myapprove:function(e){
    var that = this
    var mydata = {
      state: 2
    }
    var myurl = app.globalData.url + 'QualityManual/Approve/' + that.data.manual.id
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res)
      wx.showToast({
        title: '批准成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
            })
          }, 1000);
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  myreject: function (e) {
    var that = this
    var mydata = {
      state: 1
    }
    var myurl = app.globalData.url + 'QualityManual/Approve/' + that.data.manual.id
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res)
      wx.showToast({
        title: '拒绝成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
            })
          }, 1000);
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