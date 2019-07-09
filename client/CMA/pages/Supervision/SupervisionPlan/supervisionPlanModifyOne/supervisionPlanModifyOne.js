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
    let url = app.globalData.url + 'SupervisionPlan/modifyOne'
    let postdata = {
      "planId": thispage.data.planId,
      "content": content,
      "object": object,
      "dateFrequency": dateFrequency
    }
    console.log('supervisionPlan发生了modifyone事件，携带数据为：', e.detail.value)
    app.wxRequest(url, 'POST', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        wx.showToast({
          title: '修改成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '/pages/Supervision/Supervision/supervisionGetOne/supervisionGetOne?id=' + thispage.data.id
              })
            }, 300)
          }
        })
        console.log("修改成功")
      }
      else if (res.code == 513) {
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
            }, 300)
          }
        })
        console.log("某项数据错误")
      }
      else {
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
            }, 300)
          }
        })
        console.log('修改加失败')
      }
    }, (err) => {
      console.log('fail modifyone')
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