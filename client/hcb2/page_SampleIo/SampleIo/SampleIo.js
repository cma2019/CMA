// page_SampleIo/SampleIo/SampleIo.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //dataObj:{},
    'sampleIoIdN': '',
    infoMess: '',
    "sampleIoInfo": null,
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
  onLoad: function () {
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
  sampleIoIdInput: function (e) {
    var that = this
    that.setData({
      sampleIoIdN: e.detail.value
    })
  },
  btnClick_getone: function () {
    this.setData({
      infoMess: '',

    })
    console.log('getone发生了Click事件，携带数据为：', this.data.sampleIoIdN)
    var tmp = this
    const getoneRequest = wx.request({
      url: app.globalData.url + 'SampleIo/getOne',
      method: 'GET',
      data: {
        "sampleIoId": tmp.data.sampleIoIdN
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          var info = res.data.data
          wx.setStorage({
            key: 'info',
            data: info,
            success: function (res) {
              wx.navigateTo({
                url: '../ioShow/ioShow'
              })
            }
          })
          /*console.log('zxfcgvhjk,')
          console.log(res.data.msg)
          tmp.setData({
            dataObj: res.data.data
          })
          console.log(tmp.data.dataObj)
          wx.navigateTo({
            url: '../show/show?obtainDate='+ tmp.data.dataObj.obtainDate,
          })*/
        }
        else if (res.data.code == 521) {
          console.log(res.data.msg)
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else {//522
          console.log(res.data.msg)
          console.log("12")
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
      },
      fail(err) {
        console.log(err)
        console.log('fail getone')
      },
      complete(fin) {
        console.log('final getone')
      }
    })
  },
  btnClick_deleteone: function () {
    this.setData({
      infoMess: '',
    })
    console.log('deleteone发生了Click事件，携带数据为：', this.data.sampleIoIdN)
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SampleIo/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleIoId": this.data.sampleIoIdN
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            duration: 1500
          })
        }
        else if (res.data.code == 521) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else {
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
      },
      fail(err) {
        console.log('fail deleteone')
      },
      complete(fin) {
        console.log('final deleteone')
      }
    })
  },
  gotoAddOne: function () {
    wx.navigateTo({
      url: '../ioAddOne/ioAddOne',
    })
  },
  gotoModifyOne: function () {
    wx.navigateTo({
      url: '../ioModifyOne/ioModifyOne',
    })
  },
  goback: function () {
    wx.navigateTo({
      url: '/pages/home/home',
   })
  }
})