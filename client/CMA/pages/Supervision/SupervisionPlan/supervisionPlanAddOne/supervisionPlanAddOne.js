// pages/Supervision/SupervisionPlan/supervisionPlanAddOne/supervisionPlanAddOne.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id:options.id
    })
  },
  SupervisionPlan_addone:function(e){
    if (e.detail.value.content == null || e.detail.value.object == null || e.detail.value.dateFrequency == null) {
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
    else {
      console.log('SupervisionPlan发生了addone事件，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'SupervisionPlan/addOne'
      let id = this.data.id
      let postdata = {
        "id": id,
        "content": e.detail.value.content,
        "object": e.detail.value.object,
        "dateFrequency": e.detail.value.dateFrequency
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
                  url: '/pages/Supervision/Supervision/supervisionGetOne/supervisionGetOne?id=' + id
                })
              }, 300)
            }
          })
          console.log("添加成功")
        }
        else if (res.code == 511) {
          wx.showToast({
            title: '空白输入',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 300)
            }
          })
          console.log("空白输入")
        }
        else if(res.code == 513){
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 300)
            }
          })
          console.log("某项数据错误")
        }
        else {
          wx.showToast({
            title: '添加失败',
            image: '/icons/warning/warning.png',
            duration: 500,
            success: function () {
              setTimeout(function () {
              }, 300)
            }
          })
          console.log("添加失败")
        }
      }, (err) => {
        console.log('fail addone')
      })
    }
  },
  goback: function () {
    wx.navigateTo({
      url: '/pages/Supervision/Supervision/Supervision'
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