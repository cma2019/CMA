// pages/TestingInstitutionManagement/Certificate/CertificateGetOne/CertificateGetOne.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试数据
  data: {
    fileId: "test ID",
    fileName: "test.docx"
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //构造并跳转回查找页面
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/Certificate/Certificate',
    })
  },
  //删除按钮的处理函数
  mydelete: function (e) {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'Certificate/deleteOne/' + that.data.fileId;
    console.log(that.data.fileId);
    //请求后端
    app.wxRequest(myurl, 'POST', null, (res) => {
      //成功处理函数
      console.log(res)
      //成功提示
      wx.showToast({
        title: '删除成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          //延时
          setTimeout(function () {
            //跳转回查找界面
            wx.redirectTo({
              url: '/pages/TestingInstitutionManagement/Certificate/Certificate',
            })
          }, 1000);
        }
      })
    }, (err) => {
      //失败处理函数
      console.log(err)
    })
  },
  //下载按钮的处理函数
  mydownload: function (e) {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'Certificate/download/' + that.data.fileId;
    //存储路径
    var myFilePath
    //请求后端
    app.wxDownloadFile(myurl, (res) => {
      //成功处理函数
      console.log(res)
      console.log(res.tempFilePath)
      //存储文件
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          //成功处理函数
          console.log(res)
          myFilePath = res.savedFilePath
          console.log(myFilePath)
          //成功提示
          wx.showToast({
            title: '下载成功',
            icon: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              //延时
              setTimeout(function () {
              }, 1000);
            }
          })
        },
        fail: function (err) {
          //失败处理函数
          console.log(err)
        }
      })
    }, (err) => {
      //失败处理函数
      console.log(err)
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    //保存指针
    var that = this;
    console.log(option)
    //存储页面跳转时传递的参数
    that.setData({
      fileId: option.fileId,
      fileName: option.fileName
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