// pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformation.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "testingInstitutionName": "CMA",
    "testingInstitutionAddress": "CMA",
    "postcode": "CMA",
    "fax": "CMA",
    "email": "CMA",
    "tiPeopleInCharge": "CMA",
    "tiPicPosition": "CMA",
    "tiPicTelephone": "CMA",
    "tiPicMobilephone": "CMA",
    "liaison": "CMA",
    "liaisonPosition": "CMA",
    "liaisonTelephone": "CMA",
    "liaisonMobilephone": "CMA",
    "socialCreditCode": "CMA",
    "legalEntityBelongedName": "CMA",
    "legalEntityBelongedAddress": "CMA",
    "lebPeopelInCharge": "CMA",
    "lebPicPosition": "CMA",
    "lebPicTelephone": "CMA",
    "lebSocialCreditCode": "CMA",
    "competentDepartmentName": "CMA",
    "competentDepartmentAddress": "CMA",
    "cdPeopelInCharge": "CMA",
    "cdPicPosition": "CMA",
    "cdPicTelephone": "CMA",
    "characteristic": "CMA",
    "legalEntity": "CMA"
  },
  viewDetail: function (e) {
    console.log("display -> view")
    var that = this
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformationModify/TestingInstitutionInformationModify?testingInstitutionName=' + that.data.testingInstitutionName + "&testingInstitutionAddress=" + that.data.testingInstitutionAddress + "&postcode=" + that.data.postcode + "&fax=" + that.data.fax + "&email=" + that.data.email + "&tiPeopleInCharge=" + that.data.tiPeopleInCharge + "&tiPicPosition=" + that.data.tiPicPosition + "&tiPicTelephone=" + that.data.tiPicTelephone + "&tiPicMobilephone=" + that.data.tiPicMobilephone + "&liaison=" + that.data.liaison + "&liaisonPosition=" + that.data.liaisonPosition + "&liaisonTelephone=" + that.data.liaisonTelephone + "&liaisonMobilephone=" + that.data.liaisonMobilephone + "&socialCreditCode=" + that.data.socialCreditCode + "&legalEntityBelongedName=" + that.data.legalEntityBelongedName + "&legalEntityBelongedAddress=" + that.data.legalEntityBelongedAddress + "&lebPeopelInCharge=" + that.data.lebPeopelInCharge + "&lebPicPosition=" + that.data.lebPicPosition + "&lebPicTelephone=" + that.data.lebPicTelephone + "&lebSocialCreditCode=" + that.data.lebSocialCreditCode + "&competentDepartmentName=" + that.data.competentDepartmentName + "&competentDepartmentAddress=" + that.data.competentDepartmentAddress + "&cdPeopelInCharge=" + that.data.cdPeopelInCharge + "&cdPicPosition=" + that.data.cdPicPosition + "&cdPicTelephone=" + that.data.cdPicTelephone + "&characteristic=" + that.data.characteristic + "&legalEntity=" + that.data.legalEntity,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var myurl = app.globalData.url + 'TestingInstitutionInformation/get';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        "testingInstitutionName": res.data.testingInstitutionName,
        "testingInstitutionAddress": res.data.testingInstitutionAddress,
        "postcode": res.data.postcode,
        "fax": res.data.fax,
        "email": res.data.email,
        "tiPeopleInCharge": res.data.tiPeopleInCharge,
        "tiPicPosition": res.data.tiPicPosition,
        "tiPicTelephone": res.data.tiPicTelephone,
        "tiPicMobilephone": res.data.tiPicMobilephone,
        "liaison": res.data.liaison,
        "liaisonPosition": res.data.liaisonPosition,
        "liaisonTelephone": res.data.liaisonTelephone,
        "liaisonMobilephone": res.data.liaisonMobilephone,
        "socialCreditCode": res.data.socialCreditCode,
        "legalEntityBelongedName": res.data.legalEntityBelongedName,
        "legalEntityBelongedAddress": res.data.legalEntityBelongedAddress,
        "lebPeopelInCharge": res.data.lebPeopelInCharge,
        "lebPicPosition": res.data.lebPicPosition,
        "lebPicTelephone": res.data.lebPicTelephone,
        "lebSocialCreditCode": res.data.lebSocialCreditCode,
        "competentDepartmentName": res.data.competentDepartmentName,
        "competentDepartmentAddress": res.data.competentDepartmentAddress,
        "cdPeopelInCharge": res.data.cdPeopelInCharge,
        "cdPicPosition": res.data.cdPicPosition,
        "cdPicTelephone": res.data.cdPicTelephone,
        "characteristic": res.data.characteristic,
        "legalEntity": res.data.legalEntity
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
    var myurl = app.globalData.url + 'TestingInstitutionInformation/get';
    app.wxRequest(myurl, 'GET', null, (res) => {
      console.log(res)
      that.setData({
        "testingInstitutionName": res.data.testingInstitutionName,
        "testingInstitutionAddress": res.data.testingInstitutionAddress,
        "postcode": res.data.postcode,
        "fax": res.data.fax,
        "email": res.data.email,
        "tiPeopleInCharge": res.data.tiPeopleInCharge,
        "tiPicPosition": res.data.tiPicPosition,
        "tiPicTelephone": res.data.tiPicTelephone,
        "tiPicMobilephone": res.data.tiPicMobilephone,
        "liaison": res.data.liaison,
        "liaisonPosition": res.data.liaisonPosition,
        "liaisonTelephone": res.data.liaisonTelephone,
        "liaisonMobilephone": res.data.liaisonMobilephone,
        "socialCreditCode": res.data.socialCreditCode,
        "legalEntityBelongedName": res.data.legalEntityBelongedName,
        "legalEntityBelongedAddress": res.data.legalEntityBelongedAddress,
        "lebPeopelInCharge": res.data.lebPeopelInCharge,
        "lebPicPosition": res.data.lebPicPosition,
        "lebPicTelephone": res.data.lebPicTelephone,
        "lebSocialCreditCode": res.data.lebSocialCreditCode,
        "competentDepartmentName": res.data.competentDepartmentName,
        "competentDepartmentAddress": res.data.competentDepartmentAddress,
        "cdPeopelInCharge": res.data.cdPeopelInCharge,
        "cdPicPosition": res.data.cdPicPosition,
        "cdPicTelephone": res.data.cdPicTelephone,
        "characteristic": res.data.characteristic,
        "legalEntity": res.data.legalEntity
      })
      console.log(that.data.array)
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