// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    
  },

  onLoad: function (options) {

  },
  gotomenu(e) {
    console.log('go back')
    wx.switchTab({
      url: '../../Management/Management',
    })
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