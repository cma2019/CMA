// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
const CryptoJS = require("../../utils/cryptojs.js")
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
    /*
    var word = "131656511"
    var re = CryptoJS.Encrypt(word)
    console.log("secret")
    console.log(re)
    var re2 = CryptoJS.Decrypt(re)
    console.log("secret")
    console.log(re2)
    */
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
