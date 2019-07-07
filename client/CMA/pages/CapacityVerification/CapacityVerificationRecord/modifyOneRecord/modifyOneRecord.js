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
      console.log(res.data)
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
    }, (err) => {
      console.err('getone error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      date: e.detail.value
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
      if (res.code == 200) {
        console.log('modify record successfully')
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
      console.log('fail modify record')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }
  //}
})


