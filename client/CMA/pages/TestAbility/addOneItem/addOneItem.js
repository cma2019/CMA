// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "year":null
  },

  onLoad: function (options) {
    //在所有操作进行前，初始化year参数为其他界面给出的参数
    this.setData({
      year: options.id
    })
  },

  AddoneTestItem(e) {
    //保证所有输入都不为空
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
      //传输四个参数
      console.log(e)
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('add ability test successfully')
        //由服务器端返回数据，rescode==200即添加成功
        //显示返回成功，返回上个界面
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