const app = getApp()
const CryptoJS = require("../../utils/cryptojs.js")
Page({

  data: {

  },

  onLoad: function (options) {

  },
  onShow: function (options) {

  },

  fun1: function(e) {
    //在原有的注册基础上，添加新的接口getCode
    //原有的功能中，将用户名与密码一起明文发送，不安全
    //在新的功能中，先发送用户名，前后端将用户名一起按照某种逻辑处理后，将其作为新的密钥
    //在发送密码时，前端将密码使用新的密钥加密，并且发送。后端相应解密，实现安全的密码传递过程
    let codeurl = app.globalData.url + 'user/getCode'
    console.log("wx login")
    let userdata = {
      "username": e.detail.value.username,
    }
    //发送用户名
    console.log(userdata)
    app.wxRequest(codeurl, 'POST', userdata, (res) => {
      console.log(res)
      if (res.code == 200) {
        //发送成功，将用户名使用默认密钥加密，截取后作为新的密钥
        
        console.log('code successfully')
        console.log(res)
        CryptoJS.username = e.detail.value.username
       
        var mykey = CryptoJS.Encrypt(CryptoJS.username)
        var endkey = mykey.slice(0, 16)
          console.log(mykey)
          console.log(endkey)
          console.log(CryptoJS.key)

        var data2 = CryptoJS.Encrypt(CryptoJS.username)
        console.log("data2")
        console.log(data2)

        //CryptoJS.selfkey = endkey
        
        //由于流程较长，该代码段中存在疑似因为多线程出现的错误
        //解决的方法是，不是将函数顺序调用，而是在前一个函数的回调函数中调用后一个函数
        //此处就是一个例子，setkey结束前，有可能就已经调用了fun2
        //此处在收到setkey成功的消息后再调用fun2，则可以解决这一问题
        if (CryptoJS.SetKey(endkey)){
          console.log("usercode")
          console.log(CryptoJS.key)
          this.fun2(e)
        }
        
      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
  },

  fun2:function(e){
    //fun2负责将密码加密，并且发送给后端
    console.log("wx login end")
    let url = app.globalData.url + 'user/add'


    var data3 = CryptoJS.Encrypt(CryptoJS.username)
    console.log("data3")
    console.log(data3)

    let tempdata = {
      "password": e.detail.value.password,
      "password2": e.detail.value.password2
    }
    
    var lastdata = JSON.stringify(tempdata)
      console.log("last")
      console.log(lastdata)
      console.log(CryptoJS.username)
    
    var data = CryptoJS.Encrypt(lastdata)
    let adddata = {
      "data": data,
    }
      console.log("data secret")
      console.log(adddata)
      console.log(data)
    var resdata = CryptoJS.Decrypt(data)
      console.log("data result")
      console.log(url)
      console.log(resdata)
    
    app.wxRequest(url, 'POST', adddata, (res) => {
      
      console.log(res)
      if(res.code == 200){
        console.log('successfully')
        wx.showToast({
          title: '注册成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.redirectTo({
                url: '/pages/login/login',
              })
            }, 1000);
          }
        })
      
      }
      else {
        wx.showToast({
          title: '两次密码不匹配',
          image: '/icons/warning/warning.png',
          duration: 1000
        })

      }
    }, (err) => {
      console.log('fail intermediate check register')
    })
  },

  ApplicationAdd: function (e) {
      console.log('form发生了add事件，携带数据为：', e.detail.value)
      
      if (CryptoJS.SetKey("123456789abcdef1")) {
        console.log("usercode")
        console.log(CryptoJS.key)
        this.fun1(e)
      }
      //this.fun1(e)
  },

  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})