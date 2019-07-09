// pages/SampleManagement/SampleIo/ioReceiptDetail/ioReceiptDetail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "receiptId":null,
    "mess": null,
    array: ['《用户手册》', '《计算机软件产品登记检测申请表》', '《材料交接单》', '《软件产品功能列表》', '《软件名称与版本号确认单》', '《受测软件产品简介》', '《自主产权保证书》', '软件样品一套', '其它'],
    flag:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      receiptId: options.id
    })
    console.log("fsdgdf")
    console.log(this.data.receiptId)

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
    var thispage = this
    console.log('ioReceiptDetail发生了事件，携带数据为：', this.data.receiptId)
    let url = app.globalData.url + 'SampleReceipt/getOne'
    let postdata = {
      "sampleId": thispage.data.receiptId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      thispage.setData({
        mess: res.data
      })
    }, (err) => {
      console.log('no data')
    })
  },
  goback: function () {
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