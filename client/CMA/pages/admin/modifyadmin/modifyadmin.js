// pages/StaffManagement/ModifyStaff/ModifyStaff.js
const app = getApp()
Page({
  data: {
    list: ['用户管理', '质量体系管理', '管理评审','内审管理','自查管理','监督','期间核查','培训管理','样品管理','设备管理','检测检验能力管理','能力验证管理','标准管理','外部评审与上报管理','检测机构管理'],
    'result': null
  },
  onChange(event) {
    //console.log(result)
    this.setData({
      result: event.detail
      
    });
  },
  toggle(event) {
    const { index } = event.currentTarget.dataset;
    const checkbox = this.selectComponent(`.checkboxes-${index}`);
    checkbox.toggle();
  },

  noop() { },
  onLoad: function (options) {
    this.setData({
      id: options.id
    })
    let url = app.globalData.url + 'user/getOne'
    let postdata = {
      "username": this.data.id
    }
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      this.setData({
        username:this.data.id,
        result:res.data1
      })
    }, (err) => {
      console.err('getone error')
    })
  },
  
  intercheckmodify: function (e) {
    console.log('modify modify')
    {
      console.log('modify，携带数据为：', e.detail.value)
      let url = app.globalData.url + 'user/modifyOne';
      console.log(e.detail.value["用户管理"])
      var per = new Array()
      per[0]=e.detail.value["用户管理"]
      per[1] = e.detail.value["质量体系管理"]
      per[2] = e.detail.value["管理评审"]
      per[3] = e.detail.value["内审管理"]
      per[4] = e.detail.value["自查管理"]
      per[5] = e.detail.value["监督"]
      per[6] = e.detail.value["期间核查"]
      per[7] = e.detail.value["培训管理"]
      per[8] = e.detail.value["样品管理"]
      per[9] = e.detail.value["设备管理"]
      per[10] = e.detail.value["检测检验能力管理"]
      per[11] = e.detail.value["能力验证管理"]
      per[12] = e.detail.value["标准管理"]
      per[13] = e.detail.value["外部评审与上报管理"]
      per[14] = e.detail.value["检测机构管理"]
     console.log(per)

      let data = {
        "username":this.data.id,
        "permission":per
      };
      console.log(data)
      app.wxRequest(url, 'POST', data, (res) => {
        console.log('modify message successfully')
        console.log(res)
        wx.showToast({
          title: '修改成功',
          //icon: 'success',
          image: '/icons/ok/ok.png',
          duration: 1000,
          success: function () {
            setTimeout(function () {
              wx.navigateTo({
                url: '../printoneadmin/printoneadmin',
              })
            }, 1000);
          }
        })
      }, (err) => {
        console.log('fail modify')
      })
    }
  },
  goback: function () {
    wx.navigateBack({
      delta: 1
    })
  }
})