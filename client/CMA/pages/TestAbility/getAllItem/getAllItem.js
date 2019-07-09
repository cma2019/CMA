// pages/IntermediateCheck/IntermediateCheck.js
const app = getApp()
Page({

  data: {
    "year":null,
    "name":null,
    "mess": null,
    "temp":
      [{
        "year": 1,
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
      }]
  },

  onLoad: function (options) {
    console.log("get all items")
    console.log(options)
    //该界面有两个初始参数，year和name，最先初始化他们
    this.setData({
      year: options.year,
      name: options.name
    })
  },

  onShow: function (options) {
    let url = app.globalData.url + 'TestAbility/getAllItem'
    let data = {
      "year": this.data.year
    }
    //使用year信息，向后端请求数据
    app.wxRequest(url, 'GET', data, (res) => {
      console.log("test abbbb")
      console.log(res)
      if(res.code == 200){
        //rescode==200时，返回成功，初始化数据
        this.setData({
          mess: res.data
        })
      }else{
        wx.showToast({
          title: '连接失败',
          image: '/icons/warning/warning.png',
          duration: 1000
        })
      }
      console.log('plan get all success')
    }, (err) => {
      console.err('getone error')
      console.log('get all item success')
      wx.showToast({
        title: '添加失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },

  gotoAdd(e) {
    let target = this.data.year
    console.log('add one test ability id')
    console.log(target)
    //addoneitem需要决定向哪一年添加数据，需要传递year信息
    wx.navigateTo({
      url: '../addOneItem/addOneItem?id=' + target,
    })
  },

  gotoOne(e) {
    console.log(e)
    let target = e.currentTarget.id
    console.log('getone id')
    console.log(target)
     //getoneitem需要决定展示哪一条信息，需要传递id信息
    wx.navigateTo({
      url: '../getOneItem/getOneItem?id=' + target
    })
  },
  gotoModify(e){
    let target = this.data.year
    let target2 = this.data.name
    console.log('modify one test ability id')
    console.log(target)
    //modifyone需要决定修改哪一条信息
    //这一系列方法不存在getone方法，所以无法传递id后getone初始化，只能将需要显示的参数都传递出去
    wx.navigateTo({
      url: '../modifyOne/modifyOne?year=' + target+'&name='+target2
    })
  },

  downloadAnnex(e) {
    var that = this
    var myurl = app.globalData.url + 'TestAbility/getAnnex/' + that.data.year;
    var myFilePath
    //下载当前年份的附件，需要传递年份
    app.wxDownloadFile(myurl, (res) => {
      console.log("download one now")
      console.log(res)
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: function (res) {
          console.log("download ability annex now")
          console.log(res)
          //当下载成功时，显示文件路径
          if (res.errMsg == "saveFile:ok") {
            myFilePath = res.savedFilePath
            console.log(myFilePath)
            wx.showToast({
              title: '下载成功',
              image: '/icons/ok/ok.png',
              duration: 1000
            })
          } else {
            wx.showToast({
              title: '下载失败',
              image: '/icons/warning/warning.png',
              duration: 1000
            })
          }
        },
        fail: function (err) {
          console.log(err)
          wx.showToast({
            title: '下载失败',
            image: '/icons/warning/warning.png',
            duration: 1000
          })
        }
      })
    }, (err) => {
      console.log(err)
      wx.showToast({
        title: '连接失败',
        image: '/icons/warning/warning.png',
        duration: 1000
      })
    })
  },
})
