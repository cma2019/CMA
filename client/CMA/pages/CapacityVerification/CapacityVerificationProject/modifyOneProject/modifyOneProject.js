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
    //modify前，使用从其他界面的到的信息初始化
    this.setData({
      id: options.id
    })
  },
  gotologin(e){
    wx.navigateBack({
      delta:1
    })
  },
  onShow: function (options) {
    console.log("plan modify")
    console.log(this.data.planId)
    let url = app.globalData.url + 'CapacityVerification/getOneProject'
    let postdata = {
      "id": this.data.id
    }
    //首先使用id来getone，初始化所有的输入框
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("plan modify success")
      //rescode==200时，获取成功，置初值
      if(res.code == 200){
        this.setData({
          planId: res.data.planId,
          name: res.data.name,
          method: res.data.method,
          state: res.data.state,
          note: res.data.note,
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

  capacityprojectmodify: function (e) {
    console.log('modify projects')
    //修改时，保证所有输入不为空
    if (e.detail.value.name == null ||
      e.detail.value.method == null ||
      e.detail.value.note == null ||
      e.detail.value.state == null ||
      e.detail.value.name == "" ||
      e.detail.value.method == "" ||
      e.detail.value.note == ""||
      e.detail.value.state == "") {
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
    //传递6个数据，只有四个数据需要用户输入
    let thisstate = e.detail.value.state
    console.log(data)
    app.wxRequest(url, 'POST', data, (res) => {
      //rescode==300时，说明project的id从0变为了1，此时需要添加对应的record
      //显示请输入记录信息，跳转到添加界面
      //若plan相应的所有project的state都变为了1，则该plan的state变为1
      //相对的，state从1变为0时，plan的state变为0，删除对应的record
      if (res.code == 300) {
        console.log('modify successfully')
        let thisid = this.data.id
        wx.showToast({
          title: '请输入记录信息',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
            let target = thisid
            //跳转到添加record的界面
             wx.navigateTo({
               url: '../../CapacityVerificationRecord/addOneRecord/addOneRecord?id='+target,
             })
            }, 1000);
          }
        })
      }else if(res.code == 200){
        //rescode==200时，普通的修改成功
        //显示修改成功，返回上一界面
        console.log('modify successfully')
        wx.showToast({
          title: '修改成功',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateBack({
                delta : 1
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