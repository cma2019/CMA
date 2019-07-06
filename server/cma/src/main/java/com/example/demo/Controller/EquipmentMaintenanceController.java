package com.example.demo.Controller;
import com.example.demo.Repository.EquipmentMaintenanceRepository;
import com.example.demo.Model.EquipmentMaintenance;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path="/cma/EquipmentMaintenance")
public class EquipmentMaintenanceController {
    @Autowired
    private EquipmentMaintenanceRepository equipmentMaintenanceRepository;

    @RequestMapping(path="addOne",method=RequestMethod.POST)
    @ResponseBody
    public Response addEquipment(
            @RequestParam (value="equipmentId",required=false)Long equipmentId,
            @RequestParam (value="maintenanceDate",required=false) Date maintenanceData,
            @RequestParam (value="maintenanceContent",required=false)String maintenanceContent,
            @RequestParam (value="maintenancePerson",required=false)String maintenancePerson,
            @RequestParam (value="confirmer",required=false)String confirmer){
        Response response=new Response();
        EquipmentMaintenance equipmentMaintenance = new EquipmentMaintenance();
        equipmentMaintenance.setConfirmer(confirmer);
        equipmentMaintenance.setEquipmentId(equipmentId);
        equipmentMaintenance.setMaintenanceContent(maintenanceContent);
        equipmentMaintenance.setMaintenancePerson(maintenancePerson);
        equipmentMaintenance.setMaintenanceDate(maintenanceData);
        System.out.println(maintenanceData);
        //System.out.println(equipmentMaintenance.getMaintenanceDate());
        equipmentMaintenanceRepository.save(equipmentMaintenance);
        System.out.println(equipmentMaintenance.getId());
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
            if (equipmentMaintenanceRepository.findById(id)==null) {
                throw new Exception("ID:"+id+" does not exist");
            }
            EquipmentMaintenance equipmentMaintenance =equipmentMaintenanceRepository.findById(id);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentMaintenance));
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
        Iterable<EquipmentMaintenance> list=equipmentMaintenanceRepository.findAll();
      /*  List<Equipment> list1=new ArrayList<>();
        list.forEach(i->{list1.add(i);});*/
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<EquipmentMaintenance>) list).size(); i++)
        {
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<EquipmentMaintenance>) list).get(i)));
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
            @RequestParam (value="equipmentId",required=false)Long equipmentId,
            @RequestParam (value="maintenanceDate",required=false) Date maintenanceData,
            @RequestParam (value="maintenanceContent",required=false)String maintenanceContent,
            @RequestParam (value="maintenancePerson",required=false)String maintenancePerson,
            @RequestParam (value="confirmer",required=false)String confirmer){
        Response response=new Response();
        try{
            if (equipmentMaintenanceRepository.findById(id)==null) {
                System.out.println("111111111111111");
                throw new Exception("Equipment ID:" + id + " doesn't exist");
            }
            EquipmentMaintenance equipmentMaintenance =equipmentMaintenanceRepository.findById(id);
            equipmentMaintenance.setConfirmer(confirmer);
            equipmentMaintenance.setEquipmentId(equipmentId);
            equipmentMaintenance.setMaintenanceContent(maintenanceContent);
            equipmentMaintenance.setMaintenancePerson(maintenancePerson);
            equipmentMaintenance.setMaintenanceDate(maintenanceData);
           // JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentMaintenance));
            equipmentMaintenanceRepository.save(equipmentMaintenance);
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
            if(equipmentMaintenanceRepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            equipmentMaintenanceRepository.deleteById(id);
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

        equipmentMaintenanceRepository.deleteAll();
        response.data=null;
        response.msg="成功";
        response.code=200;
        return response;
    }
    @RequestMapping(value="/getAllByEquipmentId/{equipmentId}",method = RequestMethod.GET)
    @ResponseBody
    public Response getAllByEquipmentId(@PathVariable("equipmentId") Long id)
    {
        System.out.println(id);
        Response response=new Response();
        try{
            JSONObject alljson=new JSONObject();
            Iterable<EquipmentMaintenance> list=equipmentMaintenanceRepository.findAll();
            JSONArray jsons=new JSONArray();
            int count=0;
            for(int i = 0; i<((List<EquipmentMaintenance>) list).size(); i++)
            {
                System.out.println(((List<EquipmentMaintenance>) list).get(i).getEquipmentId());
                System.out.println(((List<EquipmentMaintenance>) list).get(i).getEquipmentId()-id);

                if(((List<EquipmentMaintenance>) list).get(i).getEquipmentId()-id==0) {
                    System.out.println("in");
                    JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<EquipmentMaintenance>) list).get(i)));
                    jsons.add(ejson);
                    count++;
                }
            }
            if(count==0)
                throw new Exception("设备维护记录不存在");
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
