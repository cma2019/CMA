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
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.Model.IntermediateChecksPlan;
import com.example.demo.Repository.IntermediateChecksPlanRepository;

@Controller//Controller
@RequestMapping(path="/cma/IntermediateChecksPlan")//接口定义
public class IntermediateChecksPlanController {
    @Autowired
    private IntermediateChecksPlanRepository IntermediateChecksPlanRepository;

    @PostMapping(path="/addOne")//添加计划
    public @ResponseBody void addPlan(HttpServletRequest request,HttpServletResponse response,
                                      @RequestParam(value="object",required=false)String object,
                                      @RequestParam(value="content",required=false)String content,
                                      @RequestParam(value="checkDate",required=false) Date checkDate,
                                      @RequestParam(value="personInCharge",required=false)String personInCharge)throws IOException{
        IntermediateChecksPlan newPlan=new IntermediateChecksPlan();
        newPlan.setObject(object);
        newPlan.setContent(content);
        newPlan.setCheckDate(checkDate);
        newPlan.setPersonInCharge(personInCharge);
        newPlan.setState(0);
        IntermediateChecksPlanRepository.save(newPlan);//以上，保存完成，数据库自动生成planId

        JSONObject json=new JSONObject(new LinkedHashMap());
        try{
            json.put("code",200);
            json.put("msg","添加成功");
        }catch (JSONException e){
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());//可用其他JSONObject包（参考CapacityVerification)直接返回JSONObject格式数据，此处返回json式样的字符串
        /*System.out.println("json:"+json);
        System.out.println("------");*/
        //return "Add Success";
        //return json;//←直接返回JSONObject，前端同样收到json数据
    }

    @PostMapping(path="/deleteOne")//删除计划
    public @ResponseBody void deletePlan(HttpServletRequest request,HttpServletResponse response,
                                               @RequestParam(value="planId",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!IntermediateChecksPlanRepository.findById(planId).isPresent())//判断是否存在
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
            IntermediateChecksPlanRepository.deleteRecord(planId);//删除对应record
            IntermediateChecksPlanRepository.deleteById(planId);//删除plan
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

    @PostMapping(path="/modifyOne")//修改计划信息
    public @ResponseBody void modifyPlan(HttpServletRequest request,HttpServletResponse response,
                                         @RequestParam(value="planId",required=false)Long planId,
                                         @RequestParam(value="object",required=false)String object,
                                         @RequestParam(value="content",required=false)String content,
                                         @RequestParam(value="checkDate",required=false)Date checkDate,
                                         @RequestParam(value="personInCharge",required=false)String personInCharge,
                                         @RequestParam(value="state",required=false)byte state)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!IntermediateChecksPlanRepository.findById(planId).isPresent())//判断是否存在，虽然前端似乎是无法传不存在planId到后端的，安全起见加上判断。
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
            /*if(object!=IntermediateChecksPlanRepository.getOne(planId).getObject())
                IntermediateChecksPlanRepository.updateobjectById(planId,object);
            if(content!=IntermediateChecksPlanRepository.getOne(planId).getContent())
                IntermediateChecksPlanRepository.updatecontentById(planId,content);
            if(checkDate!=IntermediateChecksPlanRepository.getOne(planId).getDate())
                IntermediateChecksPlanRepository.updateDateById(planId,checkDate);//
            if(personInCharge!=IntermediateChecksPlanRepository.getOne(planId).getPersonInCharge())
                IntermediateChecksPlanRepository.updatePersonById(planId,personInCharge);
            if(state!=IntermediateChecksPlanRepository.getOne(planId).getState())
                IntermediateChecksPlanRepository.updateStateById(planId,state);*/
                IntermediateChecksPlanRepository.updateById(planId,object,content,checkDate,personInCharge,state);//似乎可以用JpaRepository的save方法直接修改
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
    @GetMapping(path="/getOne")//返回单个计划信息
    public @ResponseBody void getOne(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam(value="planId",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        IntermediateChecksPlan plan=new IntermediateChecksPlan();
        if(!IntermediateChecksPlanRepository.findById(planId).isPresent())//判断是否存在
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
            plan= IntermediateChecksPlanRepository.getOne(planId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("planId",plan.getPlanId());
                data.put("object",plan.getObject());
                data.put("content",plan.getContent());
                data.put("checkDate",plan.getCheckDate());
                data.put("personInCharge",plan.getPersonInCharge());
                data.put("state",plan.getState());
            }catch (JSONException e){
                e.printStackTrace();
            }
            array.put(data);
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",array);//可以直接在data标签下传入通过Repository的find方法找到的数据
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
        /*if(IntermediateChecksPlanRepository.findAll().isEmpty())
        {
            try{
                json.put("code",100);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {*///此处不必判断是否为空，否则可能导致前端出问题。数据库为空时返回的数据也为空。
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                //json.put("data:",IntermediateChecksPlanRepository.findAll());
            }catch (JSONException e){
                e.printStackTrace();
            }

            List<IntermediateChecksPlan> planList= IntermediateChecksPlanRepository.findAll();
            JSONObject data=new JSONObject();
            JSONArray array=new JSONArray();
            for(IntermediateChecksPlan plan:planList){
                JSONObject singlePlan=new JSONObject();
                //JSONObject array=new JSONObject();
                singlePlan.put("planId",plan.getPlanId());
                singlePlan.put("object",plan.getObject());
                singlePlan.put("content",plan.getContent());
                singlePlan.put("checkDate",plan.getCheckDate());
                singlePlan.put("personInCharge",plan.getPersonInCharge());
                singlePlan.put("state",plan.getState());
                array.put(singlePlan);
                //System.out.println(array);

            }
            json.put("data",array);//可以在data标签下直接返回JpaRepository的findAll方法的数据
        //}
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

