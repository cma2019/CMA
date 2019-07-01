// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    planId: "1",
    name: "23",
    organizer: "34",
    state: "45",
    year: "56",
    note: "67",
    analysis:"analy"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      planId: options.id
    })
  },

  onShow: function (options) {
    console.log(this.data.planId)
    let url = app.globalData.url + 'CapacityVerification/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data[0])
      this.setData({
        name: res.data[0].name,
        organizer: res.data[0].organizer,
        state: res.data[0].state,
        year: res.data[0].year,
        note: res.data[0].note,
        analysis: res.data[0].analysis
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  capacityplanmodify: function (e) {
    console.log('modify modify')
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

    let url = app.globalData.url + 'CapacityVerification/modifyOne';
    console.log(url)
    console.log(this.data.planId)
    let data = {
      "planId": this.data.planId,
      "name": e.detail.value.name,
      "organizer": e.detail.value.organizer,
      "state": e.detail.value.state,
      "year": e.detail.value.year,
      "note": e.detail.value.note,
      "analysis": e.detail.value.analysis
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


