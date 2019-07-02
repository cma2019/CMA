package com.example.demo.Controller;

import com.example.demo.Model.CapacityVerificationRecord;
import com.example.demo.Repository.CapacityVerificationRecordRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
@RequestMapping(path="/cma/CapacityVerification")
public class CapacityVerificationRecordController {
    @Autowired
    private CapacityVerificationRecordRepository CapacityVerificationRecordRepository;

    @PostMapping(path = "/addOneRecord")
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
        newRecord.setprojectID(projectId);
        newRecord.setDate(date);
        newRecord.setMethodID(methodId);
        newRecord.setEquipmentName(equipmentName);
        newRecord.setEquipmentId(equipmentId);
        newRecord.setExperimenter(experimenter);
        newRecord.setResult(result);
        newRecord.setResultDeal(resultDeal);
        newRecord.setNote(note);
        CapacityVerificationRecordRepository.save(newRecord);

        JSONObject json = new JSONObject(new LinkedHashMap());
        try {
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    @PostMapping(path="/deleteOneRecord")
    public @ResponseBody JSONObject deleteRecord(@RequestParam(value="id",required=false)Long recordId){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(CapacityVerificationRecordRepository.findById(recordId)==null)
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
            CapacityVerificationRecordRepository.deleteById(recordId);
            try{
                json.put("code",200);
                json.put("msg","删除成功");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return json;
    }

    @PostMapping(path="/modifyOneRecord")
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
        if(CapacityVerificationRecordRepository.findById(recordId)==null)
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

    @GetMapping(path="/getOneRecord")
    public @ResponseBody JSONObject getOneRecord(@RequestParam(value="id",required=false)Long recordId)throws IOException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationRecord record=new CapacityVerificationRecord();
        if(CapacityVerificationRecordRepository.findById(recordId)==null)
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
            record= CapacityVerificationRecordRepository.getOne(recordId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("recordId",record.getrecordID());
                data.put("projectId",record.getprojectID());
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
            array.put(data);
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",array);
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

        return json;
    }

    @GetMapping(path="/getRecordByProjectId")
    public @ResponseBody JSONObject getOneByPlanId(@RequestParam(value="projectId",required=false)Long projectId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationRecord record=new CapacityVerificationRecord();
        if(CapacityVerificationRecordRepository.findByProjectId(projectId)==null)
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
            record= CapacityVerificationRecordRepository.findByProjectId(projectId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("recordId",record.getrecordID());
                data.put("projectId",record.getprojectID());
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
            array.put(data);
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",array);
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return json;
    }

}
