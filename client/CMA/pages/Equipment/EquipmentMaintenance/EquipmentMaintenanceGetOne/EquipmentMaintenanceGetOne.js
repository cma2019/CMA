// pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceGetOne/EquipmentMaintenanceGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    equipment: {}
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenance',
    })
  },
  mydelete: function (e) {
    var that = this
    var myurl = app.globalData.url + 'EquipmentMaintenance/deleteOne/' + that.data.equipment.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
      wx.showToast({
        title: '删除成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenance',
            })
          }, 1000);
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceModify/EquipmentMaintenanceModify?id=' + that.data.equipment.id + "&equipmentId=" + that.data.equipment.equipmentId + "&maintenanceDate=" + that.data.equipment.maintenanceDate + "&maintenanceContent=" + that.data.equipment.maintenanceContent + "&maintenancePerson=" + that.data.equipment.maintenancePerson + "&confirmer=" + that.data.equipment.confirmer,
    })
  },
  getAllById: function(e){
    console.log("getAllById")
    var that = this
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceGetAllByID/EquipmentMaintenanceGetAllByID?equipmentId=' + that.data.equipment.equipmentId,
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