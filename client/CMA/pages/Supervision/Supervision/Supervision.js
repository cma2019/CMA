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
    let url = app.globalData.url + 'Supervision/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
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
        console.log(this.data.mess)
        console.log("abcdefg")
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
    let target = e.currentTarget.id
    console.log(target)
    console.log("fhsdjkhfk")
    wx.navigateTo({
      url: 'supervisionGetOne/supervisionGetOne?id=' + target  //==SupervisionPlan/getAll
    })
  },
  gotoAdd: function () {
    wx.navigateTo({
      url: 'supervisionAddOne/supervisionAddOne',
    })
  }
})