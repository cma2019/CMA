# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco

poco = AndroidUiautomationPoco()


touch(Template(r"tpl1562639925732.png", record_pos=(-0.309, 0.927), resolution=(1080, 2160)))

poco(text="用户管理").click()
touch(Template(r"tpl1562674420115.png", record_pos=(-0.258, -0.149), resolution=(1080, 2160)))
sleep(1.0)

touch(Template(r"tpl1562674427045.png", record_pos=(-0.432, -0.681), resolution=(1080, 2160)))
sleep(1.0)

touch(Template(r"tpl1562674592607.png", record_pos=(0.105, -0.755), resolution=(1080, 2160)))

text("255")
touch(Template(r"tpl1562674480109.png", record_pos=(-0.002, -0.642), resolution=(1080, 2160)))
text("测试工程师资质认定")
touch(Template(r"tpl1562674496259.png", record_pos=(-0.41, -0.546), resolution=(1080, 2160)))
touch(Template(r"tpl1562674503581.png", record_pos=(-0.079, -0.516), resolution=(1080, 2160)))
touch(Template(r"tpl1562674517039.png", record_pos=(-0.289, -0.701), resolution=(1080, 2160)))
touch(Template(r"tpl1562674525444.png", record_pos=(0.378, 0.918), resolution=(1080, 2160)))
touch(Template(r"tpl1562674663099.png", record_pos=(-0.304, -0.563), resolution=(1080, 2160)))
touch(Template(r"tpl1562674670168.png", record_pos=(-0.415, 0.072), resolution=(1080, 2160)))
touch(Template(r"tpl1562674675350.png", record_pos=(-0.235, 0.07), resolution=(1080, 2160)))
touch(Template(r"tpl1562674680779.png", record_pos=(-0.42, -0.435), resolution=(1080, 2160)))
touch(Template(r"tpl1562674686677.png", record_pos=(0.263, 0.076), resolution=(1080, 2160)))
touch(Template(r"tpl1562674690525.png", record_pos=(-0.428, -0.499), resolution=(1080, 2160)))
touch(Template(r"tpl1562674695563.png", record_pos=(0.078, 0.077), resolution=(1080, 2160)))
