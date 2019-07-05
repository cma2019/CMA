const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    detail: {}
  },
  deleteitem: function (e) {
    var that = this
    console.log('SelfInspection发生了deleteOneFile事件:', that.data.detail.fileId)
    var myurl = app.globalData.url + 'SelfInspection/deleteOneFile?fileId=' + that.data.detail.fileId
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + that.data.detail.id
    })
  },
  downloaditem: function (e) {
    var that = this
    console.log('SelfInspection发生了downloadFile事件:', that.data.detail.fileId)
    var myurl = app.globalData.url + 'SelfInspection/downloadFile/' + that.data.detail.fileId
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          myFilePath = res.savedFilePath
          console.log(myFilePath)
        },
        fail: function (err) {
          console.log(err)
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  modifyitem: function (e) {
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this
    that.setData({
      detail: option
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