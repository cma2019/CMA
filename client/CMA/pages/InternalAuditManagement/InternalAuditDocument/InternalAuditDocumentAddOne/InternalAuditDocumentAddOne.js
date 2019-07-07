const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "year": null,
    "fileId": null,
    "fileName": null,
  },
  InternalAuditDocumentAddOne: function (e) {
    console.log(e.detail.value)
    if (e.detail.value.fileName == null || e.detail.value.fileName == '') {
      wx.showToast({
        title: '文件名称为空',
        image: '/icons/warning/warning.png',
        duration: 2000,
        success: function () {
          setTimeout(function () {
          }, 2000)
        }
      })
    }
    else{
      var myurl = app.globalData.url + 'InternalAuditManagement/addOneFile?year='+this.data.year+'&fileName='+e.detail.value.fileName;
      var year = this.data.year
      console.log('InternalAuditDocument发生了addone事件，携带数据为：', e.detail.value.fileName)
      wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success: function (res) {
          console.log("get file success")
          console.log(res)
          var mypath = res.tempFiles[0].path
          app.wxUploadFile(myurl, mypath, null, (res) => {
            console.log("upload file success")
            wx.showToast({
              title: '上传成功',
              image: '/icons/ok/ok.png',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                }, 2000)
              }
            })
            wx.redirectTo({
              url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocument?id=' + year,
            })
          }, (err) => {
            console.log(err)
          })
        },
        fail: function (err) {
          console.log("get file failed")
        }
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      year:options.id
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