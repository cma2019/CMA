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
    if (e.detail.value.year == null)
      {
      wx.showToast({
        title: '错误(空白输入)',
        icon: 'none',
        duration: 2000
      })
      console.log('错误(空白输入)')
    }
    else
    {console.log(e)
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