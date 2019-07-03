const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id":"null",
    "info":{},
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
    console.log("fsdgdf")
    console.log(this.data.id)
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
    console.log('getone发生了事件，携带数据为：', this.data.id)
    wx.request({
      url: app.globalData.url + 'SupervisionPlan/getAll',
      method: 'GET',
      data: {
        "id": this.data.id
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          console.log(res.data)
          thispage.setData({
            info : res.data.data
          })
          wx.setStorage({
            key:'supervisionPlaninfo',
            data:res.data.data
          })
        }
        else if (res.data.code == 521) {
          console.log(res)
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else {//522
          console.log(res)
          console.log("12")
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
      },
      fail(err) {
        console.log('no data')
      }
    })
  },
  supervisionPlanModify:function(){
    console.log(e)
    let planId = e.currentTarget.id
    let id = this.data.id
    let i = 0
    while(info[i].planId != planId){
      ++i
    }
    let content = info[i].content
    let object = info[i].object
    let dateFrequency = info[i].dateFrequency
    wx.navigateTo({
      url: '../SupervisionPlan/supervisionPlanModifyOne/supervisionPlanModifyOne?id=' + id +"&planId="+planId +"&content="+content+"&object="+object+"&dateFrequency"+dateFrequency
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
    let planId = e.currentTarget.id
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
      url: '../SupervisionPlan/supervisionPlanAddOne/supervisionPlanAddOne'  
    })
  },
  gotoSupervisionRecord(e){
    let planId = e.currentTarget.id
    wx.navigateTo({
      url: '../SupervisionRecord/supervisionRecord?id='+planId
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