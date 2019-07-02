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
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffLeavingRepository.findAll());
        return json;
    }

    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(required = false,value = "id")long id,@RequestParam(required = false,value = "LeavingDate")String LeavingDate){
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","不存在员工");
            json.put("data",null);
            return null;
        }
        StaffManagement staff=staffManagementRepository.getOne(id);
        StaffLeaving staffLeaving=new StaffLeaving();
        staffLeaving.setStaffid(staff.getId());
        staffLeaving.setDepartment(staff.getDepartment());
        staffLeaving.setName(staff.getName());
        staffLeaving.setPosition(staff.getPosition());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            staffLeaving.setLeavingDate(sdf.parse(LeavingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        staffLeavingRepository.save(staffLeaving);
        staffManagementRepository.deleteById(id);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
}
