// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYearGetOne/ExternalReviewManagementYearGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:1,
    year:2019,
    fileId:1,
    fileName:"1.pdf"
  },
  mydelete: function (e) {
    var that = this
    var myurl = app.globalData.url + 'ExternalReviewDocument/deleteOne/' + that.data.id;
    app.wxRequest(myurl, 'POST', null, (res) => {
      console.log(res)
    }, (err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
    })
  },
  viewDetail: function (e) {
    var that = this
    console.log(that.data)
    var myurl1 = app.globalData.url + 'ExternalReviewDocument/modifyFormData/' + that.data.id;
    var myurl2 = app.globalData.url + 'ExternalReviewDocument/modifyFile/';
    var mydata = {
      "year": that.data.year,
      "fileId": that.data.fileId
    };
    app.wxRequest(myurl1, 'POST', mydata, (res) => {
      console.log(res)
      wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success: function (res) {
          console.log("get file success")
          console.log(res)
          var mypath = res.tempFiles[0].path
          app.wxUploadFile(myurl2, mypath, null, (res) => {
            console.log("upload file success")
            console.log(res)
            wx.redirectTo({
              url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
            })
          }, (err) => {
            console.log(err)
          })
        },
        fail: function (err) {
          console.log("get file failed")
          console.log(err)
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  mydownload: function (e) {
    var that = this
    var myurl = app.globalData.url + 'ExternalReviewDocument/downloadFile/' + that.data.id;
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this;
    console.log(option)
    that.setData({
      id: option.id,
      year: option.year,
      fileId: option.fileId,
      fileName: option.fileName
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