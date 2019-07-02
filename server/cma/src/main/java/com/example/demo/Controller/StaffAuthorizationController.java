package com.example.demo.Controller;

import com.example.demo.Model.StaffAuthorization;
import com.example.demo.Model.StaffManagement;
import com.example.demo.Model.TrainingApplication;
import com.example.demo.Repository.StaffAuthorizationRepository;
import com.example.demo.Repository.StaffManagementRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/cma/StaffAuthorization")
public class StaffAuthorizationController {
    @Autowired
    private StaffAuthorizationRepository staffAuthorizationRepository;
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path = "getAll")
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffAuthorizationRepository.findAll());
        return json;
    }

    @GetMapping(path = "getOne")
    public @ResponseBody JSONObject getOne(@RequestParam(value="authorizationId",required = false)long authorizationId){
        JSONObject json=new JSONObject();
        if(staffAuthorizationRepository.existsById(authorizationId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",staffAuthorizationRepository.findById(authorizationId));
        }
        return json;
    }
    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "id",required = false) long id,@RequestParam(value = "authorizerId",required = false)long authorizerId,
                                           @RequestParam(value = "content",required = false)String content,@RequestParam(value = "authorizerDate",required = false)String authorizerDate) throws ParseException {
        StaffAuthorization staffAuthorization=new StaffAuthorization();
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","被授权人不存在");
            json.put("data",null);
            return json;
        }
        StaffManagement staffManagement=staffManagementRepository.getOne(id);
        if(staffManagementRepository.existsById(authorizerId)==false){
            json.put("code",210);
            json.put("msg","授权人不存在");
            json.put("data",null);
            return json;
        }
        StaffManagement authorizer=staffManagementRepository.getOne(authorizerId);
        staffAuthorization.setId(id);
        staffAuthorization.setName(staffManagement.getName());
        staffAuthorization.setDepartment(staffManagement.getDepartment());
        staffAuthorization.setPosition(staffManagement.getPosition());
        staffAuthorization.setAuthorizerId(authorizerId);
        staffAuthorization.setAuthorizerName(authorizer.getName());
        staffAuthorization.setContent(content);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        staffAuthorization.setAuthorizerDate(sdf.parse(authorizerDate));
        staffAuthorizationRepository.save(staffAuthorization);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "authorizationId",required = false)long authorizationId){
        JSONObject json=new JSONObject();
        if(staffAuthorizationRepository.existsById(authorizationId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else{
            staffAuthorizationRepository.deleteById(authorizationId);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "authorizationId",required = false)long authorizationId,@RequestParam(value = "id",required = false) String id,@RequestParam(value = "authorizerId",required = false)String authorizerId,
                                              @RequestParam(value = "content",required = false)String content,@RequestParam(value = "authorizerDate",required = false)String authorizerDate) throws ParseException {
        JSONObject json=new JSONObject();
        if(staffAuthorizationRepository.existsById(authorizationId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else{
            StaffAuthorization staffAuthorization=staffAuthorizationRepository.getOne(authorizationId);
            StaffManagement staffManagement=new StaffManagement();
            if(!id.equals("")) {
                long Id=Long.parseLong(id);
                if(staffManagementRepository.existsById(Id)==false){
                    json.put("code",210);
                    json.put("msg","被授权人不存在");
                    json.put("data",null);
                    return json;
                }
                staffManagement=staffManagementRepository.getOne(Id);
                staffAuthorization.setId(Id);
                staffAuthorization.setName(staffManagement.getName());
                staffAuthorization.setDepartment(staffManagement.getDepartment());
                staffAuthorization.setPosition(staffManagement.getPosition());
            }
            if(!authorizerId.equals("")) {
                long Id=Long.parseLong(authorizerId);
                if(staffManagementRepository.existsById(Id)==false){
                    json.put("code",210);
                    json.put("msg","授权人不存在");
                    json.put("data",null);
                    return json;
                }
                staffManagement=staffManagementRepository.getOne(Id);
                staffAuthorization.setAuthorizerId(Id);
                staffAuthorization.setAuthorizerName(staffManagement.getName());
            }
            if(!content.equals(""))
                staffAuthorization.setContent(content);
            if(!authorizerDate.equals("")){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                staffAuthorization.setAuthorizerDate(sdf.parse(authorizerDate));
            }
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
            staffAuthorizationRepository.save(staffAuthorization);
        }
        return json;
    }

    @GetMapping(path = "GetAllByStaff")
    public @ResponseBody JSONObject getallbystaff(@RequestParam(required = false,value = "id")long id){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffAuthorizationRepository.findAllById(id));
        return json;
    }

}
