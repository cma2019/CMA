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
      if(res.code == 200){
        this.setData({
          fileId: res.data.fileId,
          fileName: res.data.fileName,
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.err('getone error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
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
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateBack({
                delta: 1
              })
            }, 1000);
          }
        })
      }else{
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.log('delete failed')
      wx.showToast({
        title: '修改失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  downloadOne(e) {
    var that = this
    var myurl = app.globalData.url + 'StandardManagement/downloadFile/' + that.data.fileId;
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download now")
          console.log(res)
          var obj = JSON.parse(res);
          console.log(obj)
          console.log(obj.code)
          if (obj.code == 200){
            myFilePath = res.savedFilePath
            console.log(myFilePath)
            wx.showToast({
              title: '下载成功',
              image: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                setTimeout(function () {
                  wx.navigateBack({
                    delta: 1
                  })
                }, 1000);
              }
            })
          }else{
            wx.showToast({
              title: '下载失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        },
        fail: function (err) {
          console.log(err)
          wx.showToast({
            title: '连接失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      })
    }, (err) => {
      console.log(err)
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }

})