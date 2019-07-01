// pages/Equipment/EquipmentApplication/EquipmentApplicationGetOne/EquipmentApplicationGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    equipment: {}
  },
  mydelete: function (e) {
    var that = this
    var myurl = app.globalData.url + 'EquipmentApplication/deleteOne/' + that.data.equipment.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentApplication/EquipmentApplication',
    })
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentApplication/EquipmentApplicationModify/EquipmentApplicationModify?id=' + that.data.equipment.id + "&applicant=" + that.data.equipment.applicant + "&applicationDate=" + that.data.equipment.applicationDate + "&applicationPurpose=" + that.data.equipment.applicationPurpose + "&equipmentUse=" + that.data.equipment.equipmentUse + "&equipmentNumber=" + that.data.equipment.equipmentNumber + "&softwareInfo=" + that.data.equipment.softwareInfo + "&auditor=" + that.data.equipment.auditor + "&auditDate=" + that.data.equipment.auditDate + "&auditOpinion=" + that.data.equipment.auditOpinion,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this;
    console.log(option)
    that.setData({
      equipment: option
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