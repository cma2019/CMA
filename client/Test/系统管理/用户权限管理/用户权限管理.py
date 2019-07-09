# -*- encoding=utf8 -*-
__author__ = "diaohanshun"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco

poco = AndroidUiautomationPoco()


touch(Template(r"tpl1562639925732.png", record_pos=(-0.309, 0.927), resolution=(1080, 2160)))
swipe(Template(r"tpl1562675133902.png", record_pos=(-0.299, -0.233), resolution=(1080, 2160)), vector=[0.1789, -0.2483])
touch(Template(r"tpl1562675165247.png", record_pos=(-0.285, 0.796), resolution=(1080, 2160)))

swipe(Template(r"tpl1562675193263.png", record_pos=(-0.38, -0.346), resolution=(1080, 2160)), vector=[0.0013, -0.128])
touch(Template(r"tpl1562675208137.png", record_pos=(-0.167, 0.76), resolution=(1080, 2160)))
touch(Template(r"tpl1562675218930.png", record_pos=(-0.375, -0.662), resolution=(1080, 2160)))
touch(Template(r"tpl1562675223860.png", record_pos=(-0.42, -0.344), resolution=(1080, 2160)))
touch(Template(r"tpl1562675233441.png", record_pos=(-0.257, -0.664), resolution=(1080, 2160)))
touch(Template(r"tpl1562675241026.png", record_pos=(-0.344, 0.013), resolution=(1080, 2160)))
swipe(Template(r"tpl1562675246561.png", record_pos=(-0.181, 0.048), resolution=(1080, 2160)), vector=[-0.0099, -0.1917])
touch(Template(r"tpl1562675268575.png", record_pos=(-0.422, 0.863), resolution=(1080, 2160)))
touch(Template(r"tpl1562675275611.png", record_pos=(-0.277, -0.348), resolution=(1080, 2160)))


