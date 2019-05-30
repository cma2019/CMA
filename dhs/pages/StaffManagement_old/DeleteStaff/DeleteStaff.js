
Page({

  data: {
      //id:'null',
  },

  onLoad: function (options) {

  },
  StaffDelete: function (e) {

    if (e.detail.value.id== "") {
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

        url: 'http://192.168.1.106:8004/cma/StaffManagement/deleteOne',
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        data: {
          "id": e.detail.value.id
          //"name": e.detail.value.name,
        },
        success(res) {
          console.log(res.data)
          if (res.code == 200) {
            wx.showToast({
              title: '人员删除成功',
              duration: 1500
            })
            wx.navigateTo({
              url: 'pages/StaffManagement/StaffManagement',
            })
          }
        },
        fail(err) {
          wx.showToast({
            title: '删除失败',
            duration: 1500
          })
          console.log('删除失败')
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