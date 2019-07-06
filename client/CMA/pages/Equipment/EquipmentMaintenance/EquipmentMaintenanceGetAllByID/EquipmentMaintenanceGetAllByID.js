// pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceGetAllByID/EquipmentMaintenanceGetAllByID.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id": 1,
      "equipmentId": 1,
      "maintenanceDate": "2019-7-1",
      "maintenanceContent": "清理磁盘",
      "maintenancePerson": "admin",
      "confirmer": "confirmer"
    },
    {
      "id": 2,
      "equipmentId": 2,
      "maintenanceDate": "2019-6-30",
      "maintenanceContent": "清理内存",
      "maintenancePerson": "admin",
      "confirmer": "confirmer"
    }]
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenance',
    })
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceAddOne/EquipmentMaintenanceAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this
    var myurl = app.globalData.url + 'EquipmentMaintenance/getAllByEquipmentId/' + option.equipmentId;
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.data
      })
      console.log(that.data.array)
    }, (err) => {
      console.log(err)
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