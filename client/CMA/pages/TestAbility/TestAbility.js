// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "mess": null,
    "temp":
      [{
        "year": 1,
        "fileName": "222",
        "file": "content1",
      },
      {
        "year": 1,
        "fileName": "333",
        "file": "content2",
      }]
  },

  onLoad: function (options) {
  },

  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
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
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'getAllItem/getAllItem?id=' + target
    })
  }
})
