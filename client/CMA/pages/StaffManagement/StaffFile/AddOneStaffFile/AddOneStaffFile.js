const app = getApp()
Page({

  data: {
    //"year": null,
    //"fileId": null,
    //"fileName": null
  },
  onLoad: function () {

  },
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl1 = app.globalData.url + 'StaffFile/addOne';
    var myurl2 = app.globalData.url + 'StaffFile/addOneFile';
    var mydata = {
      "staffId": e.detail.value.staffId,
      "fileId": e.detail.value.fileId,
      "fileLocation": e.detail.value.fileLocation
      //"fileName":e.detail.value.fileName
    };
    app.wxRequest(myurl1, 'POST', mydata, (res) => {
      console.log(res)
      if (res.msg == "人员不存在") {
        wx.showToast({
          title: '人员不存在',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      else if (res.msg == "失败,已存在") {
        wx.showToast({
          title: '失败,已存在',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      else
      {
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
              wx.showToast({
                title: '成功',
                //icon: 'success',
                image: '/icons/ok/ok.png',
                duration: 1000,
                success: function () {
                  setTimeout(function () {
                    wx.navigateBack({
                      delta:1
                    })
                  }, 1000);
                }

              })
            }, (err) => {
              console.log(err)
            })

          },
          fail: function (err) {
            console.log("get file failed")
            if (err.errMsg == "chooseMessageFile:fail cancel") {
              wx.showToast({
                title: '取消上传',
                image: '/icons/warning/warning.png',
                duration: 1000
              })
            }
            console.log(err)
          }

        })

      }
      /*else {
        wx.showToast({
          title: '成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: '1000',
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '../StaffAuthorization',
              })
            }, 1000);
          }

        })
      }*/
    }, (err) => {
      console.log(err)
    })
    
  }

})