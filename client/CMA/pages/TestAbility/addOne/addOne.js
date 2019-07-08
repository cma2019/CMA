// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  /*
  newTestAbility: function (e) {
    
  },
  */
  gotologin(e) {
    wx.navigateBack({
      delta: 1
    })
  },
  AddOneTestAbility(e) {
    if (e.detail.value.year == null ||
      e.detail.value.fileName == null ||
      e.detail.value.year == "" ||
      e.detail.value.fileName == "") {
      console.log("message error")
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
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
                var obj = JSON.parse(res);
                console.log(obj)
                console.log(obj.code)
                if (obj.code == 200){
                  wx.showToast({
                    title: '添加成功',
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
                }else{
                  wx.showToast({
                    title: '添加失败',
                    image: '/icons/warning/warning.png',
                    duration: 1000
                  })
                }
              }, (err) => {
                console.log(err)
                wx.showToast({
                  title: '连接失败',
                  image: '/icons/warning/warning.png',
                  duration: 1000
                })
              })
            },
            fail: function (err) {
              console.log("get file failed")
              console.log(err)
              wx.showToast({
                title: '连接失败',
                image: '/icons/warning/warning.png',
                duration: 1000
              })
            }
          })
        }else{
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
        wx.showToast({
          title: '添加取消',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
})