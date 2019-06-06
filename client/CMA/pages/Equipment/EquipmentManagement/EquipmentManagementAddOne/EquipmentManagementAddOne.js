const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
  },
  newEquipment:function(e)
  {
    console.log(e.detail.value)
    var myurl = app.globalData.url + 'Equipment/add';
    var mydata = {
      "name": e.detail.value.name,
      "model": e.detail.value.model,
      "cpu": e.detail.value.cpu,
      "memory": e.detail.value.memory,
      "hardDisk": e.detail.value.hardDisk,
      "equipmentNumber": e.detail.value.equipmentNumber,
      "application": e.detail.value.application,
      "state": e.detail.value.state
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentManagement/EquipmentManagement',
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