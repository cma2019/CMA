const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    recordId:'',
    planId: '',
    department: '',
    supervisor: '',
    superviseDate: '',
    supervisedPerson: '',
    record: '',
    conclusion: '',
    operator: '',
    recordDate: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      recordId: options.recordId,
      planId: options.planId,
      department: options.department,
      supervisor: options.supervisor,
      superviseDate: options.superviseDate,
      supervisedPerson: options.supervisedPerson,
      record: options.record,
      conclusion: options.conclusion,
      operator: options.operator,
      recordDate: options.recordDate
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
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
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