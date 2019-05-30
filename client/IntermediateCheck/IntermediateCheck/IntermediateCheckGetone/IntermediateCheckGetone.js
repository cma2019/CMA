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
    this.setData({
      planId : options.id
    })
  },

  onShow:function (options){
    let url = app.globalData.url + 'cma/IntermediateChecksPlan/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      this.setData({
        object: res.data[0].object,
        content: res.data[0].content,
        checkDate: res.data[0].checkDate,
        personInCharge: res.data[0].personInCharge,
        state: res.data[0].state
      })
    }, (err) => {
      console.err('getone error')
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

  deleteData(e){
    let url = app.globalData.url + 'cma/IntermediateChecksPlan/deleteOne'
    let data = {
      "planId" : this.data.planId
    }
    app.wxRequest(url,'POST',data,(res)=>{
      console.log('delete successfully')
    },(err)=>{
      console.log('delete failed')
    })
  }

})