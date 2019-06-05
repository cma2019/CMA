// pages/Management/Management.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['0']
  },
  onChange(event) {
    this.setData({
      activeNames: event.detail
    });
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

  },
  goStaffManagement: function () {
    wx.navigateTo({
      url: '../StaffManagement/StaffManagement/StaffManagement',
    })
  },
  goTrainingApplication: function () {
    wx.navigateTo({
      url: '../TrainingManagement/TrainingApplication/TrainingApplication',
    })
  },
  gotoInterCheck:function(){
    wx.navigateTo({
      url: '../IntermediateCheck/IntermediateCheckMenu/IntermediateCheckMenu',
    })
  },
  gotoEquipment1:function(){
    wx.navigateTo({
      url: '../find/find',
    })
  },
  gotoEquipment2: function () {
    wx.navigateTo({
      url: '../find2/find2',
    })
  },
  gotoSampleManagement:function(){
    wx.navigateTo({
      url: '../SampleManagement/SampleManagementMenu/SampleManagementMenu',
    })
  }
})