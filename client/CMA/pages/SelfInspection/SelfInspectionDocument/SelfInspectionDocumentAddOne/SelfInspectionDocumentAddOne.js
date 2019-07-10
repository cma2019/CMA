const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": null,
    "fileId": null,
    "fileName": null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
  },

  SelfInspectionDocumentAddOne: function (e) {
    console.log(e.detail.value)
    if(e.detail.value.fileName == null||e.detail.value.fileName == ''){
      wx.showToast({
        title:'文件名称为空',
        image:'/icons/warning/warning.png',
        duration:500,
        success:function(){
          setTimeout(function(){
          },300)
        }
      })
    }
    else{
      var id = this.data.id
      console.log('SelfInspectionDocument发生了AddOne事件，携带数据为：', e.detail.value.fileName)
      var myurl = app.globalData.url + 'SelfInspection/addOneFile?id=' + this.data.id + '&fileName=' +e.detail.value.fileName
      wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success(res1) {
          var mypath = res1.tempFiles[0].path
          app.wxUploadFile(myurl, mypath, null, (res) => {
            var obj = JSON.parse(res)
            console.log(obj)
            if(obj.code == 200){
              console.log("上传成功")
              wx.showToast({
                title: '上传成功',
                image: '/icons/ok/ok.png',
                duration: 500,
                success: function () {
                  setTimeout(function () {
                    wx.redirectTo({
                      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + id,
                    })
                  }, 300)
                }
              })
            }
            else{ //500
              console.log("已有同名文件")
              wx.showToast({
                title: '已有同名文件！',
                image: '/icons/warning/warning.png',
                duration: 500,
                success: function () {
                  setTimeout(function () {
                    wx.redirectTo({
                      url: '/pages/SelfInspection/SelfInspectionDocument/SelfInspectionDocument?id=' + id,
                    })
                  }, 300)
                }
              })

            }
          }, (err) => {
            console.log(err)
          })
        },
        fail: function (err) {
          console.log("get file failed")
          wx.showToast({
            title: '未选择文件',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 500)
            }
          })
        }
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
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