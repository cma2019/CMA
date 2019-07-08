// pages/IntermediateChecksRecord/IntermediateChecksRecord.js
const app = getApp()
Page({

  data: {
    "mess": null,
    "temp":
      [{
        "recordId":5,
        "planId": 1,
        "object": "路由器",
        "checkDate": "2018-03-04",
        "processRecord":"xxxxx",
        "processRecordPerson":"zhang",
        "processRecordDate":"2018-03-05",
        "resultRecord":"ok",
        "resultRecordPerson":"li",
        "resultRecordDate":"2018-05-11",
        "note":"aaaa"
      },
      {
        "recordId": 5,
        "planId": 1,
        "object": "路由器",
        "checkDate": "2018-03-04",
        "processRecord": "xxxxx",
        "processRecordPerson": "zhang",
        "processRecordDate": "2018-03-05",
        "resultRecord": "ok",
        "resultRecordPerson": "li",
        "resultRecordDate": "2018-05-11",
        "note": "aaaa"
      }]
  },

  onLoad: function (options) {
    
  },

  onShow: function (options){
    let url = app.globalData.url + 'IntermediateChecksRecord/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('record getall success')
      console.log(res)
      if(res.code == 200){
        this.setData({
          mess : res.data
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.err('getone error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'IntermediateChecksRecordGetone/IntermediateChecksRecordGetone?id=' + target
    })
  }
})