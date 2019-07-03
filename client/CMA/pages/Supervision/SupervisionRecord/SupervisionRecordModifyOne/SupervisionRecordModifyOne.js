// page_SampleIo/ioModifyOne/ioModifyOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */

  data: {
    recordId: '',
    planId: '',
    department: '',
    supervisor: '',
    superviseDate: '',
    supervisedPerson: '',
    record: '',
    conclusion: '',
    operator: '',
    recordDate: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      recordId: options.recordId,
      department: options.department,
      supervisor: options.supervisor,
      superviseDate: options.superviseDate,
      supervisedPerson: options.supervisedPerson,
      record: options.record,
      conclusion: options.conclusion,
      operator: options.operator,
      recordDate: options.recordDate
    })
    //console.log(this.data.sampleIoId)
    console.log("fdsf")
  },

  bindsuperviseDateChange(e) {
    console.log(e.detail.value)
    this.setData({
      superviseDate: e.detail.value
    })
    console.log(this.newsuperviseDate)
  },

  bindrecordDateChange(e) {
    this.setData({
      recordDate: e.detail.value
    })
    console.log(this.newrecordDate)
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  SampleIo_modifyone: function (e) {
    var mod = this.data
    console.log('SampleIo发生了modifyone事件，携带数据为：', e.detail.value)
    console.log(this.data)
    if (e.detail.value.recordId != null && e.detail.value.recordId != "") {
      mod.recordId = e.detail.value.recordId
    }
    if (e.detail.value.department != null && e.detail.value.department != "") {
      mod.department = e.detail.value.department
    }
    if (e.detail.value.supervisor != null && e.detail.value.supervisor != "") {
      mod.supervisor = e.detail.value.supervisor
    }
    if (e.detail.value.supervisedPerson != null && e.detail.value.supervisedPerson != "") {
      mod.supervisedPerson = e.detail.value.supervisedPerson
    }
    if (e.detail.value.record != null && e.detail.value.record != "") {
      mod.record = e.detail.value.record
    }
    if (e.detail.value.conclusion != null && e.detail.value.conclusion != "") {
      mod.conclusion = e.detail.value.conclusion
    }
    if (e.detail.value.operator != null && e.detail.value.operator != "") {
      mod.operator = e.detail.value.operator
    }

    if (e.detail.value.superviseDate != null && e.detail.value.superviseDate != "") {
      mod.superviseDate = e.detail.value.superviseDate
    }
    if (e.detail.value.recordDate != null && e.detail.value.recordDate != "") {
      mod.recordDate = e.detail.value.recordDate
    }
    console.log("dfg")
    console.log(this.data)
    wx.request({
      url: app.globalData.url + 'SupervisionRecord/modifyOne',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      data: {
        "recordId": mod.recordId,
        "department": mod.department,
        "supervisor": mod.supervisor,
        "superviseDate": mod.superviseDate,
        "supervisedPerson": mod.supervisedPerson,
        "record": mod.record,
        "conclusion": mod.conclusion,
        "operator": mod.operator,
        "recordDate": mod.recordDate
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            duration: 1500
          })
          wx.navigateTo({
            url: '../SupervisionRecord'
          })
        }
        else if (res.data.code == 531) {
          wx.showToast({
            title: '未收到标识编号',
            duration: 1500
          })
          console.log('未收到标识编号')
        }
        else if (res.data.code == 532) {
          wx.showToast({
            title: '数据不存在',
            duration: 1500
          })
          console.log('数据不存在')
        }
        else if (res.data.code == 533) {
          wx.showToast({
            title: '修改后数据错误',
            duration: 1500
          })
          console.log('修改后数据错误')
        }
        else {
          wx.showToast({
            title: '修改后数据不合法',
            duration: 1500
          })
          console.log('修改后数据不合法')
        }
      },
      fail(err) {
        console.log('fail modifyone')
      },
      complete(fin) {
        console.log('final modifyone')
      }
    })

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