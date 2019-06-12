// page_SampleIo/ioShow/ioShow.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id":"null",
    /*"sampleNumber": "null",
    "sampleName": "null",
    "sampleAmount": "null",
    "sampleState": "null",
    "sender": "null",
    "receiver": "null",
    "sendDate": "null",
    "obtainer": "null",
    "obtainDate": "null",
    "note":"null",*/
    "info":{},
    tmp: [{
      "id": 1,
      "situation ": 2,
      "author": "老王",
      "createDate": "2018-05-08",
      "approver": "老李",
      "approveDate": "2018-06-08",
      "remark ": "无",
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log("fsdgdf")
    console.log(this.data.id)
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
    console.log('getone发生了事件，携带数据为：', this.data.id)
    wx.request({
      url: app.globalData.url + 'Supervision/getOne',
      method: 'GET',
      data: {
        "id": this.data.id
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
         /* thispage.setData({ 
            sampleNumber: res.data.data.sampleNumber,
            sampleName: res.data.data.sampleName,
            sampleAmount: res.data.data.sampleAmount,
            sampleState: res.data.data.sampleState,
            sender: res.data.data.sender,
            receiver: res.data.data.receiver,
            sendDate: res.data.data.sendDate,
            obtainer: res.data.data.obtainer,
            obtainDate: res.data.data.obtainDate,
            note:res.data.data.note
          })*/
          thispage.setData({
            info : res.data.data
          })
          wx.setStorage({
            key:'supervisionGetOneinfo',
            data:res.data.data
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

  modifyData: function(e) {
    console.log(e)
    let target = this.data.id
    //console.log("dfdg")
    //console.log(target)
    wx.navigateTo({
      url: '../supervisionModifyOne/supervisionModifyOne?id=' + target
    })
  },

  deleteData: function(e) {
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'Supervision/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "id": this.data.id
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
        wx.navigateBack({
          delta: 1
        })
      },
      fail(err) {
        console.log('fail deleteone')
      },
      complete(fin) {
        console.log('final deleteone')
      }
    })

  },

  goback: function () {
    wx.removeStorage({
      key: 'supervisionGetOneinfo',
      success: function (res) {
        console.log(res)
      }
    })
    wx.navigateBack({
      delta: 1
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