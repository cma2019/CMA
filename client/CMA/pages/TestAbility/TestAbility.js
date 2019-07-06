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
      },
      {
        "year": 1,
        "fileName": "333",
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
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: 'addOne/addOne',
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.dataset.name
    let target2 = e.currentTarget.dataset.year
    console.log('getone id')
    console.log(e.currentTarget)
    console.log(target)
    console.log(target2)
    var myurl = "1"
    if (target != "" && target2 != "") {
      myurl = 'getAllItem/getAllItem?name=' + target + '&year=' + target2
      console.log("myurl")
      console.log(myurl)
      async: wx.navigateTo({
        //url: 'getAllItem/getAllItem?name=' + target + '&year=' + target2
        url: myurl
      })
    }
  }
})
