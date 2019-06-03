// pages/IntermediateCheck/IntermediateCheckModify/IntermediateCheckModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    planId: "1",
    object: "23",
    content: "34",
    checkDate: "45",
    personInCharge: "56",
    state: "67"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      planId: options.id
    })
  },

  onShow:function(options){
    console.log(this.data.planId)
    let url = app.globalData.url + 'IntermediateChecksPlan/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data[0].checkDate)
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
  bindDateChange: function(e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      checkDate: e.detail.value
    })
  },
  intercheckmodify:function(e){
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

    let url = app.globalData.url + 'IntermediateChecksPlan/modifyOne';
    console.log(url)
    console.log(this.data.planId)
    let data = {
      "planId": this.data.planId,
      "object": e.detail.value.object,
      "content": e.detail.value.content,
      "checkDate": e.detail.value.date,
      "personInCharge": e.detail.value.personInCharge,
      "state": e.detail.value.state
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


