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
      year: options.id
    })
  },
  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getOneItem'
    let postdata = {
      "year": this.data.year
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log('test ability getone success')
      console.log(res)
      this.setData({
        id: res.data[0].id,
        productionName: res.data[0].productionName,
        ability: res.data[0].ability,
        referrence: res.data[0].referrence,
      })
    }, (err) => {
      console.err('getone error')
    })
  },

  deleteData(e) {
    let url = app.globalData.url + 'TestAbility/deleteOneItem'
    let data = {
      "id": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
    }, (err) => {
      console.log('delete failed')
    })
  }

})