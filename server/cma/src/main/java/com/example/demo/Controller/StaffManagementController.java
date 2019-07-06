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
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffManagementRepository.findAll());

        return json;
    }

    @PostMapping(path="addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value="name",required = false)String name,@RequestParam(value = "gender",required = false)String gender,
                                       @RequestParam(value = "department",required = false)String department,@RequestParam(value = "position",required = false)String position,
                                       @RequestParam(value="title",required = false)String title,@RequestParam(value = "degree",required = false)String degree,
                                       @RequestParam(value = "graduationSchool",required = false)String graduationSchool,@RequestParam(value = "graduationMajor",required = false)String graduationMajor,
                                       @RequestParam(value = "graduationDate",required = false)String graduationDate,@RequestParam(value = "workingYears",required = false)int workingYears) throws ParseException {
        JSONObject json=new JSONObject();
        StaffManagement staffManagement=new StaffManagement();
        staffManagement.setName(name);
        staffManagement.setGender(gender);
        staffManagement.setDepartment(department);
        staffManagement.setPosition(position);
        staffManagement.setTitle(title);
        staffManagement.setDegree(degree);
        staffManagement.setGraduationSchool(graduationSchool);
        staffManagement.setGraduationMajor(graduationMajor);
        DateFormat df=DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(graduationDate);
        staffManagement.setGraduationDate(date);
        staffManagement.setWorkingYears(workingYears);
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
        staffManagementRepository.save(staffManagement);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
    @GetMapping(path = "getOne")
    public @ResponseBody JSONObject get(@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("data",null);
            json.put("code",210);
            json.put("msg","失败");
        }
        else
        {
            json.put("data",staffManagementRepository.findById(id));
            json.put("code", 200);
            json.put("msg", "成功");
        }
        return  json;
    }
    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false)Long id){
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","失败");
            json.put("data",null);
        }
        else{
            staffManagementRepository.deleteById(id);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data",null);
        }
        return json;
    }
    @PostMapping(path = "modifyOne")
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
        }
        else {
            StaffManagement staffManagement = staffManagementRepository.getOne(id);
            if(!name.equals(""))
                staffManagement.setName(name);
            if(!gender.equals("")) {
                staffManagement.setGender(gender);
            }
            if(!department.equals(""))
                staffManagement.setDepartment(department);
            if(!position.equals(""))
                staffManagement.setPosition(position);
            if(!title.equals(""))
                staffManagement.setTitle(title);
            if(!degree.equals(""))
                staffManagement.setDegree(degree);
            if(!graduationSchool.equals(""))
                staffManagement.setGraduationSchool(graduationSchool);
            if(!graduationMajor.equals(""))
                staffManagement.setGraduationMajor(graduationMajor);
            if(!graduationDate.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(graduationDate);
                staffManagement.setGraduationDate(date);
            }
            if(!workingYears.equals("")) {
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
