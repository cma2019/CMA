// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    /*"id": "null",
    "name":"null",
    "gender": "null",      
    "department": "null",  
    "position": "null",      
    "title": "null",    	
    "degree": "null",      
    "graduationSchool": "null",
    "graduationMajor": "null",
    "graduationDate": "null",
    "workingYears": "null"*/
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    
  },
  
  onShow: function () {
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffManagement/getOne'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      console.log(res.data.id)
      //console.log(res.data[0].id)
      this.setData({
        name: res.data.name,
        gender: res.data.gender,
        department: res.data.department,
        position: res.data.position,
        title: res.data.title,
        degree: res.data.degree,
        graduationSchool: res.data.graduationSchool,
        graduationMajor: res.data.graduationMajor,
        graduationDate: res.data.graduationDate,
        workingYears: res.data.workingYears
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  ModifyStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ModifyStaff/ModifyStaff?id=' + target
    })
  },

  DeleteStaff(e) {
    let url = app.globalData.url + 'StaffManagement/deleteOne'
    let data = {
      "id": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
    }, (err) => {
      console.log('delete failed')
    })
  }

})