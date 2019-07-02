// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "mess": null,
    "temp":
      [{
        "planId": 1,
        "name": "namename",
        "organizer": "organizer",
        "state": "22",
        "year": "2000",
        "note": "note",
        "analysis": "analysis"
      },
      {
        "planId": 3,
        "name": "name2",
        "organizer": "2organizer",
        "state": "322",
        "year": "2030",
        "note": "note2",
        "analysis": "11analysis"
      }]
  },

  onLoad: function (options) {
  },

  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.code)
      console.log(res.data)
      this.setData({
        mess: res.data
      })
      console.log('plan get all success')
    }, (err) => {
      console.err('getone error')
    })
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'addOne/addOne',
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log(e.currentTarget)
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'getOne/getOne?id=' + target
    })
  }
})
