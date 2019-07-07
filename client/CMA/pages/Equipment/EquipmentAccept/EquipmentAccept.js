//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试用数据、模拟设备接收列表
  data: {
    array: [{
      "id":1,
      "name": 1,
      "model": 1,
      "manufacturer": 1,
      "receiveSituation": 1,
      "recipient": 1,
      "receiveDate": 1,
      "equipmentSituation": 1,
      "acceptance": 1,
      "acceptancePerson": 1,
      "acceptanceDate": 1
    },
    {
      "id": 2,
      "name": 2,
      "model": 2,
      "manufacturer": 2,
      "receiveSituation": 2,
      "recipient": 2,
      "receive_date": 2,
      "equipmentSituation": 2,
      "acceptance": 2,
      "acceptancePerson": 2,
      "acceptanceDate": 2
    }]
  },
  //跳转至GetOne，查看某次特定的设备接收记录
  viewDetail: function(e){
    console.log(e.currentTarget.dataset)
    //配置参数并跳转
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAcceptGetOne/EquipmentAcceptGetOne2?id=' + e.currentTarget.dataset.id + "&name=" + e.currentTarget.dataset.name + "&model=" + e.currentTarget.dataset.model + "&manufacturer=" + e.currentTarget.dataset.manufacturer + "&receiveSituation=" + e.currentTarget.dataset.receiveSituation + "&recipient=" + e.currentTarget.dataset.recipient + "&receiveDate=" + e.currentTarget.dataset.receiveDate + "&equipmentSituation=" + e.currentTarget.dataset.equipmentSituation + "&acceptance=" + e.currentTarget.dataset.acceptance + "&acceptancePerson=" + e.currentTarget.dataset.acceptancePerson + "&acceptanceDate=" + e.currentTarget.dataset.acceptanceDate,
    })
  },
  //跳转至添加页面
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/Equipment/EquipmentAccept/EquipmentAcceptAddOne/EquipmentAcceptAddOne',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'EquipmentReceive/getAll';
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      console.log(res)
      //把接收到的数据存储到页面
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
      //失败处理函数
      console.log(err)
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
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'EquipmentReceive/getAll';
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      //把接收到的数据存储到页面
      that.setData({
        array: res.data.Equipments
      })
      console.log(that.data.array)
    }, (err) => {
      //失败处理函数
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