const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    "id": "",
    "origindata":{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log("fdsf")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this
    wx.getStorage({
      key: 'supervisionGetOneinfo',
      success: function (res) {
        that.setData({
          'origindata': res.data
        })
      }
    }),
    console.log(this.data)
    console.log("456789")
  },

  Supervision_modifyone: function (e) {
    var mod = this.data.origindata
    console.log(mod)
    console.log('Supervision发生了modifyone事件，携带数据为：', e.detail.value)
    console.log(this.data)
    if (e.detail.value.id != null && e.detail.value.id != "") {
      mod.id = e.detail.value.id
    }
    if (e.detail.value.remark != null && e.detail.value.remark != "") {
      mod.remark = e.detail.value.remark
    }
    console.log("dfg")
    console.log(this.data)
    wx.request({
      url: app.globalData.url+ 'Supervision/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "id": this.data.id,
        "remark": mod.remark
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.removeStorage({
            key: 'supervisionGetOneinfo',
            success: function (res) {
              console.log(res)
            }
          })
          wx.navigateTo({
            url: '../Supervision'
          })
        }
        else if (res.data.code == 531) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else if (res.data.code == 532) {
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
        else if (res.data.code == 533) {
          wx.showToast({
            title: '修改后数据错误',
            duration: 1500
          })
          console.log('修改后数据错误')
        }
        else {
          wx.showToast({
            title: '修改后数据不合法',
            duration: 1500
          })
          console.log('修改后数据不合法')
        }
      },
      fail(err) {
        console.log('fail modifyone')
      },
      complete(fin) {
        console.log('final modifyone')
      }
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