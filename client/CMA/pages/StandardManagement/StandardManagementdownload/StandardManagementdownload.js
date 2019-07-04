// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "fileId": "null",
    "fileName": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      fileId: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'StandardManagement/getOne'
    let postdata = {
      "fileId": this.data.fileId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      this.setData({
        fileId: res.data.fileId,
        fileName: res.data.fileName,
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.fileId
    console.log(target)
    wx.navigateTo({
      url: '../StandardManagementmodifyOne/StandardManagementmodifyOne?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'StandardManagement/deleteOne'
    let data = {
      "fileId": this.data.fileId
    }
    app.wxRequest(url, 'POST', data, (res) => {
      if (res.code == 200) {
        console.log('delete successfully')
        wx.navigateBack({
          delta: 1
        })
      }
    }, (err) => {
      console.log('delete failed')
    })
  },

  downloadOne(e) {
    var that = this
    var myurl = app.globalData.url + 'StandardManagement/downloadFile/' + that.data.fileId;
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
  }

})