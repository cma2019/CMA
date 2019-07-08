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
    //上传文件需要调用choosemessagefile接口，该接口将会调用系统的文件选择器
    console.log('begin add')
    var myurl = app.globalData.url + 'StandardManagement/addOne';
    var mypath;
    wx.chooseMessageFile({
      count: 1,
      type: 'all',
      success: function (res) {
        console.log("get file success")
        console.log(res)
        mypath = res.tempFiles[0].path
        //文件路径获取成功
        app.wxUploadFile(myurl, mypath, null, (res) => {
          console.log("upload file success")
          console.log(res)
          //后端返回的不是json结构体而是json字符串
          //此处调用JSON.parse将res转化为json字符串再读取code
          //code==200时，显示添加成功，返回上一界面
          var obj = JSON.parse(res);
          console.log(obj)
          console.log(obj.code)
          if (obj.code == 200){
            wx.showToast({
              title: '添加成功',
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
              title: '添加失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        }, (err) => {
          console.log(err)
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        })
      },
      fail: function (err) {
        //在文件选择其中取消时，将会调用这一部分代码
        //显示添加取消，保持在原界面不动
        console.log("get file failed")
        console.log(err)
        wx.showToast({
          title: '添加取消',
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