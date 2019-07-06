const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAccept',
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'receiveDate': e.detail.value
    })
  },
  bindDateChange2: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      'acceptanceDate': e.detail.value
    })
  },
  mydelete:function(e)
  {
    var that = this
    var myurl = app.globalData.url + 'EquipmentReceive/deleteOne/' + that.data.id;
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
    var myurl = app.globalData.url + 'EquipmentReceive/modifyOne/' + that.data.id;
    var mydata = {
      "name": e.detail.value.name,
      "model": e.detail.value.model,
      "manufacturer": e.detail.value.manufacturer,
      "receiveSituation": e.detail.value.receiveSituation,
      "recipient": e.detail.value.recipient,
      "receiveDate": e.detail.value.receiveDate,
      "equipmentSituation": e.detail.value.equipmentSituation,
      "acceptance": e.detail.value.acceptance,
      "acceptancePerson": e.detail.value.acceptancePerson,
      "acceptanceDate": e.detail.value.acceptanceDate
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
              url: '/pages/Equipment/EquipmentAccept/EquipmentAccept',
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
      "name": option.name,
      "model": option.model,
      "manufacturer": option.manufacturer,
      "receiveSituation": option.receiveSituation,
      "recipient": option.recipient,
      "receiveDate": option.receiveDate,
      "equipmentSituation": option.equipmentSituation,
      "acceptance": option.acceptance,
      "acceptancePerson": option.acceptancePerson,
      "acceptanceDate": option.acceptanceDate
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