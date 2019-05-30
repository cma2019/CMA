
Page({

  data: {
    
  },

  onLoad: function (options) {
    
  },
  userregister: function (e) {
    if (e.detail.value.password != e.detail.value.password2 || e.detail.value.password == "" || e.detail.value.password2 ==""){
      wx.showToast({
        title: 'wrong password',
       
        duration: 2000
      })
      console.log('wrong password')
    }
    else{
      if(e.detail.value.name == "" || e.detail.value.email == ""){
        wx.showToast({
          title: 'wrong name or email',
          icon: 'none',
          duration: 2000
        })
        console.log('wrong name or email')
      }
      else{
        console.log('form发生了register事件，携带数据为：', e.detail.value)
        console.log('form发生了register事件，携带数据为：', e.detail.value.username)

        //var mydata = JSON.stringify(e.detail.value)
        //console.log(mydata)

        wx.request({
          url: 'http://192.168.1.109:8004/user/add',
          method:'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
          },
          data:{
            "username": e.detail.value.username,
            "email": e.detail.value.email,
            "password": e.detail.value.password,
            "password2": e.detail.value.password2
          },
          success(res) {
            console.log(res.data)
            if(res.data == "Sign up successfully."){
              wx.navigateBack({
                delta: 1
              })
            }
          },
          fail(err) {
            console.log('fail register')
          },
          complete(fin){
            console.log('final')
          }
        })

      }
    }
  },
  gotologin: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})