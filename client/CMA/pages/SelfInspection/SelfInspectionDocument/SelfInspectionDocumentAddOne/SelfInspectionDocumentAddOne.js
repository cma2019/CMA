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
    if(e.detail.value.fileName == null||e.detail.value.fileName == ''){
      wx.showToast({
        title:'文件名称为空',
        image:'/icons/warning/warning.png',
        duration:2000,
        success:function(){
          setTimeout(function(){
          },2000)
        }
      })
    }
    else{
      var myurl = app.globalData.url + 'SelfInspection/addOneFile?id='+this.data.id+'&fileName='+e.detail.value.fileName
      var id = this.data.id
      console.log('SelfInspectionDocument发生了AddOne事件，携带数据为：', e.detail.value.fileName)
      wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success(res) {
          console.log("get file success")
          console.log(res)
          var mypath = res.tempFiles[0].path
          app.wxUploadFile(myurl, mypath, null, (res) => {
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
    }
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