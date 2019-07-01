// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    year: 5,
    fileName: null,
    file: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      year: options.id
    })
  },

  onShow: function (options) {
    
  },

  modifyTestAbility: function (e) {
    console.log('modify modify')
    if (e.detail.value.year == "" || e.detail.value.fileName == "" ||
      e.detail.value.file == "") {
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
        "file": e.detail.value.file
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        /*
        if (res.data == "modify successfully.") {
          wx.navigateBack({
            delta: 1
          })
        }
        */
      }, (err) => {
        console.log('fail modify')
      })
    }
  }
})


