package com.example.demo.Controller;

import com.example.demo.Repository.EquipmentApplicationRepository;
import com.example.demo.Model.EquipmentApplication;
import com.example.demo.framework.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(path="/cma/EquipmentApplication")
public class EquipmentApplicationController {
    @Autowired
    private EquipmentApplicationRepository EARepository;

    @RequestMapping(path="addOne",method=RequestMethod.POST)
    @ResponseBody
    public Response addEquipment(
            @RequestParam (value="equipmentUse",required=false)Byte equipmentUse,
            @RequestParam (value="applicationDate",required=false) Date applicationDate,
            @RequestParam (value="applicant",required=false)String applicant,
            @RequestParam (value="applicationPurpose",required=false)String applicationPurpose,
            @RequestParam (value="equipmentNumber",required=false)String equipmentNumber,
            @RequestParam (value="softwareInfo",required=false)String softwareInfo,
            @RequestParam (value="auditor",required=false)String auditor,
            @RequestParam (value="auditOpinion",required=false)String auditOpinion,
            @RequestParam (value="auditDate",required=false)Date auditDate
            ){
        Response response=new Response();
        EquipmentApplication equipmentApplication = new EquipmentApplication();
        equipmentApplication.setApplicant(applicant);
        equipmentApplication.setApplicationDate(applicationDate);
        equipmentApplication.setApplicationPurpose(applicationPurpose);
        equipmentApplication.setAuditDate(auditDate);
        equipmentApplication.setAuditOpinion(auditOpinion);
        equipmentApplication.setEquipmentNumber(equipmentNumber);
        equipmentApplication.setSoftwareInfo(softwareInfo);
        equipmentApplication.setAuditor(auditor);
        equipmentApplication.setEquipmentUse(equipmentUse);

        EARepository.save(equipmentApplication);
        System.out.println(equipmentApplication.getId());
        // JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipment));
        response.code=200;
        response.data=null;
        response.setMessage("成功");
        return response;
    }

    @RequestMapping(value="/getOne/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Response getOne(@PathVariable("id") long id){
        Response response=new Response();
        System.out.println("finfbyid:"+id);
        try {
            if (EARepository.findById(id)==null) {
                throw new Exception("ID:"+id+" does not exist");
            }
            EquipmentApplication equipmentApplication =EARepository.findById(id);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentApplication));
            response.code=200;
            response.data=ejson;
            response.msg="成功";
        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.data=null;
            response.msg=e.getMessage();
        }
        return response;
    }


    @RequestMapping(value="/getAll",method=RequestMethod.GET)
    @ResponseBody
    public Response getAll(){
        Response response=new Response();
        JSONObject alljson=new JSONObject();
        Iterable<EquipmentApplication> list=EARepository.findAll();
      /*  List<Equipment> list1=new ArrayList<>();
        list.forEach(i->{list1.add(i);});*/
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<EquipmentApplication>) list).size(); i++)
        {
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<EquipmentApplication>) list).get(i)));
            jsons.add(ejson);
        }
        alljson.put("Equipments",jsons);
        response.code=200;
        response.data=alljson;
        response.msg="成功";
        return response;
    }

    @RequestMapping(value="/modifyOne/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response modifyOne(
            @PathVariable("id") long id,
            @RequestParam (value="equipmentUse",required=false)Byte equipmentUse,
            @RequestParam (value="applicationDate",required=false) Date applicationDate,
            @RequestParam (value="applicant",required=false)String applicant,
            @RequestParam (value="applicationPurpose",required=false)String applicationPurpose,
            @RequestParam (value="equipmentNumber",required=false)String equipmentNumber,
            @RequestParam (value="softwareInfo",required=false)String softwareInfo,
            @RequestParam (value="auditor",required=false)String auditor,
            @RequestParam (value="auditOpinion",required=false)String auditOpinion,
            @RequestParam (value="auditDate",required=false)Date auditDate){
        Response response=new Response();
        try{
            if (EARepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            EquipmentApplication equipmentApplication =EARepository.findById(id);
            equipmentApplication.setApplicant(applicant);
            equipmentApplication.setApplicationDate(applicationDate);
            equipmentApplication.setApplicationPurpose(applicationPurpose);
            equipmentApplication.setAuditDate(auditDate);
            equipmentApplication.setAuditOpinion(auditOpinion);
            equipmentApplication.setEquipmentNumber(equipmentNumber);
            equipmentApplication.setSoftwareInfo(softwareInfo);
            equipmentApplication.setEquipmentUse(equipmentUse);
            equipmentApplication.setAuditor(auditor);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentApplication));
            EARepository.save(equipmentApplication);
            response.code=200;
            response.msg="成功";
            response.data=null;
        }catch(Exception e)
        {
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }

    @RequestMapping(value="/deleteOne/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response deleteOne(@PathVariable("id") long id)
    {
        Response response=new Response();
        try{
            if(EARepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            EARepository.deleteById(id);
            response.data=null;
            response.msg="成功";
            response.code=200;

        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
