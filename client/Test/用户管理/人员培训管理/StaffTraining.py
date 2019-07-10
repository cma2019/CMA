# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco

poco = AndroidUiautomationPoco()


touch(Template(r"tpl1562639925732.png", record_pos=(-0.309, 0.927), resolution=(1080, 2160)))

poco(text="用户管理").click()

touch(Template(r"tpl1562557557546.png", record_pos=(-0.241, -0.26), resolution=(1080, 2160)))
touch(Template(r"tpl1562646692259.png", record_pos=(-0.418, -0.213), resolution=(1080, 2160)))



#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[0].child("android.view.View")[2].click()
sleep(1.0)
sleep(1.0)

touch(Template(r"tpl1562646814984.png", record_pos=(0.027, -0.746), resolution=(1080, 2160)))


text("1")

#text('1')

#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[1].child("android.view.View")[2].click()

touch(Template(r"tpl1562569382594.png", record_pos=(0.069, -0.632), resolution=(1080, 2160)))
text('仪器使用培训')
#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[2].child("android.view.View")[2].click()

touch(Template(r"tpl1562569656304.png", record_pos=(-0.224, -0.531), resolution=(1080, 2160)))

poco(text="2019").swipe([0.0, 0.0975])
poco(text="7").swipe([-0.0036, 0.0921])
poco(text="08").swipe([-0.0144, 0.0975])
#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[2].child("android.widget.RelativeLayout").child("android.widget.FrameLayout").offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View").child("android.view.View").child("android.view.View")[5].child("android.view.View")[1].child("android.view.View")[0].click()
touch(Template(r"tpl1562569682058.png", record_pos=(0.419, 0.328), resolution=(1080, 2160)))

#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[3].child("android.view.View")[2].click()
touch(Template(r"tpl1562569698430.png", record_pos=(0.024, -0.406), resolution=(1080, 2160)))

text('南京大学')
#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[5].child("android.view.View")[2].click()
touch(Template(r"tpl1562569710011.png", record_pos=(0.006, -0.298), resolution=(1080, 2160)))

text('张天')
#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[6].child("android.view.View")[2].click()
touch(Template(r"tpl1562569720349.png", record_pos=(0.082, -0.193), resolution=(1080, 2160)))

text('实验室设备使用方法')
#poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].offspring("android.view.ViewGroup").child("android.widget.FrameLayout").offspring("android.webkit.WebView")[1].child("android.view.View").child("android.view.View")[7].child("android.view.View")[2].click()
touch(Template(r"tpl1562642869358.png", record_pos=(0.072, -0.081), resolution=(1080, 2160)))

text('无')
touch(Template(r"tpl1562556457834.png", record_pos=(-0.427, 0.014), resolution=(1080, 2160)))
sleep(1.0)
touch(Template(r"tpl1562642854687.png", record_pos=(-0.109, -0.531), resolution=(1080, 2160)))
sleep(1.0)

touch(Template(r"tpl1562569847937.png", record_pos=(-0.252, 0.01), resolution=(1080, 2160)))
touch(Template(r"tpl1562569909787.png", record_pos=(-0.149, -0.408), resolution=(1080, 2160)))
touch(Template(r"tpl1562642901266.png", record_pos=(0.434, -0.42), resolution=(1080, 2160)))
touch(Template(r"tpl1562642912391.png", record_pos=(0.053, -0.415), resolution=(1080, 2160)))

text("实验室211")
touch(Template(r"tpl1562569970273.png", record_pos=(-0.421, 0.018), resolution=(1080, 2160)))
sleep(1.0)
touch(Template(r"tpl1562569989366.png", record_pos=(-0.412, 0.014), resolution=(1080, 2160)))
sleep(1.0)

touch(Template(r"tpl1562570003454.png", record_pos=(-0.419, -0.678), resolution=(1080, 2160)))
touch(Template(r"tpl1562570017283.png", record_pos=(0.016, -0.636), resolution=(1080, 2160)))
text("2")
touch(Template(r"tpl1562570759809.png", record_pos=(-0.423, -0.542), resolution=(1080, 2160)))
touch(Template(r"tpl1562570773097.png", record_pos=(-0.187, -0.64), resolution=(1080, 2160)))
touch(Template(r"tpl1562642975786.png", record_pos=(0.436, -0.631), resolution=(1080, 2160)))
touch(Template(r"tpl1562642984035.png", record_pos=(0.061, -0.637), resolution=(1080, 2160)))

text("255")
touch(Template(r"tpl1562570823495.png", record_pos=(-0.425, -0.545), resolution=(1080, 2160)))
touch(Template(r"tpl1562643054300.png", record_pos=(-0.286, -0.628), resolution=(1080, 2160)))
touch(Template(r"tpl1562643066032.png", record_pos=(-0.383, 0.126), resolution=(1080, 2160)))
touch(Template(r"tpl1562643076794.png", record_pos=(-0.094, -0.525), resolution=(1080, 2160)))
text("合格")
touch(Template(r"tpl1562643087179.png", record_pos=(-0.427, -0.43), resolution=(1080, 2160)))
touch(Template(r"tpl1562643114620.png", record_pos=(-0.158, 0.124), resolution=(1080, 2160)))
touch(Template(r"tpl1562643122233.png", record_pos=(-0.181, -0.525), resolution=(1080, 2160)))
touch(Template(r"tpl1562643128616.png", record_pos=(0.434, -0.523), resolution=(1080, 2160)))
touch(Template(r"tpl1562643135984.png", record_pos=(-0.097, -0.529), resolution=(1080, 2160)))
text("不合格")
touch(Template(r"tpl1562643150891.png", record_pos=(-0.416, -0.433), resolution=(1080, 2160)))
touch(Template(r"tpl1562643185035.png", record_pos=(0.228, 0.124), resolution=(1080, 2160)))
touch(Template(r"tpl1562643193610.png", record_pos=(-0.427, -0.321), resolution=(1080, 2160)))
touch(Template(r"tpl1562643215026.png", record_pos=(0.04, 0.127), resolution=(1080, 2160)))
sleep(1.0)

touch(Template(r"tpl1562643238540.png", record_pos=(-0.444, -0.86), resolution=(1080, 2160)))
sleep(1.0)

touch(Template(r"tpl1562643263703.png", record_pos=(-0.103, 0.01), resolution=(1080, 2160)))








