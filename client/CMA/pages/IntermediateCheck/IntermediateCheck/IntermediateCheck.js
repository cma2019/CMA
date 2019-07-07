// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
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
    let url = app.globalData.url + 'IntermediateChecksPlan/getAll'
    let postdata = ''
    
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
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
