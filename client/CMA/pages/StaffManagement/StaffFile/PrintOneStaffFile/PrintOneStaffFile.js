// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    console.log(options)
    this.setData({

      id: options.id
    })
    console.log('getone id')
    
  },
  onShow: function () {
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffFile/getOne'
    let postdata = {
      "staffId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      if (res.code == 210) {
        wx.showToast({
          title: '档案不存在!',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      this.setData({
        staffId: res.data.staffId,
        name: res.data.name,
        fileId: res.data.fileId,
        fileLocation: res.data.fileLocation,
        fileImage: res.data.fileImage
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  ModifyData(e) {
    let target = this.data.id
    console.log('getall id')
    console.log(target)
    wx.navigateTo({
      url: '../ModifyOneStaffFile/ModifyOneStaffFile?id=' + target
    })

  },
  ModifyStaff(e) {
    console.log(e)
    //let target = this.data.id
    //console.log(target)
    let url1 = app.globalData.url + 'StaffFile/modifyOneFile'
    let postdata1 = {
      "staffId": this.data.id
    }
    console.log(postdata1)
    app.wxRequest(url1, 'POST', postdata1, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
    }, (err) => {
      console.err('get one error')
    })
    var myurl2 = app.globalData.url + 'StaffFile/addOneFile';
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
          wx.showToast({
            title: '文件修改成功',
            //icon: 'success',
            image: '/icons/ok/ok.png',
            duration: 1000
          })
          //console.log(mydata)

        }, (err) => {
          console.log(err)
        })
        /*console.log(this.data.year)
        console.log(this.year)
        wx.redirectTo({
          url: '../GetAllFileManagementReview/GetAllFileManagementReview?id=' + this.data.year,
        })*/
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


  },

  DownloadStaff(e) {
    //var that = this
    var myurl = app.globalData.url + 'StaffFile/getImage/' + this.data.id;
    var myFilePath
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download now")
          console.log(res)
          myFilePath = res.savedFilePath
          console.log(myFilePath)
          wx.showToast({
            title: '文件下载成功',
            //icon: 'success',
            image: '/icons/ok/ok.png',
            duration: 1000
          })
        },
        fail: function (err) {
          console.log(err)
        }
      })
    }, (err) => {
      console.log(err)
    })
  },
  DeleteStaff(e) {
    let url2 = app.globalData.url + 'StaffFile/deleteOne'
    let data2 = {
      "staffId": this.data.id
    }
    app.wxRequest(url2, 'POST', data2, (res) => {
      console.log('delete successfully')
      wx.showToast({
        title: '文件删除成功',
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

    }, (err) => {
      console.log('delete failed')
    })
  }

})