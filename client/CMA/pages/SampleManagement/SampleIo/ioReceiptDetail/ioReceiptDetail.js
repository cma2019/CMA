// pages/SampleManagement/SampleIo/ioReceiptDetail/ioReceiptDetail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sampleId:'',
    "mess": null,
    array: ['《用户手册》', '《计算机软件产品登记检测申请表》', '《材料交接单》', '《软件产品功能列表》', '《软件名称与版本号确认单》', '《受测软件产品简介》', '《自主产权保证书》', '软件样品一套', '其它'],
    flag:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      sampleId: options.id
    })
    console.log("fsdgdf")
    console.log(this.data.sampleId)

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
    console.log('ioReceiptDetail发生了事件，携带数据为：', this.data.sampleId)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/getOne',
      method: 'GET',
      data: {
        "sampleId": this.data.sampleId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          thispage.setData({
            mess: res.data.data
          })
        }
        else {//522
          console.log("12")
          thispage.setData({
            flag: 1
          })
          wx.showToast({
            title: '未对应SampleReceipt',
            duration: 1500
          })
          console.log('未对应SampleReceipt')
        }
      },
      fail(err) {
        console.log('no data')
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