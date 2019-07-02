// page_SampleIo/ioAddone/ioAddone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    receiveDate:"",
    materialType:[],
    materialName:"",
    activeNames: ['0']
  },
  onChange(event) {
    this.setData({
      activeNames: event.detail
    })
  },
  onchange1(event){
    console.log("123456")
    console.log(event)
    this.setData({
      materialType1:event.value
    }),
    console.log(this.data.materialType1)
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
  receiptTypeAdd:function(){
    var materialType = this.data.materialType
    var newData = {}
    materialType.push(newData)
    this.setData({
      materialType: materialType
    })
    console.log(this.data.materialType)
  },
  SampleReceipt_addone: function (e) {
    /*wx.request({
      url: app.globalData.url + 'SampleReceipt/addOne',
      method: 'POST',
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      data: {
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
        "receiver": "李四"
      },
      success(res) {
      
      },
      fail(err) {
        console.log('fail addone')
      },
      complete(fin) {
        console.log('final addone')
      }
    })*/
    
    console.log(e)
    if (e.detail.value.sampleId == "" || e.detail.value.applicationUnit == "" || e.detail.value.version == "" || e.detail.value.contractId == "" || e.detail.value.testType == "" || e.detail.value.electronicMedia == "" || e.detail.value.softwareType == "" || e.detail.value.receiveUnit == "" || e.detail.value.receiveDate == "" || e.detail.value.sender == "" || e.detail.value.reciever == "") {
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
        url: app.globalData.url + 'SampleReceipt/addOne',
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
          "materialList": [{
                        "materialId": 1,
                        "materialType": this.data.materialType1
                      },
                      {
                        "materialId": 2,
                        "materialType": this.data.materialType2
                      },
                      {
                        "materialId": 3,
                        "materialType": this.data.materialType3
                      },
                      {
                        "materialId": 4,
                        "materialType": this.data.materialType4
                      },
                      {
                        "materialId": 5,
                        "materialType": this.data.materialType5
                      },
                      {
                        "materialId": 6,
                        "materialType": this.data.materialType6
                      },
                      {
                        "materialId": 7,
                        "materialType": this.data.materialType7
                      },
                      {
                        "materialId": 8,
                        "materialType": this.data.materialType8
                      },
                      {
                        "materialId": 9,
                        "materialType": this.data.materialType9,
                        "materialName": this.data.materialName
                      }],
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
            wx.navigateTo({
              url: '../SampleReceipt'
            })
          }
          else if (res.data.code == 511) {
            wx.showToast({
              title: '添加失败，缺少请求参数',
              duration: 1500
            })
            console.log('添加失败，缺少请求参数')
          }
          else  {//513
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