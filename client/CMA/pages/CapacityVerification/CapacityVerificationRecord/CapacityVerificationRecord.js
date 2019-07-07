// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    
  },

  onLoad: function (options) {

  },

  GetPlans(e) {
    console.log("get plans")
    let target = e.detail.value.id
    console.log(target)
    wx.navigateTo({
      url: 'getOneRecord/getOneRecord?id=' + target
    })
  }
})