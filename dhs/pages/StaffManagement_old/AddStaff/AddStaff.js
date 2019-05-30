
Page({

  data: {

  },

  onLoad: function (options) {

  },
  StaffAdd: function (e) {

    if (e.detail.value.name == ""
    ||e.detail.value.gender == "" 
    || e.detail.value.department == "" || e.detail.value.position == "" 
    || e.detail.value.title == "" || e.detail.value.degree == "" 
    || e.detail.value.graduationSchool == "" 
    || e.detail.value.graduationMajor == "" 
    || e.detail.value.graduationDate == ""||
      e.detail.value.workingYears == "" ) 
    {
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

    wx.request({
      
      //url: 'http://192.168.1.106:8004/StaffManagement/addOne',
      url: 'http://192.168.1.106:8004/cma/StaffManagement/addOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
          data: {
          "name": e.detail.value.name,
          "gender": e.detail.value.gender,
          "department": e.detail.value.department,
          "position": e.detail.value.position,
          "title": e.detail.value.title,
          "degree": e.detail.value.degree,
          "graduationSchool": e.detail.value.graduationSchool,
          "graduationMajor": e.detail.value.graduationMajor,
          "graduationDate": e.detail.value.graduationDate,
          "workingYears": e.detail.value.workingYears,
        },
        success(res) {
          console.log(res.data)
          if (res.code == 200) {//需要修改
            wx.showToast({
              title: '人员添加成功',
              duration: 1500
            })
            wx.navigateTo({
              url: 'pages/StaffManagement/StaffManagement',
            })
          }
        },
        fail(err) {
          wx.showToast({
            title: '添加失败',
            duration:1500
          })
          console.log('添加失败')
        },
        complete(fin) {
          console.log('final')
        }
      })

    }

  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})