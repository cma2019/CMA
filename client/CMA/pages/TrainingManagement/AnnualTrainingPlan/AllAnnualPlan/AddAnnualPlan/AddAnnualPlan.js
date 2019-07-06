const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },
  bindDateChange1(e) {
    this.setData({
      createDate: e.detail.value
    })
  },
  
  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'AnnualTrainingPlan/addAnnualPlan'
      let data = {
        "year": e.detail.value.year,
        "author": e.detail.value.author,
        "createDate": e.detail.value.createDate
      }
      if (e.detail.value.year == "" || e.detail.value.author == ""
        || e.detail.value.createDate == ""
      ) {
        wx.showToast({
          title: '空白输入',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
        console.log('错误(空白输入)')
      }
      else {
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if (res.msg == "失败") {
          wx.showToast({
            title: '已存在',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
        else {
          wx.showToast({
            title: '成功',
            //icon: 'success',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
                wx.navigateTo({
                  url: '../AllAnnualPlan',
                })
              }, 1000);
            }
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
      })
      }
    }

  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})