// page_SampleReceipt/receiptAddOne/receiptAddOne.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  SampleReceive_addone: function (e) {
    if (e.detail.value.sampleId == "" || e.detail.value.applicationUnit == "" || e.detail.value.version == "" || e.detail.value.contractId == "" || e.detail.value.testType == "" || e.detail.value.electronicMedia == "" || e.detail.value.materialList == "" || e.detail.value.softwareType == "" || e.detail.value.receiveUnit == "" || e.detail.value.receiveDate == "" || e.detail.value.sender == "" || e.detail.value.reciever == "") {
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    else {
      console.log('SampleReceipt发生了addone事件，携带数据为：', e.detail.value)
      wx.request({
        url: urlinfo + 'SampleReceipt/addOne',
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        data: {
          "sampleId": e.detail.value.sampleId,
          "applicationUnit": e.detail.value.applicationUnit,
          "version": e.detail.value.version,
          "contractId": e.detail.value.contractId,
          "testType": e.detail.value.testType,
          "electronicMedia": e.detail.value.electronicMedia,
          "materialList": e.detail.value.materialList,
          "softwareType": e.detail.value.softwareType,
          "receiveUnit": e.detail.value.receiveUnit,
          "receiveDate": e.detail.value.receiveDate,
          "sender": e.detail.value.sender,
          "reciever": e.detail.value.reciever
        },
        success(res) {
          console.log(res.data)
          if (res.data.code == 200) {
            wx.showToast({
              title: '成功',
              icon: 'none',
              duration: 1500
            })
            wx.reLaunch({
              url: '../SampleReceipt/SampleReceipt'
            })
          }
          else if (res.data.code == 512) {
            wx.showToast({
              title: '添加失败，样品编号已存在',
              duration: 1500
            })
            console.log('添加失败，样品编号已存在')
          }
          else {
            wx.showToast({
              title: '添加失败，某项数据错误',
              duration: 1500
            })
            console.log('添加失败，某项数据错误')
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