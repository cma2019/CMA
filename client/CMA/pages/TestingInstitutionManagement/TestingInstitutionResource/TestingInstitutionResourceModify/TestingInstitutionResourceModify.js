// pages/TestingInstitutionManagement/TestingInstitutionResource/TestingInstitutionResourceModify/TestingInstitutionResourceModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/TestingInstitutionResource/TestingInstitutionResource',
    })
  },
  mytest: function (e) {
    var that = this

    //console.log(e.detail.value)
    var myurl = app.globalData.url + 'TestingInstitutionResource/modifyOne';
    var mydata = {
      "totalNumber": e.detail.value.totalNumber,
      "seniorProfessionalTitle": e.detail.value.seniorProfessionalTitle,
      "intermediateProfessionalTitle": e.detail.value.intermediateProfessionalTitle,
      "primaryProfessionalTitle": e.detail.value.primaryProfessionalTitle,
      "fixedAssets": e.detail.value.fixedAssets,
      "equipmentNumber": e.detail.value.equipmentNumber,
      "floorSpace": e.detail.value.floorSpace,
      "stableArea": e.detail.value.stableArea,
      "outdoorsArea": e.detail.value.outdoorsArea,
      "nameAndAddress": e.detail.value.nameAndAddress,
      "newPlace": e.detail.value.newPlace
    };
    app.wxRequest(myurl, 'POST', mydata, (res) => {
      console.log(res.data)
      wx.showToast({
        title: '修改成功',
        icon: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/TestingInstitutionManagement/TestingInstitutionResource/TestingInstitutionResource',
            })
          }, 1000);
        }
      })
    }, (err) => {
      console.log(err)
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (option) {
    var that = this;
    console.log(option)
    that.setData({
      "totalNumber": option.totalNumber,
      "seniorProfessionalTitle": option.seniorProfessionalTitle,
      "intermediateProfessionalTitle": option.intermediateProfessionalTitle,
      "primaryProfessionalTitle": option.primaryProfessionalTitle,
      "fixedAssets": option.fixedAssets,
      "equipmentNumber": option.equipmentNumber,
      "floorSpace": option.floorSpace,
      "stableArea": option.stableArea,
      "outdoorsArea": option.outdoorsArea,
      "nameAndAddress": option.nameAndAddress,
      "newPlace": option.newPlace
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