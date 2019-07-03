const app = getApp()
var util = require('../../../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    createDate:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var TIME = util.formatDate(new Date())
    this.setData({
      createDate:TIME
    })
    console.log(this.data)
  },

  Supervision_addone: function (e) {
    console.log('Supervision发生了addone事件，携带数据为：', e.detail.value)
    wx.request({
      url: app.globalData.url + 'Supervision/addOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "author": e.detail.value.author,
        "remark": e.detail.value.remark,
        "createDate": this.data.createDate
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
            url: '../Supervision'
          })
        }
        else{
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
    wx.navigateBack({
      delta: 1
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