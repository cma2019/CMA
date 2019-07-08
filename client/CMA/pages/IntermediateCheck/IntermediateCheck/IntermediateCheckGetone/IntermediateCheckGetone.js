// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "planId" : "null",
    "object" : "null",
    "content" : "null",
    "checkDate" : "null",
    "personInCharge" : "null",
    "state" : "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      planId : options.id
    })
  },

  onShow:function (options){
    let url = app.globalData.url + 'IntermediateChecksPlan/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      if(res.code == 200){
        this.setData({
          object: res.data[0].object,
          content: res.data[0].content,
          checkDate: res.data[0].checkDate,
          personInCharge: res.data[0].personInCharge,
          state: res.data[0].state
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
  modifyData(e){
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../IntermediateCheckModify/IntermediateCheckModify?id=' + target
    })
  },
  addRecord(e) {
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../../IntermediateChecksRecord/IntermediateChecksRecordAddone/IntermediateChecksRecordAddone?id=' + target
    })
  },
  getRecord(e) {
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../../IntermediateChecksRecord/IntermediateChecksRecordGetonePlan/IntermediateChecksRecordGetonePlan?id=' + target
    })
  },
  deleteData(e){
    let url = app.globalData.url + 'IntermediateChecksPlan/deleteOne'
    let data = {
      "planId" : this.data.planId
    }
    app.wxRequest(url,'POST',data,(res)=>{
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
      }else{
        console.log('delete failed')
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    },(err)=>{
      console.log('delete failed')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }

})