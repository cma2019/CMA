
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileId:'',
    fileName:'',
    id:'',
    year:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      fileId:options.fileId,
      fileName:options.fileName,
      id:options.id,
      year:options.year
    })
  },
  SelfInspectionDocumentModifyOne: function (e) {
    console.log(e.detail.value)
    let fileName = e.detail.value.fileName
    if (fileName == null || fileName == ""){
      fileName = this.data.fileName
    }
    var id = this.data.id
    var fileId = this.data.fileId
    var myurl = app.globalData.url + 'SelfInspection/modifyOneFile?fileId='+fileId+'&fileName='+fileName+'&id='+id
    console.log('SelfInspectionDocument发生了ModifyOne事件，携带数据为：', fileName)
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        var mypath = res.tempFiles[0].path
        app.wxUploadFile(myurl, mypath, null, (res) => {
          console.log("upload file success")
          console.log(res)
          wx.showToast({
            title: '修改成功',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
              }, 1000)
            }
          })
          wx.redirectTo({
            url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + id,
          })
        }, (err) => {
          console.log(err)
        })
      },
      fail: function (err) {
        console.log("get file failed")
        wx.request({
          url: app.globalData.url + 'SelfInspection/modifyOneFormData',
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
          },
          data: {
            "id": id,
            "fileId": fileId,
            "fileName": fileName
          },
          success(res) {
            console.log(res.data)
            console.log('success modifyonefileformdata')
            wx.showToast({
              title: '修改成功',
              image: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                setTimeout(function () {
                }, 1000)
              }
            })
            wx.redirectTo({
              url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + id,
            })
          },
          fail(err) {
            console.log('fail modifyonefileformdata')
          },
          complete(fin) {
            console.log('final modifyonefileformdata')
          }
        })
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