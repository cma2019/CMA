package com.example.demo.Controller;

import com.example.demo.Model.EquipmentReceive;
import com.example.demo.Repository.EquipmentReceiveReporsitory;
import com.example.demo.framework.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * @author yxp
 *  code 200表示成功
 *  code 5xx表示失败
 *  装备信息管理
 */

@Controller
@RequestMapping(path="/cma/EquipmentReceive")
public class EquipmentReceiveController {
    @Autowired
    private EquipmentReceiveReporsitory ERRepository;


    @RequestMapping(path="add",method=RequestMethod.POST)
    @ResponseBody
    public Response addEquipment(
            @RequestParam (value="name",required=false)String name,
            @RequestParam (value="model",required=false)String model,
            @RequestParam (value="manufacturer",required=false)String manufacturer,
            @RequestParam (value="receiveSituation",required=false)String receiveSituation,
            @RequestParam (value="receiveDate",required=false) Date receiveDate,
            @RequestParam (value="equipmentSituation",required=false)String equipmentSituation,
            @RequestParam (value="acceptance",required=false)String acceptance,
            @RequestParam (value="acceptancePerson",required=false)String  acceptancePerson,
            @RequestParam (value="acceptanceDate",required=false)Date  acceptanceDate,
            @RequestParam (value="recipient",required=false)String  recipient
            ){
        Response response=new Response();
        EquipmentReceive equipment = new EquipmentReceive();
        equipment.setName(name);
        equipment.setModel(model);
        equipment.setAcceptance(acceptance);
        equipment.setAcceptanceDate(acceptanceDate);
        equipment.setAcceptancePerson(acceptancePerson);
        equipment.setManufacturer(manufacturer);
        equipment.setReceiveSituation(receiveSituation);
        equipment.setReceiveDate(receiveDate);
        equipment.setRecipient(recipient);
        equipment.setEquipmentSituation(equipmentSituation);

        ERRepository.save(equipment);
        System.out.println(equipment.getId());
        // JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipment));
        response.code=200;
        response.data=null;
        response.setMessage("成功");
        return response;
    }

    /**
     * @param id
     * @return 返回id对应的装备
     *
     */
    @RequestMapping(value="/getOne/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Response getOne(@PathVariable("id") long id){
        Response response=new Response();
        System.out.println("finfbyid:"+id);
        try {
            if (ERRepository.findById(id) == null) {
                throw new Exception("ID:"+id+" does not exist");
            }
            EquipmentReceive equipmentReceive = ERRepository.findById(id);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentReceive));
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
        Iterable<EquipmentReceive> list=ERRepository.findAll();
      /*  List<Equipment> list1=new ArrayList<>();
        list.forEach(i->{list1.add(i);});*/
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<EquipmentReceive>) list).size(); i++)
        {
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<EquipmentReceive>) list).get(i)));
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
            @PathVariable("id")long id,
            @RequestParam (value="name",required=false)String name,
            @RequestParam (value="model",required=false)String model,
            @RequestParam (value="manufacturer",required=false)String manufacturer,
            @RequestParam (value="receiveSituation",required=false)String receiveSituation,
            @RequestParam (value="receiveDate",required=false) Date receiveDate,
            @RequestParam (value="equipmentSituation",required=false)String equipmentSituation,
            @RequestParam (value="acceptance",required=false)String acceptance,
            @RequestParam (value="acceptancePerson",required=false)String  acceptancePerson,
            @RequestParam (value="acceptanceDate",required=false)Date  acceptanceDate,
            @RequestParam (value="recipient",required=false)String  recipient
            ){
        Response response=new Response();
        try{
            if(ERRepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            EquipmentReceive equipmentReceive=ERRepository.findById(id);
            equipmentReceive.setReceiveSituation(receiveSituation);
            equipmentReceive.setReceiveDate(receiveDate);
            equipmentReceive.setManufacturer(manufacturer);
            equipmentReceive.setAcceptancePerson(acceptancePerson);
            equipmentReceive.setName(name);
            equipmentReceive.setModel(model);
            equipmentReceive.setEquipmentSituation(equipmentSituation);
            equipmentReceive.setAcceptance(acceptance);
            equipmentReceive.setAcceptanceDate(acceptanceDate);
            equipmentReceive.setRecipient(recipient);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipmentReceive));
            ERRepository.save(equipmentReceive);
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

    /**
     * @param id
     * @return code,msg,data=null
     */
    @RequestMapping(value="/deleteOne/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response deleteOne(@PathVariable("id") long id)
    {
        Response response=new Response();
        try{
            if(ERRepository.findById(id)==null)
                throw new Exception("Equipment ID:"+id+" doesn't exist");
            ERRepository.deleteById(id);
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