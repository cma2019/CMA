// page_SampleIo/ioShow/ioShow.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "sampleId":"null",
    "applicationUnit": "null",
    "version": "null",
    "contractId": "null",
    "testType": "null",
    "electronicMedia": "null",
    "materialList": "null",
    "softwareType": "null",
    "receiveUnit": "null",
    "receiveDate": "null",
    "sender":"null",
    "reciever": "null",
    tmp: [{
      "sampleId": 2,
      "applicationUnit": "阿里巴巴责任有限公司",
      "version": "V1.0",
      "contractId": "2487955568",
      "testType": 0,
      "electronicMedia": "光盘1张",
      "materialList": [{
                "materialId": 1,
                "materialType": 1
              },
              {
                "materialId": 2,
                "materialType": 1
              },
              {
                "materialId": 3,
                "materialType": 1
              },
              {
                "materialId": 4,
                "materialType": 1
              },
              {
                "materialId": 6,
                "materialType": 2
              },
              {
                "materialId": 9,
                "materialType": 3,
                "materialName": "《附加材料》"
              }],
      "softwareType": 1,
      "receiveUnit": "南大测试中心",
      "receiveDate": "2018-06-15",
      "sender": "张三",
      "reciever": "李四"
    }]
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
        "sampleIoId": this.data.sampleIoId
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          thispage.setData({ 
            sampleName: res.data.data.sampleName,
            applicationUnit: res.data.data.applicationUnit,
            version: res.data.data.version,
            contractId: res.data.data.contractId,
            testType: res.data.data.testType,
            electronicMedia: res.data.data.electronicMedia,
            materialList: res.data.data.materialList,
            softwareType: res.data.data.softwareType,
            receiveUnit: res.data.data.receiveUnit,
            receiveDate: res.data.data.receiveDate,
            sender: res.data.data.sender,
            reciever: res.data.data.reciever
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

  modifyData: function(e) {
    console.log(e)
    let target = this.data.sampleId
    console.log("dfdg")
    console.log(target)
    wx.navigateTo({
      url: '../receiptModifyOne/receiptModifyOne?id=' + target
    })
  },

  deleteData: function(e) {
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
        else {//522
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