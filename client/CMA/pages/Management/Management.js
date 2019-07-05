const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['0'],
    username2: '',
    admin1: "admin",
    mess: []
  },

  onChange(event) {
    this.setData({
      activeNames: event.detail
    });
  },

  onLoad: function (options) {

    //var res1;
    let url = app.globalData.url + 'user/getOne'

    // console.log("1"+username1)
    let postdata = {
      "username": app.globalData.username1
    }
    //console.log("2" + username1)
    console.log(postdata)
    //var that=this;
    app.wxRequest(url, 'GET', postdata, (res) => {
      //console.log(res)
      //console.log(res.data1)
      this.setData({
        mess: res.data1,
        username2: app.globalData.username1
      })
      //console.log(mess)
    }, (err) => {
      console.err('getone error')
    })
  },
  onShow: function () {
    //var res1;
    let url = app.globalData.url + 'user/getOne'
    // console.log("1"+username1)
    let postdata = {
      "username": app.globalData.username1
    }
    //console.log("2" + username1)
    console.log(postdata)
    //var that=this;
    app.wxRequest(url, 'GET', postdata, (res) => {
      //console.log(res)
      //console.log(res.data1)
      this.setData({
        mess: res.data1,
        username2: app.globalData.username1

      })
      //console.log(mess)
    }, (err) => {
      console.err('getone error')
    })

  },
  goStaffManagement: function () {
    wx.navigateTo({
      url: '../StaffManagement/StaffManagement/StaffManagement',
    })
  },
  goStaffTraining: function () {
    wx.navigateTo({
      url: '../StaffManagement/StaffTraining/StaffTraining',
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
  gotoInterCheck: function () {
    wx.navigateTo({
      url: '../IntermediateCheck/IntermediateCheckMenu/IntermediateCheckMenu',
    })
  },
  gotoEquipment1: function () {
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
  },
  gotoQuality1: function () {
    wx.navigateTo({
      url: '../QualityManual/QualityManual/QualityManual',
    })
  },
  gotoQuality2: function () {
    wx.navigateTo({
      url: '../QualityManual/QualityManualApprove/QualityManualApprove',
    })
  },
  gotoAuthorityManagement: function () {
    wx.navigateTo({
      url: '../admin/admin',
    })
  },
  gotoManagementReview: function () {
    wx.navigateTo({
      url: '../ManagementReview/ManagementReview',
    })
  },
  gotoERM: function () {
    wx.navigateTo({
      url: '/pages/ExternalReviewManagement/ExternalReviewManagement/ExternalReviewManagement',
    })
  }
})