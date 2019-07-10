# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco
poco = AndroidUiautomationPoco(use_airtest_input=True, screenshot_each_action=False)
poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/dbq").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout").offspring("com.tencent.mm:id/v").child("android.widget.LinearLayout").child("android.widget.RelativeLayout")[0].offspring("com.tencent.mm:id/jp").click()
poco(text="用户管理").click()
touch(Template(r"tpl1562659248784.png", record_pos=(-0.119, -0.379), resolution=(1080, 2160)))
touch(Template(r"tpl1562659260353.png", record_pos=(-0.423, -0.681), resolution=(1080, 2160)))
touch(Template(r"tpl1562659272631.png", record_pos=(0.094, -0.749), resolution=(1080, 2160)))
text("255")
touch(Template(r"tpl1562659292796.png", record_pos=(0.03, -0.638), resolution=(1080, 2160)))
text("1")
touch(Template(r"tpl1562659307709.png", record_pos=(0.065, -0.53), resolution=(1080, 2160)))
text("1")
touch(Template(r"tpl1562659334364.png", record_pos=(-0.414, -0.433), resolution=(1080, 2160)))
touch(Template(r"tpl1562659543390.png", record_pos=(-0.262, -0.512), resolution=(1080, 2160)))


touch(Template(r"tpl1562659610100.png", record_pos=(-0.283, -0.7), resolution=(1080, 2160)))

touch(Template(r"tpl1562659358931.png", record_pos=(0.373, 0.92), resolution=(1080, 2160)))
