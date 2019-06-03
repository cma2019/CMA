// page_SampleIo/ioModifyOne/ioModifyOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "sampleIoId": "0",
    "sampleNumber": "20180602",
    "sampleName": "天猫超市",
    "sampleAmount": 1,
    "sampleState": 0,
    "sender": "张三",
    "receiver": "李四",
    "sendDate": "2018-06-16",
    "obtainer": "王五",
    "obtainDate": "2018-06-17"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      sampleIoId: options.id
    })
    console.log(this.data.sampleIoId)
    console.log("fdsf")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var thispage = this
    //console.log('getone发生了事件，携带数据为：', this.data.sampleIoId)
    wx.request({
      url: app.globalData.url + 'SampleIo/getOne',
      method: 'GET',
      data: {
        "sampleIoId": this.data.sampleIoId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          thispage.setData({
            sampleNumber: res.data.data.sampleNumber,
            sampleName: res.data.data.sampleName,
            sampleAmount: res.data.data.sampleAmount,
            sampleState: res.data.data.sampleState,
            sender: res.data.data.sender,
            receiver: res.data.data.receiver,
            sendDate: res.data.data.sendDate,
            obtainer: res.data.data.obtainer,
            obtainDate: res.data.data.obtainDate
          })
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
        console.log('no data')
      }
    })
  },

  SampleIo_modifyone: function (e) {
    console.log('SampleIo发生了modifyone事件，携带数据为：', e.detail.value)
    wx.request({
      url: app.globalData.url+ 'SampleIo/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleIoId": this.data.sampleIoId,
        "sampleNumber": e.detail.value.sampleNumber,
        "sampleName": e.detail.value.sampleName,
        "sampleAmount": e.detail.value.sampleAmount,
        "sampleState": e.detail.value.sampleState,
        "sender": e.detail.value.sender,
        "receiver": e.detail.value.receiver,
        "sendDate": e.detail.value.sendDate,
        "obtainer": e.detail.value.obtainer,
        "obtainDate": e.detail.value.obtainDate,
        "note":e.detail.value.note
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '../SampleIo'
          })
        }
        else if (res.data.code == 531) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else if (res.data.code == 532) {
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
        else if (res.data.code == 533) {
          wx.showToast({
            title: '修改后数据错误',
            duration: 1500
          })
          console.log('修改后数据错误')
        }
        else {
          wx.showToast({
            title: '修改后数据不合法',
            duration: 1500
          })
          console.log('修改后数据不合法')
        }
      },
      fail(err) {
        console.log('fail modifyone')
      },
      complete(fin) {
        console.log('final modifyone')
      }
    })

  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  modifyData: function (e) {
    console.log(e)
    let target = this.data.sampleIoId
    console.log(target)
    wx.navigateTo({
      url: '../ioModifyOne/ioModifyOne?id=' + target
    })
  },

  deleteData: function (e) {
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SampleIo/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleIoId": this.data.sampleIoId
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

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})