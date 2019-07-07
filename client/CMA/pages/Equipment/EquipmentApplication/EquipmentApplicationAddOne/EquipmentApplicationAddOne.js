// pages/Equipment/EquipmentApplication/EquipmentApplicationAddOne/EquipmentApplicationAddOne.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试用数据
  data: {
    "id": null,
    "applicant": null,
    "applicationDate": null,
    "applicationPurpose": null,
    "equipmentUse": null,
    "equipmentNumber": null,
    "softwareInfo": null,
    "auditor": null,
    "auditDate": null,
    "auditOpinion": null
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //跳转回查找页面
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentApplication/EquipmentApplication',
    })
  },
  //时间控件的处理函数
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    //跳转回查找页面
    this.setData({
      'applicationDate': e.detail.value
    })
  },
  //时间控件的处理函数
  bindDateChange2: function (e) {
    console.log("date2")
    console.log(e.detail.value)
    //跳转回查找页面
    this.setData({
      'auditDate': e.detail.value
    })
  },
  //添加按钮的处理函数
  newEquipment: function (e) {
    console.log(e.detail.value)
    //构造url
    var myurl = app.globalData.url + 'EquipmentApplication/addOne';
    //构造参数
    var mydata = {
      "applicant": e.detail.value.applicant,
      "applicationDate": e.detail.value.applicationDate,
      "applicationPurpose": e.detail.value.applicationPurpose,
      "equipmentUse": e.detail.value.equipmentUse,
      "equipmentNumber": e.detail.value.equipmentNumber,
      "softwareInfo": e.detail.value.softwareInfo,
      "auditor": e.detail.value.auditor,
      "auditDate": e.detail.value.auditDate,
      "auditOpinion": e.detail.value.auditOpinion
    };
    //请求后端
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log("add")
      console.log(res)
      //成功提示
      wx.showToast({
        title: '添加成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          //延时
          setTimeout(function () {
            //跳转回查找界面
            wx.redirectTo({
              url: '/pages/Equipment/EquipmentApplication/EquipmentApplication',
            })
          }, 1000);
        }
      })
    }, (err) => {
      //失败处理函数
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