// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "10",
    projectId: "1",
    date: "23",
    methodId: "34",
    equipmentName: "45",
    equipmentId: "67",
    experimenter: "45",
    result: "boom",
    resultDeal: "45",
    note: "67",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
  },

  onShow: function (options) {
    console.log(this.data.id)
    let url = app.globalData.url + 'CapacityVerification/getOneRecord'
    let postdata = {
      "id": this.data.id
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data[0])
      this.setData({
        projectId: res.data[0].projectId,
        date: res.data[0].date,
        methodId: res.data[0].methodId,
        equipmentName: res.data[0].equipmentName,
        equipmentId: res.data[0].equipmentId,
        experimenter: res.data[0].experimenter,
        result: res.data[0].result,
        resultDeal: res.data[0].resultDeal,
        note: res.data[0].note
      })
    }, (err) => {
      console.err('getone error')
    })
  },

  capacityrecordmodify: function (e) {
    console.log('modify projects')
    /*
    if(e.detail.value.object ==""||e.detail.value.content==""||
       e.detail.value.date == ""||e.detail.value.personInCharge==""||e.detail.value.state==""){
        wx.showToast({
          title: 'wrong message',
          duration: 2000
      })
      console.log('wrong message')
    }
    else{
    */
    console.log('modify，携带数据为：', e.detail.value)
    console.log('modify，携带数据为：', e.detail.value.object)

    let url = app.globalData.url + 'CapacityVerification/modifyOneRecord';
    console.log(url)
    console.log(this.data.planId)
    let data = {
      "id": this.data.id,
      "projectId": e.detail.value.projectId,
      "date": e.detail.value.date,
      "methodId": e.detail.value.methodId,
      "equipmentName": e.detail.value.equipmentName,
      "equipmentId": e.detail.value.equipmentId,
      "experimenter": e.detail.value.experimenter,
      "result": e.detail.value.result,
      "resultDeal": e.detail.value.resultDeal,
      "note": e.detail.value.note
    };
    console.log(data)
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('modify record successfully')
      console.log(res)
      /*
      if (res.data == "modify successfully.") {
        wx.navigateBack({
          delta: 1
        })
      }
      */
    }, (err) => {
      console.log('fail modify record')
    })
  }
  //}
})


