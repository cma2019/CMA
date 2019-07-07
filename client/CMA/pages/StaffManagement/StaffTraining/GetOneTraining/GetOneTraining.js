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
    let url = app.globalData.url + 'StaffTraining/getOneTraining'
    let postdata = {
      "trainingId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        trainingId: res.data.trainingId,
        program: res.data.program,
        trainingDate: res.data.trainingDate,
        place: res.data.place,
        presenter: res.data.presenter,
        content: res.data.content,
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
      url: '../ModifyOneTraining/ModifyOneTraining?id=' + target
    })
  },
  DeleteStaff(e) {
    let url = app.globalData.url + 'StaffTraining/deleteOne'
    let data = {
      "trainingId": this.data.id
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
  },
  SearchStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../GetTrainingPeople/GetTrainingPeople?id=' + target
    })
  }

})