// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "recordId": "null",
    "planId": "null",
    "object": "null",
    "checkDate": "null",
    "processRecord": "null",
    "processRecordPerson": "null",
    "processRecordDate": "null",
    "resultRecord": "null",
    "resultRecordPerson": "null",
    "resultRecordDate": "null",
    "note": "null"
  },

  onLoad: function (options) {
    //此界面使用planid来getone，此数据来源于其他界面
    this.setData({
      planId: options.id
    })
  },
  onShow: function (options) {
    let url = app.globalData.url + 'IntermediateChecksRecord/getOneByPlanId'
    let postdata = {
      "planId": this.data.planId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('record getone success')
      console.log(res)
      //此处getone需要传递的是planid
      //这一界面来源于每个plan，调用getonebyplanid接口
      if (res.code == 200) {
        this.setData({
          planId: res.data.planId,
          recordId: res.data.recordId,
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
      }else if(res.code == 100){
        wx.showToast({
          title: '记录不存在',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }else {
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

  modifyData(e) {
    console.log(e)
    let target = this.data.recordId
    console.log(target)
    wx.navigateTo({
      url: '../IntermediateChecksRecordModify/IntermediateChecksRecordModify?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'IntermediateChecksRecord/deleteOne'
    let data = {
      "recordId": this.data.recordId
    }
    app.wxRequest(url, 'POST', data, (res) => {
      if (res.code == 200) {
        console.log('delete successfully')
        wx.showToast({
          title: '删除成功',
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
      } else {
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.log('delete failed')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }

})