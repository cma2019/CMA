// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    
  },
  onShow:function()
  {
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
        name: res.data.name,
        people: res.data.people,
        department: res.data.department,
        trainingUnit: res.data.trainingUnit,
        expense: res.data.expense,
        reason: res.data.reason,
        createDate: res.data.createDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      createDate: e.detail.value
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    if (e.detail.value.name == ""
      || e.detail.value.people == ""
      || e.detail.value.department == "" || e.detail.value.trainingUnit == ""
      || e.detail.value.expense == "" || e.detail.value.reason == ""
      || e.detail.value.createDate == "") {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
      console.log('错误(空白输入)')
    }
    else {
      console.log('modify，携带数据为：', e.detail.value)
      console.log('modify，携带数据为：', e.detail.value.object)

      let url = app.globalData.url + 'TrainingApplication/modifyOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "id": e.detail.value.id,
        "name": e.detail.value.name,
        "people": e.detail.value.people,
        "department": e.detail.value.department,
        "trainingUnit": e.detail.value.trainingUnit,
        "expense": e.detail.value.expense,
        "reason": e.detail.value.reason,
        "createDate": e.detail.value.date,
        

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
          title: '修改成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '../PrintOneApplication/PrintOneApplication',
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