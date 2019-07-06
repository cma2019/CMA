// pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformationModify/TestingInstitutionInformationModify.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  mygo: function (e) {
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformation',
    })
  },
  mytest: function (e) {
    var that = this

    //console.log(e.detail.value)
    var myurl = app.globalData.url + 'TestingInstitutionInformation/modifyOne';
    var mydata = {
      "testingInstitutionName": e.detail.value.testingInstitutionName,
      "testingInstitutionAddress": e.detail.value.testingInstitutionAddress,
      "postcode": e.detail.value.postcode,
      "fax": e.detail.value.fax,
      "email": e.detail.value.email,
      "tiPeopleInCharge": e.detail.value.tiPeopleInCharge,
      "tiPicPosition": e.detail.value.tiPicPosition,
      "tiPicTelephone": e.detail.value.tiPicTelephone,
      "tiPicMobilephone": e.detail.value.tiPicMobilephone,
      "liaison": e.detail.value.liaison,
      "liaisonPosition": e.detail.value.liaisonPosition,
      "liaisonTelephone": e.detail.value.liaisonTelephone,
      "liaisonMobilephone": e.detail.value.liaisonMobilephone,
      "socialCreditCode": e.detail.value.socialCreditCode,
      "legalEntityBelongedName": e.detail.value.legalEntityBelongedName,
      "legalEntityBelongedAddress": e.detail.value.legalEntityBelongedAddress,
      "lebPeopleInCharge": e.detail.value.lebPeopleInCharge,
      "lebPicPosition": e.detail.value.lebPicPosition,
      "lebPicTelephone": e.detail.value.lebPicTelephone,
      "lebSocialCreditCode": e.detail.value.lebSocialCreditCode,
      "competentDepartmentName": e.detail.value.competentDepartmentName,
      "competentDepartmentAddress": e.detail.value.competentDepartmentAddress,
      "cdPeopleInCharge": e.detail.value.cdPeopleInCharge,
      "cdPicPosition": e.detail.value.cdPicPosition,
      "cdPicTelephone": e.detail.value.cdPicTelephone,
      "characteristic": e.detail.value.characteristic,
      "legalEntity": e.detail.value.legalEntity
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
              url: '/pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformation',
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
      "testingInstitutionName": option.testingInstitutionName,
      "testingInstitutionAddress": option.testingInstitutionAddress,
      "postcode": option.postcode,
      "fax": option.fax,
      "email": option.email,
      "tiPeopleInCharge": option.tiPeopleInCharge,
      "tiPicPosition": option.tiPicPosition,
      "tiPicTelephone": option.tiPicTelephone,
      "tiPicMobilephone": option.tiPicMobilephone,
      "liaison": option.liaison,
      "liaisonPosition": option.liaisonPosition,
      "liaisonTelephone": option.liaisonTelephone,
      "liaisonMobilephone": option.liaisonMobilephone,
      "socialCreditCode": option.socialCreditCode,
      "legalEntityBelongedName": option.legalEntityBelongedName,
      "legalEntityBelongedAddress": option.legalEntityBelongedAddress,
      "lebPeopleInCharge": option.lebPeopleInCharge,
      "lebPicPosition": option.lebPicPosition,
      "lebPicTelephone": option.lebPicTelephone,
      "lebSocialCreditCode": option.lebSocialCreditCode,
      "competentDepartmentName": option.competentDepartmentName,
      "competentDepartmentAddress": option.competentDepartmentAddress,
      "cdPeopleInCharge": option.cdPeopleInCharge,
      "cdPicPosition": option.cdPicPosition,
      "cdPicTelephone": option.cdPicTelephone,
      "characteristic": option.characteristic,
      "legalEntity": option.legalEntity
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