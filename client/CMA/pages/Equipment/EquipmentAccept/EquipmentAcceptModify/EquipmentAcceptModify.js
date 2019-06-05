const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    equipment:{}
  },
  mydelete:function(e)
  {
    var that = this
    myurl = app.globalData.url + 'EquipmentReceive/deleteOne/' + that.data.equipment.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAccept',
    })
  },
  mytest: function(e){
    var that = this
    
    //console.log(e.detail.value)
    myurl = app.globalData.url + 'EquipmentReceive/modifyOne/' + that.data.equipment.id;
    mydata = {
      "name": e.detail.value.name,
      "model": e.detail.value.model,
      "manufacturer": e.detail.value.manufacturer,
      "receive_situation": e.detail.value.receive_situation,
      "recipient": e.detail.value.recipient,
      "receive_date": e.detail.value.receive_date,
      "equipment_situation": e.detail.value.equipment_situation,
      "acceptance": e.detail.value.acceptance,
      "acceptance_person": e.detail.value.acceptance_person,
      "acceptance_date": e.detail.value.acceptance_date
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res.data)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAccept',
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