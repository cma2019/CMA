// pages/Supervision/SupervisionPlan/supervisionPlanAddOne/supervisionPlanAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id:options.id
    })
    console.log(this.data.id)
    console.log("fsdgfd")
  },
  SupervisionPlan_addone:function(e){
    console.log('SupervisionPlan发生了addone事件，携带数据为：', e.detail.value)
    let id = this.data.id
    wx.request({
      url: app.globalData.url + 'SupervisionPlan/addOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "id":id,
        "content": e.detail.value.content,
        "object": e.detail.value.object,
        "dateFrequency": e.detail.value.dateFrequency
      },
      success(res) {
        console.log(res.data)
        if (res.data.code == 200) {
          wx.showToast({
            title: '成功',
            icon: 'none',
            duration: 1500
          })
          wx.navigateTo({
            url: '/pages/Supervision/Supervision/supervisionGetOne/supervisionGetOne?id='+id
          })
        }
        else if(res.data.code == 513){
          wx.showToast({
            title: '某项数据错误',
            duration: 1500
          })
        }
        else {
          wx.showToast({
            title: '添加失败',
            duration: 1500
          })
          console.log('添加失败')
        }
      },
      fail(err) {
        console.log('fail addone')
      },
      complete(fin) {
        console.log('final addone')
      }
    })
  },
  goback: function () {
    wx.navigateTo({
      url: '/pages/Supervision/Supervision/Supervision'
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