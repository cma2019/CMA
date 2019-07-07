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
    console.log('InternalAuditDocument发生了deleteOneFile事件，携带数据为：', that.data.detail.fileId)
    var myurl = app.globalData.url + 'InternalAuditManagement/deleteOneFile?fileId=' + that.data.detail.fileId
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
      console.log("aaaaaaaaaaaaa")
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocument?id=' + that.data.detail.year
    })
  },
  downloaditem: function (e) {
    var that = this
    console.log('InternalAuditDocument发生了downloadFile事件，携带数据为：', that.data.detail.fileId)
    var myurl = app.globalData.url + 'InternalAuditManagement/downloadFile/' + that.data.detail.fileId
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          myFilePath = res.savedFilePath
          console.log(myFilePath)
          wx.showToast({
            title: '下载成功',
            image: '/icons/ok/ok.png',
            duration: 2000,
            success: function () {
              setTimeout(function () {
              }, 2000)
            }
          })
        },
        fail: function (err) {
          console.log(err)
          wx.getSavedFileList({
            success:function(res){
              if(res.fileList.length > 0){
                wx.removeSavedFile({
                  filePath:res.fileList[0].filePath,
                  comlplete:function(res){
                    console.log(res)
                  }
                })
              }
            }
          })
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  modifyitem: function (e) {
    var that = this.data.detail
    wx.redirectTo({
      url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocumentModifyOne/InternalAuditDocumentModifyOne?fileId=' + that.fileId + "&fileName=" + that.fileName + "&year=" + that.year
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this
    console.log(option)
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