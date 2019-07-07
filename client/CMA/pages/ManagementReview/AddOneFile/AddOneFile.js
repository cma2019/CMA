const app = getApp()
Page({

  data: {
    "year": null,
    "fileId": null,
    //"fileName": null
  },

  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl1 = app.globalData.url + 'ManagementReview/addFileData';
    var myurl2 = app.globalData.url + 'ManagementReview/addOneFile';
    var mydata = {
      "year": e.detail.value.year,
      //"fileName":e.detail.value.fileName
    };
    app.wxRequest(myurl1, 'POST', mydata, (res) => {
      console.log(res)
      
    }, (err) => {
      console.log(err)
    })
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        console.log(res.tempFiles)
        console.log(res.tempFiles[0])
        console.log(res.tempFiles[0].path)
        //mypath = res.tempFiles[0].path
        app.wxUploadFile(myurl2, res.tempFiles[0].path, null, (res) => {
          console.log("upload file success")
          console.log(res)
          console.log(mydata)
          
        }, (err) => {
          console.log(err)
        })
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
      
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
        if (err.errMsg == "chooseMessageFile:fail cancel") {
          wx.showToast({
            title: '取消上传',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }
      
    })
    
  },
  onLoad: function (options) {  
    this.setData({

      year: options.id
    })

  }
  
})