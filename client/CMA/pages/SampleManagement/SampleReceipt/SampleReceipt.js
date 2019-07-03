const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess":null,
    "flag":0,
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
      },
      {
        "sampleId": 3,
        "applicationUnit": "百度责任有限公司",
        "version": "V1.5",
        "contractId": "34855568",
        "testType": 0,
        "electronicMedia": "光盘8张",
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
        "receiveUnit": "测试中心",
        "receiveDate": "2018-06-15",
        "sender": "三",
        "reciever": "四"
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    var that = this
    let url = app.globalData.url + 'SampleReceipt/getAll'
    let postdata = ''
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      if (res.code == 522) {
        that.setData({
          mess: ""
        })
      }
      else {
        that.setData({
          mess: res.data,
          flag: 1
        })
        console.log("success")
      }
    }, (err) => {
      wx.showToast({
        title: 'getall error',
        duration: 1500
      })
      console.log('getall error')
    })
  },
  gotoOne: function (e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    console.log("dfdsfs")
    wx.navigateTo({
      url: 'receiptGetOne/receiptGetOne?id=' + target
    })
  },

  gotoAdd: function () {
    wx.navigateTo({
      url: 'receiptAddOne/receiptAddOne',
    })
  }
})