// pages/QualityManual/QualityManual/QualityManualAddOne/QualityManualAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "fileId": null,
    "fileName": null,
    "state": null,
    "current": null,
    "modifyTime": null,
    "modifier": null,
    "modifyContent": null
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'modifyTime': e.detail.value
    })
  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl1 = app.globalData.url + 'QualityManual/addOneFormData';
    var myurl2 = app.globalData.url + 'QualityManual/addOneFile';
    var mydata = {
      "state": e.detail.value.state,
      "current": e.detail.value.current,
      "modifyTime": e.detail.value.modifyTime,
      "modifier": e.detail.value.modifier,
      "modifyContent": e.detail.value.modifyContent,
    };
    app.wxRequest(myurl1,'POST',mydata,(res) => {
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
            wx.redirectTo({
              url: '/pages/QualityManual/QualityManual/QualityManual',
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
    },(err) => {
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