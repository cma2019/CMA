// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    
  },
  onShow :function()
  {
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffTraining/getOneTraining'
    let postdata = {
      "trainingId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      //console.log(res.data.program)
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
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      trainingDate: e.detail.value
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      let url = app.globalData.url + 'StaffTraining/modifyOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "trainingId": e.detail.value.trainingId,
        "program": e.detail.value.program,
        "trainingDate": e.detail.value.trainingDate,
        "place": e.detail.value.place,
        "presenter": e.detail.value.presenter,
        "content": e.detail.value.content,
        "note": e.detail.value.note
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        wx.showToast({
          title: '成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '../GetOneTraining/GetOneTraining',
              })
            }, 1000);
          }
        })
      }, (err) => {
        console.log('fail modify')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})