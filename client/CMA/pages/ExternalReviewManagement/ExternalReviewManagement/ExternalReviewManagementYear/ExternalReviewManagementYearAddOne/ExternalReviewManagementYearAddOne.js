// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYearAddOne/ExternalReviewManagementYearAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "year": null,
    "fileId": null,
    "fileName": null,
    year:null
  },
  mygo: function (e) {
    var that = this
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
    })
  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var that = this
    var myurl1 = app.globalData.url + 'ExternalReviewDocument/addOneFormData';
    var myurl2 = app.globalData.url + 'ExternalReviewDocument/addOneFile';
    var mydata = {
      "year": e.detail.value.year
    };
    app.wxRequest(myurl1, 'POST', mydata, (res) => {
      console.log(res)
      wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success: function (res) {
          console.log("get file success")
          console.log(res)
          var mypath = res.tempFiles[0].path
          app.wxUploadFile(myurl2, mypath, null, (res) => {
            console.log("upload file success")
            console.log(res)
            wx.showToast({
              title: '上传成功',
              icon: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                setTimeout(function () {
                  wx.redirectTo({
                    url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
                  })
                }, 1000);
              }
            })
          }, (err) => {
            console.log(err)
          })
        },
        fail: function (err) {
          console.log("get file failed")
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
      year: option.year
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