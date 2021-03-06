const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    "sampleId":"",
    "origindata":{},
    "receiveDate":"",
    "obtainDate":""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      sampleId: options.id
    })
  },

  bindreceiveDateChange(e) {
    console.log(e.detail.value)
    this.setData({
      receiveDate: e.detail.value
    })
  },

  bindobtainDateChange(e) {
    this.setData({
      obtainDate: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this
    wx.getStorage({
      key: 'receiveGetOneinfo',
      success:function(res){
        that.setData({
          'origindata': res.data
        })
      }
    })
  },

  SampleReceive_modifyone: function (e) {
    var mod = this.data.origindata //modifydata
    if (e.detail.value.sampleNumber != null){
      mod.sampleNumber = e.detail.value.sampleNumber
    }
    if (e.detail.value.sampleName != null && e.detail.value.sampleName != "") {
      mod.sampleName = e.detail.value.sampleName
    }
    if (e.detail.value.sampleAmount != null&& e.detail.value.sampleAmount != "") {
      mod.sampleAmount = e.detail.value.sampleAmount
    }
    if (e.detail.value.sampleState != null && e.detail.value.sampleState != "") {
      mod.sampleState = e.detail.value.sampleState
    }
    if (e.detail.value.requester != null && e.detail.value.requester != "") {
      mod.requester = e.detail.value.requester
    }
    if (e.detail.value.receiver != null&& e.detail.value.receiver != "") {
      mod.receiver =  e.detail.value.receiver
    }
    if (e.detail.value.receiveDate != "") {
       mod.receiveDate = e.detail.value.receiveDate
    }
    if (e.detail.value.obtainer != null && e.detail.value.obtainer != "") {
      mod.obtainer =  e.detail.value.obtainer
    }
    if (e.detail.value.obtainDate != "") {
      mod.obtainDate = e.detail.value.obtainDate
    }
    console.log('SampleReceive发生了modifyone事件，携带数据为：', mod)
    let url = app.globalData.url + 'SampleReceive/modifyOne'
    let postdata = {
      "sampleId": this.data.sampleId,
      "sampleNumber": mod.sampleNumber,
      "sampleName": mod.sampleName,
      "sampleAmount": mod.sampleAmount,
      "sampleState": mod.sampleState,
      "requester": mod.requester,
      "receiver": mod.receiver,
      "receiveDate": mod.receiveDate,
      "obtainer": mod.obtainer,
      "obtainDate": mod.obtainDate
    }
    app.wxRequest(url, 'POST', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        console.log(res)
        wx.removeStorage({
          key: 'receiveGetOneinfo',
          success: function (res) {
            console.log(res)
          }
        })
        wx.showToast({
          title: '修改成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '../SampleReceive'
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