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
    //初始化界面的planid信息
    this.setData({
      planId: options.id
    })
  },
  gotologin(e) {
    console.log('go back')
    wx.navigateBack({
      delta : 1
    })
  },
  onShow: function (options) {
    console.log(this.data.planId)
    let url = app.globalData.url + 'CapacityVerification/getOne'
    let postdata = {
      "id": this.data.planId
    }
    //modifyone之前，通过调用getone提前填充已有数据
    //getone部分与getone方法中所用一致
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data)
      if(res.code == 200){
        this.setData({
          name: res.data.name,
          organizer: res.data.organizer,
          state: res.data.state,
          year: res.data.year,
          note: res.data.note,
          analysis: res.data.analysis
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
  capacityplanmodify: function (e) {
    console.log('modify modify')
    //修改之前，保证所有数据不为空
    if (e.detail.value.name == null ||
      e.detail.value.organizer == null ||
      e.detail.value.year == null ||
      e.detail.value.note == null ||
      e.detail.value.name == "" ||
      e.detail.value.organizer == "" ||
      e.detail.value.year == "" ||
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
    let url = app.globalData.url + 'CapacityVerification/modifyOne';
    console.log(url)
    console.log(this.data.planId)
    let data = {
      "id": this.data.planId,
      "name": e.detail.value.name,
      "organizer": e.detail.value.organizer,
      //"state": e.detail.value.state,
      "year": e.detail.value.year,
      "note": e.detail.value.note,
    };
    //modify需要传递五个信息
    //state不应该能够手动修改，而应根据project的状态改变
    console.log(data)
    app.wxRequest(url, 'POST', data, (res) => {
      //rescode==200时，修改成功
      //显示修改成功，返回上一界面
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
  }
})


