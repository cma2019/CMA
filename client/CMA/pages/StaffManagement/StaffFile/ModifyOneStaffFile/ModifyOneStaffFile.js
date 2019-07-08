// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
  },
  onShow:function()
  {
    let url = app.globalData.url + 'StaffFile/getOne'
    let postdata = {
      "staffId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")

      this.setData({
        staffId: res.data.staffId,
        fileId: res.data.fileId,
        fileLocation: res.data.fileLocation
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  //修改数据
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      let url = app.globalData.url + 'StaffFile/modifyOne';
      console.log(url)
      console.log(this.data.id)
      let data = {
        //"id ":this.data.id,
        "staffId": e.detail.value.staffId,
        "fileId": e.detail.value.fileId,
        "fileLocation": e.detail.value.fileLocation
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        //修改成功提示
        wx.showToast({
          title: '成功',
          //icon: 'success',
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
      }, (err) => {
        console.log('fail modify')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})