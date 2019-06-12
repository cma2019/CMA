// page_SampleIo/ioAddone/ioAddone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sendDate:"",
    obtainDate:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  bindsendDateChange(e) {
    this.setData({
      sendDate: e.detail.value
    })
  },

  bindobtainDateChange(e) {
    this.setData({
      obtainDate: e.detail.value
    })
  },
  SampleIo_addone: function (e) {
    if (e.detail.value.sampleNumber == "" || e.detail.value.sampleName == "" || e.detail.value.sampleAmount == "" || e.detail.value.sampleState == "" || e.detail.value.sender == "" || e.detail.value.receiver == "" || e.detail.value.sendDate == "" || e.detail.value.obtainer == "" || e.detail.value.obtainDate == "") {
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    else {
      console.log('SampleIo发生了addone事件，携带数据为：', e.detail.value)
      wx.request({
        url: app.globalData.url + 'SampleIo/addOne',
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        data: {
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
          console.log(res.data)
          if (res.data.code == 200) {
            wx.showToast({
              title: '成功',
              icon: 'none',
              duration: 1500
            })
            wx.navigateTo({
              url: '../SampleIo'
            })
          }
          else if (res.data.code == 512) {
            wx.showToast({
              title: '添加失败，样品编号已存在',
              duration: 1500
            })
            console.log('添加失败，样品编号已存在')
          }
          else  if(res.data.code == 513){
            wx.showToast({
              title: '添加失败，某项数据错误',
              duration: 1500
            })
            console.log('添加失败，某项数据错误')
          }
          else{//514
            wx.showToast({
              title: '添加失败，样品数据与接收单不一致',
              duration: 1500
            })
            console.log('添加失败，样品数据与接收单不一致')
          }
        },
        fail(err) {
          console.log('fail addone')
        },
        complete(fin) {
          console.log('final addone')
        }
      })
    }
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

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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