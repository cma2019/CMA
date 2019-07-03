package com.example.demo.Controller;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.Repository.SupervisionPlanRepository;
import com.example.demo.Model.SupervisionPlan;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SupervisionPlan")
public class SupervisionPlanController {
    @Autowired
    private SupervisionPlanRepository SupervisionPlanRepository;
    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject getAll(@RequestParam(value = "id",required = false) String id){
        List<SupervisionPlan> res=SupervisionPlanRepository.findAllById(Long.parseLong(id));
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("planId",res.get(i).getPlanID());
                tmp.put("id",id);
                tmp.put("content",res.get(i).getContent());
                tmp.put("object",res.get(i).getObject());
                tmp.put("dateFrequency",res.get(i).getDateFrequency());
                data.add(tmp);
            }
        }
        else
        {
            code=522;
            msg="数据不存在";
            //data=null;
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path="/addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "id",required = false) String id,
                                           @RequestParam(value = "content",required = false) String content,
                                           @RequestParam(value = "object",required = false) String object,
                                           @RequestParam(value = "dateFrequency",required = false) String dateFrequency)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        //JSONObject data=null;
        try{
            Long.parseLong(id);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",null);
            return js;
        }
        SupervisionPlan sp=new SupervisionPlan();
        sp.setObject(object);
        sp.setDateFrequency(dateFrequency);
        sp.setContent(content);
        sp.setId(Long.parseLong(id));
        SupervisionPlanRepository.save(sp);
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "planId",required = false) String planId,
                                              @RequestParam(value = "content",required = false) String content,
                                              @RequestParam(value = "object",required = false) String object,
                                              @RequestParam(value = "dateFrequency",required = false) String dateFrequency)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        //JSONObject data=null;
        SupervisionPlan sp=SupervisionPlanRepository.findByPlanID(Long.parseLong(planId));
        sp.setContent(content);
        sp.setDateFrequency(dateFrequency);
        sp.setObject(object);
        SupervisionPlanRepository.saveAndFlush(sp);
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "planId",required = false) String planId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=null;
        SupervisionPlanRepository.deleteById(Long.parseLong(planId));
        json.put("code",code);
        json.put("msg",msg);
        //json.put("data",data);
        return json;
    }
}
