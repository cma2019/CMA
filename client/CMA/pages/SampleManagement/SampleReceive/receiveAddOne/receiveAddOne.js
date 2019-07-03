const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    receiveDate: "",
    obtainDate: "",
    sampleNumberMessage: "样品编号不能为空",
    sampleNameMessage: "样品名称不能为空",
    requesterMessage: "委托单位不能为空",
    receiverMessage: "接收人不能为空",
    obtainerMessage: "领取人不能为空",
    dis: "false",
    receiptId:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.id != undefined){
      this.setData({
        receiptId: options.id
      })
    }
    else{
      this.setData({
        receiptId:0
      })
    }
    console.log("fsdgdf")
    console.log(this.data.receiptId)
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
    } 
    else {
      return true;
    }
  },
  requesterChange: function (event) {
    const no = event.detail
    let message = ''
    let disable = false
    if (no.length == 0 || no.length > 20) {
      if (no.length == 0) {
        message = '委托单位不能为空'
        disable = true
      } else {
        message = '委托单位长度有误'
        disable = true
      }
    }
    this.setData({
      requesterMessage: message,
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
  bindreceiveDateChange(e) {
    this.setData({
      receiveDate: e.detail.value
    })
  },

  bindobtainDateChange(e) {
    this.setData({
      obtainDate: e.detail.value
    })
  },
  SampleReceive_addone: function (e) {
    console.log(this.data.dis)
    if (this.data.dis == true || e.detail.value.obtainDate == ""){
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    else {
      console.log('SampleReceive发生了add事件，携带数据为：', e.detail.value)
      console.log(this.data.receiptId)
      wx.request({
        url: app.globalData.url + 'SampleReceive/addOne',
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        data: {
          "receiptId":this.data.receiptId,
          "sampleNumber": e.detail.value.sampleNumber,
          "sampleName": e.detail.value.sampleName,
          "sampleAmount": e.detail.value.sampleAmount,
          "sampleState": e.detail.value.sampleState,
          "requester": e.detail.value.requester,
          "receiver": e.detail.value.receiver,
          "receiveDate": e.detail.value.receiveDate,
          "obtainer": e.detail.value.obtainer,
          "obtainDate": e.detail.value.obtainDate
        },
        success(res) {
          console.log(res.data)
          if (res.data.code == 200) {
            wx.showToast({
              title: '成功',
              icon: 'none',
              duration: 1500
            })
            wx.navigateTo({
              url: '../SampleReceive'
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