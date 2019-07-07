// pages/Equipment/EquipmentMaintenance/EquipmentMaintenance.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试用数据、模拟设备接收列表
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
  //跳转至添加页面
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceAddOne/EquipmentMaintenanceAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'EquipmentMaintenance/getAll';
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      console.log(res)
      //把接收到的数据存储到页面
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
      //失败处理函数
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
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'EquipmentMaintenance/getAll';
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      console.log(res)
      //把接收到的数据存储到页面
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
      //失败处理函数
      console.log(err)
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