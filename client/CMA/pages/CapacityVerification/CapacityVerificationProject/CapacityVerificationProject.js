// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "planId":null
  },

  onLoad: function (options) {

  },

  GetProjects(e) {
    console.log("get projects")
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: 'showProjects/showProjects?id=' + target
    })
  }
})