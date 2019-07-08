// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "recordId": "null",
    "projectId": "null",
    "date": "null",
    "methodId": "null",
    "equipmentName": "null",
    "equipmentId": "null",
    "experimenter": "null",
    "result": "null",
    "resultDeal": "null",
    "note": "null"
  },

  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      recordId: options.id
    })
  },
  
  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOneRecord'
    let postdata = {
      "id": this.data.recordId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan get one project success')
      if(res.code == 200){
        this.setData({
          projectId: res.data.projectId,
          date: res.data.date,
          methodId: res.data.methodId,
          equipmentName: res.data.equipmentName,
          equipmentId: res.data.equipmentId,
          experimenter: res.data.experimenter,
          result: res.data.result,
          resultDeal: res.data.resultDeal,
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
      console.err('get one project error')
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
      url: '../modifyOneRecord/modifyOneRecord?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOneRecord'
    let data = {
      "id": this.data.recordId
    }
    app.wxRequest(url, 'POST', data, (res) => {
      if (res.code == 200) {
        console.log('delete record successfully')
        let url2 = app.globalData.url + 'CapacityVerification/modifyState'
        let data2 = {
          "projectId" : this.data.projectId,
          "state": 0
        }
        app.wxRequest(url2,'POST',data2,(res)=>{
          if(res.code == 200){
            let mypro = this.data.projectId
            wx.showToast({
              title: '删除成功',
              image: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                setTimeout(function () {
                  wx.navigateTo({
                    url: '../../CapacityVerificationProject/getOneProject/getOneProject?id='+mypro,
                  })
                }, 1000);
              }
            })
          }else{
            wx.showToast({
              title: '修改状态失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        })
      }else{
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.log('delete plan failed')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }
})