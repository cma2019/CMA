// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "projectId": null,
    "mess": null,
    "temp":
      [{
        "recordId": 3,
        "date": "2",
        "methodId": "namename",
        "equipmentName": "22",
        "equipmentId": "2000",
        "experimenter": "note",
        "result": "note",
        "resultDeal": "note",
        "note": "note"
      },
      {
        "recordId": 5,
        "date": "33",
        "methodId": "methodIdmethodId",
        "equipmentName": "2321",
        "equipmentId": "232",
        "experimenter": "noeate",
        "result": "notdsade",
        "resultDeal": "noasaste",
        "note": "nodsaste"
      }]
  },

  onLoad: function (options) {
    this.setData({
      projectId: options.id
    })
  },

  onShow: function (options) {
    console.log("record list")
    let url = app.globalData.url + 'CapacityVerification/getRecordByProjectId'
    let data = {
      "projectId": this.data.projectId,
    }
    console.log(data)
    app.wxRequest(url, 'GET', data, (res) => {
      this.setData({
        mess: res.data
      })
      console.log(res)
      console.log(this.data.mess)
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
    let target = this.data.projectId
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: '../addOneRecord/addOneRecord?id=' + target,
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone record id')
    console.log(target)
    wx.navigateTo({
      url: '../getOneRecord/getOneRecord?id=' + target
    })
  }
})
