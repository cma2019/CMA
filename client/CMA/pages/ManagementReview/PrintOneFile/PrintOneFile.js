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
    let url = app.globalData.url + 'ManagementReview/modifyOneFile'
    let postdata = {
      "fileId": this.data.id
    }
    console.log(postdata)
    app.wxRequest(url, 'POST', postdata, (res) => {
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
          console.log(mydata)
          
        }, (err) => {
          console.log(err)
        })
        wx.redirectTo({
          url: '../GetAllFileManagementReview/GetAllFileManagementReview?id=' + e.detail.value.year,
        })
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
      }
    })


  },

  DownloadStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ModifyAnnualTrainingPlan/ModifyAnnualTrainingPlan?id=' + target
    })
  },
  DeleteStaff(e) {
    let url = app.globalData.url + 'AnnualTrainingPlan/deleteOne'
    let data = {
      "planId": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
    }, (err) => {
      console.log('delete failed')
    })
  }

})