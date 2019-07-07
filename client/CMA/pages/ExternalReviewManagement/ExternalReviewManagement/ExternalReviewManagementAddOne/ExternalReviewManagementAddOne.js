// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementAddOne/ExternalReviewManagementAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "year": null,
    "date": null
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagement',
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'date': e.detail.value
    })
  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl = app.globalData.url + 'ExternalReviewManagement/addOne';
    var mydata = {
      "year": e.detail.value.year,
      "date": e.detail.value.date
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log("add")
      console.log(res)
      wx.showToast({
        title: '添加成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagement',
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
  onLoad: function (options) {

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