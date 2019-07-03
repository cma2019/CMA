// page_SampleIo/ioModifyOne/ioModifyOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    "sampleIoId": "",
    "origindata":{},
    "sendDate": "",
    "obtainDate": ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      sampleIoId: options.id
    })
    //console.log(this.data.sampleIoId)
    console.log("fdsf")
  },

  bindsendDateChange(e) {
    console.log(e.detail.value)
    this.setData({
      sendDate: e.detail.value
    })
    console.log(this.sendDate)
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
    /*var thispage = this
    //console.log('getone发生了事件，携带数据为：', this.data.sampleIoId)
    wx.request({
      url: app.globalData.url + 'SampleIo/getOne',
      method: 'GET',
      data: {
        "sampleIoId": this.data.sampleIoId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          thispage.setData({
            sampleNumberinfo: res.data.data.sampleNumber,
            sampleNameinfo: res.data.data.sampleName,
            sampleAmountinfo: res.data.data.sampleAmount,
            sampleStateinfo: res.data.data.sampleState,
            senderinfo: res.data.data.sender,
            receiverinfo: res.data.data.receiver,
            sendDateinfo: res.data.data.sendDate,
            obtainerinfo: res.data.data.obtainer,
            obtainDateinfo: res.data.data.obtainDate,
            noteinfo: res.data.data.note
          })
        }
        else if (res.data.code == 521) {
          console.log(res.data.msg)
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else {//522
          console.log(res.data.msg)
          console.log("12")
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
    })*/
    var that = this
    wx.getStorage({
      key: 'ioGetOneinfo',
      success: function (res) {
        console.log("fdsgfgdhjkgh")
        console.log(res)
        that.setData({
          'origindata': res.data
        })
      }
    }),
    console.log(this.data)
    console.log("456789")
  },

  SampleIo_modifyone: function (e) {
    var mod = this.data.origindata
    console.log(mod)
    console.log('SampleIo发生了modifyone事件，携带数据为：', e.detail.value)
    console.log(this.data)
    if (e.detail.value.sampleNumber != null && e.detail.value.sampleNumber != "") {
        mod.sampleNumber = e.detail.value.sampleNumber
    }
    if (e.detail.value.sampleName != null && e.detail.value.sampleName != "") {
        mod.sampleName = e.detail.value.sampleName
    }
    if (e.detail.value.sampleAmount != null && e.detail.value.sampleAmount != "") {
        mod.sampleAmount =  e.detail.value.sampleAmount
    }
    if (e.detail.value.sampleState != null && e.detail.value.sampleState != "") {
        mod.sampleState = e.detail.value.sampleState
    }
    if (e.detail.value.sender != null && e.detail.value.sender != "") {
        mod.sender = e.detail.value.sender
    }
    if (e.detail.value.receiver != null && e.detail.value.receiver != "") {
        mod.receiver = e.detail.value.receiver
    }
    if (e.detail.value.sendDate != null && e.detail.value.sendDate != "") {
        mod.sendDate =  e.detail.value.sendDate
    }
    if (e.detail.value.obtainer != null && e.detail.value.obtainer != "") {
        mod.obtainer = e.detail.value.obtainer
    }
    if (e.detail.value.obtainDate != null && e.detail.value.obtainDate != "") {
        mod.obtainDate =  e.detail.value.obtainDate
    }
    if (e.detail.value.note != null && e.detail.value.note != "") {
        mod.note =  e.detail.value.note
    }
    console.log("dfg")
    console.log(this.data)
    wx.request({
      url: app.globalData.url+ 'SampleIo/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleIoId": this.data.sampleIoId,
        "sampleNumber": mod.sampleNumber,
        "sampleName":mod.sampleName,
        "sampleAmount": mod.sampleAmount,
        "sampleState": mod.sampleState,
        "sender": mod.sender,
        "receiver": mod.receiver,
        "sendDate": mod.sendDate,
        "obtainer": mod.obtainer,
        "obtainDate": mod.obtainDate,
        "note": mod.note
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.removeStorage({
            key: 'ioGetOneinfo',
            success: function (res) {
              console.log(res)
            }
          })
          wx.navigateTo({
            url: '../SampleIo'
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