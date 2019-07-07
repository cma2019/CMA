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
    this.setData({
      id: options.id
    })
  },
  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getOneItem'
    let postdata = {
      "id": this.data.id
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('test ability getone success')
      console.log(res)
      this.setData({
        year: res.data.year,
        id: res.data.id,
        productionName: res.data.productionName,
        ability: res.data.ability,
        referrence: res.data.referrence,
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

  deleteData(e) {
    let url = app.globalData.url + 'TestAbility/deleteOneItem'
    let data = {
      "id": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
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