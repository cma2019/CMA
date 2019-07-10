const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ['《用户手册》','《计算机软件产品登记检测申请表》','《材料交接单》','《软件产品功能列表》','《软件名称与版本号确认单》','《受测软件产品简介》','《自主产权保证书》','软件样品一套','其它'],
    materialList: [
      { "materialId": 1, "materialType": 0, "materialName": null},
      { "materialId": 2, "materialType": 0, "materialName": null },
      { "materialId": 3, "materialType": 0, "materialName": null },
      { "materialId": 4, "materialType": 0, "materialName": null },
      { "materialId": 5, "materialType": 0, "materialName": null },
      { "materialId": 6, "materialType": 0, "materialName": null },
      { "materialId": 7, "materialType": 0, "materialName": null },
      { "materialId": 8, "materialType": 0, "materialName": null },
      { "materialId": 9, "materialType": 0, "materialName": null },
    ]
  },
  /**
   * 绑定加数量事件
   */
  addCount(e) {
    const index = e.currentTarget.dataset.index
    let materialList = this.data.materialList
    let materialType = materialList[index].materialType
    if (materialType >= 3) {
      return false
    }
    materialType = materialType + 1
    materialList[index].materialType = materialType
    this.setData({
      materialList: materialList
    })
  },

  /**
   * 绑定减数量事件
   */
  minusCount(e) {
    const index = e.currentTarget.dataset.index
    let materialList = this.data.materialList
    let materialType = materialList[index].materialType
    if (materialType <= 0) {
      return false
    }
    materialType = materialType - 1
    materialList[index].materialType = materialType
    this.setData({
      materialList: materialList
    })
  },
  bindmaterialName:function(e){
    let materialList = this.data.materialList
    materialList[8].materialName = e.detail.value
    this.setData({
      materialList: materialList
    })
  },
  materialList_addone:function(e){
    console.log(this.data.materialList)
    console.log(e)
    var that = this.data.materialList
    for(let i =0;i<9;++i){
      if(that[i].materialType != 0){
        let tmp = {
          "materialId": that[i].materialId,
          "materialType": that[i].materialType,
          "materialName": that[i].materialName
        }
        app.globalData.materialList1.push(tmp)
      }
    }
    console.log(app.globalData.materialList1)
    console.log("asfsdag")
    wx.navigateBack({
      delta: 1
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  /*
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
  },*/
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