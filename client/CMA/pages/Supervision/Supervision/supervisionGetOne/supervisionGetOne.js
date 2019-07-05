const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:null,
    info:[],
    flag:0,
    tmp: [{
      "planId ": 5,
      "id": 1,
      "content": "操作规范",
      "object": "张三",
      "dateFrequency": ""
    },
    {
      "planId ": 6,
      "id": 1,
      "content": "仪器使用规范",
      "object": "李四",
      "dateFrequency": ""
    }]
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
    wx.request({
      url: app.globalData.url + 'SupervisionPlan/getAll',
      method: 'GET',
      data: {
        "id": thispage.data.id
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {

          thispage.setData({
            info : res.data.data,
            flag: 1
          })
          wx.setStorage({
            key:'supervisionPlaninfo',
            data:res.data.data
          })
        }/*
        else if (res.data.code == 521) {
          console.log(res)
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }*/
        else {//522
          wx.showToast({
            title: '暂未含有SupervisionPlan',
            duration: 1500
          })
          console.log('暂未含有SupervisionPlan')
        }
      },
      fail(err) {
        console.log('no data')
      }
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
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SupervisionPlan/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "planId": planId
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '/pages/Supervision/Supervision/supervisionGetOne/supervisionGetOne?id=' + id
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