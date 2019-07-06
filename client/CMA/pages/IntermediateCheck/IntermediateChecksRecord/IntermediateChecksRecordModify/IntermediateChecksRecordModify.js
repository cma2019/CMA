// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    recordId: 5,
    planId: 1,
    object: "路由器",
    checkDate: "2018-03-04",
    processRecord: "xxxxx",
    processRecordPerson: "zhang",
    processRecordDate: "2018-03-05",
    resultRecord: "ok",
    resultRecordPerson: "li",
    resultRecordDate: "2018-05-11",
    note: "aaaa"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      recordId: options.id
    })
  },
  gotologin(e) {
    wx.navigateBack({
      delta: 1
    })
  },
  onShow :function (options){
    let url = app.globalData.url + 'IntermediateChecksRecord/getOneByRecordId'
    let postdata = {
      "recordId": this.data.recordId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('record modify success')
      this.setData({
        planId: res.data[0].planId,
        object: res.data[0].object,
        checkDate: res.data[0].checkDate,
        processRecord: res.data[0].processRecord,
        processRecordPerson: res.data[0].processRecordPerson,
        processRecordDate: res.data[0].processRecordDate,
        resultRecord: res.data[0].resultRecord,
        resultRecordPerson: res.data[0].resultRecordPerson,
        resultRecordDate: res.data[0].resultRecordDate,
        note: res.data[0].note
      })
    }, (err) => {
      console.err('getone error')
    })
  },

  bindCheckDateChange(e) {
    console.log(e)
    this.setData({
      checkDate: e.detail.value
    })
  },

  bindDateChange1(e) {
    console.log(e)
    this.setData({
      processRecordDate: e.detail.value
    })
  },

  bindDateChange2(e) {
    console.log(e)
    this.setData({
      resultRecordDate: e.detail.value
    })
  },

  intercheckmodify: function (e) {
    console.log('modify modify')
    if (e.detail.value.object == "" || e.detail.value.content == "" ||
      e.detail.value.date == "" || e.detail.value.personInCharge == "" || e.detail.value.state == "") {
      wx.showToast({
        title: 'wrong message',
        duration: 2000
      })
      console.log('wrong message')
    }
    else {
      console.log('modify，携带数据为：', e.detail.value)
      console.log('modify，携带数据为：', e.detail.value.object)

      let url = app.globalData.url + 'IntermediateChecksRecord/modifyOne';
      console.log(url)
      console.log(this.data.planId)
      let data = {
        "recordId": this.data.recordId,
        "planId": e.detail.value.planId,
        "object": e.detail.value.object,
        "checkDate": e.detail.value.checkDate,
        "processRecord": e.detail.value.processRecord,
        "processRecordPerson": e.detail.value.processRecordPerson,
        "processRecordDate": e.detail.value.processRecordDate,
        "resultRecord": e.detail.value.resultRecord,
        "resultRecordPerson": e.detail.value.resultRecordPerson,
        "resultRecordDate": e.detail.value.resultRecordDate,
        "note": e.detail.value.note
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        if (res.code == 200) {
          console.log('modify successfully')
          wx.navigateBack({
            delta: 1
          })
        }
      }, (err) => {
        console.log('fail modify')
      })
    }
  }
})


