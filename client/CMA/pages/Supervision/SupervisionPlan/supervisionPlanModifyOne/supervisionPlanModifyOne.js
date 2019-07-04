// pages/Supervision/SupervisionPlan/supervisionPlanModifyOne/supervisionPlanModifyOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    planId:'',
    content:'',
    object:'',
    dateFrequency:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id,
      planId:options.planId,
      content: options.content,
      object: options.object,
      dateFrequency: options.dateFrequency
    })
    console.log("fsdgdf")
    console.log(this.data.id)
    console.log(this.data.planId)
    console.log(this.data.content)
    console.log(this.data.object)
    console.log(this.data.dateFrequency)
  },
  SupervisionPlan_modifyone:function(e){
    let content = e.detail.value.content
    let object = e.detail.value.object
    let dateFrequency = e.detail.value.dateFrequency
    if(content == null){
      content = this.data.content
    }
    if(object == null){
      object = this.data.object
    }
    if(dateFrequency == null){
      dateFrequency = this.data.dateFrequency
    }
    var thispage = this
    console.log('supervisionPlan发生了modifyone事件，携带数据为：', e.detail.value)
    wx.request({
      url: app.globalData.url + 'SupervisionPlan/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "planId": thispage.data.planId,
        "content": content,
        "object":object,
        "dateFrequency":dateFrequency
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '/pages/Supervision/Supervision/supervisionGetOne/supervisionGetOne?id='+thispage.data.id
          })
        }
        else if (res.data.code == 531) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else if (res.data.code == 532) {
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
        else if (res.data.code == 533) {
          wx.showToast({
            title: '数据错误',
            duration: 1500
          })
          console.log('数据错误')
        }
        else {
          wx.showToast({
            title: '数据不合法',
            duration: 1500
          })
          console.log('数据不合法')
        }
      },
      fail(err) {
        console.log('fail modifyone')
      },
      complete(fin) {
        console.log('final modifyone')
      }
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