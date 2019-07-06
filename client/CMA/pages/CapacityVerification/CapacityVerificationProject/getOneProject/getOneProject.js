// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": "null",
    "projectId": "null",
    "planId": "null",
    "name": "null",
    "method": "null",
    "state": "null",
    "note": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      id: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOneProject'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan get one project success')
      this.setData({
        projectId: res.data.projectId,
        planId: res.data.planId,
        name: res.data.name,
        method: res.data.method,
        state: res.data.state,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one project error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../modifyOneProject/modifyOneProject?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOneProject'
    let data = {
      "id": this.data.id
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
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  getPlans(e) {
    console.log("get projects")
    let target = this.data.projectId
    console.log(target)
    wx.navigateTo({
      url: '../../CapacityVerificationRecord/getRecordByProjectId/getRecordByProjectId?id=' + target
    })
  }
})