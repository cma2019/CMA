// page_SampleReceipt/SampleReceipt/SampleReceipt.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //dataObj:{},
    'sampleIdN': '',
    infoMess: '',
    "samplereceiveInfo": "fghjnk"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    
  },
  sampleIdInput: function (e) {
    var that = this
    that.setData({
      sampleIdN: e.detail.value
    })
  },
  btnClick_getone: function () {
    this.setData({
      infoMess: '',

    })
    console.log('getone发生了Click事件，携带数据为：', this.data.sampleIdN)
    var tmp = this
    const getoneRequest = wx.request({
      url: urlinfo + 'SampleReceipt/getOne',
      method: 'GET',
      data: {
        "sampleId": tmp.data.sampleIdN
      },
      header: {
        'content-type': 'application/json',
        'Accept': 'application/json'
      },
      success(res) {
        if (res.data.code == 200) {
          var info = res.data.data
          wx.setStorage({
            key: 'info',
            data: info,
            success: function (res) {
              wx.navigateTo({
                url: '../receiptShow/receiptShow'
              })
            }
          })
          /*console.log('zxfcgvhjk,')
          console.log(res.data.msg)
          tmp.setData({
            dataObj: res.data.data
          })
          console.log(tmp.data.dataObj)
          wx.navigateTo({
            url: '../show/show?obtainDate='+ tmp.data.dataObj.obtainDate,
          })*/
        }
        else if (res.data.code == 521) {
          console.log(res.data.msg)
          wx.showToast({
            title: '查询失败\n未收到标识编号',
            duration: 1500
          })
          console.log('查询失败，未收到标识编号')
        }
        else {//522
          console.log(res.data.msg)
          console.log("12")
          wx.showToast({
            title: '查询失败\n数据不存在',
            duration: 1500
          })
          console.log('查询失败，数据不存在')
        }
      },
      fail(err) {
        console.log(err)
        console.log('fail getone')
      },
      complete(fin) {
        console.log('final getone')
      }
    })
  },
  btnClick_deleteone: function () {
    this.setData({
      infoMess: '',
    })
    console.log('deleteone发生了Click事件，携带数据为：', this.data.sampleIdN)
    const deleteoneRequest = wx.request({
      url: urlinfo + 'SampleReceipt/deleteOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "sampleId": this.data.sampleIdN
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
            title: '删除失败，未收到标识编号',
            duration: 1500
          })
          console.log('删除失败，未收到标识编号')
        }
        else {
          wx.showToast({
            title: '删除失败，数据不存在',
            duration: 1500
          })
          console.log('删除失败，数据不存在')
        }
      },
      fail(err) {
        console.log('fail deleteone')
      },
      complete(fin) {
        console.log('final deleteone')
      }
    })
  },
  gotoAddOne: function () {
    wx.reLaunch({
      url: '../receiptAddOne/receiptAddOne',
    })
  },
  gotoModifyOne: function () {
    wx.reLaunch({
      url: '../receiptModifyOne/receiptModifyOne',
    })
  },
  goback: function () {
    wx.reLaunch({
      url: '/pages/home/home',
    })
  },
})