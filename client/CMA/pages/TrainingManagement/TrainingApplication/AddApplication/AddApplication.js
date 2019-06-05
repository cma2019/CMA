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
  
  ApplicationAdd: function (e) {

    /*if (e.detail.value.name == ""
      || e.detail.value.people == ""
      || e.detail.value.department == "" || e.detail.value.trainingUnit == ""
      || e.detail.value.expense == "" || e.detail.value.reason == ""
      || e.detail.value.createDate == ""
      || e.detail.value.graduationMajor == ""
      || e.detail.value.date == "" ||
      e.detail.value.workingYears == "") {
      wx.showToast({
        title: '错误(空白输入)',
        icon: 'none',
        duration: 2000
      })
      console.log('错误(空白输入)')
    }
    else*/ {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      //console.log('form发生了add事件，携带数据为：', e.detail.value.id)

      //var mydata = JSON.stringify(e.detail.value)
      //console.log(mydata)

      //wx.request({

      //url: 'http://192.168.1.106:8004/StaffManagement/addOne',
      //url: app.globalData.url +'StaffManagement/addOne',
      //app.globalData.url + 'cma/getAll'
      //method: 'POST',
      /*header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },*/
      let url = app.globalData.url + 'TrainingApplication/addOne'
      let data = {
        "name": e.detail.value.name,
        "people": e.detail.value.people,
        "department": e.detail.value.department,
        "trainingUnit": e.detail.value.trainingUnit,
        "expense": e.detail.value.expense,
        "reason": e.detail.value.reason,
        "createDate": e.detail.value.date 
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('send intermediate check message successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
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