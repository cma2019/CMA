// pages/Management/Management.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['0']
  },
  "mess": null,
  onChange(event) {
    this.setData({
      activeNames: event.detail
    });
    wx.getStorage({
      key: 'key',
      success(res) {
        console.log(res.data)
        var username1=res.data
        console.log(username)
      }
    })
  },
  onLoad: function (options) {
    let url = app.globalData.url + 'user/getOne'
    let postdata = {
      "username": username1
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      this.setData({
        username:username1,
        mess: res.data1
      })
      console.log(mess)
    }, (err) => {
      console.err('getone error')
    })
  },
  onShow: function () {

  },
  goStaffManagement: function () {
    wx.navigateTo({
      url: '../StaffManagement/StaffManagement/StaffManagement',
    })
  },
  goStaffAuthorization: function () {
    wx.navigateTo({
      url: '../StaffManagement/StaffAuthorization/StaffAuthorization',
    })
  },
  goStaffLeaving: function () {
    wx.navigateTo({
      url: '../StaffManagement/StaffLeaving/StaffLeaving',
    })
  },
  goTrainingApplication: function () {
    wx.navigateTo({
      url: '../TrainingManagement/TrainingApplication/TrainingApplication',
    })
  },
  goAnnualTrainingPlan: function () {
    wx.navigateTo({
      url: '../TrainingManagement/AnnualTrainingPlan/AnnualTrainingPlan',
    })
  },
  gotoInterCheck:function(){
    wx.navigateTo({
      url: '../IntermediateCheck/IntermediateCheckMenu/IntermediateCheckMenu',
    })
  },
  gotoEquipment1:function(){
    wx.navigateTo({
      url: '../Equipment/EquipmentManagement/EquipmentManagement',
    })
  },
  gotoEquipment2: function () {
    wx.navigateTo({
      url: '../Equipment/EquipmentAccept/EquipmentAccept',
    })
  },
  gotoEquipment3: function () {
    wx.navigateTo({
      url: '../Equipment/EquipmentUse/EquipmentUse',
    })
  },
  gotoEquipment4: function () {
    wx.navigateTo({
      url: '../Equipment/EquipmentMaintenance/EquipmentMaintenance',
    })
  },
  gotoEquipment5: function () {
    wx.navigateTo({
      url: '../Equipment/EquipmentApplication/EquipmentApplication',
    })
  },
  gotoTestInstitution1: function () {
    wx.navigateTo({
      url: '../TestingInstitutionManagement/TestingInstitutionInformation/TestingInstitutionInformation',
    })
  },
  gotoTestInstitution2: function () {
    wx.navigateTo({
      url: '../TestingInstitutionManagement/TestingInstitutionResource/TestingInstitutionResource',
    })
  },
  gotoTestInstitution3: function () {
    wx.navigateTo({
      url: '../TestingInstitutionManagement/Certificate/Certificate',
    })
  }
})