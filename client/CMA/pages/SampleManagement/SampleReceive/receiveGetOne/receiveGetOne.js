const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "sampleId": "null",
    "receiptId":"null",
    "info":{},
    array:['待处理','在测','测毕']
  },

  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    this.setData({
      sampleId: options.id
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
  onShow: function (options) {
    var thispage = this
    console.log('getone发生了事件，携带数据为：', this.data)
    wx.request({
      url: app.globalData.url + 'SampleReceive/getOne',
      method: 'GET',
      data: {
        "sampleId": this.data.sampleId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          thispage.setData({
            info : res.data.data,
            receiptId:res.data.data.receiptId
          })
          wx.setStorage({
            key:'receiveGetOneinfo',
            data:res.data.data
          })
        }
        else if (res.data.code == 521) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else {//522
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
      },
      fail(err) {
        console.log('no data')
      }
    })
  },

  modifyData: function (e) {
    console.log(e)
    let sampleId = this.data.sampleId
    wx.navigateTo({
      url: '../receiveModifyOne/receiveModifyOne?id=' + sampleId
    })
  },

  deleteData: function (e) {
    let url = app.globalData.url + 'SampleReceive/deleteOne'
    let postdata = {
      "sampleId": this.data.sampleId
    }
    app.wxRequest(url, 'POST', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        wx.removeStorage({
          key: 'receiveGetOneinfo',
          success: function (res) {
            console.log(res)
          }
        })
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '/pages/SampleManagement/SampleReceive/SampleReceive'
              })
            }, 300)
          }
        })
      }
      else {
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 300
        })
        console.log('删除失败')
      }
    }, (err) => {
      console.log('fail deleteone')
    })
  },

  goback: function () {
    wx.removeStorage({
      key: 'receiveGetOneinfo',
      success: function(res) {
        console.log(res)
      }
    })
    wx.navigateBack({
      delta: 1
    })
  },
  looktoreceipt: function (e) {
    console.log(e)
    let target = this.data.receiptId
    if (target != null) {
      wx.navigateTo({
        url: '/pages/SampleManagement/SampleIo/ioReceiptDetail/ioReceiptDetail?id=' + target
      })
    }
    else {
      wx.showToast({
        title: '未对应接收单',
        image: '/icons/warning/warning.png',
        duration: 500,
        success: function () {
          setTimeout(function () {
          }, 300)
        }
      })
    }
  },
  addsampleIo:function(){
    wx.navigateTo({
      url: '/pages/SampleManagement/SampleIo/ioAddOne/ioAddOne?sampleNumber=' + this.data.info.sampleNumber+"&sampleName="+this.data.info.sampleName+"&sampleAmount="+this.data.info.sampleAmount+"&sampleState="+this.data.info.sampleState+"&receiptId="+this.data.receiptId
    })
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