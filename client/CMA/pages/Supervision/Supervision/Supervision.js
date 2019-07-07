const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess":null,
    "flag":0,
    tmp: [{
      "id": 1,
      "situation ": 2,
      "author": "老王",
      "createDate": "2018-05-08",
      "approver": "老李",
      "approveDate": "2018-06-08",
      "remark ": "无",
    },
    {
      "id": 3,
      "situation ": 5,
      "author": "老块",
      "createDate": "2016-06-08",
      "approver": "老八",
      "approveDate": "2018-07-08",
      "remark ": "happy",
    }]
  },
  onLoad:function(){
    wx.removeStorage({
      key: 'supervisionPlaninfo',
      success: function (res) {
        console.log(res)
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    console.log("Supervision发生了getAll事件")
    let url = app.globalData.url + 'Supervision/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
      if(res.code == 522){
        this.setData({
          mess : ""
        })
      }
      else{
        this.setData({
          mess: res.data,
          flag: 1
        })
      }
    }, (err) => {
      wx.showToast({
        title: 'getall error',
        duration: 1500
      })
      console.log('getall error')
    })
  },
  gotoOne: function (e) {
    console.log(e)
    let id = e.currentTarget.id
    wx.navigateTo({
      url: 'supervisionGetOne/supervisionGetOne?id=' + id  //==SupervisionPlan/getAll
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'supervisionAddOne/supervisionAddOne',
    })
  },
  supervisionPlanModify: function (e) {
    console.log(e)
    let i = e.currentTarget.dataset.index
    let info = this.data.info
    let id = this.data.id
    let planId = info[i].planId
    let content = info[i].content
    let object = info[i].object
    let dateFrequency = info[i].dateFrequency
    wx.navigateTo({
      url: '/pages/Supervision/SupervisionPlan/supervisionPlanModifyOne/supervisionPlanModifyOne?id=' + id + "&planId=" + planId + "&content=" + content + "&object=" + object + "&dateFrequency=" + dateFrequency
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
  supervisionDelete: function (e) {
    let id = e.currentTarget.id
    console.log("supervision发生了deleteOne事件，携带数据为",id)
    wx.request({
      url: app.globalData.url + 'Supervision/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "id": id
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '/pages/Supervision/Supervision/Supervision'
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
      },
      fail(err) {
        console.log('fail deleteone')
      },
      complete(fin) {
        console.log('final deleteone')
      }
    })
  },
  supervisionModify: function (e) {
    let id = e.currentTarget.id
    wx.navigateTo({
      url: '/pages/Supervision/Supervision/supervisionModifyOne/supervisionModifyOne?id=' + id
    })
  }
})