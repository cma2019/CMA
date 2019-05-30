
Page({

  data: {
    user_name : "null",
    user_email : "null"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    this.setData({
      
      })
  },

  formsubmit: function (e) {
      console.log('form发生了submit事件，携带数据为：', e.detail.value)
      console.log('form发生了submit事件，携带数据为：', e.detail.value.email)

      const loginRequest =  wx.request({
        url: 'http://192.168.1.109:8004/user/find',
        method:'GET',
        data:{
          "email": e.detail.value.email,
          "password": e.detail.value.password
        },
        success(res){
          console.log(res.data)
          if(res.data == "Sign in successfully."){
            wx.showToast({
              title: 'log in successfully',
              duration: 2000
            })
          }
          console.log(loginRequest)
        },
        fail(err){
          console.log('fail login')
        }
      })

  },

  gotoregister: function () {
    wx.navigateTo({
      url: '../register/register'
    })
  }
})