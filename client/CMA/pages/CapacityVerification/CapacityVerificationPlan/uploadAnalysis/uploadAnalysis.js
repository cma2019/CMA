// pages/CapacityVerification/CapacityVerificationPlan/uploadAnalysis/uploadAnalysis.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id":null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('upload options')
    console.log(options)
    this.setData({
      id: options.id
    })
  },
  UploadAnalysis(e) {
    console.log('UploadAnalysis now')
    let url = app.globalData.url + 'CapacityVerification/uploadAnalysis'
      let data = {
        "id": this.data.id
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('add ability test successfully')
        console.log(res)
        if (res.code == 200) {
          console.log('begin add testability')
          var myurl = app.globalData.url + 'CapacityVerification/uploadAnalysisFile';
          var mypath;
          wx.chooseMessageFile({
            count: 1,
            type: 'all',
            success: function (res) {
              console.log("get file success")
              console.log(res)
              mypath = res.tempFiles[0].path
              console.log("mypath")
              console.log(mypath)
              app.wxUploadFile(myurl, mypath, null, (res) => {
                console.log("upload file success")
                console.log(res)
                var obj = JSON.parse(res);
                console.log(obj)
                console.log(obj.code)
                if (obj.code == 200){
                  wx.showToast({
                    title: '上传成功',
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
                }else{
                  wx.showToast({
                    title: '上传失败',
                    image: '/icons/warning/warning.png',
                    duration: 1000
                  })
                }
              }, (err) => {
                console.log(err)
                wx.showToast({
                  title: '上传失败',
                  image: '/icons/warning/warning.png',
                  duration: 1000
                })
              })
            },
            fail: function (err) {
              console.log("get file failed")
              console.log(err)
              wx.showToast({
                title: '上传取消',
                image: '/icons/warning/warning.png',
                duration: 1000
              })
            }
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})