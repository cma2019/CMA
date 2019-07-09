// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "planId" : "null",
    "object" : "null",
    "content" : "null",
    "checkDate" : "null",
    "personInCharge" : "null",
    "state" : "null"
  },

  onLoad: function (options) {
    //getone时所需的planid，是由其他界面传递过来的
    //options中包含了url中保存的变量值
    console.log('getone options')
    console.log(options)
    this.setData({
      planId : options.id
    })
  },

  onShow:function (options){
    let url = app.globalData.url + 'IntermediateChecksPlan/getOne'
    let postdata = {
      "planId": this.data.planId
    }
    //传递的数据只有planid，方式为GET
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log('plan getone success')
      //传回的数据虽然只有一组，但是是数组形式
      //取data数组的第一项，取出数据
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
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
  modifyData(e){
    //修改数据需要知道修改的是哪一组数据
    //planid由getone界面传递给modifyone界面
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../IntermediateCheckModify/IntermediateCheckModify?id=' + target
    })
  },
  addRecord(e) {
    //record在逻辑上是依附于plan的，所以在plan界面给出添加record的按钮
    //同时，逻辑上record和plan是一一对应的，所以若是已经有record存在，将会不能添加
    //我们需要知道是为哪个plan添加record，所以此处需要传递planid变量
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../../IntermediateChecksRecord/IntermediateChecksRecordAddone/IntermediateChecksRecordAddone?id=' + target
    })
  },
  getRecord(e) {
    //逻辑上record和plan是一一对应的，我们可以直接使用planid找到相应的record
    //此处调用的是getonebyplanid接口，需要传递planid这一数据
    console.log(e)
    let target = this.data.planId
    console.log(target)
    wx.navigateTo({
      url: '../../IntermediateChecksRecord/IntermediateChecksRecordGetonePlan/IntermediateChecksRecordGetonePlan?id=' + target
    })
  },
  deleteData(e){
    //delete没有特殊的操作，只需要传递planid这一数据
    let url = app.globalData.url + 'IntermediateChecksPlan/deleteOne'
    let data = {
      "planId" : this.data.planId
    }
    app.wxRequest(url,'POST',data,(res)=>{
      //删除成功时，服务器端返回res.code == 200
      //删除成功时，显示删除成功，并且返回上一界面
      if (res.code == 200) {
        console.log('delete successfully')
        wx.showToast({
          title: '删除成功',
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
        console.log('delete failed')
        wx.showToast({
          title: '删除失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    },(err)=>{
      console.log('delete failed')
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  }

})