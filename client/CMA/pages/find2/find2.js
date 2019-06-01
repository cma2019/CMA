Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id":1,
      "name": 1,
      "model": 1,
      "manufacturer": 1,
      "receive_situation": 1,
      "recipient": 1,
      "receive_date": 1,
      "equipment_situation": 1,
      "acceptance": 1,
      "acceptance_person": 1,
      "acceptance_date": 1
    },
    {
      "id": 2,
      "name": 2,
      "model": 2,
      "manufacturer": 2,
      "receive_situation": 2,
      "recipient": 2,
      "receive_date": 2,
      "equipment_situation": 2,
      "acceptance": 2,
      "acceptance_person": 2,
      "acceptance_date": 2
    }]
  },
  viewDetail: function(e){
    console.log(e.currentTarget.dataset)
    wx.redirectTo({
      url: '/pages/view2/view2?id=' + e.currentTarget.dataset.id + "&name=" + e.currentTarget.dataset.name + "&model=" + e.currentTarget.dataset.model + "&manufacturer=" + e.currentTarget.dataset.manufacturer + "&receive_situation=" + e.currentTarget.dataset.receive_situation + "&recipient=" + e.currentTarget.dataset.recipient + "&receive_date=" + e.currentTarget.dataset.receive_date + "&equipment_situation=" + e.currentTarget.dataset.equipment_situation + "&acceptance=" + e.currentTarget.dataset.acceptance + "&acceptance_person=" + e.currentTarget.dataset.acceptance_person + "&acceptance_date=" + e.currentTarget.dataset.acceptance_date,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    //console.log(this.data.array)
    var that = this
    wx.request({
      url: 'http://192.168.1.113:8004/EquipmentReceive/getAll',
      method:'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      success(res){
        //console.log(res)
        //console.log(res.data.data.Equipments)
        that.setData({
          array: res.data.data.Equipments
        })
        console.log(that.data.array)
      },
      fail(err) {
        console.log(err)
      }
    })
    //console.log(that.data.array)
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
    wx.request({
      url: 'http://192.168.1.113:8004/EquipmentReceive/getAll',
      method: 'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      success(res) {
        //console.log(res)
        //console.log(res.data.data.Equipments)
        that.setData({
          array: res.data.data.Equipments
        })
        console.log(that.data.array)
      },
      fail(err) {
        console.log(err)
      }
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