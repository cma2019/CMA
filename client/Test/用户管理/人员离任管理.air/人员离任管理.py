# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco

poco = AndroidUiautomationPoco()


touch(Template(r"tpl1562639925732.png", record_pos=(-0.309, 0.927), resolution=(1080, 2160)))

poco(text="用户管理").click()
touch(Template(r"tpl1562674752913.png", record_pos=(-0.111, -0.041), resolution=(1080, 2160)))
touch(Template(r"tpl1562674761020.png", record_pos=(-0.423, -0.38), resolution=(1080, 2160)))
touch(Template(r"tpl1562674775789.png", record_pos=(0.094, -0.747), resolution=(1080, 2160)))
text("256")
touch(Template(r"tpl1562674789559.png", record_pos=(-0.154, -0.644), resolution=(1080, 2160)))
touch(Template(r"tpl1562674797108.png", record_pos=(0.423, 0.326), resolution=(1080, 2160)))
touch(Template(r"tpl1562674801515.png", record_pos=(-0.419, -0.55), resolution=(1080, 2160)))
