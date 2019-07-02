# -*- encoding=utf8 -*-
__author__ = "Administrator"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.android.uiautomation import AndroidUiautomationPoco
poco = AndroidUiautomationPoco()

poco("android.widget.LinearLayout").offspring("com.tencent.mm:id/d88").child("android.widget.FrameLayout").child("android.widget.FrameLayout").child("android.widget.FrameLayout").offspring("com.tencent.mm:id/w").child("android.widget.LinearLayout").child("android.widget.RelativeLayout")[0].offspring("com.tencent.mm:id/jb").click()
poco(text="样品管理").click()

poco(text="设备管理").click()
poco(text="sampleNumber:345y").click()

poco(text="back ").click()

poco("android.widget.Button").click()
touch(Template(r"tpl1561947691742.png", record_pos=(-0.006, -0.721), resolution=(1080, 2340)))


text('132465')
touch(Template(r"tpl1561948624970.png", record_pos=(-0.077, -0.597), resolution=(1080, 2340)))
text('happy')
touch(Template(r"tpl1561949534853.png", record_pos=(-0.029, -0.475), resolution=(1080, 2340)))
text('1')
touch(Template(r"tpl1561948234084.png", record_pos=(0.116, -0.289), resolution=(1080, 2340)))
text('1')
touch(Template(r"tpl1561947780434.png", record_pos=(-0.017, -0.157), resolution=(1080, 2340)))
text('NanjingUniversity')
touch(Template(r"tpl1561947942820.png", record_pos=(-0.032, -0.044), resolution=(1080, 2340)))
text('hcb')
touch(Template(r"tpl1561948459464.png", record_pos=(0.09, 0.093), resolution=(1080, 2340)))
poco(text="6").click()
poco(text="01").click()
poco(text="确定").click()
touch(Template(r"tpl1561948705680.png", record_pos=(0.021, 0.206), resolution=(1080, 2340)))
text('hhj')
touch(Template(r"tpl1561949081101.png", record_pos=(0.075, 0.398), resolution=(1080, 2340)))
poco(text="确定").click()
touch(Template(r"tpl1561949117986.png", record_pos=(-0.415, 0.499), resolution=(1080, 2340)))




