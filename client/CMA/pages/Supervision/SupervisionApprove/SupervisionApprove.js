const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess": null,
    "flag": 0,
    //"flag":1,/* */
    select:[],
   //select: [false, false],/* */
    selectAllStatus: false,
    tmp: [{/* */
      "id": 1,
      "situation ": 0,
      "author": "老王",
      "createDate": "2018-05-08",
      "approver": "老李",
      "approveDate": "2018-06-08",
      "remark ": "无",
    },
    {
      "id": 3,
      "situation ": 0,
      "author": "老块",
      "createDate": "2016-06-08",
      "approver": "老八",
      "approveDate": "2018-07-08",
      "remark ": "happy",
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    /*
    console.log(this.data.mess)
    let url = app.globalData.url + 'Supervision/getAll'
    let postdata = ''
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      if (res.code == 522) {
        this.setData({
          mess: "",
        })
      }
      else {
        this.setData({
          mess: res.data,
          flag: 1
        })
        for(let i = 0;i < res.data.length;++i){
          var obj = false
          let select = this.data.select
          select.push(obj)
          console.log(select)
          this.setData({
          select: select
          })
        }
        console.log(this.data)
      }
    }, (err) => {
      wx.showToast({
        title: 'getall error',
        duration: 1500
      })
      console.log('getall error')
    })*/
  },
  selectList(e){
    const index = e.currentTarget.dataset.index
    console.log(index)
    let mess = this.data.mess
    let select = this.data.select
    const selected = select[index]
    select[index] = !selected
    this.setData({
      select:select
    })
    console.log("abcd")
    console.log(this.data)
  },
  selectAll(e) {
    let selectAllStatus = this.data.selectAllStatus;
    selectAllStatus = !selectAllStatus;
    let select = this.data.select;

    for (let i = 0; i < select.length; i++) {
      select[i] = selectAllStatus;
    }
    this.setData({
      selectAllStatus: selectAllStatus,
      select: select
    })
    console.log("qwert")
    console.log(this.data.select)
  },
  gotoOne: function (e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    console.log("dfdsfs")
    wx.navigateTo({
      url: '../Supervision/supervisionGetOne/supervisionGetOne?id=' + target
    })
  },
  gotoApprove: function (e) {
    console.log('SupervisionApprove发生了approveOne事件，携带数据为：', e)
    let flag = false
    let select = this.data.select
    for(let i = 0;i<select.length;++i){
      if(select[i]==true){
        flag = true
        wx.request({
          url: app.globalData.url + 'Supervision/approveOne',
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
          },
          data: {
            "id": this.data.mess[i].id,
            "approver": this.data.mess[i].approver
          },
          success(res) {
            if (res.data.code == 200) {
              console.log("approve success")
            }
            else {
              console.log('您没有权限')
            }
          },
          fail(err) {
            console.log('fail approveone')
          },
          complete(fin) {
            console.log('final approveone')
          }
        })
      }
    }
    if(flag == false){
      wx.showToast({
        title: '批准数量为0',
        duration: 1500
      })
    }
    this.onLoad()
  }
})