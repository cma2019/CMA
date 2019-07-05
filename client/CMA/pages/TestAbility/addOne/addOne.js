// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  newTestAbility: function (e) {
    console.log('begin add testability')
    var myurl = app.globalData.url + 'TestAbility/addOneFile';
    var mypath;
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        mypath = res.tempFiles[0].path
        app.wxUploadFile(myurl, mypath, null, (res) => {
          console.log("upload file success")
          console.log(res)
          wx.navigateBack({
            delta: 1
          })
        }, (err) => {
          console.log(err)
        })
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
      }
    })
  },
  AddOneTestAbility(e) {
    if (e.detail.value.year == "" ||
      e.detail.value.fileName == ""){
      console.log("message error")
      console.log(e.detail.value.year)
      console.log(e.detail.value.fileName)
    }
    else {
      let url = app.globalData.url + 'TestAbility/addOne'
      let data = {
        "year": e.detail.value.year,
        "fileName": e.detail.value.fileName,
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('add ability test successfully')
        console.log(res)
        if(res.code == 200){
          newTestAbility(e)
        }
      }, (err) => {
        console.log('fail intermediate check register')
      })
    }
  }
})