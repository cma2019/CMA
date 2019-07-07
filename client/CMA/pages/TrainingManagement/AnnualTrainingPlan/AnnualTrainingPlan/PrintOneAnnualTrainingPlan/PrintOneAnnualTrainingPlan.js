// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    console.log(options)
    this.setData({
      
      id: options.id
    })
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'AnnualTrainingPlan/getOne'
    let postdata = {
      "planId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      console.log(res.data.planId)
      //console.log(res.data[0].id)
      this.setData({
        year: res.data.year,
        planId: res.data.planId,
        trainProject: res.data.trainProject,
        people: res.data.people,
        method: res.data.method,
        trainingTime: res.data.trainingTime,
        startTime: res.data.startTime,
        endTime: res.data.endTime,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  onShow:function()
  {
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'AnnualTrainingPlan/getOne'
    let postdata = {
      "planId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      console.log(res.data.planId)
      //console.log(res.data[0].id)
      this.setData({
        year: res.data.year,
        planId: res.data.planId,
        trainProject: res.data.trainProject,
        people: res.data.people,
        method: res.data.method,
        trainingTime: res.data.trainingTime,
        startTime: res.data.startTime,
        endTime: res.data.endTime,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  ModifyStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ModifyAnnualTrainingPlan/ModifyAnnualTrainingPlan?id=' + target
    })
  },
  DeleteStaff(e) {
    let url = app.globalData.url + 'AnnualTrainingPlan/deleteOne'
    let data = {
      "planId": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
      wx.showToast({
        title: '删除成功',
        //icon: 'success',
        image: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.navigateBack({
              delta: 1
            })
          }, 1000);
        }
      })
    }, (err) => {
      console.log('delete failed')
    })
  }

})