// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "planId": "null",
    "name": "null",
    "organizer": "null",
    "state": "null",
    "year": "null",
    "note": "null",
    "analysis": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      planId: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOne'
    let postdata = {
      "id": this.data.planId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      this.setData({
        name: res.data.name,
        organizer: res.data.organizer,
        state: res.data.state,
        year: res.data.year,
        note: res.data.note,
        analysis: res.data.analysis
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../modifyOne/modifyOne?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOne'
    let data = {
      "id": this.data.planId
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

  getProjects(e){
    console.log("get projects")
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../../CapacityVerificationProject/showProjects/showProjects?id=' + target
    })
  },
  uploadAna(e){
    console.log("upload plan")
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../uploadAnalysis/uploadAnalysis?id=' + target
    })
  },
  downloadAnnex(e) {
    console.log("download annex now")
    var that = this
    var myurl = app.globalData.url + 'CapacityVerification/downloadAnalysis/' + that.data.planId;
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download ability annex now")
          console.log(res)
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
  },
  deleteAna(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteAnalysis'
    let data = {
      "id": this.data.planId
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
  }
})