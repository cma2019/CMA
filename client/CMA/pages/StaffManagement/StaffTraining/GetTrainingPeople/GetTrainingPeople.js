// pages/StaffManagement/StaffManagement.js
const app = getApp()
Page({

  data: {
    mess:[]
  },

  onLoad: function (options) {
    //console.log(this.data.planId)
    this.setData({
      id: options.id
    })
    //console.log(this.data.year)
    let url = app.globalData.url + 'StaffTraining/getTrainingPeople'
    let postdata = {
      "trainingId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        mess: res.data
      })

      console.log(this.data.mess)
    }, (err) => {
      //console.err('getone error')
      wx.showToast({
        title: 'getone error',
        duration: 1500
      })
      console.log('getone error')
    })
  },
  onShow: function () {
  },

  gotoAdd(e) {
    wx.navigateTo({
      url: '../AddTrainingPeople/AddTrainingPeople?id='+this.data.id,
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = [e.currentTarget.id,this.data.id]
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: 'PrintOneAnnualTrainingPlan/PrintOneAnnualTrainingPlan?id=' + target
    })
  }
})