// pages/Equipment/EquipmentUse/EquipmentUseAddOne/EquipmentUseAddOne.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试用数据
  data: {
    "id": null,
    "equipmentId": null,
    "useDate": null,
    "openDate": null,
    "closeDate": null,
    "sampleNumber": null,
    "testProject": null,
    "beforeUse": null,
    "afterUse": null,
    "user": null,
    "remark": null
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //跳转回查找页面
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentUse/EquipmentUse',
    })
  },
  //时间控件的处理函数
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    //动态设置时间变量的值
    this.setData({
      'useDate': e.detail.value
    })
  },
  //时间控件的处理函数
  bindDateChange2: function (e) {
    console.log("date2")
    console.log(e.detail.value)
    //动态设置时间变量的值
    this.setData({
      'openDate': e.detail.value
    })
  },
  //时间控件的处理函数
  bindDateChange3: function (e) {
    console.log("date3")
    console.log(e.detail.value)
    //动态设置时间变量的值
    this.setData({
      'closeDate': e.detail.value
    })
  },
  //添加按钮的处理函数
  newEquipment: function (e) {
    console.log(e.detail.value)
    //构造url
    var myurl = app.globalData.url + 'EquipmentUse/add';
    //构造参数
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
    //请求后端
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      //成功处理函数
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
              url: '/pages/Equipment/EquipmentUse/EquipmentUse',
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