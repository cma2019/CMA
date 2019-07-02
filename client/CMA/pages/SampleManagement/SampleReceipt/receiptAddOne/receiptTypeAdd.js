// pages/SampleManagement/SampleReceipt/receiptAddOne/receiptTypeAdd.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ['《用户手册》','《计算机软件产品登记检测申请表》','《材料交接单》','《软件产品功能列表》','《软件名称与版本号确认单》','《受测软件产品简介》','《自主产权保证书》','软件样品一套','其它'],
    objectArray:[
      {
        id:0,
        name:'《用户手册》'
      },
      {
        id: 1,
        name: '《计算机软件产品登记检测申请表》'
      },
      {
        id: 2,
        name: '《材料交接单》'
      },
      {
        id: 3,
        name: '《软件产品功能列表》'
      },
      {
        id: 4,
        name: '《软件名称与版本号确认单》'
      },
      {
        id: 5,
        name: '《受测软件产品简介》'
      },
      {
        id: 6,
        name: '《自主产权保证书》'
      }, 
      {
        id: 7,
        name: '软件样品一套'
      },
      {
        id: 8,
        name: '其它'
      }
    ],
    receiptType:[],
    flag: -1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  bindreceiptTypeChange:function(e){
    console.log(e)
    let tmp = e.detail.value
    let receiptType = this.data.receiptType
    let flag = this.data.flag
    receiptType[flag] = tmp
    this.setData({
      receiptType: receiptType
    })
    console.log(this.data.receiptType)
    console.log("cchgfjhf")
  },
  receiptTypeAdd:function(){
    let receiptType = this.data.receiptType
    let newData = ""
    let flag = this.data.flag
    console.log(flag)
    console.log(receiptType[flag])
    if(receiptType[flag] !=""){
      flag = flag + 1
      receiptType.push(newData)
      this.setData({
        receiptType: receiptType,
        flag : flag
      })
      console.log(this.data.flag)
      console.log(this.data.receiptType)
      console.log("chkdjhcfs")
    }
    else{
      wx.showToast({
        title: '还存在未填写的信息',
        duration: 1500
      })
    }
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