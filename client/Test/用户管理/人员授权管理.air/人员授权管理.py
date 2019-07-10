# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco
poco = AndroidUiautomationPoco(use_airtest_input=True, screenshot_each_action=False)
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/dbq").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout").offspring("com.tencent.mm:id/v").child("android.widget.LinearLayout").child("android.widget.RelativeLayout")[0].offspring("com.tencent.mm:id/jp").click()
poco(text="用户管理").click()
touch(Template(r"tpl1562648914177.png", record_pos=(-0.245, -0.487), resolution=(1080, 2160)))
touch(Template(r"tpl1562648924605.png", record_pos=(-0.413, -0.681), resolution=(1080, 2160)))
touch(Template(r"tpl1562648943179.png", record_pos=(0.047, -0.75), resolution=(1080, 2160)))
text("255")
touch(Template(r"tpl1562648956936.png", record_pos=(0.04, -0.638), resolution=(1080, 2160)))
text("256")
touch(Template(r"tpl1562648967072.png", record_pos=(0.065, -0.524), resolution=(1080, 2160)))
text("1")
touch(Template(r"tpl1562648979400.png", record_pos=(-0.046, -0.423), resolution=(1080, 2160)))
touch(Template(r"tpl1562648986032.png", record_pos=(0.411, 0.321), resolution=(1080, 2160)))
touch(Template(r"tpl1562648992699.png", record_pos=(-0.425, -0.318), resolution=(1080, 2160)))
touch(Template(r"tpl1562649023453.png", record_pos=(0.077, -0.583), resolution=(1080, 2160)))
text("255")
touch(Template(r"tpl1562649034337.png", record_pos=(-0.356, -0.487), resolution=(1080, 2160)))
