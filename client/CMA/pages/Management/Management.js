const app = getApp()
var username1 = "admin";
wx.getStorage({
  key: 'key',
  success(res1) {
    //console.log(res1.data)
    username1 = res1.data
    //console.log(username1)
  }
});
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['0'],
    mess:null
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
      "username": username1
    }
    //console.log("2" + username1)
    console.log(postdata)
    //var that=this;
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data1)
      this.setData({
        mess:res.data1
      })
      //console.log(mess)
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
  },
  gotoAuthorityManagement: function () {
    wx.navigateTo({
      url: '../admin/admin',
    })
  }
})