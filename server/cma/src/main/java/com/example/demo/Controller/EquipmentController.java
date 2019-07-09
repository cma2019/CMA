package com.example.demo.Controller;

import com.example.demo.Repository.EquipmentRepository;
import com.example.demo.Model.Equipment;
import com.example.demo.framework.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author YXP
 * 设备管理
 */
@Controller
@RequestMapping(path="cma/Equipment")
public class EquipmentController {
    @Autowired
    private EquipmentRepository ERepository;

    @RequestMapping(path="add",method=RequestMethod.POST)
    @ResponseBody
    public Response addEquipment(
            @RequestParam (value="name",required=false)String name,
            @RequestParam (value="model",required=false)String model,
            @RequestParam (value="cpu",required=false)String cpu,
            @RequestParam (value="memory",required=false)String memory,
            @RequestParam (value="hardDisk",required=false)String harddisk,
            @RequestParam (value="equipmentNumber",required=false)String equipmentnumber,
            @RequestParam (value="application",required=false)String application,
            @RequestParam (value="state",required=false)Byte state){
            Response response=new Response();
            Equipment equipment = new Equipment();
            equipment.setName(name);
            equipment.setModel(model);
            equipment.setCpu(cpu);
            equipment.setMemory(memory);
            equipment.setHardDisk(harddisk);
            equipment.setEquipmentNumber(equipmentnumber);
            equipment.setApplication(application);
            equipment.setState(state);
            ERepository.save(equipment);
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
            if (ERepository.findById(id) == null) {
                throw new Exception("ID:"+id+" does not exist");
            }
            Equipment equipment = ERepository.findById(id);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipment));
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
        Iterable<Equipment> list=ERepository.findAll();
      /*  List<Equipment> list1=new ArrayList<>();
        list.forEach(i->{list1.add(i);});*/
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<Equipment>) list).size(); i++)
        {
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<Equipment>) list).get(i)));
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
            @RequestParam (value="name",required=false)String name,
            @RequestParam (value="model",required=false)String model,
            @RequestParam (value="cpu",required=false)String cpu,
            @RequestParam (value="memory",required=false)String memory,
            @RequestParam (value="hardDisk",required=false)String harddisk,
            @RequestParam (value="equipmentNumber",required=false)String equipmentnumber,
            @RequestParam (value="application",required=false)String application,
            @RequestParam (value="state",required=false)Byte state){
        Response response=new Response();
        try{
            if(ERepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            Equipment equipment=ERepository.findById(id);
            equipment.setState(state);
            equipment.setApplication(application);
            equipment.setEquipmentNumber(equipmentnumber);
            System.out.println(harddisk);
            System.out.println(equipmentnumber);
            equipment.setHardDisk(harddisk);
            equipment.setMemory(memory);
            equipment.setModel(model);
            equipment.setCpu(cpu);
            equipment.setName(name);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipment));
            ERepository.save(equipment);
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
            if(ERepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            ERepository.deleteById(id);
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
}
