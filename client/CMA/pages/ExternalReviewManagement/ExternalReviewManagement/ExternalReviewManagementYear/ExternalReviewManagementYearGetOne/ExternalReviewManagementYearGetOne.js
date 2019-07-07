// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYearGetOne/ExternalReviewManagementYearGetOne.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试数据
  data: {
    id:1,
    year:2019,
    fileId:1,
    fileName:"1.pdf"
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //保存指针
    var that = this
    //构造并跳转回查找页面
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
    })
  },
  //删除按钮的处理函数
  mydelete: function (e) {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'ExternalReviewDocument/deleteOne/' + that.data.id;
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
              url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
            })
          }, 1000);
        }
      })
    }, (err) => {
      //失败处理函数
      console.log(err)
    })
  },
  //修改按钮的处理函数
  viewDetail: function (e) {
    //保存指针
    var that = this
    //配置参数并跳转至修改界面
    console.log(that.data)
    //构造url1
    var myurl1 = app.globalData.url + 'ExternalReviewDocument/modifyFormData/' + that.data.id;
    //构造url2
    var myurl2 = app.globalData.url + 'ExternalReviewDocument/modifyFile/';
    //构造参数
    var mydata = {
      "year": that.data.year,
      "fileId": that.data.fileId
    };
    //请求后端
    app.wxRequest(myurl1, 'POST', mydata, (res) => {
      //成功处理函数
      console.log(res)
      //选择文件
      wx.chooseMessageFile({
        count: 1,
        type: 'all',
        success: function (res) {
          //成功处理函数
          console.log("get file success")
          console.log(res)
          //获取文件路径
          var mypath = res.tempFiles[0].path
          //请求后端
          app.wxUploadFile(myurl2, mypath, null, (res) => {
            //成功处理函数
            console.log("upload file success")
            console.log(res)
            //成功提示
            wx.showToast({
              title: '修改成功',
              icon: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                //延时
                setTimeout(function () {
                  //跳转回查找界面
                  wx.redirectTo({
                    url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear?year=' + that.data.year,
                  })
                }, 1000);
              }
            })
          }, (err) => {
            //失败处理函数
            console.log(err)
          })
        },
        fail: function (err) {
          //失败处理函数
          console.log("get file failed")
          console.log(err)
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
    var myurl = app.globalData.url + 'ExternalReviewDocument/downloadFile/' + that.data.id;
    //存储路径
    var myFilePath
    //请求后端
    app.wxDownloadFile(myurl, (res) => {
      //成功处理函数
      console.log(res)
      //存储文件
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          //成功处理函数
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
      id: option.id,
      year: option.year,
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