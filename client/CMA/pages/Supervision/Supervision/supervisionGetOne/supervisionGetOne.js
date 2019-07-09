const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:null,
    info:[],
    flag:0
  },

  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    wx.removeStorage({
      key: 'supervisionPlaninfo',
      success: function (res) {
        console.log(res)
      }
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
  onShow: function (options) {
    var thispage = this
    console.log('SupervisionPlan发生了getAll事件，携带数据为：', thispage.data.id)
    let url = app.globalData.url + 'SupervisionPlan/getAll'
    let postdata = {
      "id": thispage.data.id
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      if (res.code == 200) {
        this.setData({
          info: res.data,
          flag: 1
        })
        wx.setStorage({
          key: 'supervisionPlaninfo',
          data: res.data.data
        })
        console.log("SupervisionPlan-getAll成功")
      }
      else { //210
        this.setData({
          info: null,
          flag: 0
        })
        console.log("SupervisionPlan-getAll无有效信息")
      }
    }, (err) => {
      wx.showToast({
        title: 'SupervisionPlan-getAll error',
        duration: 1500
      })
      console.log('SupervisionPlan-getAll error')
    })
  },
  supervisionPlanModify:function(e){
    console.log(e)
    let i = e.currentTarget.dataset.index
    let info = this.data.info
    let id = this.data.id
    let planId = info[i].planId
    let content = info[i].content
    let object = info[i].object
    let dateFrequency = info[i].dateFrequency
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionPlan/supervisionPlanModifyOne/supervisionPlanModifyOne?id=' + id +"&planId="+planId +"&content="+content+"&object="+object+"&dateFrequency="+dateFrequency
    })
  },
  goback: function () {
    wx.removeStorage({
      key: 'supervisionPlaninfo',
      success: function (res) {
        console.log(res)
      }
    })
    wx.navigateBack({
      delta: 1
    })
  },
  supervisionPlanDelete:function(e){
    let i = e.currentTarget.dataset.index
    let planId = this.data.info[i].planId
    let id = this.data.id
    console.log("supervisionPlan发生了deleteOne事件，携带数据为",planId)
    let url = app.globalData.url + 'SupervisionPlan/deleteOne'
    let postdata = {
      "planId": planId
    }
    app.wxRequest(url, 'POST', postdata, (res) => {
      if (res.code == 200) {
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '/pages/Supervision/Supervision/supervisionGetOne/supervisionGetOne?id=' + id
              })
            }, 500)
          }
        })
      }
      else {
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 500
        })
        console.log('删除失败')
      }
    }, (err) => {
      console.log('fail deleteone')
    })
  },
  supervisionPlanAdd:function(){
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionPlan/supervisionPlanAddOne/supervisionPlanAddOne?id='+this.data.id  
    })

  },
  gotoSupervisionRecord(e){
    let i = e.currentTarget.dataset.index
    let planId = this.data.info[i].planId
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionRecord/SupervisionRecord?id='+planId
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