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
    let url = app.globalData.url + 'StaffAuthorization/getOne'
    let postdata = {
      "authorizationId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      console.log(res.data.id)
      this.setData({
        authorizationId: res.data.authorizationId,
        id: res.data.id,
        authorizerId: res.data.authorizerId,
        content: res.data.content,
        authorizerDate: res.data.authorizerDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      authorizerDate: e.detail.value
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

      let url = app.globalData.url + 'StaffAuthorization/modifyOne';
      console.log(url)
      let data = {
        //"id ":this.data.id,
        "authorizationId": e.detail.value.authorizationId,
        "id": e.detail.value.id,
        "authorizerId": e.detail.value.authorizerId,
        "content": e.detail.value.content,
        "authorizerDate": e.detail.value.authorizerDate,
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