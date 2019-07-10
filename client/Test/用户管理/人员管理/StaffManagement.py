# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)

from poco.drivers.android.uiautomation import AndroidUiautomationPoco
poco = AndroidUiautomationPoco(use_airtest_input=True, screenshot_each_action=False)

poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/dbq").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout").offspring("com.tencent.mm:id/v").child("android.widget.LinearLayout").child("android.widget.RelativeLayout")[0].offspring("com.tencent.mm:id/jp").click()
poco(text="用户管理").click()
touch(Template(r"tpl1562640068640.png", record_pos=(-0.16, -0.599), resolution=(1080, 2160)))
touch(Template(r"tpl1562640084762.png", record_pos=(-0.418, -0.093), resolution=(1080, 2160)))
touch(Template(r"tpl1562640232042.png", record_pos=(-0.01, -0.743), resolution=(1080, 2160)))

text("小强")
touch(Template(r"tpl1562640241947.png", record_pos=(-0.155, -0.64), resolution=(1080, 2160)))

text("男")
touch(Template(r"tpl1562640249021.png", record_pos=(0.019, -0.529), resolution=(1080, 2160)))
text("市场部")
touch(Template(r"tpl1562640368645.png", record_pos=(0.051, -0.401), resolution=(1080, 2160)))
text("部员")
touch(Template(r"tpl1562641396679.png", record_pos=(0.063, -0.305), resolution=(1080, 2160)))
text("教授")
touch(Template(r"tpl1562640608408.png", record_pos=(0.08, -0.192), resolution=(1080, 2160)))
text("博士研究生")
touch(Template(r"tpl1562640634639.png", record_pos=(0.103, -0.087), resolution=(1080, 2160)))
text("南京大学")
touch(Template(r"tpl1562640647589.png", record_pos=(0.059, 0.031), resolution=(1080, 2160)))
text("计算机软件")
touch(Template(r"tpl1562640672650.png", record_pos=(-0.232, 0.138), resolution=(1080, 2160)))
swipe(Template(r"tpl1562640698608.png", record_pos=(-0.139, 0.74), resolution=(1080, 2160)), vector=[0.0065, 0.076])
touch(Template(r"tpl1562640714802.png", record_pos=(0.417, 0.324), resolution=(1080, 2160)))
touch(Template(r"tpl1562640727970.png", record_pos=(0.083, 0.252), resolution=(1080, 2160)))
text("5")
touch(Template(r"tpl1562640741285.png", record_pos=(-0.425, 0.347), resolution=(1080, 2160)))
sleep(1.0)
touch(Template(r"tpl1562640939401.png", record_pos=(-0.307, 0.05), resolution=(1080, 2160)))
touch(Template(r"tpl1562640956897.png", record_pos=(-0.416, 0.455), resolution=(1080, 2160)))
touch(Template(r"tpl1562641014690.png", record_pos=(-0.144, -0.298), resolution=(1080, 2160)))
touch(Template(r"tpl1562641041430.png", record_pos=(0.434, -0.302), resolution=(1080, 2160)))
touch(Template(r"tpl1562641053291.png", record_pos=(0.047, -0.296), resolution=(1080, 2160)))

text("部长")

touch(Template(r"tpl1562641078309.png", record_pos=(-0.416, 0.458), resolution=(1080, 2160)))
sleep(1.0)
touch(Template(r"tpl1562641090076.png", record_pos=(-0.272, 0.448), resolution=(1080, 2160)))




