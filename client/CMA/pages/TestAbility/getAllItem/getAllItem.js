// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "year":null,
    "name":null,
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
    console.log("get all items")
    console.log(options)
    this.setData({
      year: options.year,
      name: options.name
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getAllItem'
    let data = {
      "year": this.data.year,
    }
    app.wxRequest(url, 'GET', data, (res) => {
      this.setData({
        mess: res.data
      })
      console.log('plan get all success')
    }, (err) => {
      console.err('getone error')
      console.log('get all item success')
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
    let target2 = this.data.name
    console.log('modify one test ability id')
    console.log(target)
    wx.navigateTo({
      url: '../modifyOne/modifyOne?year=' + target+'&name='+target2
    })
  },

  downloadAnnex(e) {
    var that = this
    var myurl = app.globalData.url + 'TestAbility/getAnnex/' + that.data.year;
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download ability annex now")
          console.log(res)
          myFilePath = res.savedFilePath
          console.log(myFilePath)
        },
        fail: function (err) {
          console.log(err)
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
})
