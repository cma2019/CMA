// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {   
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'TrainingApplication/getOne'
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
        people: res.data.people,
        trainingUnit: res.data.trainingUnit,
        expense: res.data.expense,
        reason: res.data.reason,
        situation: res.data.situation,
        department: res.data.department,
        createDate: res.data.createDate,
        approver: res.data.approver,
        approveDate: (res.data.approver == "") ? res.data.approver : res.data.approveDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  onShow:function()
  {
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'TrainingApplication/getOne'
    let postdata = {
      "id": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      console.log(res.data.id)
      var s;
      if (res.data.situation==0)
      {
          s="未审查"
      }
      else if (res.data.situation == 1)
      {
        s = "未通过"
      }
      else
      {
        s="已通过"
      }
      //console.log(res.data[0].id)
      this.setData({
        name: res.data.name,
        people: res.data.people,
        trainingUnit: res.data.trainingUnit,
        expense: res.data.expense,
        reason: res.data.reason,
        situation: s,
        department: res.data.department,
        createDate: res.data.createDate,
        approver: res.data.approver,
        approveDate: (res.data.approver == "") ? res.data.approver : res.data.approveDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  ModifyStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ModifyApplication/ModifyApplication?id=' + target
    })
  },
  ApproveStaff(e) {
    console.log(e)
    let target = this.data.id
    console.log(target)
    wx.navigateTo({
      url: '../ApproveOneApplication/ApproveOneApplication?id=' + target
    })
  },
  DeleteStaff(e) {
    let url = app.globalData.url + 'TrainingApplication/deleteOne'
    let data = {
      "id": this.data.id
    }
    app.wxRequest(url, 'POST', data, (res) => {
      console.log('delete successfully')
      wx.showToast({
        title: '删除成功',
        //icon: 'success',
        image: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.navigateBack({
              delta: 1
            })
          }, 1000);
        }
      })
    }, (err) => {
      console.log('delete failed')
    })
  }

})