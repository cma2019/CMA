// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'TrainingApplication/getOne'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      console.log(res.data.name)
      this.setData({
        /*object: res.data[0].object,
        content: res.data[0].content,
        checkDate: res.data[0].checkDate,
        personInCharge: res.data[0].personInCharge,
        state: res.data[0].state*/
        id: res.data.id,
        situation:res.data.situation,
        approver:res.data.approver,
        approveDate: res.data.approveDate


      })
    }, (err) => {
      console.err('get one error')
    })
  },
  /*
  onShow: function (options) {
    this.setData({
      id: options.id
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'TrainingApplication/getOne'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      console.log(res.data.name)
      this.setData({
        
        id: res.data.id,
        situation: res.data.situation,
        approver: res.data.approver,
        approveDate: res.data.approveDate


      })
    }, (err) => {
      console.err('get one error')
    })
  },*/
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      approveDate: e.detail.value
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
   /* if (e.detail.value.object == "" || e.detail.value.content == "" ||
      e.detail.value.date == "" || e.detail.value.personInCharge == "" || e.detail.value.state == "") {
      wx.showToast({
        title: 'wrong message',
        duration: 2000
      })
      console.log('wrong message')
    }
    else */{
      console.log('modify，携带数据为：', e.detail.value)
      console.log('modify，携带数据为：', e.detail.value.object)

      let url = app.globalData.url + 'TrainingApplication/approveOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "id": e.detail.value.id,
        "situation": e.detail.value.situation,
        "approver": e.detail.value.approver,
        "approveDate": e.detail.value.date


      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        /*
        if (res.data == "modify successfully.") {
          wx.navigateBack({
            delta: 1
          })
        }
        */
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