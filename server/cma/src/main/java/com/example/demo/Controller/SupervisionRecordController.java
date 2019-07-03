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
public class SupervisionRecordController {
    @Autowired
    private SupervisionRecordRepository SupervisionRecordRepository;
    @GetMapping(path = "/getAll")
    public @ResponseBody JSONObject getAll(@RequestParam(value = "planId",required = false) String planId){
        List<SupervisionRecord> res=SupervisionRecordRepository.findAllByPlanId(Long.parseLong(planId));
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
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
        else{
            code=522;
        msg="数据不存在";
        //data=null;
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
}
