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
        "note": "note"
      },
      {
        "projectId": 5,
        "planId": "2",
        "name": "organizer",
        "method": "22",
        "state": "2000",
        "note": "note"
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
     "planId": this.data.planId,
    }
    console.log("show pros")
    console.log(this.data.planId)
    app.wxRequest(url, 'GET', data, (res) => {
      if(res.code == 200){
        this.setData({
          mess: res.data
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      console.log(res)
      console.log('get projects from planid')
    }, (err) => {
      console.log('fail projects from planid')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  gotoAdd(e) {
    console.log(e)
    let target = this.data.planId
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: '../addOneProject/addOneProject?id=' + target,
    })
  },

  gotoOne(e) {
    console.log("pro go to one")
    console.log(e)
    console.log(e.currentTarget)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: '../getOneProject/getOneProject?id=' + target
    })
  }
})
