const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mess": null,
    "flag": 0,
    select:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
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
    })
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
    wx.navigateTo({
      url: '../Supervision/SupervisionApprove'
    })
  }
})