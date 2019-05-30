/*2019/5/22记 期间核查计划后端代码
* 返回JSONObject时报错No converter found for return value of type: class org.json.JSONObject
* 在添加jackson包后依然无法解决，留疑
* 现暂用response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        * 来代替return json(JSONObject)
        *
        * alibaba.fastjson
*by 刘宇涛*/


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
import java.util.Map;
import java.util.Optional;
import com.example.cma.IntermediateChecksPlan;

@Controller//Controller
@RequestMapping(path="/cma/IntermediateChecksPlan")
public class IntermediateChecksPlanController {
    @Autowired
    private PlanRepository PlanRepository;

    @PostMapping(path="/addOne")
    public @ResponseBody void addPlan(HttpServletRequest request,HttpServletResponse response,
                                      @RequestParam(value="object",required=false)String object,
                                      @RequestParam(value="content",required=false)String content,
                                      @RequestParam(value="checkDate",required=false) Date checkDate,
                                      @RequestParam(value="personInCharge",required=false)String personInCharge)throws IOException{
        IntermediateChecksPlan newPlan=new IntermediateChecksPlan();
        newPlan.setObject(object);
        newPlan.setContent(content);
        newPlan.setDate(checkDate);
        newPlan.setPersonInCharge(personInCharge);
        newPlan.setState(0);
        PlanRepository.save(newPlan);

        JSONObject json=new JSONObject(new LinkedHashMap());
        try{
            json.put("code",200);
            json.put("msg","添加成功");
        }catch (JSONException e){
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        /*System.out.println("json:"+json);
        System.out.println("------");*/
        //return "Add Success";
        //return json;
    }

    @PostMapping(path="/deleteOne")
    public @ResponseBody void deletePlan(HttpServletRequest request,HttpServletResponse response,
                                               @RequestParam(value="planId",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(PlanRepository.findById(planId)==null)
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
            PlanRepository.deleteById(planId);
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
    public @ResponseBody void modifyPlan(HttpServletRequest request,HttpServletResponse response,
                                         @RequestParam(value="planId",required=false)Long planId,
                                         @RequestParam(value="object",required=false)String object,
                                         @RequestParam(value="content",required=false)String content,
                                         @RequestParam(value="checkDate",required=false)Date checkDate,
                                         @RequestParam(value="personInCharge",required=false)String personInCharge,
                                         @RequestParam(value="state",required=false)byte state)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(PlanRepository.findById(planId)==null)
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
            /*if(object!=PlanRepository.getOne(planId).getObject())
                PlanRepository.updateobjectById(planId,object);
            if(content!=PlanRepository.getOne(planId).getContent())
                PlanRepository.updatecontentById(planId,content);
            if(checkDate!=PlanRepository.getOne(planId).getDate())
                PlanRepository.updateDateById(planId,checkDate);//
            if(personInCharge!=PlanRepository.getOne(planId).getPersonInCharge())
                PlanRepository.updatePersonById(planId,personInCharge);
            if(state!=PlanRepository.getOne(planId).getState())
                PlanRepository.updateStateById(planId,state);*/
                PlanRepository.updateById(planId,object,content,checkDate,personInCharge,state);
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
    @GetMapping(path="/getOne")
    public @ResponseBody void getOne(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam(value="planId",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        IntermediateChecksPlan plan=new IntermediateChecksPlan();
        if(PlanRepository.findById(planId)==null)
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
            plan=PlanRepository.getOne(planId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("planId",plan.getID());
                data.put("object",plan.getObject());
                data.put("content",plan.getContent());
                data.put("checkDate",plan.getDate());
                data.put("personInCharge",plan.getPersonInCharge());
                data.put("state",plan.getState());
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

    @GetMapping(path="/getAll")
    /*public @ResponseBody String getAll(){
        return "Hello,World";*/
    public @ResponseBody void getAll(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(PlanRepository.findAll()==null)
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

            List<IntermediateChecksPlan> planList=PlanRepository.findAll();
            JSONObject data=new JSONObject();
            JSONArray array=new JSONArray();
            for(IntermediateChecksPlan plan:planList){
                JSONObject singlePlan=new JSONObject();
                //JSONObject array=new JSONObject();
                singlePlan.put("planId",plan.getID());
                singlePlan.put("object",plan.getObject());
                singlePlan.put("content",plan.getContent());
                singlePlan.put("checkDate",plan.getDate());
                singlePlan.put("personInCharge",plan.getPersonInCharge());
                singlePlan.put("state",plan.getState());
                array.put(singlePlan);
                //System.out.println(array);

            }
            json.put("data",array);
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());
        System.out.println(json);
        //return json.toString();

    }

    /*@GetMapping(path="/jsontest")
    public @ResponseBody void jsontest(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException {
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","输入S");


        //return json;
      //return json.toString();
        //response.setContentType("text/html;charset=utf-8");
        //response.getWriter().write(json.toString());
    }*/
}

