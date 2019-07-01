// pages/Equipment/EquipmentApplication/EquipmentApplicationAddOne/EquipmentApplicationAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "applicant": null,
    "applicationDate": null,
    "applicationPurpose": null,
    "equipmentUse": null,
    "equipmentNumber": null,
    "softwareInfo": null,
    "auditor": null,
    "auditDate": null,
    "auditOpinion": null
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'applicationDate': e.detail.value
    })
  },
  bindDateChange2: function (e) {
    console.log("date2")
    console.log(e.detail.value)
    this.setData({
      'auditDate': e.detail.value
    })
  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl = app.globalData.url + 'EquipmentApplication/add';
    var mydata = {
      "applicant": e.detail.value.applicant,
      "applicationDate": e.detail.value.applicationDate,
      "applicationPurpose": e.detail.value.applicationPurpose,
      "equipmentUse": e.detail.value.equipmentUse,
      "equipmentNumber": e.detail.value.equipmentNumber,
      "softwareInfo": e.detail.value.softwareInfo,
      "auditor": e.detail.value.auditor,
      "auditDate": e.detail.value.auditDate,
      "auditOpinion": e.detail.value.auditOpinion
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log("add")
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentApplication/EquipmentApplication',
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