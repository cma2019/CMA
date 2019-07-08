
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileId: '',
    fileName: '',
    year: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      fileId: options.fileId,
      fileName: options.fileName,
      year: options.year
    })
  },
  InternalAuditDocumentModifyOne: function (e) {
    console.log(e.detail.value)
    let fileName = e.detail.value.fileName
    if (fileName == null || fileName == "") {
      fileName = this.data.fileName
    }
    var year = this.data.year
    var fileId = this.data.fileId
    var myurl = app.globalData.url + 'InternalAuditManagement/modifyOneFile?year='+year+'&fileId='+fileId+'&fileName='+fileName
    console.log('InternalAuditDocument发生了ModifyOne事件，携带数据为：', fileName)
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
            title: '修改成功',
            image: '/icons/ok/ok.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
                wx.redirectTo({
                  url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocument?id=' + year,
                })
              }, 300)
            }
          })
        }, (err) => {
          console.log(err)
        })
      },
      fail: function (err) {
        console.log("get file failed")
        if(e.detail.value.fileName != ''&e.detail.value.fileName != null){
          console.log("InternalAuditManagement发生了modifyOneFormData事件")
          let url = app.globalData.url + 'InternalAuditManagement/modifyOneFormData'
          let postdata = {
            "year": year,
            "fileId": fileId,
            "fileName": e.detail.value.fileName
          }
          app.wxRequest(url, 'POST', postdata, (res) => {
            console.log(res.data)
            console.log('success modifyonefileformdata')
            wx.showToast({
              title: '修改成功',
              image: '/icons/ok/ok.png',
              duration: 500,
              success: function () {
                setTimeout(function () {
                  wx.redirectTo({
                    url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocument?id=' + year,
                  })
                }, 300)
              }
            })
          }, (err) => {
            console.log('fail modifyonefileformdata')
          })
        }
        else{
          console.log('fail modifyonefileformdata')
          wx.showToast({
            title: '修改失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
                wx.redirectTo({
                  url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocument?id=' + year,
                })
              }, 300)
            }
          })
        }
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