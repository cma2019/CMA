const app = getApp()
Page({

  data: {
      //trainingId:null
      //id:[]
  },

  onLoad: function (options) {
    this.setData({
      trainingId: options.id
    })

  },
  onShow: function () {
    /*this.setData({
      trainingId: this.data.trainingId
    })*/
  },
  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffTraining/addTrainingPeople'
      let data = {   
        "trainingId": e.detail.value.trainingId,
        "id": e.detail.value.id
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if (res.msg == "无法找到该人员") {
          wx.showToast({
            title: '无法找到该人员',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
        else if (res.msg == "已存在该人员") {
          wx.showToast({
            title: '已存在该人员',
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
                  url: '../GetOneTrainingPeople/GetOneTrainingPeople',
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