const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sampleId: "",
    activeNames: ['0'],
    mess: null,
    array: ['0','《用户手册》', '《计算机软件产品登记检测申请表》', '《材料交接单》', '《软件产品功能列表》', '《软件名称与版本号确认单》', '《受测软件产品简介》', '《自主产权保证书》', '软件样品一套', '其它'],
    array1:['没有材料','仅有电子文档','仅有书面文档','既有电子文档，又有书面文档'],
    testTypeArray:["登记检测","确定检测","验收检测"],
    softTypeArray:["系统软件","支持软件","应用软件","其他软件"]
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
  onChange:function(event){
    this.setData({
      activeNames: event.detail
    })
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
        console.log(res)
        if (res.data.code == 200) {
          thispage.setData({
            mess: res.data.data
          })
          wx.setStorage({
            key: 'receiptGetOneinfo',
            data: res.data.data
          })
        }
        else if (res.data.code  == 521) {
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
    wx.navigateTo({
      url: '/pages/SampleManagement/SampleReceipt/receiptModifyOne/receiptModifyOne?id=' + this.data.sampleId
    })
  },

  deleteData: function (e) {
    let url = app.globalData.url + 'SampleReceipt/deleteOne'
    let postdata = {
      "sampleId": this.data.sampleId
    }
    console.log("SampleReceipt发生了deleteOne事件，携带数据为：",this.data.sampleId)
    app.wxRequest(url, 'POST', postdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 500,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '/pages/SampleManagement/SampleReceipt/SampleReceipt'
              })
            }, 300)
          }
        })
      }
      else {
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
        console.log('删除失败')
      }
    }, (err) => {
      console.log('fail deleteone')
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