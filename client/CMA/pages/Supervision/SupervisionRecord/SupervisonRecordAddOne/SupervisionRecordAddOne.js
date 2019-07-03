const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    superviseDate: "",
    recordDate: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },
  bindsuperviseDateChange(e) {
    this.setData({
      superviseDate: e.detail.value
    })
  },
  bindrecordDateChange(e) {
    this.setData({
      recordDate: e.detail.value
    })
  },
  SupervisionRecord_addone: function (e) {
    if (e.detail.value.superviseDate == "") {
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    else {
      console.log('SupervisionRecord发生了addone事件，携带数据为：', e.detail.value)
        wx.request({
          url: app.globalData.url + 'SupervisionRecord/addOne',
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
          },
          data: {
            "planId": e.detail.value.planId,
            "department": e.detail.value.department,
            "supervisor": e.detail.value.supervisor,
            "superviseDate": e.detail.value.superviseDate,
            "supervisedPerson": e.detail.value.supervisedPerson,
            "record": e.detail.value.record,
            "conclusion": e.detail.value.conclusion,
            "operator": e.detail.value.operator,
            "recordDate": e.detail.value.recordDate
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
                title: '添加失败，监督计划编号已存在',
                duration: 1500
              })
              console.log('添加失败，监督计划编号已存在')
            }
            else{
              wx.showToast({
                title: '添加失败，某项数据错误',
                duration: 1500
              })
              console.log('添加失败，某项数据错误')
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