package com.example.demo.Controller;

import com.example.demo.Model.StaffLeaving;
import com.example.demo.Model.StaffManagement;
import com.example.demo.Repository.StaffFileRepository;
import com.example.demo.Repository.StaffLeavingRepository;
import com.example.demo.Repository.StaffManagementRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/cma/StaffLeaving")
public class StaffLeavingController {
    @Autowired
    private StaffLeavingRepository staffLeavingRepository;
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path = "getAll")
    //获取所有离任人员信息
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffLeavingRepository.findAll());
        //返回数据库中所有离任人员的信息
        return json;
    }

    @PostMapping(path = "addOne")
    //添加一个离任人员
    public @ResponseBody JSONObject addOne(@RequestParam(required = false,value = "id")long id,@RequestParam(required = false,value = "leavingDate")String leavingDate){
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","不存在员工");
            json.put("data",null);
            return json;
            //人员列表中不存在该人员
        }
        StaffManagement staff=staffManagementRepository.getOne(id);
        //获取该人员的信息
        StaffLeaving staffLeaving=new StaffLeaving();
        staffLeaving.setStaffid(staff.getId());
        //输入人员id
        staffLeaving.setDepartment(staff.getDepartment());
        //输入部门
        staffLeaving.setName(staff.getName());
        //输入人员姓名
        staffLeaving.setPosition(staff.getPosition());
        //输入职位
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            staffLeaving.setLeavingDate(sdf.parse(leavingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //输入人员离任日期
        staffLeavingRepository.save(staffLeaving);
        staffManagementRepository.deleteById(id);
        //在人员列表中删除该人员
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
}
