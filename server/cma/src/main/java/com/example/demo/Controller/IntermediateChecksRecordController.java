package com.example.demo.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import com.example.cma.IntermediateChecksRecord;
@Controller//Controller
@RequestMapping(path="/cma/IntermediateChecksRecord")
public class IntermediateChecksRecordController {
    @Autowired
    private RecordRepository RecordRepository;

    @GetMapping(path="/getAll")
    /*public @ResponseBody String getAll(){
        return "Hello,World";*/
    public @ResponseBody void getAllRecord(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(RecordRepository.findAll()==null)
        {
            try{
                json.put("code",100);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                //json.put("data:",PlanRepository.findAll());
            }catch (JSONException e){
                e.printStackTrace();
            }

            List<IntermediateChecksRecord> recordList=RecordRepository.findAll();
            JSONObject data=new JSONObject();
            JSONArray array=new JSONArray();
            for(IntermediateChecksRecord record:recordList){
                JSONObject singlePlan=new JSONObject();
                //JSONObject array=new JSONObject();
                singlePlan.put("recordId",record.getrecordId());
                singlePlan.put("planId",record.getplanID());
                singlePlan.put("object",record.getObject());
                singlePlan.put("checkDate",record.getDate());
                singlePlan.put("processRecord",record.getProcessRecord());
                singlePlan.put("processRecordPerson",record.getProcessRecordPerson());
                singlePlan.put("processRecordDate",record.getProcessRecordDate());
                singlePlan.put("resultRecord",record.getResultRecord());
                singlePlan.put("resultRecordPerson",record.getResultRecordPerson());
                singlePlan.put("resultRecordDate",record.getResultRecordDate());
                singlePlan.put("note",record.getNote());
                array.put(singlePlan);
                //System.out.println(array);

            }
            json.put("data",array);
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //System.out.println(json);
        //return json.toString();

    }

    @GetMapping(path="/getOneByRecordId")
    public @ResponseBody void getOneByRecordId(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam(value="recordId",required=false)Long recordId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        IntermediateChecksRecord record=new IntermediateChecksRecord();
        if(RecordRepository.findById(recordId)==null)
        {
            try{
                json.put("code",100);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            record=RecordRepository.getOne(recordId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("recordId",record.getrecordId());
                data.put("planId",record.getplanID());
                data.put("object",record.getObject());
                data.put("checkDate",record.getDate());
                data.put("processRecord",record.getProcessRecord());
                data.put("processRecordPerson",record.getProcessRecordPerson());
                data.put("processRecordDate",record.getProcessRecordDate());
                data.put("resultRecord",record.getResultRecord());
                data.put("resultRecordPerson",record.getResultRecordPerson());
                data.put("resultRecordDate",record.getResultRecordDate());
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
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //return json;
    }



    @PostMapping(path = "/addOne")
    public @ResponseBody
    void addRecord(HttpServletRequest request, HttpServletResponse response,
                 @RequestParam(value = "planId", required = false) Long planId,
                 @RequestParam(value = "object", required = false) String object,
                 @RequestParam(value = "checkDate", required = false) Date checkDate,
                 @RequestParam(value = "processRecord", required = false) String processRecord,
                 @RequestParam(value = "processRecordPerson", required = false) String processRecordPerson,
                 @RequestParam(value = "processRecordDate", required = false) Date processRecordDate,
                 @RequestParam(value = "resultRecord", required = false) String resultRecord,
                 @RequestParam(value = "resultRecordPerson", required = false) String resultRecordPerson,
                 @RequestParam(value = "resultRecordDate", required = false) Date resultRecordDate,
                 @RequestParam(value = "note", required = false) String note) throws IOException {
        IntermediateChecksRecord newRecord = new IntermediateChecksRecord();
        newRecord.setplanID(planId);
        newRecord.setObject(object);
        newRecord.setDate(checkDate);
        newRecord.setProcessRecord(processRecord);
        newRecord.setProcessRecordPerson(processRecordPerson);
        newRecord.setProcessRecordDate(processRecordDate);
        newRecord.setResultRecord(resultRecord);
        newRecord.setResultRecordPerson(resultRecordPerson);
        newRecord.setResultRecordDate(resultRecordDate);
        newRecord.setNote(note);
        RecordRepository.save(newRecord);

        JSONObject json = new JSONObject(new LinkedHashMap());
        try {
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
    }

    @PostMapping(path="/deleteOne")
    public @ResponseBody void deleteRecord(HttpServletRequest request,HttpServletResponse response,
                                         @RequestParam(value="recordId",required=false)Long recordId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(RecordRepository.findById(recordId)==null)
        {
            try{
                json.put("code",100);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            RecordRepository.deleteById(recordId);
            try{
                json.put("code",200);
                json.put("msg","删除成功");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //return json;
    }

    @PostMapping(path="/modifyOne")
    public @ResponseBody void modifyRecord(HttpServletRequest request,HttpServletResponse response,
                                         @RequestParam(value="recordId",required=false)Long recordId,
                                         @RequestParam(value = "planId", required = false) Long planId,
                                         @RequestParam(value = "object", required = false) String object,
                                         @RequestParam(value = "checkDate", required = false) Date checkDate,
                                         @RequestParam(value = "processRecord", required = false) String processRecord,
                                         @RequestParam(value = "processRecordPerson", required = false) String processRecordPerson,
                                         @RequestParam(value = "processRecordDate", required = false) Date processRecordDate,
                                         @RequestParam(value = "resultRecord", required = false) String resultRecord,
                                         @RequestParam(value = "resultRecordPerson", required = false) String resultRecordPerson,
                                         @RequestParam(value = "resultRecordDate", required = false) Date resultRecordDate,
                                         @RequestParam(value = "note", required = false) String note)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(RecordRepository.findById(recordId)==null)
        {
            try{
                json.put("code",100);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            RecordRepository.updateById(recordId,planId,object,checkDate,processRecord,processRecordPerson,processRecordDate,resultRecord,resultRecordPerson,resultRecordDate,note);
            //content,checkDate,personInCharge,state
            //System.out.println("changed object");
            try{
                json.put("code",200);
                json.put("msg","修改成功");
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //return json;
    }

    @GetMapping(path="/getOneByPlanId")
    public @ResponseBody void getOneByPlanId(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam(value="planId",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        IntermediateChecksRecord record=new IntermediateChecksRecord();
        if(RecordRepository.findByPlanId(planId)==null)
        {
            try{
                json.put("code",100);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            record=RecordRepository.findByPlanId(planId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("recordId",record.getrecordId());
                data.put("planId",record.getplanID());
                data.put("object",record.getObject());
                data.put("checkDate",record.getDate());
                data.put("processRecord",record.getProcessRecord());
                data.put("processRecordPerson",record.getProcessRecordPerson());
                data.put("processRecordDate",record.getProcessRecordDate());
                data.put("resultRecord",record.getResultRecord());
                data.put("resultRecordPerson",record.getResultRecordPerson());
                data.put("resultRecordDate",record.getResultRecordDate());
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
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //return json;
    }
}
