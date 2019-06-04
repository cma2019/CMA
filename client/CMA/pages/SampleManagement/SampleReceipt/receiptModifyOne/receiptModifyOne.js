// page_SampleIo/ioModifyOne/ioModifyOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    "sampleId": "",
    "applicationUnit": "",
    "version": "",
    "contractId": "",
    "testType": "",
    "electronicMedia": "",
    "materialList": "",
    "softwareType": "",
    "receiveUnit": "",
    "receiveDate": "",
    "sender": "",
    "reciever": "",

    "applicationUnitinfo": "",
    "versioninfo": "",
    "contractIdinfo": "",
    "testTypeinfo": "",
    "electronicMediainfo": "",
    "materialListinfo": "",
    "softwareTypeinfo": "",
    "receiveUnitinfo": "",
    "receiveDateinfo": "",
    "senderinfo": "",
    "recieverinfo": "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      sampleId: options.id
    })
    console.log(this.data.sampleId)
    console.log("fdsf")
  },

  bindreceiveDateChange(e) {
    console.log(e.detail.value)
    this.setData({
      receiveDate: e.detail.value
    })
    console.log(this.receiveDate)
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var thispage = this
    //console.log('getone发生了事件，携带数据为：', this.data.sampleIoId)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/getOne',
      method: 'GET',
      data: {
        "sampleId": this.data.sampleId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          thispage.setData({
            sampleNameinfo: res.data.data.sampleName,
            applicationUnitinfo: res.data.data.applicationUnit,
            versioninfo: res.data.data.version,
            contractIdinfo: res.data.data.contractId,
            testTypeinfo: res.data.data.testType,
            electronicMediainfo: res.data.data.electronicMedia,
            materialListinfo: res.data.data.materialList,
            softwareTypeinfo: res.data.data.softwareType,
            receiveUnitinfo: res.data.data.receiveUnit,
            receiveDateinfo: res.data.data.receiveDate,
            senderinfo: res.data.data.sender,
            recieverinfo: res.data.data.reciever
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
    })
  },

  SampleReceipt_modifyone: function (e) {
    console.log('SampleReceipt发生了modifyone事件，携带数据为：', e.detail.value)
    console.log(this.data)
    if (e.detail.value.applicationUnit != null && e.detail.value.applicationUnit != "") {
      this.setData({
        applicationUnitinfo: e.detail.value.applicationUnit
      })
    }
    if (e.detail.value.version != null && e.detail.value.version != "") {
      this.setData({
        versioninfo: e.detail.value.version
      })
    }
    if (e.detail.value.contractId != null && e.detail.value.contractId != "") {
      this.setData({
        scontractIdinfo: e.detail.value.contractId
      })
    }
    if (e.detail.value.testType != null && e.detail.value.testType != "") {
      this.setData({
        testTypeinfo: e.detail.value.testType
      })
    }
    if (e.detail.value.electronicMedia != null && e.detail.value.electronicMedia != "") {
      this.setData({
        electronicMediainfo: e.detail.value.electronicMedia
      })
    }
    if (e.detail.value.materialList != null && e.detail.value.materialList != "") {
      this.setData({
        materialListinfo: e.detail.value.materialList
      })
    }
    if (e.detail.value.softwareType != null && e.detail.value.softwareType != "") {
      this.setData({
        softwareTypeinfo: e.detail.value.softwareType
      })
    }
    if (e.detail.value.receiveUnit != null && e.detail.value.receiveUnit != "") {
      this.setData({
        receiveUnitinfo: e.detail.value.receiveUnit
      })
    }
    if (e.detail.value.receiveDate != null && e.detail.value.receiveDate != "") {
      this.setData({
        receiveDateinfo: e.detail.value.receiveDate
      })
    }
    if (e.detail.value.sender != null && e.detail.value.sender != "") {
      this.setData({
        senderinfo: e.detail.value.sender
      })
    }
    if (e.detail.value.reciever != null && e.detail.value.reciever != "") {
      this.setData({
        recieverinfo: e.detail.value.reciever
      })
    }
    console.log("dfg")
    console.log(this.data)
    wx.request({
      url: app.globalData.url+ 'SampleReceipt/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleId": this.data.sampleId,
        "applicationUnit": this.data.applicationUnitinfo,
        "version": this.data.versioninfo,
        "contractId": this.data.contractIdinfo,
        "testType": this.data.testTypeinfo,
        "electronicMedia": this.data.electronicMediainfo,
        "materialList": this.data.materialListinfo,
        "softwareType": this.data.softwareTypeinfo,
        "receiveUnit": this.data.receiveUnitinfo,
        "receiveDate": this.data.receiveDateinfo,
        "sender": this.data.senderinfo,
        "reciever": this.data.recieverinfo,
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '../SampleReceipt'
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