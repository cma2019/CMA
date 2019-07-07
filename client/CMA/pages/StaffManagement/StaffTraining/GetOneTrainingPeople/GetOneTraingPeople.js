// pages/StaffManagement/PrintOneStaff/PrintOneStaff.js
const app = getApp()
Page({
  data: {
    str:"12",
    str2:[]
  },

  onLoad: function (options) {
    console.log(options)
    console.log(this.data.str)
    // console.log(str)
    //this.data.str=options
    //str1=this.data.str.split(",")
    //console.log(str1)
    this.data.str=String(options.id)
    console.log(this.data.str)
    this.data.str2=this.data.str.split(",")
    console.log(this.data.str2)
    this.setData({

      id: this.data.str2[0],
      trainingId:this.data.str2[1]
    })
    console.log('getone id')
    console.log(this.data.id)
    let url = app.globalData.url + 'StaffTraining/getOne'
    let postdata = {
      "id": this.data.id,
      "trainingId":this.data.trainingId
    }
    console.log(url)
    console.log(postdata)
    app.wxRequest(url, 'GET', postdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      this.setData({
        trainingId: res.data.trainingId,
        program: res.data.program,
        result: res.data.result,
        id:res.data.id,
        name:res.data.name,
        place:res.data.place,
        presenter:res.data.presenter,
        trainingDate:res.data.trainingDate,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  onShow:function()
  {
    let myurl = app.globalData.url + 'StaffTraining/getOne'
    let mypostdata = {
      "id": this.data.id,
      "trainingId": this.data.trainingId
    }
    app.wxRequest(myurl, 'GET', mypostdata, (res) => {
      console.log(res)
      console.log(res.data)
      //console.log(res.data[0].id)
      this.setData({
        trainingId: res.data.trainingId,
        program: res.data.program,
        result: res.data.result,
        id: res.data.id,
        name: res.data.name,
        place: res.data.place,
        presenter: res.data.presenter,
        trainingDate: res.data.trainingDate,
        note: res.data.note
      })
    }, (err) => {
      console.err('get one error')
    })
  },
  AddResult(e)
  {
    console.log(e)
    let target = []
    target[0] = this.data.trainingId
    target[1] = this.data.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
      url: '../AddTrainingResult/AddTrainingResult?id=' + target
    })
  },
  ModifyResult(e) {
    console.log(e)
    let target1 = []
    target1[0] = this.data.trainingId
    target1[1] = this.data.id
    console.log('getone id')
    //console.log(target)
    wx.navigateTo({
      url: '../ModifyTrainingResult/ModifyTrainingResult?id=' + target1
    })
  },
  Search(e)
  {
    wx.navigateTo({
      url: '../GetAllStaffTraining/GetAllStaffTraining?id=' + this.data.id
    })
  },
  Delete(e) {
    let url1 = app.globalData.url + 'StaffTraining/deleteTrainingPeople'
    let data1 = {
      "id": this.data.id,
      "trainingId":this.data.trainingId
    }
    app.wxRequest(url1, 'POST', data1, (res) => {
      console.log('delete successfully')
      wx.showToast({
        title: '成功',
        //icon: 'success',
        image: '/icons/ok/ok.png',
        duration: 1000,
        success: function () {
          setTimeout(function () {
            wx.navigateTo({
              url: '../GetTrainingPeople/GetTrainingPeople',
            })
          }, 1000);
        }
      })

    }, (err) => {
      console.log('delete failed')
    })
  }

})