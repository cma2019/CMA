// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
  },

  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    console.log(this.data.id)
    let url = app.globalData.url + 'AnnualTrainingPlan/getAnnualPlan'
    let postdata = {
      "year": this.data.id
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log("data modify")
      console.log(res.data.author)
      this.setData({
        year: res.data.year,
        author: res.data.author,
        createDate: res.data.createDate,
        approver: res.data.approver,
        approveDate: res.data.approveDate
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  bindDateChange: function (e) {
    console.log("date")
    console.log(e.detail.value)
    this.setData({
      approveDate: e.detail.value
    })
  },
 
  intercheckmodify: function (e) {
    
    {
      let url = app.globalData.url + 'AnnualTrainingPlan/approveAnnualPlan';
      console.log(url)
      
      let data = {
        //"id ":this.data.id,
        "year": e.detail.value.year,
        "author": e.detail.value.author,
        "createDate": e.detail.value.createDate,
        "approver": e.detail.value.approver,
        "approveDate": e.detail.value.approveDate
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        /*
        if (res.data == "modify successfully.") {
          wx.navigateBack({
            delta: 1
          })
        }
        */
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