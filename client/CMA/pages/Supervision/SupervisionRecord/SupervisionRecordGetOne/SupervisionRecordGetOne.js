// pages/Supervision/SupervisionRecord/SupervisionRecordGetOne/SupervisionRecordGetOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    recordId:'',
    planId:'',
    department: '',
    supervisor: '',
    superviseDate: '',
    supervisedPerson: '',
    record: '',
    conclusion: '',
    operator: '',
    recordDate: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      recordId: options.recordId,
      planId: options.planId,
      department: options.department,
      supervisor: options.supervisor,
      superviseDate: options.superviseDate,
      supervisedPerson: options.supervisedPerson,
      record: options.record,
      conclusion: options.conclusion,
      operator: options.operator,
      recordDate: options.recordDate
    })
    console.log(this.data)
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
  modifyData: function (e) {
    console.log(e)
    let recordId = this.data.recordId
    wx.navigateTo({
      url: '../SupervisionRecordModifyOne/SupervisionRecordModifyOne?recordId=' + recordId + "&department=" + this.data.department + "&supervisor=" + this.data.supervisor + "&superviseDate=" + this.data.superviseDate + "&supervisedPerson=" + this.data.supervisedPerson + "&record=" + this.data.record + "&conclusion=" + this.data.conclusion + "&operator=" + this.data.operator + "&recordDate=" + this.data.recordDate
    })
  },

  deleteData: function (e) {
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SupervisionRecord/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "recordId": this.data.recordId
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