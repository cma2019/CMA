// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "projectId": null,
  },

  onLoad: function (options) {
    this.setData({
      projectId: options.id
    })
  },
  bindDateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },

  AddNewRecord(e) {
    if (e.detail.value.date == null ||
      e.detail.value.methodId == null ||
      e.detail.value.equipmentName == null||
      e.detail.value.equipmentId == null ||
      e.detail.value.experimenter == null ||
      e.detail.value.result == null ||
      e.detail.value.resultDeal == null ||
      e.detail.value.note == null || 
      e.detail.value.date == "" ||
      e.detail.value.methodId == "" ||
      e.detail.value.equipmentName == "" ||
      e.detail.value.equipmentId == "" ||
      e.detail.value.experimenter == "" ||
      e.detail.value.result == "" ||
      e.detail.value.resultDeal == "" ||
      e.detail.value.note == "") {
      console.log("message error")
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else {
      let url = app.globalData.url + 'CapacityVerification/addOneRecord'
      console.log("add one record id")
      console.log(this.data.projectId)
      let data = {
        "projectId": this.data.projectId,
        "date": e.detail.value.date,
        "methodId": e.detail.value.methodId,
        "equipmentName": e.detail.value.equipmentName,
        "equipmentId": e.detail.value.equipmentId,
        "experimenter": e.detail.value.experimenter,
        "result": e.detail.value.result,
        "resultDeal": e.detail.value.resultDeal,
        "note": e.detail.value.note
      }
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        if (res.code == 200) {
          console.log('add record successfully')
          let mypro = this.data.projectId
          wx.showToast({
            title: '添加成功',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
                wx.navigateTo({
                  url: '../getRecordByProjectId/getRecordByProjectId?id='+mypro,
                })
              }, 1000);
            }
          })
        }else{
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail CapacityVerificationPlan register')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
})