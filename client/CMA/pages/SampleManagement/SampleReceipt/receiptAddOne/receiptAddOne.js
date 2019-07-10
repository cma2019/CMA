const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    receiveDate:"",
    /*
    materialList: [
      { "materialType": 0, "materialId": 1, "materialName": null },
      { "materialType": 0, "materialId": 2,"materialName": null },
      { "materialType": 0, "materialId": 3, "materialName": null },
      { "materialType": 0, "materialId": 4, "materialName": null },
      { "materialType": 0, "materialId": 5, "materialName": null },
      { "materialType": 0, "materialId": 6, "materialName": null },
      { "materialType": 0, "materialId": 7,"materialName": null },
      { "materialType": 0, "materialId": 8,"materialName": null },
      { "materialType": 0, "materialId": 9, "materialName": null },
    ],*/
    flag:0
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },

  bindreceiveDateChange(e) {
    this.setData({
      receiveDate: e.detail.value
    })
  },
  SampleReceipt_addone: function (e) {
    console.log(e)
    let receipttmp = e.detail.value
    if (receipttmp.sampleName != "" && receipttmp.applicationUnit != "" && receipttmp.version != "" && receipttmp.contractId != "" && receipttmp.electronicMedia != "" && receipttmp.receiveUnit != "" && receipttmp.receiveDate != "" && receipttmp.sender != "" && receipttmp.receiver != ""){
      var that = this
      /*
      let newmaterialList = []
      wx.getStorage({
        key: 'materialListinfo',
        success: function (res) {
          let i =0
          let tmp = []
          while(i < 9){
            console.log(res.data[i].materialType)
            if (res.data[i].materialType!=0){
              tmp={"materialId":res.data[i].materialId,
                "materialType": res.data[i].materialType,
                "materialName":res.data[i].materialName
              }
              console.log(newmaterialList)
              newmaterialList.push(tmp)
              console.log(newmaterialList)
            }
            ++i
          }
          that.setData({
            flag: 1
          })
        }
      })*/
      console.log('SampleReceipt发生了addone事件')
      let postdata = {
        "sampleId": e.detail.value.sampleId,
        "applicationUnit": e.detail.value.applicationUnit,
        "version": e.detail.value.version,
        "contractId": e.detail.value.contractId,
        "testType": e.detail.value.testType,
        "electronicMedia": e.detail.value.electronicMedia,
        "materialList": app.globalData.materialList1,
        "softwareType": e.detail.value.softwareType,
        "receiveUnit": e.detail.value.receiveUnit,
        "receiveDate": e.detail.value.receiveDate,
        "sender": e.detail.value.sender,
        "receiver": e.detail.value.receiver
      }
      console.log(postdata)
      wx.request({
        url: app.globalData.url + 'SampleReceipt/addOne',
        method: 'POST',
        header: {
          'content-type': 'application/json',
          'Accept': 'application/json'
        },
        data: postdata,
        success(res) {
          console.log(res.data)
          if (res.data.code == 200) {
            wx.showToast({
              title: '成功',
              icon: 'none',
              duration: 1500
            })
            wx.navigateTo({
              url: '../SampleReceipt'
            })
          }
          else if (res.data.code == 511) {
            wx.showToast({
              title: '缺少请求参数',
              duration: 1500
            })
            console.log('缺少请求参数')
          }
          else if(res.data.code == 512){
            wx.showToast({
              title: '样品标识编号已存在',
              duration: 1500
            })
            console.log('样品标识编号已存在')
          }
          else  {//513
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
    }
    else{
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    wx.removeStorage({
      key: 'materialListinfo',
      success: function (res) {
        console.log(res)
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