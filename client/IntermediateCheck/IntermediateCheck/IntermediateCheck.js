// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
/*
Component({
  properties:{
    prop:{
      type:String,
      value:'index.properties'
    },
  },
})
*/

Page({

  data: {
    "mess": null,
    "temp":
    [{
      "planId": 1,
      "object": "路由器",
      "content": "content1",
      "checkDate": "2018-03-04",
      "personInCharge": "小明",
      "state": "0"
    },
      {
        "planId": 6,
        "object": "路由器",
        "content": "content2",
        "checkDate": "2018-05-13",
        "personInCharge": "小明",
        "state": "1"
      }]
  },

  onLoad: function (options) {
  },

  onShow:function(options){
    let url = app.globalData.url + 'cma/IntermediateChecksPlan/getAll'
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

  gotoAdd(e){
    wx.navigateTo({
      url: 'IntermediateCheckAddone/IntermediateCheckAddone',
    })
  },
  
  gotoOne(e){
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'IntermediateCheckGetone/IntermediateCheckGetone?id=' + target
    })
  }
})
