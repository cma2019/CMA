const app = getApp()
Page({

  data: {
    //trainingId: null,
    //id:null,
    str:"12",
    str2:[]
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
 

  },
  onShow: function () {
    this.setData({
      trainingId: this.data.trainingId
    })
  },
  ApplicationAdd: function (e) {
    {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffTraining/addTrainingResult'
      let data = {
        "trainingId": e.detail.value.trainingId,
        "id": e.detail.value.id,
        "result":e.detail.value.result
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if (res.code == 210) {
          wx.showToast({
            title: '添加失败!',
            icon: "none",
            duration: 2000
          })
        }
        else
        {
          wx.showToast({
            title: '添加成功!',
            icon: "success",
            duration: 2000
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