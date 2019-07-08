// pages/QualityManual/QualityManualApprove/QualityManualApproveGetOne/QualityManualApproveGetOne.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    manual: {}
  },
  //返回按钮的处理函数
  mygo: function (e) {
    //跳转回查找页面
    wx.redirectTo({
      url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
    })
  },
  //批准按钮的处理函数
  myapprove:function(e){
    //保存指针
    var that = this
    //构造参数
    var mydata = {
      state: 2
    }
    //构造url
    var myurl = app.globalData.url + 'QualityManual/Approve/' + that.data.manual.id
    //请求后端
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      //成功处理函数
      console.log(res)
      //成功提示
      wx.showToast({
        title: '批准成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          //延时
          setTimeout(function () {
            //跳转回查找界面
            wx.redirectTo({
              url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
            })
          }, 1000);
        }
      })
    }, (err) => {
      //失败处理函数
      console.log(err)
    })
  },
  //拒绝按钮的处理函数
  myreject: function (e) {
    //保存指针
    var that = this
    //构造参数
    var mydata = {
      state: 1
    }
    //构造url
    var myurl = app.globalData.url + 'QualityManual/Approve/' + that.data.manual.id
    //请求后端
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      //成功处理函数
      console.log(res)
      //成功提示
      wx.showToast({
        title: '拒绝成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          //延时
          setTimeout(function () {
            //跳转回查找界面
            wx.redirectTo({
              url: '/pages/QualityManual/QualityManualApprove/QualityManualApprove',
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
    var that = this;
    console.log(option)
    //存储页面跳转时传递的参数
    that.setData({
      manual: option
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