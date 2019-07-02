// pages/TestingInstitutionManagement/TestingInstitutionResource/TestingInstitutionResource.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "totalNumber": "CMA",
    "seniorProfessionalTitle": "CMA",
    "intermediateProfessionalTitle": "CMA",
    "primaryProfessionalTitle": "CMA",
    "fixedAssets": "CMA",
    "equipmentNumber": "CMA",
    "floorSpace": "CMA",
    "stableArea": "CMA",
    "outdoorsArea": "CMA",
    "nameAndAddress": "CMA",
    "newPlace": "CMA"
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/TestingInstitutionResource/TestingInstitutionResourceModify/TestingInstitutionResourceModify?totalNumber=' + that.data.totalNumber + "&seniorProfessionalTitle=" + that.data.seniorProfessionalTitle + "&intermediateProfessionalTitle=" + that.data.intermediateProfessionalTitle + "&primaryProfessionalTitle=" + that.data.primaryProfessionalTitle + "&fixedAssets=" + that.data.fixedAssets + "&equipmentNumber=" + that.data.equipmentNumber + "&floorSpace=" + that.data.floorSpace + "&stableArea=" + that.data.stableArea + "&outdoorsArea=" + that.data.outdoorsArea + "&nameAndAddress=" + that.data.nameAndAddress + "&newPlace=" + that.data.newPlace,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var myurl = app.globalData.url + 'TestingInstitutionResource/get';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        "totalNumber": res.data.totalNumber,
        "seniorProfessionalTitle": res.data.seniorProfessionalTitle,
        "intermediateProfessionalTitle": res.data.intermediateProfessionalTitle,
        "primaryProfessionalTitle": res.data.primaryProfessionalTitle,
        "fixedAssets": res.data.fixedAssets,
        "equipmentNumber": res.data.equipmentNumber,
        "floorSpace": res.data.floorSpace,
        "stableArea": res.data.stableArea,
        "outdoorsArea": res.data.outdoorsArea,
        "nameAndAddress": res.data.nameAndAddress,
        "newPlace": res.data.newPlace
      })
      console.log(that.data)
    }, (err) => {
      console.log(err)
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
    var that = this
    var myurl = app.globalData.url + 'TestingInstitutionResource/get';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        "totalNumber": res.data.totalNumber,
        "seniorProfessionalTitle": res.data.seniorProfessionalTitle,
        "intermediateProfessionalTitle": res.data.intermediateProfessionalTitle,
        "primaryProfessionalTitle": res.data.primaryProfessionalTitle,
        "fixedAssets": res.data.fixedAssets,
        "equipmentNumber": res.data.equipmentNumber,
        "floorSpace": res.data.floorSpace,
        "stableArea": res.data.stableArea,
        "outdoorsArea": res.data.outdoorsArea,
        "nameAndAddress": res.data.nameAndAddress,
        "newPlace": res.data.newPlace
      })
      console.log(that.data)
    }, (err) => {
      console.log(err)
    })
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