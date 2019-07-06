package com.example.demo.Controller;

import com.example.demo.Model.EquipmentUse;
import com.example.demo.Repository.EquipmentUseRepository;
import com.example.demo.framework.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path="/cma/EquipmentUse")
public class EquipmentUseController {
    @Autowired
    private EquipmentUseRepository EURepository;
    @RequestMapping(path="add",method=RequestMethod.POST)
    @ResponseBody
    public Response addEquipment(
            @RequestParam (value="equipmentId",required=false)Long equipmentId,
            @RequestParam (value="useDate",required=false)Date useDate,
            @RequestParam (value="openDate",required=false)Date openDate,
            @RequestParam (value="closeDate",required=false)Date closeDate,
            @RequestParam (value="sampleNumber",required=false)String sampleNumber,
            @RequestParam (value="testProject",required=false)String testProject,
            @RequestParam (value="beforeUse",required=false)String beforeUse,
            @RequestParam (value="afterUse",required=false)String  afterUse,
            @RequestParam (value="user",required=false)String  user,
            @RequestParam (value="remark",required=false)String  remark
    ){
        Response response=new Response();
        EquipmentUse equipment = new EquipmentUse();
        equipment.setEquipmentId(equipmentId);
        equipment.setUseDate(useDate);
        equipment.setOpenDate(openDate);
        equipment.setCloseDate(closeDate);
        equipment.setSampleNumber(sampleNumber);
        equipment.setTestProject(testProject);
        equipment.setBeforeUse(beforeUse);
        equipment.setAfterUse(afterUse);
        equipment.setUser(user);
        equipment.setRemark(remark);

        EURepository.save(equipment);
        System.out.println(equipment.getId());
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
            if (EURepository.findById(id) == null) {
                throw new Exception("ID:"+id+" does not exist");
            }
            EquipmentUse equipmentUse = EURepository.findByid(id);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentUse));
            response.code=200;
            response.data=ejson;
            response.msg="成功";
        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }


    @RequestMapping(value="/getAll",method=RequestMethod.GET)
    @ResponseBody
    public Response getAll(){
        Response response=new Response();
        JSONObject alljson=new JSONObject();
      //  System.out.println("11111111111111111");
        Iterable<EquipmentUse> list=EURepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<EquipmentUse>) list).size(); i++)
        {
         //   System.out.println("2222222222222");
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<EquipmentUse>) list).get(i)));
            jsons.add(ejson);
        }
        alljson.put("data",jsons);
        response.code=200;
        response.data=alljson;
        response.msg="成功";
        return response;
    }

    @RequestMapping(value="/modifyOne/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response modifyOne(
            @PathVariable("id")long id,
            @RequestParam (value="equipmentId",required=false)Long equipmentId,
            @RequestParam (value="useDate",required=false)Date useDate,
            @RequestParam (value="openDate",required=false)Date openDate,
            @RequestParam (value="closeDate",required=false)Date closeDate,
            @RequestParam (value="sampleNumber",required=false) String sampleNumber,
            @RequestParam (value="testProject",required=false)String testProject,
            @RequestParam (value="beforeUse",required=false)String beforeUse,
            @RequestParam (value="afterUse",required=false)String  afterUse,
            @RequestParam (value="user",required=false)String  user,
            @RequestParam (value="remark",required=false)String  remark
    ){
        Response response=new Response();
        try{
            if(EURepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            EquipmentUse equipmentUse=EURepository.findByid(id);
            equipmentUse.setRemark(remark);
            equipmentUse.setUser(user);
            equipmentUse.setAfterUse(afterUse);
            equipmentUse.setBeforeUse(beforeUse);
            equipmentUse.setTestProject(testProject);
            equipmentUse.setSampleNumber(sampleNumber);
            equipmentUse.setCloseDate(closeDate);
            equipmentUse.setOpenDate(openDate);
            equipmentUse.setUseDate(useDate);
            equipmentUse.setEquipmentId(equipmentId);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentUse));
            EURepository.save(equipmentUse);
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
            if(EURepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            EURepository.deleteById(id);
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
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
    @ResponseBody
    public Response deleteAll()
    {
        Response response=new Response();

            EURepository.deleteAll();
            response.data=null;
            response.msg="成功";
            response.code=200;
        return response;
    }
    @RequestMapping(value="/getAllByEquipmentId/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Response getAllByEquipmentId(@PathVariable("id") Long id)
    {
        Response response=new Response();
        try{
            if(EURepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            JSONObject alljson=new JSONObject();
            Iterable<EquipmentUse> list=EURepository.findAll();
            JSONArray jsons=new JSONArray();
            for(int i = 0; i<((List<EquipmentUse>) list).size(); i++)
            {
                if(((List<EquipmentUse>) list).get(i).getEquipmentId()==id) {
                    JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<EquipmentUse>) list).get(i)));
                    jsons.add(ejson);
                }
            }
            alljson.put("data",jsons);
            response.data=alljson;
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
