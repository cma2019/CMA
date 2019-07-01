// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "projectId":null
  },

  onLoad: function (options) {

  },

  GetPlans(e) {
    console.log("get plans")
    let target = this.data.projectId
    console.log(target)
    wx.navigateTo({
      url: 'getRecordByProjectId/getRecordByProjectId?id=' + target
    })
  }
})