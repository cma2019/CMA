// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    console.log(options)
    this.setData({
      id: options.id
    })
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffAuthorization/getOne'
    let postdata = {
      "authorizationId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      this.setData({
        name: res.data.name,
        department: res.data.department,
        position: res.data.position,
        authorizerId: res.data.authorizerId,
        content: res.data.content,
        authorizerName: res.data.authorizerName,
        authorizerDate: res.data.authorizerDate
      })
    }, (err) => {
      console.err('get one error')
    })
    
  },
  onShow:function()
  {
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffAuthorization/getOne'
    let postdata = {
      "authorizationId": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      this.setData({
        name: res.data.name,
        department: res.data.department,
        position: res.data.position,
        authorizerId: res.data.authorizerId,
        content: res.data.content,
        authorizerName: res.data.authorizerName,
        authorizerDate: res.data.authorizerDate
      })
    }, (err) => {
      console.err('get one error')
    })
  } ,
 ModifyStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ModifyOneStaffAuthorization/ModifyOneStaffAuthorization?id=' + target,
    })
  },
  DeleteStaff(e) {
    let url = app.globalData.url + 'StaffAuthorization/deleteOne'
    let data = {
      "authorizationId": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
    }, (err) => {
      console.log('delete failed')
    })
  }

})