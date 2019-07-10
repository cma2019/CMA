package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.CapacityVerificationRecord;
import com.example.demo.Repository.CapacityVerificationRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;

/**
 * @author lyt
 * 返回code   200→成功
 *            5xx→异常
 */
@Controller
@RequestMapping(path="/cma/CapacityVerification")//定义接口
public class CapacityVerificationRecordController {
    @Autowired
    private CapacityVerificationRecordRepository CapacityVerificationRecordRepository;

    @PostMapping(path = "/addOneRecord")//添加记录
    public @ResponseBody JSONObject addRecord(@RequestParam(value = "projectId", required = false) Long projectId,
                         @RequestParam(value = "date", required = false) Date date,
                         @RequestParam(value = "methodId", required = false) String methodId,
                         @RequestParam(value = "equipmentName", required = false) String equipmentName,
                         @RequestParam(value = "equipmentId", required = false) String equipmentId,
                         @RequestParam(value = "experimenter", required = false) String experimenter,
                         @RequestParam(value = "result", required = false) String result,
                         @RequestParam(value = "resultDeal", required = false) String resultDeal,
                         @RequestParam(value = "note", required = false) String note){
        CapacityVerificationRecord newRecord = new CapacityVerificationRecord();
        newRecord.setprojectId(projectId);
        newRecord.setDate(date);
        newRecord.setMethodId(methodId);
        newRecord.setEquipmentName(equipmentName);
        newRecord.setEquipmentId(equipmentId);
        newRecord.setExperimenter(experimenter);
        newRecord.setResult(result);
        newRecord.setResultDeal(resultDeal);
        newRecord.setNote(note);
        CapacityVerificationRecordRepository.save(newRecord);//保存记录

        JSONObject json = new JSONObject(new LinkedHashMap());
        try {
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    @PostMapping(path="/deleteOneRecord")//删除记录
    public @ResponseBody JSONObject deleteRecord(@RequestParam(value="id",required=false)Long recordId){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!CapacityVerificationRecordRepository.findById(recordId).isPresent())//是否为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            CapacityVerificationRecordRepository.deleteById(recordId);//删除
            try{
                json.put("code",200);
                json.put("msg","删除成功");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return json;
    }

    @PostMapping(path="/modifyOneRecord")//修改记录
    public @ResponseBody JSONObject modifyRecord(@RequestParam(value="id",required=false)Long recordId,
                                                  @RequestParam(value="projectId",required = false)Long projectId,
                                                  @RequestParam(value="date",required=false)Date date,
                                                  @RequestParam(value="methodId",required=false)String methodId,
                                                  @RequestParam(value="equipmentName",required=false)String equipmentName,
                                                  @RequestParam(value="equipmentId",required=false)String equipmentId,
                                                  @RequestParam(value="experimenter",required=false)String experimenter,
                                                  @RequestParam(value="result",required=false)String result,
                                                  @RequestParam(value="resultDeal",required=false)String resultDeal,
                                                  @RequestParam(value="note",required=false)String note){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!CapacityVerificationRecordRepository.findById(recordId).isPresent())//是否为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
//修改信息
            CapacityVerificationRecordRepository.updateById(recordId,projectId,date,methodId,equipmentName,equipmentId,experimenter,result,resultDeal,note);
            //content,checkDate,personInCharge,state
            //System.out.println("changed object");
            try{
                json.put("code",200);
                json.put("msg","修改成功");
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

        return json;
    }

    @GetMapping(path="/getOneRecord")//获取单个记录
    public @ResponseBody JSONObject getOneRecord(@RequestParam(value="id",required=false)Long recordId)throws IOException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationRecord record=new CapacityVerificationRecord();
        if(!CapacityVerificationRecordRepository.findById(recordId).isPresent())//是否为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            json.put("code",200);
            json.put("msg","获取成功");
            json.put("data",CapacityVerificationRecordRepository.findById(recordId));//返回信息
            /*record= CapacityVerificationRecordRepository.getOne(recordId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("recordId",record.getrecordId());
                data.put("projectId",record.getprojectId());
                data.put("date",record.getDate());
                data.put("methodId",record.getMethodID());
                data.put("equipmentName",record.getEquipmentName());
                data.put("equipmentId",record.getEquipmentId());
                data.put("experimenter",record.getExperimenter());
                data.put("result",record.getResult());
                data.put("resultDeal",record.getResultDeal());
                data.put("note",record.getNote());
            }catch (JSONException e){
                e.printStackTrace();
            }
            //array.put(data);
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",data);
            }catch (JSONException e){
                e.printStackTrace();
            }*/

        }

        return json;
    }

    @GetMapping(path="/getRecordByProjectId")//获得对应记录
    public @ResponseBody JSONObject getRecordByProjectId(@RequestParam(value="projectId",required=false)Long projectId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationRecord record=new CapacityVerificationRecord();
        /*if(CapacityVerificationRecordRepository.findAllByProjectId(projectId)==null)
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {*/
            json.put("code",200);
            json.put("msg","获取成功");
            json.put("data",CapacityVerificationRecordRepository.findAllByProjectId(projectId));//返回信息
            /*record= CapacityVerificationRecordRepository.findByProjectId(projectId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("recordId",record.getrecordId());
                data.put("projectId",record.getprojectId());
                data.put("date",record.getDate());
                data.put("methodId",record.getMethodID());
                data.put("equipmentName",record.getEquipmentName());
                data.put("equipmentId",record.getEquipmentId());
                data.put("experimenter",record.getExperimenter());
                data.put("result",record.getResult());
                data.put("resultDeal",record.getResultDeal());
                data.put("note",record.getNote());
            }catch (JSONException e){
                e.printStackTrace();
            }
            //array.put(data);
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",data);
            }catch (JSONException e){
                e.printStackTrace();
            }*/

        //}
        return json;
    }

}
