package com.example.demo.Controller;

import com.example.demo.Model.EquipmentReceive;
import com.example.demo.Repository.EquipmentReceiveReporsitory;
import com.example.demo.framework.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;



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
    private int attachment_Id=0;

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
                System.out.println("finfbyid1:"+id);
                throw new Exception("ID:"+id+" does not exist");
            }
            System.out.println("finfbyid2:"+id);
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
   /* @RequestMapping(path="/Attachment/{id}",method=RequestMethod.POST)
    @ResponseBody
    public Response addAttachment(
            @PathVariable("id") long id,
            MultipartFile attachment
    ){
        Response response=new Response();
        JSONObject js=new JSONObject();
        try{
            if(ERRepository.findById(id)==null)
                throw new Exception("EquipmentReceive ID:"+id+" doesn't exist");
            EquipmentReceive equipmentReceive=ERRepository.findById(id);
            equipmentReceive.setAttachment(MfiletoFile(attachment));
            String name=attachment.getName();
            long receivedId=id;
            js.put("attachmentid",attachment_Id++);
            js.put("name",name);
            js.put("receivedId",receivedId);
            response.data=js;
            response.msg="成功";
            response.code=200;

        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }*/
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
    @ResponseBody
    public Response deleteAll()
    {
        Response response=new Response();

        ERRepository.deleteAll();
        response.data=null;
        response.msg="成功";
        response.code=200;
        return response;
    }
   /* public File MfiletoFile(MultipartFile multipartFile)throws Exception{
        String path = "F:\\Attachment\\";
        File file = new File(path,"demo.txt");
        multipartFile.transferTo(file);
        // 读取文件第一行
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.println(bufferedReader.readLine());
        // 输出绝对路径
        System.out.println(file.getAbsolutePath());
        bufferedReader.close();
        // 操作完上的文件 需要删除在根目录下生成的文件
        if (file.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");

        }
        return file;
    }*/
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}