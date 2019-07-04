const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess": null,
    "flag":0, //0:database empty;  1:database not empty
    tmp: [{
      "sampleId": 2,
      "sampleNumber": "20180602",
      "sampleName": "天猫超市",
      "sampleAmount": 1,
      "sampleState": 0,
      "requester": "阿里巴巴责任有限公司",
      "receiver": "黄四郎",
      "receiveDate": "2018-06-10",
      "obtainer": "张麻子",
      "obtainDate": "2018-06-15"
    },
    {
      "sampleId": 5,
      "sampleNumber": "20170612",
      "sampleName": "教育超市",
      "sampleAmount": 2,
      "sampleState": 2,
      "requester": "百度",
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
    let url = app.globalData.url + 'SampleReceive/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('success')
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
      url: 'receiveGetOne/receiveGetOne?id=' + target
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'receiveAddOne/receiveAddOne',
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})