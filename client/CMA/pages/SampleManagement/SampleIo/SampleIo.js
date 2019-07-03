const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess":null,
    "flag":0,
    tmp: [{
      "sampleIoId": 2,
      "sampleNumber": "20180602",
      "sampleName": "天猫超市",
      "sampleAmount": 1,
      "sampleState": 0,
      "sender": "张三",
      "receiver": "李四",
      "receiveDate": "2018-06-16",
      "obtainer": "王五",
      "obtainDate": "2018-06-17"
      },
      {
      "sampleIoId": 5,
      "sampleNumber": "20170612",
      "sampleName": "教育超市",
      "sampleAmount": 2,
      "sampleState": 2,
      "sender": "海皮",
      "receiver": "长的",
      "receiveDate": "2003-10-6",
      "obtainer": "海门",
      "obtainDate": "2014-09-7"
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    let url = app.globalData.url + 'SampleIo/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      if(res.code == 522){
        this.setData({
          mess : ""
        })
      }
      else{
        this.setData({
          mess: res.data,
          flag: 1
        })
        console.log('success')
      }
    }, (err) => {
      wx.showToast({
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  gotoOne: function (e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    console.log("dfdsfs")
    wx.navigateTo({
      url: 'ioGetOne/ioGetOne?id=' + target
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'ioAddOne/ioAddOne',
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
})