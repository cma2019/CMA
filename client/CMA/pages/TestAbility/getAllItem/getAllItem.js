// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
<<<<<<< HEAD
=======
    "year":null,
>>>>>>> 83d7b2d741731358cf1f7a8d93f09d00b8c07072
    "mess": null,
    "temp":
      [{
        "year": 1,
<<<<<<< HEAD
        "fileName": "222",
        "file": "content1",
      },
      {
        "year": 1,
        "fileName": "333",
        "file": "content2",
=======
        "id": "222",
        "productionName": "content1",
        "ability": "cont21",
        "referrence": "co1nt1",
      },
      {
        "year": 1,
        "id": "333",
        "productionName": "dwa",
        "ability": "dac",
        "referrence": "dca",
>>>>>>> 83d7b2d741731358cf1f7a8d93f09d00b8c07072
      }]
  },

  onLoad: function (options) {
<<<<<<< HEAD
  },

  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getAll'
    let postdata = ''
=======
    this.setData({
      year: options.id
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getAllItem'
    let data = {
      "year": this.data.year,
    }
>>>>>>> 83d7b2d741731358cf1f7a8d93f09d00b8c07072
    app.wxRequest(url, 'GET', postdata, (res) => {
      this.setData({
        mess: res.data
      })
<<<<<<< HEAD
      console.log('plan get all success')
    }, (err) => {
      console.err('getone error')
=======
      console.log('get all item success')
    }, (err) => {
      console.err('get all item error')
>>>>>>> 83d7b2d741731358cf1f7a8d93f09d00b8c07072
    })
  },

  gotoAdd(e) {
<<<<<<< HEAD
    wx.navigateTo({
      url: 'addOne/addOne',
=======
    let target = this.data.year
    console.log('add one test ability id')
    console.log(target)
    wx.navigateTo({
      url: 'addOneItem/addOneItem?id=' + target,
>>>>>>> 83d7b2d741731358cf1f7a8d93f09d00b8c07072
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
    wx.navigateTo({
<<<<<<< HEAD
      url: 'modifyOne/modifyOne?year=' + target
=======
      url: 'getOneItem/getOneItem?id=' + target
    })
  },
  gotoModify(e){
    let target = this.data.year
    console.log('modify one test ability id')
    console.log(target)
    wx.navigateTo({
      url: 'modifyOne/modifyOne?id=' + target,
>>>>>>> 83d7b2d741731358cf1f7a8d93f09d00b8c07072
    })
  }
})
