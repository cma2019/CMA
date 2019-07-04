const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    receiveDate: "",
    materialList: [
      { "materialId": 1, "materialType": 0, "materialName": null },
      { "materialId": 2, "materialType": 0, "materialName": null },
      { "materialId": 3, "materialType": 0, "materialName": null },
      { "materialId": 4, "materialType": 0, "materialName": null },
      { "materialId": 5, "materialType": 0, "materialName": null },
      { "materialId": 6, "materialType": 0, "materialName": null },
      { "materialId": 7, "materialType": 0, "materialName": null },
      { "materialId": 8, "materialType": 0, "materialName": null },
      { "materialId": 9, "materialType": 0, "materialName": null },
    ],
    flag: 0,
    sampleId:'',
    "origindata": {}
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    this.setData({
      sampleId: options.id
    })
  },

  bindreceiveDateChange(e) {
    this.setData({
      receiveDate: e.detail.value
    })
  },
  SampleReceipt_modifyone: function (e) {
    var that = this
    var mod = this.data.origindata
    console.log(mod)
    console.log(this.data)
    console.log('SampleReceipt发生了modifyone事件，携带数据为：', e.detail.value)
    if (e.detail.value.applicationUnit != null && e.detail.value.applicationUnit != "") {
      mod.applicationUnit = e.detail.value.applicationUnit
    }
    if (e.detail.value.version != null) {
      mod.version = e.detail.value.version
    }
    if (e.detail.value.contractId != null) {
      mod.contractId = e.detail.value.contractId
    }
    if (e.detail.value.testType != null) {
      mod.testType = e.detail.value.testType
    }
    if (e.detail.value.electronicMedia != null) {
      mod.electronicMedia = e.detail.value.electronicMedia
    }
    if (e.detail.value.softwareType != null) {
      mod.softwareType = e.detail.value.softwareType
    }
    if (e.detail.value.receiveUnit != null) {
      mod.receiveUnit = e.detail.value.receiveUnit
    }
    if (e.detail.value.receiveDate != null&& e.detail.value.receiveDate != "") {
      mod.receiveDate = e.detail.value.receiveDate
    }
    if (e.detail.value.sender != null) {
      mod.sender = e.detail.value.sender
    }
    if (e.detail.value.receiver != null) {
      mod.receiver = e.detail.value.receiver
    }
    wx.getStorage({
      key: 'materialListModifyinfo',
      success: function (res) {
        let i = 0
        let tmp = that.data.materialList
        while (i < 9) {
          tmp[i].materialId = res.data[i].materialId
          tmp[i].materialType = res.data[i].materialType
          tmp[i].materialName = res.data[i].materialName
          ++i
        }
        that.setData({
          materialList: tmp,
          flag: 1
        })
      }
    }),
    console.log("lkokokko")
    console.log(this.data)
    let materialList = this.data.materialList
    var newmaterialList = []
    for (let i = 0; i < 9; ++i) {
      newmaterialList.push(materialList[i])
    }
    console.log(newmaterialList)
    console.log("4564569999999")
    let sampleId = this.data.sampleId
    console.log(mod)
    console.log(sampleId)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      data: {
        "sampleId": sampleId,
        "applicationUnit": mod.applicationUnit,
        "version": mod.version,
        "contractId": mod.contractId,
        "testType": mod.testType,
        "electronicMedia": mod.electronicMedia,
        "materialList": newmaterialList,
        "softwareType": mod.softwareType,
        "receiveUnit": mod.receiveUnit,
        "receiveDate": mod.receiveDate,
        "sender": mod.sender,
        "receiver": mod.receiver
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
            url: '/pages/SampleManagement/SampleReceipt/SampleReceipt'
          })
        }
        else if (res.data.code == 511) {
          wx.showToast({
            title: '缺少请求参数',
            duration: 1500
          })
          console.log('缺少请求参数')
        }
        else if (res.data.code == 512) {
          wx.showToast({
            title: '样品标识编号已存在',
            duration: 1500
          })
          console.log('样品标识编号已存在')
        }
        else {//513
          wx.showToast({
            title: '某项数据错误',
            duration: 1500
          })
          console.log('某项数据错误')
        }
      },
      fail(err) {
        console.log('fail addone')
      },
      complete(fin) {
        console.log('final addone')
      }
    })
    wx.removeStorage({
      key: 'materialListModifyinfo',
      success: function (res) {
        console.log(res)
        console.log("qewrwuwndcjcncj")
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
  onShow: function (options) {
    var that = this
    wx.getStorage({
      key: 'receiptGetOneinfo',
      success: function (res) {
        console.log("fdsgfgdhjkgh")
        console.log(res)
        that.setData({
          'origindata': res.data
        })
      }
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