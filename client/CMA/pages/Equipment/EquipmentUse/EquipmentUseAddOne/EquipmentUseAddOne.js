// pages/Equipment/EquipmentUse/EquipmentUseAddOne/EquipmentUseAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "equipmentId": null,
    "useDate": null,
    "openDate": null,
    "closeDate": null,
    "sampleNumber": null,
    "testProject": null,
    "beforeUse": null,
    "afterUse": null,
    "user": null,
    "remark": null
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'useDate': e.detail.value
    })
  },
  bindDateChange2: function (e) {
    console.log("date2")
    console.log(e.detail.value)
    this.setData({
      'openDate': e.detail.value
    })
  },
  bindDateChange3: function (e) {
    console.log("date3")
    console.log(e.detail.value)
    this.setData({
      'closeDate': e.detail.value
    })
  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl = app.globalData.url + 'EquipmentUse/add';
    var mydata = {
      "equipmentId": e.detail.value.equipmentId,
      "useDate": e.detail.value.useDate,
      "openDate": e.detail.value.openDate,
      "closeDate": e.detail.value.closeDate,
      "sampleNumber": e.detail.value.sampleNumber,
      "testProject": e.detail.value.testProject,
      "beforeUse": e.detail.value.beforeUse,
      "afterUse": e.detail.value.afterUse,
      "user": e.detail.value.user,
      "remark": e.detail.value.remark
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log("add")
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentUse/EquipmentUse',
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