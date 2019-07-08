const app = getApp()
var util = require('../../../utils/util.js');
Page({

  data: {
    year: "",
    select: [],
    currentYear: ''
  },

  onLoad: function (options) {
    var YEAR = util.formatYear(new Date())
    this.setData({
      currentYear: YEAR
    })
    console.log(this.data)
    let select = this.data.select
    for (let i = this.data.currentYear + 3; i >= 1990; --i) {
      select.push(i)
    }
    this.setData({
      select: select
    })
  },
  onShow: function (options) {

  },
  bindyearChange(e) {
    let year = this.data.select[e.detail.value]
    this.setData({
      year: year
    })
  },
  ApplicationAdd: function (e)
  {
    var regNum = new RegExp('[0-9]', 'g');
    if (e.detail.value.year == null)
      {
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
      console.log('错误(空白输入)')
    }
    else if (regNum.exec(e.detail.value.year) == null) {
      wx.showToast({
        title: '请输入正确年份',
        image: '/icons/warning/warning.png',
        duration: 1000

      })
    }
    else
    {
      console.log(e)
    let target = e.detail.value.year
    console.log(e.detail.value)
    console.log('getall id')
    console.log(target)
    wx.navigateTo({
      url: 'AnnualTrainingPlan/AnnualTrainingPlan?year=' + target
    })
    }
  },
  gotwo: function () {
    wx.navigateTo({
      url:'AllAnnualPlan/AllAnnualPlan'
    })
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})