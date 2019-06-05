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
    myurl = app.globalData.url + 'EquipmentReceive/deleteOne/' + that.data.equipment.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAccept',
    })
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAcceptModify/EquipmentAcceptModify?id=' + that.data.equipment.id + "&name=" + that.data.equipment.name + "&model=" + that.data.equipment.model + "&manufacturer=" + that.data.equipment.manufacturer + "&receive_situation=" + that.data.equipment.receive_situation + "&recipient=" + that.data.equipment.recipient + "&receive_date=" + that.data.equipment.receive_date + "&equipment_situation=" + that.data.equipment.equipment_situation + "&acceptance=" + that.data.equipment.acceptance + "&acceptance_person=" + that.data.equipment.acceptance_person + "&acceptance_date=" + that.data.equipment.acceptance_date,
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