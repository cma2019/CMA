// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    
  },

  onLoad: function (options) {

  },

  GetProjects(e) {
    console.log("get projects")
    let target = e.detail.value.planId
    console.log(target)
    wx.navigateTo({
      url: 'getOneProject/getOneProject?id=' + target
    })
  }
})