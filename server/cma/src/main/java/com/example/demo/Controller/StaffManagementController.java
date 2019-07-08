package com.example.demo.Controller;

import com.example.demo.Repository.StaffManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;

import net.minidev.json.JSONObject;

import com.example.demo.Model.StaffManagement;
@Controller
@RequestMapping(path="/cma/StaffManagement")
public class StaffManagementController {
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path="getAll")
    //获取所有人员信息
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffManagementRepository.findAll());
        //返回数据库中所有人员的信息

        return json;
    }

    @PostMapping(path="addOne")
    //添加人员信息
    public @ResponseBody JSONObject addOne(@RequestParam(value="name",required = false)String name,@RequestParam(value = "gender",required = false)String gender,
                                       @RequestParam(value = "department",required = false)String department,@RequestParam(value = "position",required = false)String position,
                                       @RequestParam(value="title",required = false)String title,@RequestParam(value = "degree",required = false)String degree,
                                       @RequestParam(value = "graduationSchool",required = false)String graduationSchool,@RequestParam(value = "graduationMajor",required = false)String graduationMajor,
                                       @RequestParam(value = "graduationDate",required = false)String graduationDate,@RequestParam(value = "workingYears",required = false)int workingYears) throws ParseException {
        JSONObject json=new JSONObject();
        StaffManagement staffManagement=new StaffManagement();
        staffManagement.setName(name);
        //输入姓名
        staffManagement.setGender(gender);
        //输入性别
        staffManagement.setDepartment(department);
        //输入部门
        staffManagement.setPosition(position);
        //输入职位
        staffManagement.setTitle(title);
        //输入头衔
        staffManagement.setDegree(degree);
        //输入学历
        staffManagement.setGraduationSchool(graduationSchool);
        //输入毕业学校
        staffManagement.setGraduationMajor(graduationMajor);
        //输入毕业专业
        DateFormat df=DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(graduationDate);
        staffManagement.setGraduationDate(date);
        //输入毕业时间
        staffManagement.setWorkingYears(workingYears);
        //输入工龄
        List<StaffManagement> list=staffManagementRepository.findAll();
        for (int i=0;i<list.size();i++){
            StaffManagement temp=list.get(i);
            if(staffManagement.Eqauls(temp)==true)
            {
                json.put("code",210);
                json.put("msg","失败,已存在员工");
                json.put("data",null);
                return json;
            }
        }
        //判断该人员是否已存在
        staffManagementRepository.save(staffManagement);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
    @GetMapping(path = "getOne")
    //获取某个人员信息
    public @ResponseBody JSONObject get(@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("data",null);
            json.put("code",210);
            json.put("msg","失败");
            //若该人员不存在，返回信息
        }
        else
        {
            json.put("data",staffManagementRepository.findById(id));
            json.put("code", 200);
            json.put("msg", "成功");
            //返回该人员的详细信息
        }
        return  json;
    }
    @PostMapping(path = "deleteOne")
    //删除某个人员
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false)Long id){
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","失败");
            json.put("data",null);
            //该人员不存在
        }
        else{
            staffManagementRepository.deleteById(id);
            //删除该人员
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data",null);
        }
        return json;
    }
    @PostMapping(path = "modifyOne")
    //修改人员信息，除了id都是选填项
    public @ResponseBody JSONObject modifyOne(@RequestParam(value="id",required = false)long id,@RequestParam(value="name",required = false)String name,@RequestParam(value = "gender",required = false)String gender,
                                              @RequestParam(value = "department",required = false)String department,@RequestParam(value = "position",required = false)String position,
                                              @RequestParam(value="title",required = false)String title,@RequestParam(value = "degree",required = false)String degree,
                                              @RequestParam(value = "graduationSchool",required = false)String graduationSchool,@RequestParam(value = "graduationMajor",required = false)String graduationMajor,
                                              @RequestParam(value = "graduationDate",required = false)String graduationDate,@RequestParam(value = "workingYears",required = false)String workingYears) throws ParseException {
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","失败");
            json.put("data",null);
            //该人员不存在
        }
        else {
            StaffManagement staffManagement = staffManagementRepository.getOne(id);
            if(!name.equals(""))
                //姓名是否修改
                staffManagement.setName(name);
            if(!gender.equals("")) {
                //性别是否修改
                staffManagement.setGender(gender);
            }
            if(!department.equals(""))
                //部门是否修改
                staffManagement.setDepartment(department);
            if(!position.equals(""))
                //职位是否修改
                staffManagement.setPosition(position);
            if(!title.equals(""))
                //头衔是否修改
                staffManagement.setTitle(title);
            if(!degree.equals(""))
                //学历是否修改
                staffManagement.setDegree(degree);
            if(!graduationSchool.equals(""))
                //毕业学校是否修改
                staffManagement.setGraduationSchool(graduationSchool);
            if(!graduationMajor.equals(""))
                //毕业专业是否修改
                staffManagement.setGraduationMajor(graduationMajor);
            if(!graduationDate.equals("")) {
                //毕业日期是否修改
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(graduationDate);
                staffManagement.setGraduationDate(date);
            }
            if(!workingYears.equals("")) {
                //工龄是否修改
                int wy=Integer.parseInt(workingYears);
                staffManagement.setWorkingYears(wy);
            }
            staffManagementRepository.save(staffManagement);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }

}
