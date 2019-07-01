// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },

  InterCheckRegister(e) {
    if (e.detail.value.year == "" ||
      e.detail.value.fileName == "" ||
      e.detail.value.file == "") {
      console.log("message error")
      console.log(e.detail.value.year)
      console.log(e.detail.value.fileName)
      console.log(e.detail.value.file)
    }
    else {
      let url = app.globalData.url + 'TestAbility/addOne'
      let data = {
        "year": e.detail.value.year,
        "fileName": e.detail.value.fileName,
        "file": e.detail.value.file
      }
      console.log(e)
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('add ability test successfully')
        console.log(res)
        console.log(res.msg)
        console.log(res.code)
      }, (err) => {
        console.log('fail intermediate check register')
      })
    }
  }
})