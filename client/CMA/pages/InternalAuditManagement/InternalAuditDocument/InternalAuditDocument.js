const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [],
    year:"",
    flag:0
  },
  gotoOne:function(e){
    console.log(e)
    const index = e.currentTarget.dataset.index
    console.log(index)
    let that = this.data.array
    console.log(that[index])
    console.log("456456465465465")
    wx.redirectTo({
      url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocumentGetOne/InternalAuditDocumentGetOne?year=' + this.data.year + "&fileId=" + that[index].fileId +"&file=" + that[index].file+"&fileName=" + that[index].fileName
    })
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/InternalAuditManagement/InternalAuditDocument/InternalAuditDocumentAddOne/InternalAuditDocumentAddOne?id=' + this.data.year,
    })
  },
  goback: function () {
    wx.redirectTo({
      url: '/pages/InternalAuditManagement/InternalAudit/InternalAudit',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      year:options.id
    })
    var year = this.data.year
    var that = this
    console.log('InternalAuditDocument发生了getAllFile事件，携带数据为：', year)
    var myurl1 = app.globalData.url + 'InternalAuditManagement/getAllFile?year=' + year
    app.wxRequest(myurl1, 'GET', null, (res) => {
      console.log(res)
     // if(res.data.code == 200){
        that.setData({
          array: res.data,
          flag: 1
        })
     // }
      console.log(that.data)
    }, (err) => {
      console.log(err)
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