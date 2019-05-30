const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  bindDateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },
  StaffAdd: function (e) {

    if (e.detail.value.name == ""
      || e.detail.value.gender == ""
      || e.detail.value.department == "" || e.detail.value.position == ""
      || e.detail.value.title == "" || e.detail.value.degree == ""
      || e.detail.value.graduationSchool == ""
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
    else {
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
      let url = app.globalData.url + 'StaffManagement/addOne'
      let data = {
        "name": e.detail.value.name,
        "gender": e.detail.value.gender,
        "department": e.detail.value.department,
        "position": e.detail.value.position,
        "title": e.detail.value.title,
        "degree": e.detail.value.degree,
        "graduationSchool": e.detail.value.graduationSchool,
        "graduationMajor": e.detail.value.graduationMajor,
        "graduationDate": e.detail.value.date,
        "workingYears": e.detail.value.workingYears
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