// pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformation.js
//获取全局app实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  //测试用数据
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
    "lebPeopleInCharge": "CMA",
    "lebPicPosition": "CMA",
    "lebPicTelephone": "CMA",
    "lebSocialCreditCode": "CMA",
    "competentDepartmentName": "CMA",
    "competentDepartmentAddress": "CMA",
    "cdPeopleInCharge": "CMA",
    "cdPicPosition": "CMA",
    "cdPicTelephone": "CMA",
    "characteristic": "CMA",
    "legalEntity": "CMA"
  },
  //修改按钮的处理函数
  viewDetail: function (e) {
    console.log("display -> view")
    //保存指针
    var that = this
    //配置参数并跳转至修改界面
    wx.redirectTo({
      url: '/pages/TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformationModify/TestingInstitutionInformationModify?testingInstitutionName=' + that.data.testingInstitutionName + "&testingInstitutionAddress=" + that.data.testingInstitutionAddress + "&postcode=" + that.data.postcode + "&fax=" + that.data.fax + "&email=" + that.data.email + "&tiPeopleInCharge=" + that.data.tiPeopleInCharge + "&tiPicPosition=" + that.data.tiPicPosition + "&tiPicTelephone=" + that.data.tiPicTelephone + "&tiPicMobilephone=" + that.data.tiPicMobilephone + "&liaison=" + that.data.liaison + "&liaisonPosition=" + that.data.liaisonPosition + "&liaisonTelephone=" + that.data.liaisonTelephone + "&liaisonMobilephone=" + that.data.liaisonMobilephone + "&socialCreditCode=" + that.data.socialCreditCode + "&legalEntityBelongedName=" + that.data.legalEntityBelongedName + "&legalEntityBelongedAddress=" + that.data.legalEntityBelongedAddress + "&lebPeopleInCharge=" + that.data.lebPeopleInCharge + "&lebPicPosition=" + that.data.lebPicPosition + "&lebPicTelephone=" + that.data.lebPicTelephone + "&lebSocialCreditCode=" + that.data.lebSocialCreditCode + "&competentDepartmentName=" + that.data.competentDepartmentName + "&competentDepartmentAddress=" + that.data.competentDepartmentAddress + "&cdPeopleInCharge=" + that.data.cdPeopleInCharge + "&cdPicPosition=" + that.data.cdPicPosition + "&cdPicTelephone=" + that.data.cdPicTelephone + "&characteristic=" + that.data.characteristic + "&legalEntity=" + that.data.legalEntity,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'TestingInstitutionInformation/get';
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      console.log(res)
      //把接收到的数据存储到页面
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
        "lebPeopleInCharge": res.data.lebPeopleInCharge,
        "lebPicPosition": res.data.lebPicPosition,
        "lebPicTelephone": res.data.lebPicTelephone,
        "lebSocialCreditCode": res.data.lebSocialCreditCode,
        "competentDepartmentName": res.data.competentDepartmentName,
        "competentDepartmentAddress": res.data.competentDepartmentAddress,
        "cdPeopleInCharge": res.data.cdPeopleInCharge,
        "cdPicPosition": res.data.cdPicPosition,
        "cdPicTelephone": res.data.cdPicTelephone,
        "characteristic": res.data.characteristic,
        "legalEntity": res.data.legalEntity
      })
      console.log(that.data)
    }, (err) => {
      //失败处理函数
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
    //保存指针
    var that = this
    //构造url
    var myurl = app.globalData.url + 'TestingInstitutionInformation/get';
    //请求后端
    app.wxRequest(myurl, 'GET', null, (res) => {
      //成功处理函数
      console.log(res)
      //把接收到的数据存储到页面
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
        "lebPeopleInCharge": res.data.lebPeopleInCharge,
        "lebPicPosition": res.data.lebPicPosition,
        "lebPicTelephone": res.data.lebPicTelephone,
        "lebSocialCreditCode": res.data.lebSocialCreditCode,
        "competentDepartmentName": res.data.competentDepartmentName,
        "competentDepartmentAddress": res.data.competentDepartmentAddress,
        "cdPeopleInCharge": res.data.cdPeopleInCharge,
        "cdPicPosition": res.data.cdPicPosition,
        "cdPicTelephone": res.data.cdPicTelephone,
        "characteristic": res.data.characteristic,
        "legalEntity": res.data.legalEntity
      })
    }, (err) => {
      //失败处理函数
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