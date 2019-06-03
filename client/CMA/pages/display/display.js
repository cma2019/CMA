// pages/display/display.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    equipment: {}
  },
  mydelete: function (e) {
    var that = this
    wx.request({
      url: 'http://192.168.1.108:8004/Equipment/deleteOne/' + that.data.equipment.id,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      success(res) {
        //console.log(res.data)
      },
      fail(err) {
        console.log(err)
      }
    })
    wx.redirectTo({
      url: '/pages/find/find',
    })
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/view/view?id=' + that.data.equipment.id + "&name=" + that.data.equipment.name + "&model=" + that.data.equipment.model + "&cpu=" + that.data.equipment.cpu + "&memory=" + that.data.equipment.memory + "&application=" + that.data.equipment.application + "&state=" + that.data.equipment.state,
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