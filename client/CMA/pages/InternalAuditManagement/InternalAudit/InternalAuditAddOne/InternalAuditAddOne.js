const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    date: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  binddateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },
  InternalAudit_addone: function (e) {
    console.log(e.detail.value)
    if (e.detail.value.year == "" || e.detail.value.date == "") {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 2000,
        success:function(){
          setTimeout(function(){
          },2000)
        }
      })
      console.log('空白输入')
    }
    else {
      console.log('InternalAudit发生了addone事件，携带数据为：', e.detail.value)
      wx.request({
        url: app.globalData.url + 'InternalAuditManagement/addOne',
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        data: {
          "year": e.detail.value.year,
          "date": e.detail.value.date
        },
        success(res) {
          console.log(res.data)
          if (res.data.code == 200) {
            console.log("45646546546546")
            wx.showToast({
              title: '成功',
              image: '/icons/ok/ok.png',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                  wx.navigateTo({
                    url: '../InternalAudit'
                  })
                }, 2000)
              }
            })
            console.log("成功")
          }
          else if(res.data.code == 511){
            wx.showToast({
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                }, 2000)
              }
            })
            console.log("缺少必选请求参数")
          }
          else if (res.data.code == 512) {
            wx.showToast({
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                }, 2000)
              }
            })
            console.log("添加重复数据")
          }
          else if (res.data.code == 513) {
            wx.showToast({
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                }, 2000)
              }
            })
            console.log("添加数据不合法")
          }
          else{ //513
            wx.showToast({
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                }, 2000)
              }
            })
            console.log("添加数据不符合一致性")
          }
        },
        fail(err) {
          console.log('fail addone')
        },
        complete(fin) {
          console.log('final addone')
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