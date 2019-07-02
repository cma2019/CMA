// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "10",
    planId: "1",
    name: "23",
    method: "34",
    state: "45",
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
    console.log("plan modify")
    console.log(this.data.planId)
    let url = app.globalData.url + 'CapacityVerification/getOneProject'
    let postdata = {
      "id": this.data.id
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      this.setData({
        planId: res.data.planId,
        name: res.data.name,
        method: res.data.method,
        state: res.data.state,
        note: res.data.note,
      })
    }, (err) => {
      console.err('getone error')
    })
  },

  capacityprojectmodify: function (e) {
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

    let url = app.globalData.url + 'CapacityVerification/modifyOneProject';
    console.log(url)
    console.log(this.data.planId)
    let data = {
      "id": this.data.id,
      "planId": this.data.planId,
      "name": e.detail.value.name,
      "method": e.detail.value.method,
      "state": e.detail.value.state,
      "note": e.detail.value.note
    };
    console.log(data)
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('modify message successfully')
      console.log(res)
      /*
      if (res.data == "modify successfully.") {
        wx.navigateBack({
          delta: 1
        })
      }
      */
    }, (err) => {
      console.log('fail modify')
    })
  }
  //}
})


