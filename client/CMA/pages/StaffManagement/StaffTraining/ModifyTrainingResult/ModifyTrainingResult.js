// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
    str: "12",
    str2: []
  },

  onLoad: function (options) {
    this.data.str = String(options.id)
    console.log(this.data.str)
    this.data.str2 = this.data.str.split(",")
    console.log(this.data.str2)
    this.setData({
      trainingId: this.data.str2[0],
      id: this.data.str2[1]
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffTraining/getOne'
    let postdata = {
      "trainingId": this.data.trainingId,
      "id":this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      //console.log(res.data.program)
      this.setData({
        trainingId: res.data.trainingId,
        id: res.data.id,
        result: res.data.result
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      let url = app.globalData.url + 'StaffTraining/modifyResult';
      console.log(url)
      //console.log(this.data.planId)
      let data = {
        //"id ":this.data.id,
        "trainingId": e.detail.value.trainingId,
        "id": e.detail.value.id,
        "result": e.detail.value.result
      };

      console.log(data)
      if (e.detail.value.result != "合格" && e.detail.value.result != "不合格") {
        wx.showToast({
          title: '培训结果错误',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      else {
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        if (res.msg == "未有结果") {
          wx.showToast({
            title: '未有结果',
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
                  delta: 1
                })
              }, 1000);
            }
          })
        }
      }, (err) => {
        console.log('fail modify')
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