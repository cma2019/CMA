const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {
    
  },
  onShow: function (options) {

  },
  bindDateChange1(e) {
    this.setData({
      startTime: e.detail.value
    })
  },
  bindDateChange2(e) {
    this.setData({
      endTime: e.detail.value
    })
  },
  ApplicationAdd: function (e) {
{
      var regNum = new RegExp('[0-9]', 'g');
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'AnnualTrainingPlan/addOne'
      let data = {
        "year": e.detail.value.year,
        "trainProject": e.detail.value.trainProject,
        "people": e.detail.value.people,
        "method": e.detail.value.method,
        "trainingTime": e.detail.value.trainingTime,
        "note": e.detail.value.note,
        "startTime": e.detail.value.startTime,
        "endTime":e.detail.value.endTime
      }
      if (e.detail.value.year == "" || e.detail.value.trainProject == ""
        || e.detail.value.people == ""
        || e.detail.value.method == "" || e.detail.value.trainingTime == ""
        || e.detail.value.note == "" || e.detail.value.startTime == ""
        || e.detail.value.endTime == "" 
      ) {
        wx.showToast({
          title: '空白输入',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
        console.log('错误(空白输入)')
      }
      else if (regNum.exec(e.detail.value.trainingTime) == null) {
        wx.showToast({
          title: '培训课时非数字',
          image: '/icons/warning/warning.png',
          duration: 1000

        })
      }
      else {
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
        if (res.msg == "已存在") {
          wx.showToast({
            title: '已存在',
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
        console.log('fail intermediate check register')
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