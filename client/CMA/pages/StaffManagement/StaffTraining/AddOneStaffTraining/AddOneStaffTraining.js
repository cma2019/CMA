const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },
  bindDateChange(e) {
    this.setData({
      trainingDate: e.detail.value
    })
  },
  StaffAdd: function (e) {

    if (e.detail.value.program == "" || e.detail.value.trainingId == ""
      || e.detail.value.trainingDate == ""
      || e.detail.value.place == "" || e.detail.value.presenter == ""
      ) {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
      console.log('错误(空白输入)')
    }
    else {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'StaffTraining/addOne'
      //let url1 = app.globalData.url + 'StaffTraining/addOneFile';
      let data = {
        //trainId
        "trainingId":e.detail.value.trainingId,
        "program": e.detail.value.program,
        "trainingDate": e.detail.value.trainingDate,
        "place": e.detail.value.place,
        "presenter": e.detail.value.presenter,
        "content": e.detail.value.content,
        "note": e.detail.value.note
       // "file": e.detail.value.file
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('send intermediate check message successfully')
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
     /* wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success: function (res) {
          console.log("get file success")
          console.log(res)
          console.log(res.tempFiles)
          console.log(res.tempFiles[0])
          console.log(res.tempFiles[0].path)
          //mypath = res.tempFiles[0].path
          app.wxUploadFile(url1, res.tempFiles[0].path, null, (res) => {
            console.log("upload file success")
            console.log(res)
           // console.log(mydata)

          }, (err) => {
            console.log(err)
          })
          wx.redirectTo({
            url: '../GetAllFileManagementReview/GetAllFileManagementReview?id=' + e.detail.value.year,
          })
        },
        fail: function (err) {
          console.log("get file failed")
          console.log(err)
        }

      })*/

    }

  },
  addfile: function () {
    
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})