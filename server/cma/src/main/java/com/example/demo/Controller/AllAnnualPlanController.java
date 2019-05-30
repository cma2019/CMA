package com.example.demo.Controller;

import com.example.demo.Repository.AllAnnualPlanRepository;
import com.example.demo.Model.AllAnnualPlan;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(path="/cma/AnnualTrainingPlan")
public class AllAnnualPlanController {
    @Autowired
    private AllAnnualPlanRepository allAnnualPlanRepository;

    @PostMapping(path = "addAnnualPlan")
    public @ResponseBody JSONObject addAnnualPlan(@RequestParam(value = "year",required = false)long year,@RequestParam(value = "author",required = false)String author,
                                                  @RequestParam(value = "createDate",required = false)String createDate){
        AllAnnualPlan allAnnualPlan=new AllAnnualPlan();
        allAnnualPlan.setYear(year);
        allAnnualPlan.setAuthor(author);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            allAnnualPlan.setCreateDate(sdf.parse(createDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        allAnnualPlan.setApprover("");
        try {
            allAnnualPlan.setApproveDate(sdf.parse("2000-1-1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject json=new JSONObject();
        List<AllAnnualPlan> list=allAnnualPlanRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).Equals(allAnnualPlan)==true)
            {
                json.put("code",210);
                json.put("msg","失败");
                json.put("data",null);
                return json;
            }
        }
        allAnnualPlanRepository.save(allAnnualPlan);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
}
