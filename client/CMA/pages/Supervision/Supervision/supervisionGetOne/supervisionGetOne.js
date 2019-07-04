const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id":"null",
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
          console.log(res.data.data)
          console.log("fsdkflksd")
          thispage.setData({
            info : res.data.data,
            flag: 1
          })
          console.log(thispage.data.info)
          console.log("fsdf")
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
  supervisionPlanModify:function(e){
    console.log(e)
    let planId = e.currentTarget.id
    let info = this.data.info
    let id = this.data.id
    let i = 0
    while(info[i].planId != planId){
      ++i
    }
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
    let planId = e.currentTarget.id
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