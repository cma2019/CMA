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
      if(res.code == 200){
        this.setData({
          planId: res.data.planId,
          object: res.data.object,
          checkDate: res.data.checkDate,
          processRecord: res.data.processRecord,
          processRecordPerson: res.data.processRecordPerson,
          processRecordDate: res.data.processRecordDate,
          resultRecord: res.data.resultRecord,
          resultRecordPerson: res.data.resultRecordPerson,
          resultRecordDate: res.data.resultRecordDate,
          note: res.data.note
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.err('getone error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
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
    if (e.detail.value.object == null ||
      e.detail.value.checkDate == null ||
      e.detail.value.processRecord == null ||
      e.detail.value.processRecordPerson == null ||
      e.detail.value.processRecordDate == null ||
      e.detail.value.resultRecord == null ||
      e.detail.value.resultRecordPerson == null ||
      e.detail.value.resultRecordDate == null ||
      e.detail.value.note == null ||
      e.detail.value.object == "" ||
      e.detail.value.checkDate == "" ||
      e.detail.value.processRecord == "" ||
      e.detail.value.processRecordPerson == "" ||
      e.detail.value.processRecordDate == "" ||
      e.detail.value.resultRecord == "" ||
      e.detail.value.resultRecordPerson == "" ||
      e.detail.value.resultRecordDate == "" ||
      e.detail.value.note == "") {
      console.log("message error")
      wx.showToast({
        title: '修改失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
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
          wx.showToast({
            title: '修改成功',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
                wx.navigateBack({
                  delta: 1
                })
              }, 1000);
            }
          })
        }else{
          wx.showToast({
            title: '修改失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail modify')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
})


