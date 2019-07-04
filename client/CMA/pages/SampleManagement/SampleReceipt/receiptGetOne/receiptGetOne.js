const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sampleId: "",
    mess: null,
    array: ['《用户手册》', '《计算机软件产品登记检测申请表》', '《材料交接单》', '《软件产品功能列表》', '《软件名称与版本号确认单》', '《受测软件产品简介》', '《自主产权保证书》', '软件样品一套', '其它'],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      sampleId: options.id
    })
    console.log("fsdgdf")
    console.log(this.data.sampleId)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /*
  searchInput:function(e){
    console.log(e.detail.value)
    this.setData({
      search_sampleId: e.detail.value
    })
  },
  searchSubmit:function(){
    console.log(this.data.search_sampleId)
    var thispage = this
    console.log('getone发生了事件，携带数据为：', this.data.search_sampleId)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/getOne',
      method: 'GET',
      data: {
        "sampleId": this.data.search_sampleId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          this.setData({
            mess:res.data
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
    })
    let mess = this.data.mess
    if(mess == null){
      this.setData({
        search_sampleId: ""
      })
    }
  },
  cancelSearch:function(){
    wx.navigateBack({
      delta: 1
    })
  },
  */
  gotoAddsampleReceive: function () {
    wx.navigateTo({
      url: '/pages/SampleManagement/SampleReceive/receiveAddOne/receiveAddOne?id=' + this.data.sampleId,
    })
  },
  gotoAddsampleIo: function () {
    wx.navigateTo({
      url: '/pages/SampleManagement/SampleIo/ioAddOne/ioAddOne?id=' + this.data.sampleId,
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function (options) {
    var thispage = this
    console.log('getone发生了事件，携带数据为：', this.data.sampleId)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/getOne',
      method: 'GET',
      data: {
        "sampleId": thispage.data.sampleId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          thispage.setData({
            mess: res.data.data
          })
          wx.setStorage({
            key: 'receiptGetOneinfo',
            data: res.data.data
          })
          console.log(thispage.data.mess)
          console.log("hhhhh")
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
    })
  },

  modifyData: function (e) {
    console.log(e)
    let target = this.data.sampleId
    //console.log("dfdg")
    //console.log(target)
    wx.navigateTo({
      url: '/pages/SampleManagement/SampleReceipt/receiptModifyOne/receiptModifyOne?id=' + target
    })
  },

  deleteData: function (e) {
    const deleteoneRequest = wx.request({
      url: app.globalData.url + 'SampleReceipt/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleId": this.data.sampleId
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '删除成功',
            duration: 1500
          })
        }
        else if (res.data.code == 521) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else {
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
        wx.navigateBack({
          delta: 1
        })
      },
      fail(err) {
        console.log('fail deleteone')
      },
      complete(fin) {
        console.log('final deleteone')
      }
    })

  },

  goback: function () {
    wx.removeStorage({
      key: 'receiptGetOneinfo',
      success: function (res) {
        console.log(res)
      }
    })
    wx.navigateBack({
      delta: 1
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