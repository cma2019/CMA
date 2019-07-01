// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "year":null,
    "mess": null,
    "temp":
      [{
        "year": 1,
        "id": "222",
        "productionName": "content1",
        "ability": "cont21",
        "referrence": "co1nt1",
      },
      {
        "year": 1,
        "id": "333",
        "productionName": "dwa",
        "ability": "dac",
        "referrence": "dca",
      }]
  },

  onLoad: function (options) {
    this.setData({
      year: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getAllItem'
    let data = {
      "year": this.data.year,
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        mess: res.data
      })
      console.log('get all item success')
    }, (err) => {
      console.err('get all item error')
    })
  },

  gotoAdd(e) {
    let target = this.data.year
    console.log('add one test ability id')
    console.log(target)
    wx.navigateTo({
      url: '../addOneItem/addOneItem?id=' + target,
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: '../getOneItem/getOneItem?id=' + target
    })
  },
  gotoModify(e){
    let target = this.data.year
    console.log('modify one test ability id')
    console.log(target)
    wx.navigateTo({
      url: '../modifyOne/modifyOne?id=' + target,
    })
  }
})
