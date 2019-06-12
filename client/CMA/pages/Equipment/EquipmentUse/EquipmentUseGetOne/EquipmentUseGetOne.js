// pages/Equipment/EquipmentUse/EquipmentUseGetOne/EquipmentUseGetOne.js
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
    var myurl = app.globalData.url + 'EquipmentUse/deleteOne/' + that.data.equipment.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentUse/EquipmentUse',
    })
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentUse/EquipmentUseModify/EquipmentUseModify?id=' + that.data.equipment.id + "&equipmentId=" + that.data.equipment.equipmentId + "&useDate=" + that.data.equipment.useDate + "&openDate=" + that.data.equipment.openDate + "&closeDate=" + that.data.equipment.closeDate + "&sampleNumber=" + that.data.equipment.sampleNumber + "&testProject=" + that.data.equipment.testProject + "&beforeUse=" + that.data.equipment.beforeUse + "&afterUse=" + that.data.equipment.afterUse + "&user=" + that.data.equipment.user + "&remark=" + that.data.equipment.remark,
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