// pages/Equipment/EquipmentApplication/EquipmentApplication.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id": 1,
      "applicant": "ttz",
      "applicationDate": "2018-3-3",
      "applicationPurpose": "测试软件",
      "equipmentUse": 3,
      "equipmentNumber": "01A001",
      "softwareInfo": "XXX",
      "auditor": "qwl",
      "auditDate": "2018-3-4",
      "auditOpinion": "通过"
    },
    {
      "id": 2,
      "applicant": "abc",
      "applicationDate": "2019-07-06",
      "applicationPurpose": "测试机器",
      "equipmentUse": 4,
      "equipmentNumber": "003SF",
      "softwareInfo": "XXXXX",
      "auditor": "qwlwq",
      "auditDate": "2019-10-03",
      "auditOpinion": "通过"
    }]
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentApplication/EquipmentApplicationAddOne/EquipmentUseAddOne',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var myurl = app.globalData.url + 'EquipmentApplication/getAll';
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
    var that = this
    var myurl = app.globalData.url + 'EquipmentApplication/getAll';
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