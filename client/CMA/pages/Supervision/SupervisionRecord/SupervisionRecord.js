const app = getApp()
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
        "planId": thispage.data.planId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        console.log(res)
        if (res.data.code == 522) {
          thispage.setData({
            mess: ""
          })
        }
        else if(res.data.code == 200){
          thispage.setData({
            mess: res.data.data,
            flag: 1
          })
        }
        else{

        }
        console.log(thispage.data.mess)
        console.log("fdsgfg")
      },
      fail(err) {
        console.log('no data')
      }
    })
  },
  supervisionRecordModify: function (e) {
    console.log(e)
    let recordId = e.currentTarget.id
    let info = this.data.mess
    let planId = this.data.planId
    let i = 0
    while (info[i].recordId != recordId) {
      ++i
    }
    let department = info[i].department
    let supervisor = info[i].supervisor
    let superviseDate = info[i].superviseDate
    let supervisedPerson = info[i].supervisedPerson
    let record = info[i].record
    let conclusion = info[i].conclusion
    let operator = info[i].operator
    let recordDate = info[i].recordDate
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionRecord/SupervisionRecordModifyOne/SupervisionRecordModifyOne?planId=' + planId + "&recordId=" + recordId + "&department=" + department + "&supervisor=" + supervisor + "&superviseDate=" + superviseDate + "&supervisedPerson=" + supervisedPerson + "&record=" + record + "&conclusion=" + conclusion + "&operator=" + operator + "&recordDate=" + recordDate
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  supervisionPlanDelete: function (e) {
    let recordId = e.currentTarget.id
    let planId = this.data.planId
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SupervisionRecord/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "recordId": recordId
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '/pages/Supervision/SupervisionRecord/SupervisionRecord?id=' + planId
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
  supervisionRecordAdd: function () {
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionRecord/SupervisonRecordAddOne/SupervisionRecordAddOne?id=' + this.data.planId
    })
  },
  gotodetail:function(e){
    console.log(e)
    let recordId = e.currentTarget.id
    let info = this.data.mess
    let planId = this.data.planId
    let i = 0
    while (info[i].recordId != recordId) {
      ++i
    }
    let department = info[i].department
    let supervisor = info[i].supervisor
    let superviseDate = info[i].superviseDate
    let supervisedPerson = info[i].supervisedPerson
    let record = info[i].record
    let conclusion = info[i].conclusion
    let operator = info[i].operator
    let recordDate = info[i].recordDate
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionRecord/SupervisionRecordDetail/SupervisionRecordDetail?planId=' + planId + "&recordId=" + recordId + "&department=" + department + "&supervisor=" + supervisor + "&superviseDate=" + superviseDate + "&supervisedPerson=" + supervisedPerson + "&record=" + record + "&conclusion=" + conclusion + "&operator=" + operator + "&recordDate=" + recordDate
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