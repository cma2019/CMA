const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    "sampleId": "",
    "origindata": {},
    "receiveDate": "",
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
    ]
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
    }),
    console.log(this.data)
    console.log("456789")
  },

  SampleReceipt_modifyone: function (e) {
    var mod = this.data.origindata
    console.log(mod)
    console.log('SampleReceipt发生了modifyone事件，携带数据为：', e.detail.value)
    console.log(this.data)
    if (e.detail.value.applicationUnit != null && e.detail.value.applicationUnit != "") {
      mod.applicationUnit = e.detail.value.applicationUnit
    }
    if (e.detail.value.version != null && e.detail.value.version != "") {
      mod.version = e.detail.value.version
    }
    if (e.detail.value.contractId != null && e.detail.value.contractId != "") {
      mod.contractId = e.detail.value.contractId
    }
    if (e.detail.value.testType != null && e.detail.value.testType != "") {
      mod.testType = e.detail.value.testType
    }
    if (e.detail.value.electronicMedia != null && e.detail.value.electronicMedia != "") {
      mod.electronicMedia = e.detail.value.electronicMedia
    }
    if (e.detail.value.materialList != null && e.detail.value.materialList != "") {
      mod.materialList = e.detail.value.materialList
    }
    if (e.detail.value.softwareType != null && e.detail.value.softwareType != "") {
      mod.softwareType = e.detail.value.softwareType
    }
    if (e.detail.value.receiveUnit != null && e.detail.value.receiveUnit != "") {
      mod.receiveUnit = e.detail.value.receiveUnit
    }
    if (e.detail.value.receiveDate != null && e.detail.value.receiveDate != "") {
      mod.receiveDate = e.detail.value.receiveDate
    }
    if (e.detail.value.sender != null && e.detail.value.sender != "") {
      mod.sender = e.detail.value.sender
    }
    if (e.detail.value.reciever != null && e.detail.value.reciever != "") {
      mod.reciever = e.detail.value.reciever
    }
    var that = this
    wx.getStorage({
      key: 'materialListModifyinfo',
      success: function (res) {
        let i = 0
        let tmp = that.data.materialList
        while (i < 9) {
          tmp[i].materialId = res.data[i].materialId
          if(res.data.materialType == -1){ //不修改
            let j = 0
            while (res.data[i].materialId != origindata.materialList[j].materialId&& j < origindata.materialList.length){
              ++j
            }
            if(res.data[i].materialId != origindata.materialList[j].materialId){ //找到了
              tmp[i].materialType = origindata.materialList[j].materialType
              tmp[i].materialName = origindata.materialList[j].materialName
            }
            //未找到将Type设为0，Name设为null
          }
          else{ //修改
            tmp[i].materialType = res.data[i].materialType
            if(i == 8){
              tmp[i].materialName = res.data[i].materialName
            }
          }
          ++i
        }
        that.setData({
          materialList: tmp
        })
      }
    }),
    console.log(this.data.materialList)
    let materialList = this.data.materialList
    var newmaterialList = []
    for (let i = 0; i < 9; ++i) {
      if (materialList[i].materialType != 0) {
        newmaterialList.push(materialList[i])
      }
    }
    console.log(newmaterialList)
    wx.request({
      url: app.globalData.url + 'SampleReceipt/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleId": this.data.sampleId,
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
        "reciever": mod.reciever
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.removeStorage({
            key: 'receiptGetOneinfo',
            success: function (res) {
              console.log(res)
            }
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