// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "planId": "null",
  },

  onLoad: function (options) {
    this.setData({
      planId: options.id
    })
  },
  gotologin(e) {
    wx.navigateBack({
      delta: 1
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

  InterCheckRegister(e) {
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
      e.detail.value.note == "" ) {
      console.log("message error")
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else {
      let url = app.globalData.url + 'IntermediateChecksRecord/addOne'
      let data = {
        "planId": this.data.planId,
        "object": e.detail.value.object,
        "checkDate": e.detail.value.checkDate,
        "processRecord": e.detail.value.processRecord,
        "processRecordPerson": e.detail.value.processRecordPerson,
        "processRecordDate": e.detail.value.processRecordDate,
        "resultRecord": e.detail.value.resultRecord,
        "resultRecordPerson": e.detail.value.resultRecordPerson,
        "resultRecordDate": e.detail.value.resultRecordDate,
        "note": e.detail.value.note
      }
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        if (res.code == 200) {
          console.log('add one record successfully')
          wx.showToast({
            title: '添加成功',
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
        }else if(res.code == 300){
          console.log('fail intermediate check register')
          wx.showToast({
            title: '相应记录已存在',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }else{
          console.log('fail intermediate check register')
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
})