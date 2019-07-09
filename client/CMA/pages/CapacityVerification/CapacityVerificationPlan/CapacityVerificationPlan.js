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
    //getall无须数据
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.code)
      console.log(res.data)
      //接收到后端传递的rescode==200后，得知getall成功，设置mess
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
  gotomenu(e) {
    console.log('go back')
    wx.switchTab({
      url: '../../Management/Management',
    })
  },
  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log(e.currentTarget)
    console.log('getone id')
    console.log(target)
    //getone需要得知自身的id信息
    wx.navigateTo({
      url: 'getOne/getOne?id=' + target
    })
  }
})
