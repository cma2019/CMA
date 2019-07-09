package com.example.demo.Controller;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.example.demo.Model.SupervisionRecord;
import com.example.demo.Repository.SupervisionRecordRepository;
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
//import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SupervisionPlan")
public class SupervisionPlanController {
    @Autowired
    private SupervisionPlanRepository SupervisionPlanRepository;
    @Autowired
    private SupervisionRecordRepository SupervisionRecordRepository;
    @GetMapping(path="/getAll")
    //获取监督计划列表
    public @ResponseBody JSONObject getAll(@RequestParam(value = "id",required = false) String id)
    {
        List<SupervisionPlan> res=SupervisionPlanRepository.findAllById(Long.parseLong(id));
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        //遍历列表，将必要信息存放在data
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
        //列表为空
        else
        {
            code=210;
            msg="无有效信息返回";
            //data=null;
        }
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path="/addOne")
    //为一项监督添加监督计划
    public @ResponseBody JSONObject addOne(@RequestParam(value = "id",required = false) String id,
                                           @RequestParam(value = "content",required = false) String content,
                                           @RequestParam(value = "object",required = false) String object,
                                           @RequestParam(value = "dateFrequency",required = false) String dateFrequency)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //确保参数合法
        try{
            Long.parseLong(id);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //确保参数非空
        if(content.equals("")||object.equals("")
        ||dateFrequency.equals(""))
        {
            code=511;
            msg="缺少参数";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //正常新增
        SupervisionPlan sp=new SupervisionPlan();
        sp.setObject(object);
        sp.setDateFrequency(dateFrequency);
        sp.setContent(content);
        sp.setId(Long.parseLong(id));
        SupervisionPlanRepository.save(sp);
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping(path="/modifyOne")
   //修改一项监督计划
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "planId",required = false) String planId,
                                              @RequestParam(value = "content",required = false) String content,
                                              @RequestParam(value = "object",required = false) String object,
                                              @RequestParam(value = "dateFrequency",required = false) String dateFrequency)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        SupervisionPlan sp=SupervisionPlanRepository.findByPlanID(Long.parseLong(planId));
        sp.setContent(content);
        sp.setDateFrequency(dateFrequency);
        sp.setObject(object);
        SupervisionPlanRepository.saveAndFlush(sp);
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path = "deleteOne")
   //删除一项监督计划
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "planId",required = false) String planId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        //确保参数合法
        try {
            Long.parseLong(planId);
        }catch (NumberFormatException E){
            json.put("code",500);
            json.put("msg","缺少请求参数");
            json.put("data",null);
        }
        JSONObject data=new JSONObject();
        //获取某项监督计划下的监督记录列表，逐项删除
        List<SupervisionRecord>res=SupervisionRecordRepository.findAllByPlanId(Long.parseLong(planId));
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                SupervisionRecordRepository.delete(res.get(i));
            }
        }
        //删除监督计划
        SupervisionPlanRepository.deleteById(Long.parseLong(planId));
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
}
//select * from supervision_plan;
//alter table supervision_plan ENGINE =InnoDB;
