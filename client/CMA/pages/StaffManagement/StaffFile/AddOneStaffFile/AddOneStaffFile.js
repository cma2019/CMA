const app = getApp()
Page({

  data: {
    //"year": null,
    //"fileId": null,
    //"fileName": null
  },

  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl1 = app.globalData.url + 'StaffFile/addOne';
    var myurl2 = app.globalData.url + 'StaffFile/addOneFile';
    var mydata = {
      "id": e.detail.value.id,
      "fileId": e.detail.value.fileId,
      "fileLocation": e.detail.value.fileLocation
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
        wx.redirectTo({
          url: '../PrintOneStaffFile/PrintOneStaffFile?id=' + e.detail.value.id,
        })
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
      }

    })

  },
  onLoad: function () {

  }

})