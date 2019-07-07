// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
   /* "id": "null",
    "name": "null",
    "gender": "null",
    "department": "null",
    "position": "null",
    "title": "null",
    "degree": "null",
    "graduationSchool": "null",
    "graduationMajor": "null",
    "graduationDate": "null",
    "workingYears": "null"*/
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
  },
  
  onShow: function (options) {
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffManagement/getOne'
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
        name: res.data.name,
        gender: res.data.gender,
        department: res.data.department,
        position: res.data.position,
        title: res.data.title,
        degree: res.data.degree,
        graduationSchool: res.data.graduationSchool,
        graduationMajor: res.data.graduationMajor,
        graduationDate: res.data.graduationDate,
        workingYears: res.data.workingYears
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      graduationDate: e.detail.value
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    var regNum = new RegExp('[0-9]', 'g');
   /* if (e.detail.value.object == "" || e.detail.value.content == "" ||
      e.detail.value.date == "" || e.detail.value.personInCharge == "" || e.detail.value.state == "") {
      wx.showToast({
        title: 'wrong message',
        duration: 2000
      })
      console.log('wrong message')
    }*/
    if(regNum.exec(e.detail.value.workingYears) == null)
{
  wx.showToast({
    title: '工龄非数字',
    image: '/icons/warning/warning.png',
    duration: 1000

  })
}
    else {
      console.log('modify，携带数据为：', e.detail.value)
      console.log('modify，携带数据为：', e.detail.value.object)

      let url = app.globalData.url + 'StaffManagement/modifyOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "id": e.detail.value.id,
        "name": e.detail.value.name,
        "gender": e.detail.value.gender,
        "department": e.detail.value.department,
        "position": e.detail.value.position,
        "title": e.detail.value.title,
        "degree": e.detail.value.degree,
        "graduationSchool": e.detail.value.graduationSchool,
        "graduationMajor": e.detail.value.graduationMajor,
        "graduationDate": e.detail.value.date,
        "workingYears": e.detail.value.workingYears,
        
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
        wx.showToast({
          title: '成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '../StaffManagement',
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