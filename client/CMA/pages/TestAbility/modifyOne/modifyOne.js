// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "year": 5,
    "fileName": 112,
    "file": 213,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      year: options.year,
      fileName : options.name
    })
  },

  onShow: function (options) {
    
  },
  gotologin(e) {
    wx.navigateBack({
      delta: 1
    })
  },
  modifyTestAbility: function (e) {
    console.log('modify modify')
    if (e.detail.value.year == "" 
    || e.detail.value.fileName == ""){
      wx.showToast({
        title: 'wrong message',
        duration: 2000
      })
      console.log('wrong message')
    }
    else {
      console.log('modify，携带数据为：', e.detail.value)
      console.log('modify，携带数据为：', e.detail.value.year)

      let url = app.globalData.url + 'TestAbility/modifyOne';
      console.log(url)
      let data = {
        "year": e.detail.value.year,
        "fileName": e.detail.value.fileName,
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        if (res.code == 200) {
          console.log('begin add testability')
          var myurl = app.globalData.url + 'TestAbility/modifyOneFile';
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
                    title: '修改成功',
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
                    title: '修改失败',
                    image: '/icons/warning/warning.png',
                    duration: 1000
                  })
                }
              }, (err) => {
                console.log(err)
                wx.showToast({
                  title: '修改失败',
                  image: '/icons/warning/warning.png',
                  duration: 1000
                })
              })
            },
            fail: function (err) {
              console.log("get file failed")
              console.log(err)
              wx.showToast({
                title: '修改失败',
                image: '/icons/warning/warning.png',
                duration: 1000
              })
            }
          })
        }else{
          wx.showToast({
            title: '修改失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail modify')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
  /*
  newTestAbility: function (e) {
    
  }
  */
})


