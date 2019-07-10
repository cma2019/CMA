package com.example.demo.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.Repository.SupervisionRecordRepository;
import com.example.demo.Model.SupervisionRecord;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SupervisionRecord")
public class SupervisionRecordController
{
    @Autowired
    private SupervisionRecordRepository SupervisionRecordRepository;
    @GetMapping(path = "/getAll")
   //获取全部监督记录列表
    public @ResponseBody JSONObject getAll(@RequestParam(value = "planId",required = false) String planId)
    {
        List<SupervisionRecord> res=SupervisionRecordRepository.findAllByPlanId(Long.parseLong(planId));
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        //将必要信息存放在data，前端用来展示
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("recordId",res.get(i).getRecordId());
                tmp.put("planId",res.get(i).getPlanId());
                tmp.put("department",res.get(i).getDepartment());
                tmp.put("supervisor",res.get(i).getSupervisor());
                tmp.put("superviseDate",res.get(i).getSuperviseDate());
                tmp.put("supervisedPerson",res.get(i).getSupervisedPerson());
                tmp.put("record",res.get(i).getRecord());
                tmp.put("conclusion",res.get(i).getConclusion());
                tmp.put("operator",res.get(i).getOperator());
                tmp.put("recordDate",res.get(i).getRecordDate());
                data.add(tmp);
            }
        }
        //监督记录列表为空
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
    @PostMapping(path = "/addOne")
    //为一项监督记录添加监督记录
    public @ResponseBody JSONObject addOne(@RequestParam(value = "planId",required = false) String planId,
                                           @RequestParam(value = "department",required = false) String department,
                                           @RequestParam(value = "supervisor",required = false) String supervisor,
                                           @RequestParam(value = "superviseDate",required = false) String superviseDate,
                                           @RequestParam(value = "supervisedPerson",required = false) String supervisedPerson,
                                           @RequestParam(value = "record",required = false) String record,
                                           @RequestParam(value = "conclusion",required = false) String conclusion,
                                           @RequestParam(value = "operator",required = false) String operator,
                                           @RequestParam(value = "recordDate",required = false) String recordDate)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //确保参数合法
        try
        {
            Long.parseLong(planId);
            java.sql.Date.valueOf(superviseDate);
            java.sql.Date.valueOf(recordDate);
        }
        catch (NumberFormatException e)
        {
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //确保参数非空
        if(department.equals("")||supervisor.equals("")||supervisedPerson.equals("")||
        record.equals("")||conclusion.equals("")||operator.equals(""))
        {
            code=511;
            msg="缺少请求参数";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //正常添加
        //System.out.println(planId);
        SupervisionRecord sr=new SupervisionRecord();
        sr.setSupervisor(supervisor);
        sr.setSupervisedPerson(supervisedPerson);
        sr.setSuperviseDate(java.sql.Date.valueOf(superviseDate));
        sr.setRecordDate(java.sql.Date.valueOf(recordDate));
        sr.setRecord(record);
        sr.setPlanId(Long.parseLong(planId));
        sr.setOperator(operator);
        sr.setDepartment(department);
        sr.setConclusion(conclusion);
        SupervisionRecordRepository.save(sr);
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/modifyOne")
    //修改某项监督记录
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "recordId",required = false) String recordId,
                                              @RequestParam(value = "department",required = false) String department,
                                              @RequestParam(value = "supervisor",required = false) String supervisor,
                                              @RequestParam(value = "superviseDate",required = false) String superviseDate,
                                              @RequestParam(value = "supervisedPerson",required = false) String supervisedPerson,
                                              @RequestParam(value = "record",required = false) String record,
                                              @RequestParam(value = "conclusion",required = false) String conclusion,
                                              @RequestParam(value = "operator",required = false) String operator,
                                              @RequestParam(value = "recordDate",required = false) String recordDate)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //确保参数格式合法
        try
        {
            Long.parseLong(recordId);
            java.sql.Date.valueOf(superviseDate);
            java.sql.Date.valueOf(recordDate);
        }
        catch (NumberFormatException e)
        {
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //确保参数非空
        if(department.equals("")||supervisor.equals("")||supervisedPerson.equals("")||
                record.equals("")||conclusion.equals("")||operator.equals(""))
        {
            code=511;
            msg="缺少请求参数";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //正常修改更新监督记录
        SupervisionRecord sr=SupervisionRecordRepository.findByRecordId(Long.parseLong(recordId));
        sr.setSupervisor(supervisor);
        sr.setSupervisedPerson(supervisedPerson);
        sr.setSuperviseDate(java.sql.Date.valueOf(superviseDate));
        sr.setRecordDate(java.sql.Date.valueOf(recordDate));
        sr.setRecord(record);
        sr.setOperator(operator);
        sr.setDepartment(department);
        sr.setConclusion(conclusion);
        SupervisionRecordRepository.saveAndFlush(sr);
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/deleteOne")
    // 删除某项监督记录
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "recordId",required = false) String recordId)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //确保参数格式合法
        try
        {
            Long.parseLong(recordId);
        }
        catch (NumberFormatException e)
        {
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        SupervisionRecordRepository.deleteById(Long.parseLong(recordId));
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
}
//select * from supervision_record;
//alter table supervision_record ENGINE =InnoDB;