// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "year":null
  },

  onLoad: function (options) {
    this.setData({
      year: options.id
    })
  },

  AddoneTestItem(e) {
    if (e.detail.value.productionName == "" ||
      e.detail.value.ability == "" ||
      e.detail.value.referrence == "") {
      console.log("message error")
      console.log(e.detail.value.productionName)
      console.log(e.detail.value.ability)
      console.log(e.detail.value.referrence)
    }
    else {
      let url = app.globalData.url + 'TestAbility/addOneItem'
      let data = {
        "productionName":this.data.year,
        "productionName": e.detail.value.productionName,
        "ability": e.detail.value.ability,
        "referrence": e.detail.value.referrence
      }
      console.log(e)
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('add ability test successfully')
        if(res.code == 200){
          wx.navigateBack({
            delta : 1
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
      })
    }
  }
})