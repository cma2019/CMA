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
    var myurl = app.globalData.url + 'StandardManagement/modifyOne';
    var mypath;
    var myfileId = this.data.fileId
    //用户仅仅需要选择文件，其他信息由前端自动填写。
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        mypath = res.tempFiles[0].path
        console.log(myfileId)
        let formdata = {
          "fileId": myfileId
        }
        console.log("formdata")
        console.log(formdata)
        app.wxUploadFile(myurl, mypath, formdata, (res) => {
          console.log("upload file success")
          console.log(res)
          //由于modify也是一种上传，此处有一样的问题
          //后端返回的不是json结构体而是json字符串
          //此处调用JSON.parse将res转化为json字符串再读取code
          //code==200时，显示添加成功，返回上一界面
          var obj = JSON.parse(res);
          console.log(obj)
          console.log(obj.code)
          if (obj.code == 200){
            wx.showToast({
              title: '修改成功',
              image: '/icons/ok/ok.png',
              duration: 1000,
              success: function () {
                setTimeout(function () {
                  wx.navigateBack({
                    delta: 1
                  })
                }, 1000);
              }
            })
          }else{
            wx.showToast({
              title: '修改失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        }, (err) => {
          console.log(err)
          wx.showToast({
            title: '修改失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        })
      },
      fail: function (err) {
        console.log("get file failed")
        console.log(err)
        wx.showToast({
          title: '修改失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('getone options')
    console.log(options)
    this.setData({
      fileId: options.id
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