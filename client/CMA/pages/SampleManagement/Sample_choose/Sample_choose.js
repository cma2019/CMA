// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    this.setData({

    })
  },

  gotoSampleReceive: function(){
    wx.navigateTo({
      url: '../page_samplereceive/SampleReceive/SampleReceive',
    })
  },
  gotoSampleIo:function(){
    wx.navigateTo({
      url: '../page_SampleIo/SampleIo/SampleIo',
    })
  },
  gotoSampleReceipt: function () {
    wx.navigateTo({
      url: '../page_SampleReceipt/SampleReceipt/SampleReceipt',
    })
  }
})