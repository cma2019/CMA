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
  gotologin(e) {
    wx.navigateBack({
      delta: 1
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
      if (res.code == 200) {
        console.log('modify successfully')
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
      console.log('fail modify')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }
  //}
})


