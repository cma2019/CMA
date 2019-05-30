
Page({

  data: {
    books:[]
  },

  onLoad: function (options) {

  },
  StaffDelete: function (e) {

    if (e.detail.value.id == "") {
      wx.showToast({
        title: '错误(空白输入)',
        icon: 'none',
        duration: 2000
      })
      console.log('错误(空白输入)')
    }
    else {
      console.log('form发生了delete事件，携带数据为：', e.detail.value)
      //var id = e.currentTarget.dataset.deleteid;
      wx.request({

        url: 'http://192.168.1.106:8004/cma/StaffManagement/getOne',
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        data: {
          "id": e.detail.value.id
          //"name": e.detail.value.name,
        },
        success(res) {
          console.log(res.data)
          that.setData({
            books: e.data,
          })
          console.log(e.data);
          if (res.code == 200) {
            wx.showToast({
              title: '成功',
              duration: 15000
            })
            wx.navigateTo({
              url: 'pages/StaffManagement/StaffManagement',
            })
          }
        },
        fail(err) {
          wx.showToast({
            title: '查看失败',
            duration: 1500
          })
          console.log('查看失败')
        },
        complete(fin) {
          console.log('final')
        }
      })

    }

  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})