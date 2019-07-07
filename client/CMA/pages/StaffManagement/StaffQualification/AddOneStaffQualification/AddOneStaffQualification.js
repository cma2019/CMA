const app = getApp()
Page({

  data: {
    //"year": null,
    //"qualificationName": null,
    //"fileName": null
  },
  onLoad: function () {

  },
  newEquipment: function (e) {
    console.log(e.detail.value)
   
    var myurl2 = app.globalData.url + 'StaffQualification/addOneFile?staffId=' + e.detail.value.staffId + '&qualificationName=' + e.detail.value.qualificationName;
    /*
    var mydata = {
      "staffId": e.detail.value.staffId,
      "qualificationName": e.detail.value.qualificationName,
      //"fileLocation": e.detail.value.fileLocation
      //"fileName":e.detail.value.fileName
    };*/
    /*app.wxRequest(myurl1, 'POST', mydata, (res) => {
      console.log(res)
      if (res.msg == "不存在人员") {
        wx.showToast({
          title: '人员不存在',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      else*/
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
              var obj = JSON.parse(res)
              console.log("upload file success")
              console.log(res)
              //console.log(mydata)
              if (obj.msg == "不存在人员") {
                wx.showToast({
                  title: '人员不存在',
                  image: '/icons/warning/warning.png',
                  duration: 1000
                })
              }
              else{
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

              })}
            }, (err) => {
              console.log(err)
            })
            /* wx.redirectTo({
               url: '../PrintOneStaffQualification/PrintOneStaffQualification',
             })*/
          },
          fail: function (err) {
            console.log("get file failed")
            if (err.errMsg =="chooseMessageFile:fail cancel")
            {
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
    
    

  }

})