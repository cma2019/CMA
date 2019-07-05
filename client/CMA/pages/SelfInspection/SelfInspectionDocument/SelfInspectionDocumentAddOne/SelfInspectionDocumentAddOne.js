const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "fileId": null,
    "fileName": null,
  },
  SelfInspectionDocumentAddOne: function (e) {
    console.log(e.detail.value)
    var myurl1 = app.globalData.url + 'SelfInspection/addOneFormData';
    var myurl2 = app.globalData.url + 'SelfInspection/addOneFile';
    var mydata = {
      "id": this.data.id,
      "fileName": e.detail.value.fileName //大于等于4位
    };
    var id = this.data.id
    console.log('SelfInspectionDocument发生了AddOne事件:', mydata)
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
              url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id='+id,
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
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id:options.id
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