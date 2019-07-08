const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "sampleIoId":"null",
    "receiptId":"null",
    array:['待处理','在测','测毕'],
    "info":{},
    tmp: [{
      "sampleIoId": 2,
      "sampleNumber": "20180602",
      "sampleName": "天猫超市",
      "sampleAmount": 1,
      "sampleState": 0,
      "sender": "张三",
      "receiver": "李四",
      "sendDate": "2018-06-16",
      "obtainer": "王五",
      "obtainDate": "2018-06-17",
      "note":"fdgsfg"
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    this.setData({
      sampleIoId: options.id
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /**
     * 生命周期函数--监听页面显示
     */
  onShow: function (options) {
    var thispage = this
    console.log('getone发生了事件，携带数据为：', this.data.sampleIoId)
    let url = app.globalData.url + 'SampleIo/getOne'
    let postdata = {
      "sampleIoId": thispage.data.sampleIoId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        thispage.setData({
          info: res.data,
          receiptId: res.data.receiptId
        })
        wx.setStorage({
          key: 'ioGetOneinfo',
          data: res.data
        })
      }
      else if (res.code == 521) {
        console.log(res.data.msg)
        wx.showToast({
          title: '未收到标识编号',
          duration: 1500
        })
        console.log('未收到标识编号')
      }
      else {//522
        console.log(res.msg)
        console.log("12")
        wx.showToast({
          title: '数据不存在',
          duration: 1500
        })
        console.log('数据不存在')
      }
    }, (err) => {
      console.log('fail getone')
    })
  },

  modifyData: function(e) {
    console.log(e)
    let target = this.data.sampleIoId
    wx.navigateTo({
      url: '../ioModifyOne/ioModifyOne?id=' + target
    })
  },

  deleteData: function(e) {
    console.log("SampleIo发生了deleteOne事件，携带数据为：",this.data.sampleIoId)
    let url = app.globalData.url + 'SampleIo/deleteOne'
    let postdata = {
      "sampleIoId": this.data.sampleIoId
    }
    app.wxRequest(url, 'POST', postdata, (res) => {
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
      wx.navigateBack({
        delta: 1
      })
    }, (err) => {
      console.log('fail deleteone')
    })
  },

  goback: function () {
    wx.removeStorage({
      key: 'ioGetOneinfo',
      success: function (res) {
        console.log(res)
      }
    })
    wx.navigateBack({
      delta: 1
    })
  },
  looktoreceipt:function(e){
    console.log(e)
    let target = this.data.receiptId
    if(target != null){
      wx.navigateTo({
        url: '../ioReceiptDetail/ioReceiptDetail?id=' + target
      })
    }
    else{
      wx.showToast({
        title: '未对应接收单',
        duration: 1500
      })
    }
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