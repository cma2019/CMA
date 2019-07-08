const app = getApp()
Page({

  data: {
    "year": null,
    "fileId": null,
    //"fileName": null
  },

  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl2 = app.globalData.url + 'ManagementReview/addOneFile?year=' + e.detail.value.year;
   
    //选择文件
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        //打印报错信息
        console.log("get file success")
        console.log(res)
        console.log(res.tempFiles)
        console.log(res.tempFiles[0])
        console.log(res.tempFiles[0].path)
        //mypath = res.tempFiles[0].path
        //上传文件，调用接口上传文件
        app.wxUploadFile(myurl2, res.tempFiles[0].path, null, (res) => {
          console.log("upload file success")
          console.log(res)
          
        }, (err) => {
          console.log(err)
        })
        //打印成功信息
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
      //上传失败
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
        //因为取消选择文件导致上传失败的弹窗
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