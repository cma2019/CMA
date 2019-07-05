// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    console.log(options)
    this.setData({

      id: options.id
    })
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'ManagementReview/getOne'
    let postdata = {
      "fileId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      this.setData({
        year: res.data.year,
        fileId: res.data.fileId,
        fileName: res.data.fileName,
        file: res.data.file
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  ModifyStaff(e) {
    console.log(e)
    //let target = this.data.id
    //console.log(target)
    let url1 = app.globalData.url + 'ManagementReview/modifyOneFile'
    let postdata1 = {
      "fileId": this.data.id
    }
    console.log(postdata1)
    app.wxRequest(url1, 'POST', postdata1, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
    }, (err) => {
      console.err('get one error')
    })
    var myurl2 = app.globalData.url + 'ManagementReview/addOneFile';
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        console.log(res.tempFiles)
        console.log(res.tempFiles[0])
        console.log(res.tempFiles[0].path)
        //mypath = res.tempFiles[0].path
        app.wxUploadFile(myurl2, res.tempFiles[0].path, null, (res) => {
          console.log("upload file success")
          console.log(res)
          wx.showToast({
            title: '修改成功!',
            icon:'success',
            duration:2000
          })
          //console.log(mydata)
          
        }, (err) => {
          console.log(err)
        })
        /*console.log(this.data.year)
        console.log(this.year)
        wx.redirectTo({
          url: '../GetAllFileManagementReview/GetAllFileManagementReview?id=' + this.data.year,
        })*/
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
      }
    })


  },

  DownloadStaff(e) {
    //var that = this
    var myurl = app.globalData.url + 'ManagementReview/downloadFile/' + this.data.id;
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download now")
          console.log(res)
          myFilePath = res.savedFilePath
          console.log(myFilePath)
          wx.showToast({
            title: '下载成功!',
            icon:'success',
            duration:2000
          })
        },
        fail: function (err) {
          console.log(err)
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  DeleteStaff(e) {
    let url2 = app.globalData.url + 'ManagementReview/deleteOneFile'
    let data2 = {
      "fileId": this.data.id
    }
    app.wxRequest(url2, 'POST', data2, (res) => {
      console.log('delete successfully')
      wx.showToast({
        title: '删除成功!',
        icon: 'success',
        duration: 2000
      })
    }, (err) => {
      console.log('delete failed')
    })
  }

})