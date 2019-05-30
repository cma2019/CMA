// page_SampleReceipt/receiptModifyOne/receiptModifyOne.js
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
  SampleReceive_modifyone: function (e) {
    this.setData({
      testTypeInfo: e.detail.value.testType,
      softwareTypeInfo: e.detail.value.softwareType,
      //idInfo: e.detail.value.sampleId
    })
      /*
      else if (this.data.idInfo.length == 0) {
        this.setData({ idInfo: '-1' })
      }*/
    //}
    console.log("sfgd")
    console.log('SampleReceipt发生了modifyone事件，携带数据为：', e.detail.value)
    console.log("sfdd")

    wx.request({
      url: urlinfo + 'SampleReceipt/modifyOne',
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
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.reLaunch({
            url: '../SampleReceive/SampleReceive'
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