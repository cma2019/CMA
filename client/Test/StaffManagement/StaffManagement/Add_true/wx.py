# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco
poco = AndroidUiautomationPoco()

poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout").offspring("com.tencent.mm:id/w").child("android.widget.LinearLayout").child("android.widget.RelativeLayout")[0].offspring("com.tencent.mm:id/jb").click()

poco(text="用户管理").click()
touch(Template(r"tpl1560392244116.png", record_pos=(-0.017, -0.594), resolution=(1080, 2160)))


poco("android.widget.Button").click()

poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[0].child("android.view.View")[2].click()

text('张天')

poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[1].child("android.view.View")[2].click()
text('1')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[2].child("android.view.View")[2].click()
text('市场部')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[3].child("android.view.View")[2].click()
text('部员')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[4].child("android.view.View")[2].click()

text('教授')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[6].child("android.view.View")[2].click()
text('博士研究生')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[7].child("android.view.View")[2].click()
text('南京大学')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[8].child("android.view.View")[2].click()

text('计算机软件')
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[9].child("android.view.View")[2].click()

poco(text="确定").click()

poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[1].offspring("com.tencent.mm:id/x").child("android.widget.FrameLayout").child("android.widget.FrameLayout")[0].child("android.widget.FrameLayout").offspring("android.webkit.WebView").child("android.view.View").child("android.view.View")[10].child("android.view.View")[2].click()
text('6')
touch(Template(r"tpl1560392541103.png", record_pos=(-0.421, 0.346), resolution=(1080, 2160)))


poco(text="go back ").click()







