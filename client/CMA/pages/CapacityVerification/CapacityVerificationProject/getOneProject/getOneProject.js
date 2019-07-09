// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "id": "null",
    "projectId": "null",
    "planId": "null",
    "name": "null",
    "method": "null",
    "state": "null",
    "note": "null"
  },

  onLoad: function (options) {
    //getone需要project的id数据
    console.log('getone options')
    console.log(options)
    this.setData({
      id: options.id
    })
  },
  goback(e){
    //返回键不是返回上一界面，因为有多个渠道到达这一界面
    //这里选择回到对应plan的showprojects界面
    let target = this.data.planId
    wx.redirectTo({
      url: '../showProjects/showProjects?id=' + target,
    })
  },
  onShow: function (options) {
    let url = app.globalData.url + 'CapacityVerification/getOneProject'
    let postdata = {
      "id": this.data.id
    }
    //传递id给后端
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan get one project success')
      //后端返回rescode==200,则成功，置初值
      if(res.code == 200){
        this.setData({
          projectId: res.data.projectId,
          planId: res.data.planId,
          name: res.data.name,
          method: res.data.method,
          state: res.data.state,
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
      console.err('get one project error')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  modifyData(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../modifyOneProject/modifyOneProject?id=' + target
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'CapacityVerification/deleteOneProject'
    let data = {
      "id": this.data.id
    }
    //删除数据时，需要传递projcet的id
    //删除时，若是删除的projcet的state为0，可能会使得对应plan的state变为1
    app.wxRequest(url, 'POST', data, (res) => {
      //rescode == 200时，删除成功
      //显示删除成功，并且返回对应planid的showprojects界面
      if (res.code == 200) {
        let myid = this.data.planId
        console.log('delete successfully')
        wx.showToast({
          title: '删除成功',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '../showProjects/showProjects?id='+myid,
              })
            }, 1000);
          }
        })
      }else{
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    }, (err) => {
      console.log('delete failed')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  getPlans(e) {
    console.log("get projects")
    let target = this.data.projectId
    console.log(target)
    //跳转到对应的record界面，projcet和record一一对应，此处传递projectid跳转
    if(this.data.state == 1){
      wx.redirectTo({
        url: '../../CapacityVerificationRecord/getRecordByProjectId/getRecordByProjectId?id=' + target
      })
    }else{
      wx.showToast({
        title: '记录不存在',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
  }
})