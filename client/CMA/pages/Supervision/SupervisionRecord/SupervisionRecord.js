Page({

  /**
   * 页面的初始数据
   */
  data: {
    planId:'',
    "mess": null,
    "flag": 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      planId: options.id
    })
    console.log("fsdgdf")
    console.log(this.data.planId)
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
    console.log('getone发生了事件，携带数据为：', this.data.planId)
    wx.request({
      url: app.globalData.url + 'SupervisionRecord/getAll',
      method: 'GET',
      data: {
        "planId": this.data.planId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.code == 522) {
          this.setData({
            mess: ""
          })
        }
        else {
          this.setData({
            mess: res.data,
            flag: 1
          })
        }
      },
      fail(err) {
        console.log('no data')
      }
    })
  },
  gotoOne: function (e) {
    console.log(e)
    let recordId = e.currentTarget.id
    let i =0
    let recordlist = this.data.mess
    while (recordlist[i].recordId != recordId){
      ++i
    }
    wx.navigateTo({
      url: 'SupervisonRecordGetOne/SupervisonRecordGetOne?recordId=' + recordId + "&planId=" + recordlist[i].planId + "&department=" + recordlist[i].department + "&supervisor=" + recordlist[i].supervisor + "&superviseDate=" + recordlist[i].superviseDate + "&supervisedPerson=" + recordlist[i].supervisedPerson + "&record=" + recordlist[i].record + "&conclusion=" + recordlist[i].conclusion + "&operator=" + recordlist[i].operator +"&recordDate="+recordlist[i].recordDate
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'SupervisionRecordAddOne/SupervisionRecordAddOne',
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