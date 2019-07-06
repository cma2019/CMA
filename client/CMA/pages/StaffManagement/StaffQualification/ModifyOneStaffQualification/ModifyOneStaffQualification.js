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
    let url = app.globalData.url + 'StaffQualification/getOne'
    let postdata = {
      "qualificationId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")

      this.setData({
        staffId: res.data.staffId,
        qualificationId: res.data.qualificationId,
        qualificationName: res.data.qualificationName
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      let url = app.globalData.url + 'StaffQualification/modifyOne';
      console.log(url)
      console.log(this.data.id)
      let data = {
        //"id ":this.data.id,
        "qualificationId": e.detail.value.qualificationId,
        "qualificationName": e.detail.value.qualificationName
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        wx.showToast({
          title: '成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '../PrintOneStaffQualification/PrintOneStaffQualification',
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