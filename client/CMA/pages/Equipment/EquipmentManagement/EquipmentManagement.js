
const app = getApp()
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
      "hardDisk":1,
      "equipmentNumber":1,
      "application":1,
      "state":1
    },
    {
      "id": 2,
      "name": 2,
      "model": 2,
      "cpu": 2,
      "memory": 2,
      "hardDisk": 2,
      "equipmentNumber": 2,
      "application": 2,
      "state": 2
    }]
  },
  gotoAdd: function(e){
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentManagement/EquipmentManagementAddOne/EquipmentManagementAddOne',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    //console.log(this.data.array)
    var that = this
    var myurl = app.globalData.url + 'Equipment/getAll';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
      console.log(err)
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
    var myurl = app.globalData.url + 'Equipment/getAll';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
      console.log(err)
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