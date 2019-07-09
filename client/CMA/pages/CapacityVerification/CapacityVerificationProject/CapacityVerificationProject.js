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
    //在该界面输入projectid，获得相应的project信息
    console.log(target)
    wx.navigateTo({
      url: 'getOneProject/getOneProject?id=' + target
    })
  }
})