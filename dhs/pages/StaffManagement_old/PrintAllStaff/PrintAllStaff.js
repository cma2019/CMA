var app = getApp()
Page({
  data: {
    books: [] 
  },
  onLoad: function (options) {

    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: 'http://192.168.1.106:8004/cma/StaffManagement/getAll',
      method: 'GET',
      data: {},
      header: {
        //'Accept': 'application/json'
        'content-type':'application/json'
      },
      success: function (e) {
        that.setData({
          books: e.data,
        })
        console.log(e.data);
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    })
  }
})