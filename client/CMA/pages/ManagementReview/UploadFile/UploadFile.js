// pages/TestingInstitutionManagement/Certificate/CertificateAddOne/CertificateAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileId: "test fileId",
    fileName: "test.docx"
  },

  newEquipment: function (e) {
    console.log('begin add')
    var myurl = app.globalData.url + 'ManagementReview/UpLoad';
    var mypath;
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        mypath = res.tempFiles[0].path
        app.wxUploadFile(myurl, mypath, null, (res) => {
          console.log("upload file success")
          console.log(res)
        }, (err) => {
          console.log(err)
        })
        wx.redirectTo({
          url: '/pages/TestingInstitutionManagement/Certificate/Certificate',
        })
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
      }
    })
  }
})