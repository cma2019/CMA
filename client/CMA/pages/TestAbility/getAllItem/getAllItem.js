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
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
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
          if (res.code == 200) {
            myFilePath = res.savedFilePath
            console.log(myFilePath)
            wx.showToast({
              title: '添加成功',
              image: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                setTimeout(function () {
                  wx.navigateBack({
                    delta: 1
                  })
                }, 1000);
              }
            })
          } else {
            wx.showToast({
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        },
        fail: function (err) {
          console.log(err)
          wx.showToast({
            title: '下载失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      })
    }, (err) => {
      console.log(err)
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
})
