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
    //获取所有授权记录
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffAuthorizationRepository.findAll());
        //返回所有的授权记录
        return json;
    }

    @GetMapping(path = "getOne")
    //获取某一次授权记录
    public @ResponseBody JSONObject getOne(@RequestParam(value="authorizationId",required = false)long authorizationId){
        JSONObject json=new JSONObject();
        if(staffAuthorizationRepository.existsById(authorizationId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        //该次授权记录不存在，返回信息
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",staffAuthorizationRepository.findById(authorizationId));
            //返回该次授权记录的详细信息
        }
        return json;
    }
    @PostMapping(path = "addOne")
    //添加某一次授权记录
    public @ResponseBody JSONObject addOne(@RequestParam(value = "id",required = false) long id,@RequestParam(value = "authorizerId",required = false)long authorizerId,
                                           @RequestParam(value = "content",required = false)String content,@RequestParam(value = "authorizerDate",required = false)String authorizerDate) throws ParseException {
        StaffAuthorization staffAuthorization=new StaffAuthorization();
        JSONObject json=new JSONObject();
        if(staffManagementRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","被授权人不存在");
            json.put("data",null);
            return json;
            //被授权人不在人员列表中，返回信息
        }
        StaffManagement staffManagement=staffManagementRepository.getOne(id);
        if(staffManagementRepository.existsById(authorizerId)==false){
            json.put("code",210);
            json.put("msg","授权人不存在");
            json.put("data",null);
            return json;
            //授权人不在人员列表中，返回信息
        }
        StaffManagement authorizer=staffManagementRepository.getOne(authorizerId);
        //获取授权人的信息
        staffAuthorization.setId(id);
        //输入被授权人id
        staffAuthorization.setName(staffManagement.getName());
        //输入被授权人姓名
        staffAuthorization.setDepartment(staffManagement.getDepartment());
        //输入被授权人部门
        staffAuthorization.setPosition(staffManagement.getPosition());
        //输入被授权人职位
        staffAuthorization.setAuthorizerId(authorizerId);
        //输入授权人id
        staffAuthorization.setAuthorizerName(authorizer.getName());
        //输入授权人姓名
        staffAuthorization.setContent(content);
        //输入授权记录内容
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        staffAuthorization.setAuthorizerDate(sdf.parse(authorizerDate));
        //输入授权日期
        staffAuthorizationRepository.save(staffAuthorization);
        //保存授权记录
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除某次授权记录
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "authorizationId",required = false)long authorizationId){
        JSONObject json=new JSONObject();
        if(staffAuthorizationRepository.existsById(authorizationId)==false){
            json.put("code",210);
            json.put("msg","授权信息不存在");
            json.put("data",null);
        }
        //该授权记录不存在，返回信息
        else{
            staffAuthorizationRepository.deleteById(authorizationId);
            //删除该授权记录
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    //修改授权记录，除了授权记录id其他都是可选项
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "authorizationId",required = false)long authorizationId,@RequestParam(value = "id",required = false) String id,@RequestParam(value = "authorizerId",required = false)String authorizerId,
                                              @RequestParam(value = "content",required = false)String content,@RequestParam(value = "authorizerDate",required = false)String authorizerDate) throws ParseException {
        JSONObject json=new JSONObject();
        if(staffAuthorizationRepository.existsById(authorizationId)==false){
            json.put("code",210);
            json.put("msg","授权信息不存在");
            json.put("data",null);
        }
        //该授权记录不存在，返回信息
        else{
            StaffAuthorization staffAuthorization=staffAuthorizationRepository.getOne(authorizationId);
            //获取该次授权记录的信息
            StaffManagement staffManagement=new StaffManagement();
            if(!id.equals("")) {
                //判断可选项是否被修改
                long Id=Long.parseLong(id);
                if(staffManagementRepository.existsById(Id)==false){
                    json.put("code",210);
                    json.put("msg","被授权人不存在");
                    json.put("data",null);
                    return json;
                }
                //修改的被授权人不存在
                staffManagement=staffManagementRepository.getOne(Id);
                //修改的被授权人存在
                staffAuthorization.setId(Id);
                staffAuthorization.setName(staffManagement.getName());
                staffAuthorization.setDepartment(staffManagement.getDepartment());
                staffAuthorization.setPosition(staffManagement.getPosition());
                //更改授权记录中被授权人的信息
            }
            if(!authorizerId.equals("")) {
                //判断授权人是否被修改
                long Id=Long.parseLong(authorizerId);
                if(staffManagementRepository.existsById(Id)==false){
                    json.put("code",210);
                    json.put("msg","授权人不存在");
                    json.put("data",null);
                    return json;
                    //修改的授权人不存在
                }
                staffManagement=staffManagementRepository.getOne(Id);
                //获取修改的授权人信息
                staffAuthorization.setAuthorizerId(Id);
                staffAuthorization.setAuthorizerName(staffManagement.getName());
                //修改该记录中授权人的信息
            }
            if(!content.equals(""))
                //判断授权内容是否被修改
                staffAuthorization.setContent(content);
            if(!authorizerDate.equals("")){
                //判断授权日期是否被修改
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
    //获取某个人员所有的授权信息
    public @ResponseBody JSONObject getallbystaff(@RequestParam(required = false,value = "id")long id){
        //输入被授权人id
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffAuthorizationRepository.findAllById(id));
        //获取所有该人员作为被授权人的授权记录
        return json;
    }

}
