// pages/SampleManagement/SampleReceipt/receiptSearch/receiptSearch.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    search_sampleId: "",
    mess: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  searchInput:function(e){
    console.log(e.detail.value)
    this.setData({
      search_sampleId: e.detail.value
    })
  },
  searchSubmit:function(){
    console.log(this.data.search_sampleId)
    var thispage = this
    console.log('getone发生了事件，携带数据为：', this.data.search_sampleId)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/getOne',
      method: 'GET',
      data: {
        "sampleId": this.data.search_sampleId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          this.setData({
            mess:res.data
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
    let mess = this.data.mess
    if(mess == null){
      this.setData({
        search_sampleId: ""
      })
    }
  },
  cancelSearch:function(){
    wx.navigateBack({
      delta: 1
    })
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