const app = getApp()
Page({

  data: {

  },

  onLoad: function (options) {
  },
  onShow: function (options) {
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