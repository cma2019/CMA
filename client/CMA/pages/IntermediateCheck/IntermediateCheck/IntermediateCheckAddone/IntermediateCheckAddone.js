// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {

  },
  gotologin(e){
    //返回上一界面
    wx.navigateBack({
      delta : 1
    })
  },
  bindDateChange(e) {
    //date picker 必备，使显示值和保存值保持相同
    this.setData({
      date: e.detail.value
    })
  },

  InterCheckRegister(e) {
    console.log(e.detail)
    //在前端保证输入不为空
    //微信端中，若没有点输入框，则输入为null
    //点击但是不输入，则输入为""，这两种情况都需要考虑
    if (e.detail.value.object == null ||
      e.detail.value.content == null ||
      e.detail.value.date == null ||
      e.detail.value.personInCharge == null ||
      e.detail.value.name == "" ||
      e.detail.value.method == "" ||
      e.detail.value.personInCharge == "" ||
      e.detail.value.date == "") {
      console.log("message error")
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    }
    else {
      //传递的四个值，都来自于用户的输入
      let url = app.globalData.url + 'IntermediateChecksPlan/addOne'
      let data = {
        "object": e.detail.value.object,
        "content": e.detail.value.content,
        "checkDate": e.detail.value.date,
        "personInCharge": e.detail.value.personInCharge
      }
      console.log(e)
      console.log(url)
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        //在服务器返回res且code为200时，显示添加成功，并且返回上一界面
        if (res.code == 200){
          console.log('send intermediate check message successfully')
          console.log('send intermediate check message successfully')
          wx.showToast({
            title: '添加成功',
            image: '/icons/ok/ok.png',
            duration: 1000,
            success: function () {
              setTimeout(function () {
                wx.navigateBack({
                  delta:1
                })
              }, 1000);
            }
          })
          //返回code不是200的情况下，添加失败
        }else{
          console.log('send intermediate check message failed')
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      }, (err) => {
        //fail回调函数被调用时，说明函数调用失败，常为连接失败问题
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