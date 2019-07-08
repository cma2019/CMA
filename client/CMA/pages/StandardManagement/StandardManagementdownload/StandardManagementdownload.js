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
    //fileid变量是由其他界面传入的，率先进行初始化
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
      //rescode==200时，说明getone成功，初始化界面信息
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
    //修改信息需要fileid信息，由此界面传递给modify界面
    wx.navigateTo({
      url: '../StandardManagementmodifyOne/StandardManagementmodifyOne?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'StandardManagement/deleteOne'
    let data = {
      "fileId": this.data.fileId
    }
    //delete需要fileid信息
    app.wxRequest(url, 'POST', data, (res) => {
      //删除成功时，返回rescode==200
      //显示删除成功，返回上一界面
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
    //下载文件时，需要传递fileid信息
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download now")
          console.log(res)
          //由于没有code信息，此处使用其他信息判断有没有下载成功
          //下载成功时，将会在控制台输出文件链接，点击即可打开文件
          if (res.errMsg == "saveFile:ok"){
            myFilePath = res.savedFilePath
            console.log(myFilePath)
            wx.showToast({
              title: '下载成功',
              image: '/icons/ok/ok.png',
              duration: 1000
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