const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    "id": "",
    "remark":''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id,
      remark:options.remark
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  Supervision_modifyone: function (e) {
    let remark =e.detail.value.remark
    if (remark == null || remark == "") {
      remark = this.data.remark
    }
    let url = app.globalData.url + 'Supervision/modifyOne'
    let postdata={
      "id": this.data.id,
      "remark": remark
    }
    console.log('Supervision发生了modifyone事件，携带数据为：', e.detail.value)
    app.wxRequest(url, 'POST', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        wx.showToast({
          title: '修改成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '../Supervision'
              })
            }, 300)
          }
        })
      }
      else if (res.code == 531) {
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
            }, 300)
          }
        })
        console.log('未收到标识编号')
      }
      else if (res.code == 532) {
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
            }, 300)
          }
        })
        console.log('数据不存在')
      }
      else if (res.code == 533) {
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
            }, 300)
          }
        })
        console.log('修改后数据错误')
      }
      else {
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
            }, 300)
          }
        })
        console.log('修改后数据不合法')
      }
    }, (err) => {
      console.log('fail modifyone')
    })
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