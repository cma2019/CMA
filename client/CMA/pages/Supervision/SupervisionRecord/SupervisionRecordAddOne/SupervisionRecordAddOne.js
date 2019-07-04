<<<<<<< HEAD
const app = getApp()
=======
// pages/Supervision/SupervisionRecord/SupervisionRecordAddOne/SupervisionRecordAddOne.js
>>>>>>> a8a5724cc8623347d68a3f074ef2fa680b2a8b84
Page({

  /**
   * 页面的初始数据
   */
  data: {
<<<<<<< HEAD
    planid: '',
    superviseDate: "",
    recordDate: ""
=======

>>>>>>> a8a5724cc8623347d68a3f074ef2fa680b2a8b84
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
<<<<<<< HEAD
    console.log(options)
    this.setData({
      planid: options.id
    })
    console.log("1aaaaa")
    console.log(this.data.planid)
  },
  bindsuperviseDateChange(e) {
    this.setData({
      superviseDate: e.detail.value
    })
  },
  bindrecordDateChange(e) {
    this.setData({
      recordDate: e.detail.value
    })
  },
  SupervisionRecord_addone: function (e) {
    let planid = this.data.planid
    let thispage = this
    if (e.detail.value.superviseDate == "") {
      wx.showToast({
        title: '错误（空白输入）',
        icon: 'none',
        duration: 2000
      })
      console.log('错误（空白输入）')
    }
    else {
      console.log(planid)
      console.log('SupervisionRecord发生了addone事件，携带数据为：', e.detail.value)
      wx.request({
        url: app.globalData.url + 'SupervisionRecord/addOne',
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        data: {
          "planId": planid,
          "department": e.detail.value.department,
          "supervisor": e.detail.value.supervisor,
          "superviseDate": e.detail.value.superviseDate,
          "supervisedPerson": e.detail.value.supervisedPerson,
          "record": e.detail.value.record,
          "conclusion": e.detail.value.conclusion,
          "operator": e.detail.value.operator,
          "recordDate": e.detail.value.recordDate
        },
        success(res) {
          console.log(res.data)
          if (res.data.code == 200) {
            wx.showToast({
              title: '成功',
              icon: 'none',
              duration: 1500
            })
            console.log("4545646546578")
            console.log(thispage.data.superviseDate)
            wx.navigateTo({
              url: '/pages/Supervision/SupervisionRecord/SupervisionRecord?id=' + planid
            })
          }
          else if (res.data.code == 512) {
            wx.showToast({
              title: '监督计划编号已存在',
              duration: 1500
            })
            console.log('监督计划编号已存在')
          }
          else {
            console.log(planid)
            console.log("fsdgf465465")
            wx.showToast({
              title: '某项数据错误',
              duration: 1500
            })
            console.log('某项数据错误')
          }
        },
        fail(err) {
          console.log('fail addone')
        },
        complete(fin) {
          console.log('final addone')
        }
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
=======

  },

>>>>>>> a8a5724cc8623347d68a3f074ef2fa680b2a8b84
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
<<<<<<< HEAD
    console.log("465465456465")
=======

>>>>>>> a8a5724cc8623347d68a3f074ef2fa680b2a8b84
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})