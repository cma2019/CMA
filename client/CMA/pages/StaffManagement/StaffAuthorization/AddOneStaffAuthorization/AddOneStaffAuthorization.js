const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },
  bindDateChange(e) {
    this.setData({
      authorizerDate: e.detail.value
    })
  },
/* add*/
  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffAuthorization/addOne'
      let data = {
        "id": e.detail.value.id,
        "authorizerId": e.detail.value.authorizerId,
        "content": e.detail.value.content,
        "authorizerDate": e.detail.value.authorizerDate
      }
      console.log(url)
      console.log(data)
      if (e.detail.value.id == "" || e.detail.value.authorizerId == "" || e.detail.value.content == "" || e.detail.value.authorizerDate=="")
      {
        wx.showToast({
          title: '错误，空白输入',
          image: '/icons/warning/warning.png',
          duration:2000
        })
      }
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if(res.msg=="被授权人不存在")
        {
          wx.showToast({
            title: '被授权人不存在',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
        else if (res.msg == "授权人不存在") {
          wx.showToast({
            title: '授权人不存在',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
        else{
          wx.showToast({
            title: '成功',
            //icon: 'success',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
               wx.navigateBack({
                 delta:1
               })
              }, 1000);
            }

          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
      })

    }

  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})