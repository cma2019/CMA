Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [{
      "id":1,
      "name":1,
      "model":1,
      "cpu":1,
      "memory":1,
      "application":1,
      "state":1
    },
    {
      "id": 2,
      "name": 2,
      "model": 2,
      "cpu": 2,
      "memory": 2,
      "application": 2,
      "state": 2
    }]
  },
  viewDetail: function(e){
    console.log(e.currentTarget.dataset)
    wx.redirectTo({
      url: '/pages/view/view?id=' + e.currentTarget.dataset.id + "&name=" + e.currentTarget.dataset.name + "&model=" + e.currentTarget.dataset.model + "&cpu=" + e.currentTarget.dataset.cpu + "&memory=" + e.currentTarget.dataset.memory + "&application=" + e.currentTarget.dataset.application + "&state=" + e.currentTarget.dataset.state,
    })
  },
  gotoAdd: function(e){
    wx.redirectTo({
      url: '/pages/new/new',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    //console.log(this.data.array)
    var that = this
    wx.request({
      url: 'http://192.168.1.115:8004/Equipment/getAll',
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
      url: 'http://192.168.1.115:8004/Equipment/getAll',
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