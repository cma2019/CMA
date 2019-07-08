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
    //修改之前，需要初始化id信息
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
    //使用id来getone，初始化所有的输入框
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data)
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
    //修改时，所有输入不能为空
    console.log('modify projects')
    if (e.detail.value.date == null ||
      e.detail.value.methodId == null ||
      e.detail.value.equipmentName == null ||
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
        title: '修改失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else{
    
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
      //返回rescode==200时，修改成功
      //显示修改成功，返回上一界面
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
  }
})


