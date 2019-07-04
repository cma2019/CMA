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
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ModifyAnnualTrainingPlan/ModifyAnnualTrainingPlan?id=' + target
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