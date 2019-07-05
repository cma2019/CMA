const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: [],
    id:"",
    flag:0
  },
  gotoOne:function(e){
    const index = e.currentTarget.dataset.index
    let that = this.data.array
    wx.redirectTo({
      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocumentGetOne/SelfInspectionDocumentGetOne?id=' + this.data.id + "&fileId=" + that[index].fileId + "&year=" + that[index].year + "&file=" + that[index].file+"&fileName=" + that[index].fileName
    })
  },
  gotoAdd: function (e) {
    wx.redirectTo({
      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocumentAddOne/SelfInspectionDocumentAddOne?id='+this.data.id,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id:options.id
    })
    var id = this.data.id
    var that = this
    console.log('SelfInspection发生了getAllFile事件，携带数据为：', id)
    var myurl1 = app.globalData.url + 'SelfInspection/getAllFile?id=' + id
    app.wxRequest(myurl1, 'GET', null, (res) => {
      console.log(res)
      if(res.code == 200){
        that.setData({
          array: res.data,
          flag: 1
        })
      }
      console.log(that.data)
    }, (err) => {
      console.log(err)
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