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
  
  onShow: function () {
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
        id: res.data.id,
        situation: res.data.situation,
        approver: res.data.approver,
        approveDate: res.data.approveDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      approveDate: e.detail.value
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    if (e.detail.value.id == "" || e.detail.value.situation == "" ||
      e.detail.value.approver == "" || e.detail.value.approveDate == "" ) {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
      console.log('wrong message')
    }
    else if (e.detail.value.situation != "1" && e.detail.value.situation != "2") {
      wx.showToast({
        title: '状态输入错误',
        image: '/icons/warning/warning.png',
        duration: 1000

      })
    }
    else {
      console.log('modify，携带数据为：', e.detail.value)
      console.log('modify，携带数据为：', e.detail.value.object)

      let url = app.globalData.url + 'TrainingApplication/approveOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "id": e.detail.value.id,
        "situation": e.detail.value.situation,
        "approver": e.detail.value.approver,
        "approveDate": e.detail.value.date
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
        if (res.msg == "已审查") {
          wx.showToast({
            title: '已审查',
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
                  url: '../PrintOneApplication/PrintOneApplication',
                })
              }, 1000);
            }
          })
        }
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