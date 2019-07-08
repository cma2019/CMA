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
    if (e.detail.value.productionName == null ||
      e.detail.value.ability == null ||
      e.detail.value.referrence == null ||
      e.detail.value.productionName == "" ||
      e.detail.value.ability == "" ||
      e.detail.value.referrence == "") {
      console.log("message error")
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else {
      let url = app.globalData.url + 'TestAbility/addOneItem'
      let data = {
        "year":this.data.year,
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
          wx.showToast({
            title: '添加成功',
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
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        console.log('fail intermediate check register')
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      })
    }
  }
})