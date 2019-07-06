// pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceAddOne/EquipmentMaintenanceAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "equipmentId": null,
    "maintenanceDate": null,
    "maintenanceContent": null,
    "maintenancePerson": null,
    "confirmer": null
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenance',
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'maintenanceDate': e.detail.value
    })
  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl = app.globalData.url + 'EquipmentMaintenance/addOne';
    var mydata = {
      "equipmentId": e.detail.value.equipmentId,
      "maintenanceDate": e.detail.value.maintenanceDate,
      "maintenanceContent": e.detail.value.maintenanceContent,
      "maintenancePerson": e.detail.value.maintenancePerson,
      "confirmer": e.detail.value.confirmer
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log("add")
      console.log(res)
      wx.showToast({
        title: '添加成功',
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