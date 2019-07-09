// pages/IntermediateCheck/IntermediateCheckGetone/IntermediateCheckGetone.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "year": "null",
    "id": "null",
    "productionName": "null",
    "ability": "null",
    "referrence": "null",
  },

  onLoad: function (options) {
    //进入界面加载时，初始化id，来源为其他界面
    this.setData({
      id: options.id
    })
  },
  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getOneItem'
    let postdata = {
      "id": this.data.id
    }
    //使用传递进来的id进行getoneitem
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('test ability getone success')
      console.log(res)
      //getoneitem成功时，rescode==200
      //使用获得的数据给数据赋值，并且展示出来
      if(res.code == 200){
        this.setData({
          year: res.data.year,
          id: res.data.id,
          productionName: res.data.productionName,
          ability: res.data.ability,
          referrence: res.data.referrence,
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

  deleteData(e) {
    let url = app.globalData.url + 'TestAbility/deleteOneItem'
    let data = {
      "id": this.data.id
    }
    //删除item时，需要传递id来决定删除哪一个数据
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
      //删除成功时，从服务器端返回rescode==200
      //接收到rescode==200后，显示删除成功，返回上一界面
      if (res.code == 200) {
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
  }

})