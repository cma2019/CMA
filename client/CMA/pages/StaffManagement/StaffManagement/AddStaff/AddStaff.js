const app = getApp()
Page({

  data: {
    radio: '1'
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
  onChange(event) {
    this.setData({
      checked: event.detail
    });
  },
  StaffAdd: function (e) {
    var regNum = new RegExp('[0-9]', 'g');
    if (e.detail.value.name == ""
      || e.detail.value.gender == ""
      || e.detail.value.department == "" || e.detail.value.position == ""
      || e.detail.value.title == "" || e.detail.value.degree == ""
      || e.detail.value.graduationSchool == ""
      || e.detail.value.graduationMajor == ""
      || e.detail.value.date == "" ||
      e.detail.value.workingYears == "") {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 1000

      })
      console.log('错误(空白输入)')
    }
    else if (regNum.exec(e.detail.value.workingYears)==null)
    {
      wx.showToast({
        title: '工龄非数字',
        image: '/icons/warning/warning.png',
        duration: 1000

      })
    }
    else if (e.detail.value.gender != "男" &&e.detail.value.gender != "女") {
      wx.showToast({
        title: '性别输入错误',
        image: '/icons/warning/warning.png',
        duration: 1000

      })
    }
    else {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
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
        if (res.msg == "失败,已存在员工") {
          wx.showToast({
            title: '已存在员工',
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