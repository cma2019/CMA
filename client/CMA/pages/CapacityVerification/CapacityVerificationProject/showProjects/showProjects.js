// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "planId": null,
    "mess":null,
    "temp":
      [{
        "projectId": 3,
        "planId": "2",
        "name": "namename",
        "method": "22",
        "state": "2000",
        "note": "note",
      },
      {
        "projectId": 5,
        "planId": "2",
        "name": "organizer",
        "method": "22",
        "state": "2000",
        "note": "note",
      }]
  },

  onLoad: function (options) {
    this.setData({
      planId: options.id
    })
  },

  onShow: function (options) {  
    let url = app.globalData.url + 'CapacityVerification/getAllProject'
    let data = {
     "planId": e.detail.value.id,
    }
    console.log(data)
    app.wxRequest(url, 'GET', data, (res) => {
      this.setData({
        mess: res.data
      })
      console.log('get projects from planid')
    }, (err) => {
      console.log('fail projects from planid')
    })
  },

  gotoAdd(e) {
    console.log(e)
    let target = e.currentTarget.planId
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'addOneProject/addOneProject?id=' + target,
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.projectId
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'getOneProject/getOneProject?id=' + target
    })
  }
})
