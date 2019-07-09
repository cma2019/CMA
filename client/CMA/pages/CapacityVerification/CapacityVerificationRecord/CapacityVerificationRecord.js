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
  GetPlans(e) {
    console.log("get plans")
    let target = e.detail.value.id
    console.log(target)
    //在该界面输入recordid信息，到达相应record界面
    wx.navigateTo({
      url: 'getOneRecord/getOneRecord?id=' + target
    })
  }
})