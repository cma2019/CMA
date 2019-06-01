// page_SampleIo/SampleIo/SampleIo.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
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
  onLoad: function (options) {
    var thispage = this
    wx.request({
      url: app.globalData.url +'SampleIo/getAll',
      method: 'GET',
      data: {
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        console.log(res.data.data)
        console.log(res.data.code)
        console.log(res.data.msg)
        thispage.setData({ sampleIoInfo: res.data.data })
        if (res.data.code != 200) {
          thispage.setData({ sampleIoInfo: 'null' })
        }
      },
      fail(err) {
        console.log('no data')
      }
    })
  },
  gotoOne: function (e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'ioGetOne/ioGetOne?id=' + target
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'ioAddOne/ioAddOne',
    })
  }
})