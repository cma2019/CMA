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
      date: e.detail.value
    })
  },
  StaffAdd: function (e) {
    var regNum = new RegExp('[a-zA-Z]', 'g');
    if (e.detail.value.year == "" ||
      e.detail.value.date == "") {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        //icon: 'none',
        duration: 1000
      })
      console.log('错误(空白输入)')
    } 
    else if (regNum.exec(e.detail.value.year) != null) {

      wx.showToast({
        title: '年份输入数字',
        //icon: 'none',
        image: '/icons/warning/warning.png',
        duration: 2000
      })
    }
    else {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'ManagementReview/addOne'
      let data = {
        "year": e.detail.value.year,  
        "date": e.detail.value.date
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('send intermediate check message successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if(res.msg=="已存在")
        {
          wx.showToast({
            title: '已存在',
            image: '/icons/warning/warning.png',
            //icon: 'none',
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
                delta: 1
              })
            }, 1000);
          }
        })}
        
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