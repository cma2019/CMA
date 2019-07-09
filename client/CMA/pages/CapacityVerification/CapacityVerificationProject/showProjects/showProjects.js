// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "planId": null,
    "mess":null,
    "temp":
      [{
        "projectId": 3,
        "planId": "2",
        "name": "namename",
        "method": "22",
        "state": "2000",
        "note": "note"
      },
      {
        "projectId": 5,
        "planId": "2",
        "name": "organizer",
        "method": "22",
        "state": "2000",
        "note": "note"
      }]
  },

  onLoad: function (options) {
    //使用其他界面的信息初始化planid信息
    this.setData({
      planId: options.id
    })
  },
  goback(e){
    let target = this.data.planId
    //此处的返回不是返回上一界面，而是返回到相信planid的getone界面
    wx.redirectTo({
      url: '../../CapacityVerificationPlan/getOne/getOne?id='+target,
    })
  },
  onShow: function (options) {  
    let url = app.globalData.url + 'CapacityVerification/getAllProject'
    let data = {
     "planId": this.data.planId,
    }
    //使用planid，获取其下的所有project信息
    console.log("show pros")
    console.log(this.data.planId)
    app.wxRequest(url, 'GET', data, (res) => {
      //rescode==200时，获取成功，修改mess值，打印信息
      if(res.code == 200){
        this.setData({
          mess: res.data
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      console.log(res)
      console.log('get projects from planid')
    }, (err) => {
      console.log('fail projects from planid')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  gotoAdd(e) {
    console.log(e)
    let target = this.data.planId
    console.log('getone id')
    console.log(target)
    //添加project时，需传递添加的planid信息
    wx.navigateTo({
      url: '../addOneProject/addOneProject?id=' + target,
    })
  },

  gotoOne(e) {
    console.log("pro go to one")
    console.log(e)
    console.log(e.currentTarget)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    //显示project详细信息时，需要知道project的id
    wx.navigateTo({
      url: '../getOneProject/getOneProject?id=' + target
    })
  }
})
