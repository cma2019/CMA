// pages/Equipment/EquipmentUse/EquipmentUseModify/EquipmentUseModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentUse/EquipmentUse',
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'useDate': e.detail.value
    })
  },
  bindDateChange2: function (e) {
    console.log("date2")
    console.log(e.detail.value)
    this.setData({
      'openDate': e.detail.value
    })
  },
  bindDateChange3: function (e) {
    console.log("date3")
    console.log(e.detail.value)
    this.setData({
      'closeDate': e.detail.value
    })
  },
  mytest: function (e) {
    var that = this

    //console.log(e.detail.value)
    var myurl = app.globalData.url + 'EquipmentUse/modifyOne/' + that.data.id;
    var mydata = {
      "equipmentId": e.detail.value.equipmentId,
      "useDate": e.detail.value.useDate,
      "openDate": e.detail.value.openDate,
      "closeDate": e.detail.value.closeDate,
      "sampleNumber": e.detail.value.sampleNumber,
      "testProject": e.detail.value.testProject,
      "beforeUse": e.detail.value.beforeUse,
      "afterUse": e.detail.value.afterUse,
      "user": e.detail.value.user,
      "remark": e.detail.value.remark
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res.data)
      wx.showToast({
        title: '修改成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/Equipment/EquipmentUse/EquipmentUse',
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
  onLoad: function (option) {
    var that = this;
    console.log(option)
    that.setData({
      "id": option.id,
      "equipmentId": option.equipmentId,
      "useDate": option.useDate,
      "openDate": option.openDate,
      "closeDate": option.closeDate,
      "sampleNumber": option.sampleNumber,
      "testProject": option.testProject,
      "beforeUse": option.beforeUse,
      "afterUse": option.afterUse,
      "user": option.user,
      "remark": option.remark
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