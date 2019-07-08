const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sendDate:"",
    obtainDate:"",
    sampleNumberMessage: "样品编号不能为空",
    sampleNameMessage: "样品名称不能为空",
    senderMessage: "送样人不能为空",
    receiverMessage: "接收人不能为空",
    obtainerMessage: "领取人不能为空",
    sendDateMessage:"",
    dis: "false",
    receiptId:'',
    sampleNumber:null,
    sampleName:null,
    sampleAmount:null,
    sampleState:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.id != undefined) {
      this.setData({
        receiptId: options.id
      })
    }
    else{
      if(options.sampleNumber !=undefined){
        this.setData({
          receiptId: 0,
          sampleNumber: options.sampleNumber,
          sampleName: options.sampleName,
          sampleAmount: options.sampleAmount,
          sampleState: options.sampleState
        })
      }
    }
    console.log("fsdgdf")
    console.log(this.data)
  },
  sampleNumberChange: function (event) {
    const no = event.detail
    let message = ''
    let disable = false
    if (no.length == 0 || no.length > 10) {
      if (no.length == 0) {
        message = '样品编号不能为空'
        disable = true
      } else {
        message = '样品编号长度有误'
        disable = true
      }
    }
    this.setData({
      sampleNumberMessage: message,
      dis: disable
    })
    if (this.data.dis == true) {
      return false;
    } else {
      return true;
    }
  },
  sampleNameChange: function (event) {
    const no = event.detail
    let message = ''
    let disable = false
    if (no.length == 0 || no.length > 20) {
      if (no.length == 0) {
        message = '样品名称不能为空'
        disable = true
      } else {
        message = '样品名称长度有误'
        disable = true
      }
    }
    this.setData({
      sampleNameMessage: message,
      dis: disable
    })
    if (this.data.dis == true) {
      return false;
    } else {
      return true;
    }
  },
  senderChange: function (event) {
    const no = event.detail
    let message = ''
    let disable = false
    if (no.length == 0 || no.length > 20) {
      if (no.length == 0) {
        message = '送样人不能为空'
        disable = true
      } else {
        message = '送样人长度有误'
        disable = true
      }
    }
    this.setData({
      senderMessage: message,
      dis: disable
    })
    if (this.data.dis == true) {
      return false;
    } else {
      return true;
    }
  },
  receiverChange: function (event) {
    const no = event.detail
    let message = ''
    let disable = false
    if (no.length == 0 || no.length > 20) {
      if (no.length == 0) {
        message = '接收人不能为空'
        disable = true
      } else {
        message = '接收人长度有误'
        disable = true
      }
    }
    this.setData({
      receiverMessage: message,
      dis: disable
    })
    if (this.data.dis == true) {
      return false;
    } else {
      return true;
    }
  },
  obtainerChange: function (event) {
    const no = event.detail
    let message = ''
    let disable = false
    if (no.length == 0 || no.length > 20) {
      if (no.length == 0) {
        message = '领取人不能为空'
        disable = true
      } else {
        message = '领取人长度有误'
        disable = true
      }
    }
    this.setData({
      obtainerMessage: message,
      dis: disable
    })
    if (this.data.dis == true) {
      return false;
    } else {
      return true;
    }
  },
  bindsendDateChange(e) {
    this.setData({
      sendDate: e.detail.value
    })
  },

  bindobtainDateChange(e) {
    this.setData({
      obtainDate: e.detail.value
    })
  },
  SampleIo_addone: function (e) {
    console.log(this.data.dis)
    if (this.data.dis == true|| e.detail.value.obtainDate == "") {
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    else {
      let sampleNumber = this.data.sampleNumber
      let sampleName = this.data.sampleName
      let sampleAmount = this.data.sampleAmount
      let sampleState = this.data.sampleState
      if (this.data.sampleNumber == '' || this.data.sampleNumber == null) {
        sampleNumber = e.detail.value.sampleNumber
        sampleName = e.detail.value.sampleName
        sampleAmount = e.detail.value.sampleAmount
        sampleState = e.detail.value.sampleState
      }
      console.log('SampleIo发生了addone事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'SampleIo/addOne'
      let postdata = {
        "receiptId": this.data.receiptId,
        "sampleNumber": sampleNumber,
        "sampleName": sampleName,
        "sampleAmount": sampleAmount,
        "sampleState": sampleState,
        "sender": e.detail.value.sender,
        "receiver": e.detail.value.receiver,
        "sendDate": e.detail.value.sendDate,
        "obtainer": e.detail.value.obtainer,
        "obtainDate": e.detail.value.obtainDate,
        "note": e.detail.value.note
      }
      app.wxRequest(url, 'POST', postdata, (res) => {
        console.log(res.data)
        if (res.data.code == 200) {
          wx.showToast({
            title: '成功',
            icon: 'none',
            duration: 1500
          })
          wx.navigateTo({
            url: '../SampleIo'
          })
        }
        else if (res.data.code == 512) {
          wx.showToast({
            title: '样品编号已存在',
            duration: 1500
          })
          console.log('样品编号已存在')
        }
        else if (res.data.code == 513) {
          wx.showToast({
            title: '某项数据错误',
            duration: 1500
          })
          console.log('某项数据错误')
        }
        else {//514
          wx.showToast({
            title: '样品数据与接收单不一致',
            duration: 1500
          })
          console.log('样品数据与接收单不一致')
        }
      }, (err) => {
        console.log('fail addone')
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