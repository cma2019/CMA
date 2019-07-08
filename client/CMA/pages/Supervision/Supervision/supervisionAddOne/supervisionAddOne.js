const app = getApp()
var util = require('../../../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    createDate:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var TIME = util.formatDate(new Date())
    this.setData({
      createDate:TIME
    })
    console.log(this.data)
  },

  Supervision_addone: function (e) {
    if(e.detail.value.author == null||e.detail.value.author == ''){
      wx.showToast({
        title: '空白输入',
        image: '/icons/warning/warning.png',
        duration: 500,
        success: function () {
          setTimeout(function () {
          }, 300)
        }
      })
      console.log('空白输入')
    }
    else{
      console.log('Supervision发生了addone事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'Supervision/addOne'
      let postdata={
        "author": e.detail.value.author,
        "remark": e.detail.value.remark,
        "createDate": this.data.createDate
      }
      app.wxRequest(url, 'POST', postdata, (res) => {
        console.log(res)
        if (res.code == 200) {
          wx.showToast({
            title: '添加成功',
            image: '/icons/ok/ok.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
                wx.navigateTo({
                  url: '../Supervision'
                })
              }, 300)
            }
          })
          console.log("添加成功")
        }
        else if (res.data.code == 511) {
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 500)
            }
          })
          console.log("缺少必选请求参数")
        }
        else if (res.data.code == 512) {
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 500)
            }
          })
          console.log("添加重复数据")
        }
        else if (res.data.code == 513) {
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 500)
            }
          })
          console.log("添加数据不合法")
        }
        else { //514
          wx.showToast({
            title: '重复添加',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
                wx.navigateTo({
                  url: '../Supervision'
                })
              }, 300)
            }
          })
          console.log("添加数据不符合一致性")
        }
      }, (err) => {
        console.log('fail addone')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
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