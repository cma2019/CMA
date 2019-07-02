// pages/Equipment/EquipmentApplication/EquipmentApplicationModify/EquipmentApplicationModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

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
  mytest: function (e) {
    var that = this

    //console.log(e.detail.value)
    var myurl = app.globalData.url + 'EquipmentApplication/modifyOne/' + that.data.id;
    var mydata = {
      "applicant": e.detail.value.applicant,
      "applicationDate": e.detail.value.applicationDate,
      "applicationPurpose": e.detail.value.applicationPurpose,
      "equipmentUse": e.detail.value.equipmentUse,
      "equipmentNumber": e.detail.value.equipmentNumber,
      "softwareInfo": e.detail.value.softwareInfo,
      "auditor": e.detail.value.auditor,
      "auditDate": e.detail.value.auditDate,
      "auditOpinion": e.detail.value.auditOpinion,
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res.data)
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
  onLoad: function (option) {
    var that = this;
    console.log(option)
    that.setData({
      "id": option.id,
      "applicant": option.applicant,
      "applicationDate": option.applicationDate,
      "applicationPurpose": option.applicationPurpose,
      "equipmentUse": option.equipmentUse,
      "equipmentNumber": option.equipmentNumber,
      "softwareInfo": option.softwareInfo,
      "auditor": option.auditor,
      "auditDate": option.auditDate,
      "auditOpinion": option.auditOpinion
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