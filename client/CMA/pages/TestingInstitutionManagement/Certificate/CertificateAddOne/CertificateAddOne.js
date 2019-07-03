// pages/TestingInstitutionManagement/Certificate/CertificateAddOne/CertificateAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "test id",
    fileName: "test.docx"
  },
  
  newEquipment: function (e) {
    console.log(e.detail.value)
    var myurl = app.globalData.url + 'Certificate/addOne';
    var mypath;
    wx.chooseMessageFile({
      count:1,
      type:'file',
      success:function(res){
        console.log(res)
        mypath = res.tempFilePaths
      },
      fail:function(err){
        console.log(err)
      }
    })
    app.wxUploadFile(myurl,mypath,null,(res) => {
      console.log(res)
    },(err) => {
      console.log(err)
    })
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/Certificate/Certificate',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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