const app = getApp()
// pages/Supervision/SupervisionRecord/SupervisionRecordAddOne/SupervisionRecordAddOne.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    planid: '',
    superviseDate: "",
    recordDate: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    this.setData({
      planid: options.id
    })
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
    if (e.detail.value.superviseDate == "" || e.detail.value.recordDate == "" ) {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 500,
        success: function () {
          setTimeout(function () {
          }, 300)
        }
      })
      console.log('空白输入')
    }
    else {
      console.log('SupervisionRecord发生了addone事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'SupervisionRecord/addOne'
      let postdata = {
        "planId": planid,
        "department": e.detail.value.department,
        "supervisor": e.detail.value.supervisor,
        "superviseDate": e.detail.value.superviseDate,
        "supervisedPerson": e.detail.value.supervisedPerson,
        "record": e.detail.value.record,
        "conclusion": e.detail.value.conclusion,
        "operator": e.detail.value.operator,
        "recordDate": e.detail.value.recordDate
      }
      app.wxRequest(url, 'POST', postdata, (res) => {
        console.log(res)
        if (res.code == 200) {
          wx.showToast({
            title: '添加成功',
            image: '/icons/ok/ok.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
                wx.navigateTo({
                  url: '/pages/Supervision/SupervisionRecord/SupervisionRecord?id=' + planid
                })
              }, 300)
            }
          })
          console.log("添加成功")
        }
        else if (res.code == 511) {
          wx.showToast({
            title: '空白输入',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 300)
            }
          })
          console.log("缺少请求参数")
        }
        else if (res.code == 513) {
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 300)
            }
          })
          console.log("某项数据错误")
        }
        else {
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 300)
            }
          })
          console.log("添加失败")
        }
      }, (err) => {
        console.log('fail addone')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
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