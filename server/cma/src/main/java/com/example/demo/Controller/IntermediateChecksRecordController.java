package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import com.example.demo.Model.IntermediateChecksRecord;
import com.example.demo.Repository.IntermediateChecksRecordRepository;

/**
 * @author lyt
 *
 */
@Controller//Controller
@RequestMapping(path="/cma/IntermediateChecksRecord")//接口定义
public class IntermediateChecksRecordController {
    @Autowired
    private IntermediateChecksRecordRepository IntermediateChecksRecordRepository;

    @GetMapping(path="/getAll")//获取所有信息
    /*public @ResponseBody String getAll(){
        return "Hello,World";*/
    public @ResponseBody JSONObject getAllRecord(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        /*if(IntermediateChecksRecordRepository.findAll().isEmpty())
        {

                json.put("code",100);
                json.put("msg","查找不到");
        }
        else
        {*///不判断为空理由见IntermediateChecksPlanController的getAll方法w

                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",IntermediateChecksRecordRepository.findAll());


            /*List<IntermediateChecksRecord> recordList= IntermediateChecksRecordRepository.findAll();
            JSONObject data=new JSONObject();
            JSONArray array=new JSONArray();
            for(IntermediateChecksRecord record:recordList){
                JSONObject singlePlan=new JSONObject();
                //JSONObject array=new JSONObject();
                singlePlan.put("recordId",record.getRecordId());
                singlePlan.put("planId",record.getPlanId());
                singlePlan.put("object",record.getObject());
                singlePlan.put("checkDate",record.getCheckDate());
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
            json.put("data",array);*/
        //}
        //response.setContentType("text/html;charset=utf-8");
        //response.getWriter().write(json.toString());
        //System.out.println(json);
        //return json.toString();
        return json;
    }

    @GetMapping(path="/getOneByRecordId")//获取单个信息
    public @ResponseBody JSONObject getOneByRecordId(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam(value="recordId",required=false)Long recordId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        IntermediateChecksRecord record=new IntermediateChecksRecord();
        if(!IntermediateChecksRecordRepository.findById(recordId).isPresent())//判断是否为空
        {
                json.put("code",100);
                json.put("msg","查找不到");

        }
        else
        {
            /*record= IntermediateChecksRecordRepository.getOne(recordId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
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

            array.put(data);*/
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",IntermediateChecksRecordRepository.findById(recordId));


        }
        //response.setContentType("text/html;charset=utf-8");
        //response.getWriter().write(json.toString());
        return json;
    }



    @PostMapping(path = "/addOne")//添加信息
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
        JSONObject json = new JSONObject(new LinkedHashMap());
        if(IntermediateChecksRecordRepository.findByPlanId(planId).isPresent())//判断是否已存在plan对应的记录，plan与record一对一
        {

                json.put("code", 300);
                json.put("msg", "存在记录");

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json.toString());
            return;
        }
        newRecord.setPlanId(planId);
        newRecord.setObject(object);
        newRecord.setCheckDate(checkDate);
        newRecord.setProcessRecord(processRecord);
        newRecord.setProcessRecordPerson(processRecordPerson);
        newRecord.setProcessRecordDate(processRecordDate);
        newRecord.setResultRecord(resultRecord);
        newRecord.setResultRecordPerson(resultRecordPerson);
        newRecord.setResultRecordDate(resultRecordDate);
        newRecord.setNote(note);
        IntermediateChecksRecordRepository.save(newRecord);//保存record,记录id自动生成



            json.put("code", 200);
            json.put("msg", "添加成功");

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
    }

    @PostMapping(path="/deleteOne")//删除记录
    public @ResponseBody void deleteRecord(HttpServletRequest request,HttpServletResponse response,
                                         @RequestParam(value="recordId",required=false)Long recordId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!IntermediateChecksRecordRepository.findById(recordId).isPresent())//判断是否为空
        {
                json.put("code",100);
                json.put("msg","查找不到");

        }
        else
        {
            IntermediateChecksRecordRepository.deleteById(recordId);

                json.put("code",200);
                json.put("msg","删除成功");

        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //return json;
    }

    @PostMapping(path="/modifyOne")//修改信息
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
        if(!IntermediateChecksRecordRepository.findById(recordId).isPresent())//判断是否为空
        {

                json.put("code",100);
                json.put("msg","查找不到");

        }
        else
        {
            IntermediateChecksRecordRepository.updateById(recordId,planId,object,checkDate,processRecord,processRecordPerson,processRecordDate,resultRecord,resultRecordPerson,resultRecordDate,note);
            //content,checkDate,personInCharge,state
            //System.out.println("changed object");

                json.put("code",200);
                json.put("msg","修改成功");


        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        //return json;
    }

    @GetMapping(path="/getOneByPlanId")//获取对应plan的record
    public @ResponseBody JSONObject getOneByPlanId(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam(value="planId",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        IntermediateChecksRecord record=new IntermediateChecksRecord();
        if(!IntermediateChecksRecordRepository.findByPlanId(planId).isPresent())//判断是否为空
        {
                json.put("code",100);
                json.put("msg","查找不到");

        }
        else
        {
            json.put("code",200);
            json.put("msg","获取成功");
            json.put("data",IntermediateChecksRecordRepository.findByPlanId(planId));
            /*record= IntermediateChecksRecordRepository.findOnePlanId(planId);
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
            }*/

        }
        //response.setContentType("text/html;charset=utf-8");
        //response.getWriter().write(json.toString());
        return json;
    }
}
