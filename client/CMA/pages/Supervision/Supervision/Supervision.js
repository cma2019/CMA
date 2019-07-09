const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess":null,
    "flag":0
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
      if (res.code == 200) {
        this.setData({
          mess: res.data,
          flag: 1
        })
        console.log("Supervision-getAll成功")
      }
      else { //210
        this.setData({
          mess: null,
          flag: 0
        })
        console.log("Supervision-getAll无有效信息")
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
    let url = app.globalData.url + 'Supervision/deleteOne'
    let postdata={
      "id": id
    }
    app.wxRequest(url, 'POST', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '/pages/Supervision/Supervision/Supervision'
              })
            }, 300)
          }
        })
      }
      else {
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 300
        })
        console.log('删除失败')
      }
    }, (err) => {
      console.log('fail deleteone')
    })
  },
  supervisionModify: function (e) {
    console.log(e)
    let index = e.currentTarget.dataset.index
    let id = this.data.mess[index].id
    wx.navigateTo({
      url: '/pages/Supervision/Supervision/supervisionModifyOne/supervisionModifyOne?id=' + id+'&remark='+this.data.mess[index].remark
    })
  }
})