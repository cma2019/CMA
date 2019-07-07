// pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceGetOne/EquipmentMaintenanceGetOne.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    equipment: {}
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //跳转回查找页面
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenance',
    })
  },
  //删除按钮的处理函数
  mydelete: function (e) {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'EquipmentMaintenance/deleteOne/' + that.data.equipment.id;
    //请求后端
    app.wxRequest(myurl, 'POST', null, (res) => {
      //成功处理函数
      console.log(res)
      //成功提示
      wx.showToast({
        title: '删除成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          //延时
          setTimeout(function () {
            //跳转回查找界面
            wx.redirectTo({
              url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenance',
            })
          }, 1000);
        }
      })
    }, (err) => {
      //失败处理函数
      console.log(err)
    })
  },
  //修改按钮的处理函数
  viewDetail: function (e) {
    console.log("display -> view")
    //保存指针
    var that = this
    //配置参数并跳转至修改界面
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceModify/EquipmentMaintenanceModify?id=' + that.data.equipment.id + "&equipmentId=" + that.data.equipment.equipmentId + "&maintenanceDate=" + that.data.equipment.maintenanceDate + "&maintenanceContent=" + that.data.equipment.maintenanceContent + "&maintenancePerson=" + that.data.equipment.maintenancePerson + "&confirmer=" + that.data.equipment.confirmer,
    })
  },
  getAllById: function(e){
    console.log("getAllById")
    //保存指针
    var that = this
    //配置参数并跳转至修改界面
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentMaintenance/EquipmentMaintenanceGetAllByID/EquipmentMaintenanceGetAllByID?equipmentId=' + that.data.equipment.equipmentId,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    //保存指针
    var that = this;
    console.log(option)
    //存储页面跳转时传递的参数
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