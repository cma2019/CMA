// pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYear.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试用数据
  data: {
    array: [{
      "id": 1,
      "year": 2019,
      "fileId": "1",
      "fileName": "1.pdf"
    },
    {
      "id": 2,
      "year": 2020,
      "fileId": "2",
      "fileName": "2.pdf"
    }],
    year:null
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //跳转回查找页面
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagement',
    })
  },
  //跳转至添加页面
  gotoAdd: function (e) {
    var that = this
    wx.redirectTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagementYear/ExternalReviewManagementYearAddOne/ExternalReviewManagementYearAddOne?year=' + that.data.year,
    })
  },
  //删除按钮的处理函数
  gotoDelete: function(e){
    //保存指针
    var that = this
    console.log(that.data)
    //构造url
    var myurl = app.globalData.url + 'ExternalReviewManagement/deleteOne/' + that.data.year;
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
              url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagement',
            })
          }, 1000);
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
    var that = this
    //存储页面跳转时传递的参数
    that.setData({
      year: option.year
    })
    //构造ur;
    var myurl = app.globalData.url + 'ExternalReviewDocument/getAllFile/' + option.year;
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      console.log(res)
      //把接收到的数据存储到页面
      that.setData({
        array: res.data
      })
      console.log(that.data.array)
    }, (err) => {
      //失败处理函数
      console.log(err)
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
  onShow: function (option) {
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