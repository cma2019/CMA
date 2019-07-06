const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    equipment:{}
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentManagement/EquipmentManagement',
    })
  },
  mydelete:function(e)
  {
    var that = this
    var myurl = app.globalData.url + 'Equipment/deleteOne/' + that.data.equipment.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentManagement/EquipmentManagement',
    })
  },
  submit: function(e){
    var that = this
    console.log(that.data)
  },
  myChange: function(e){
    console.log("change")
    console.log(e)
  },
  mytest: function(e){
    var that = this
    
    console.log("Modify")
    console.log(e)

    var myurl = app.globalData.url + 'Equipment/modifyOne/' + that.data.equipment.id;
    var mydata = {
      "name": e.detail.value.name,
      "model": e.detail.value.model,
      "cpu": e.detail.value.cpu,
      "memory": e.detail.value.memory,
      "hardDisk": e.detail.value.hardDisk,
      "equipmentNumber": e.detail.value.equipmentNumber,
      "application": e.detail.value.application,
      "state": e.detail.value.state
    }
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res)
      wx.showToast({
        title: '修改成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/Equipment/EquipmentManagement/EquipmentManagement',
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
      equipment:option
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