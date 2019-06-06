// pages/Equipment/EquipmentUse/EquipmentUse.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array : [{
      "id": 1,
      "equipmentId": 1,
      "useDate": "2018-3-3",
      "openDate": "11:12:22",
      "clostDate": "12:30:22",
      "sampleNumber": "A002",
      "testProject": "负载测试",
      "beforeUse": "使用正常",
      "afterUse": "使用正常",
      "user": "张婷",
      "remark": "无"
      },
      {
        "id": 2,
        "equipmentId": 1,
        "useDate": "2018-3-4",
        "openDate": "13:12:22",
        "clostDate": "14:30:22",
        "sampleNumber": "G002",
        "testProject": "穿越测试",
        "beforeUse": "使用正常",
        "afterUse": "使用正常",
        "user": "张婷婷",
        "remark": "无"
      }]
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentUse/EquipmentUseAddOne/EquipmentUseAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var myurl = app.globalData.url + 'EquipmentUse/getAll';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.Equipments
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
    var that = this
    var myurl = app.globalData.url + 'EquipmentUse/getAll';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
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