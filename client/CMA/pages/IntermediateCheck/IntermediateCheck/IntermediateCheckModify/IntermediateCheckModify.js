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
    //此界面的planid参数是由其他界面传递来的
    this.setData({
      planId: options.id
    })
  },
  gotologin(e) {
    //返回键的功能是，返回到上一界面
    wx.navigateBack({
      delta: 1
    })
  },
  onShow:function(options){
    console.log(this.data.planId)
    //modify之前，我们需要将已有的数据预先填在输入框中
    //为了做到这一点，在各个界面之间不传递过多数据的情况下，我们需要首先调用getone接口
    let url = app.globalData.url + 'IntermediateChecksPlan/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      console.log(res.data[0].checkDate)
      //此处getone实现和另一界面相同，唯一不同在于界面上的显示方式不同
      if(res.code == 200){
        this.setData({
          object: res.data[0].object,
          content: res.data[0].content,
          checkDate: res.data[0].checkDate,
          personInCharge: res.data[0].personInCharge,
          state: res.data[0].state
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
    })
  },
  bindDateChange: function(e) {
    //将日期选择器，数据和显示互相绑定
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
    console.log(e.detail.value.state)
    //保证所有输入不为空，再向后端传递
    //有输入为空时，显示修改失败
    //state为0时，也会被判断为是""，原因未知，需要额外判断
    if (e.detail.value.object == null ||
      e.detail.value.content == null ||
      e.detail.value.date == null ||
      e.detail.value.personInCharge == null ||
      e.detail.value.object == "" ||
      e.detail.value.content == "" ||
      e.detail.value.personInCharge == "" ||
      (e.detail.value.state == "" && e.detail.value.state != 0)||
      e.detail.value.date == "") {
      console.log("message error")
      wx.showToast({
        title: '修改失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else{
    //传递6个信息，其中五个由用户决定
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
      //修改成功时，后端发送code==200，显示修改成功，跳转到上一界面
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


