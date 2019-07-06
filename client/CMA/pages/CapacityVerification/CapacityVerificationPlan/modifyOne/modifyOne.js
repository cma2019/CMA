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
      "id": this.data.planId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data)
      this.setData({
        name: res.data.name,
        organizer: res.data.organizer,
        state: res.data.state,
        year: res.data.year,
        note: res.data.note,
        analysis: res.data.analysis
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
      "id": this.data.planId,
      "name": e.detail.value.name,
      "organizer": e.detail.value.organizer,
      "state": e.detail.value.state,
      "year": e.detail.value.year,
      "note": e.detail.value.note,
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


