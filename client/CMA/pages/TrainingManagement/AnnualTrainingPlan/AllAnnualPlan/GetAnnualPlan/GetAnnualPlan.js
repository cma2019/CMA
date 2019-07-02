// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    console.log(options)
    this.setData({

      year: options.id
    })
    console.log('getone year')
    console.log(this.data.year)
    let url = app.globalData.url + 'AnnualTrainingPlan/getAnnualPlan'
    let postdata = {
      "year": this.data.year
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      this.setData({
        year: res.data.year,
        author: res.data.author,
        createDate: res.data.createDate,
        approver: res.data.approver,
        approveDate: (res.data.approver == "") ? res.data.approver:res.data.approveDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  ApproveStaff(e) {
    console.log(e)
    let target = this.data.year
    console.log(target)
    wx.navigateTo({
      url: '../ApproveAnnualPlan/ApproveAnnualPlan?id=' + target
    })
  }

})