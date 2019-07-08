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
    //getone界面首先需要知道自身的id
    this.setData({
      planId: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOne'
    let postdata = {
      "id": this.data.planId
    }
    //向服务器端传递id，获取需要的数据
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      //rescode==200时，获取成功，初始化界面数据
      if(res.code == 200){
        this.setData({
          name: res.data.name,
          organizer: res.data.organizer,
          state: res.data.state,
          year: res.data.year,
          note: res.data.note,
          analysis: res.data.analysis
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
    let target = this.data.planId
    console.log(target)
    //modify需要所修改界面的id信息
    wx.navigateTo({
      url: '../modifyOne/modifyOne?id=' + target
    })
  },
  goback(e){
    wx.redirectTo({
      url: '../CapacityVerificationPlan',
    })
  },
  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOne'
    let data = {
      "id": this.data.planId
    }
    //delete需要所删除界面的id信息
    app.wxRequest(url, 'POST', data, (res) => {
      //删除成功后，将会从后端传递回rescode==200
      //接收到后，显示删除成功，返回上一界面
      if (res.code == 200) {
        console.log('delete successfully')
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '../CapacityVerificationPlan',
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
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  getProjects(e){
    console.log("get projects")
    let target = this.data.planId
    console.log(target)
    //获取plan相关的project信息，需要planid
    wx.navigateTo({
      url: '../../CapacityVerificationProject/showProjects/showProjects?id=' + target
    })
  },
  uploadAna(e){
    console.log("upload plan")
    let target = this.data.planId
    console.log(target)
    //上传plan分析报告，需要planid决定添加给哪个计划
    wx.navigateTo({
      url: '../uploadAnalysis/uploadAnalysis?id=' + target
    })
  },
  downloadAnnex(e) {
    console.log("download annex now")
    var that = this
    var myurl = app.globalData.url + 'CapacityVerification/downloadAnalysis/' + that.data.planId;
    //下载文件时，需要知道下载哪个计划的分析报告
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
        wx.saveFile({
          tempFilePath: res.tempFilePath,
          success: function (res) {
            console.log("download ability annex now")
            console.log(res)
            //下载成功后，显示下载成功，显示文件路径
            if (res.errMsg == "saveFile:ok"){
              myFilePath = res.savedFilePath
              console.log(myFilePath)
              wx.showToast({
                title: '下载成功',
                image: '/icons/ok/ok.png',
                duration: 1000,
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
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        })
    }, (err) => {
      console.log(err)
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  deleteAna(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteAnalysis'
    let data = {
      "id": this.data.planId
    }
    //删除份报告时，需要知道删除哪个计划的分析报告
    app.wxRequest(url, 'POST', data, (res) => {
      if (res.code == 200) {
        console.log('delete successfully')
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 1000
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
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }
})