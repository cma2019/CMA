package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Repository.AllAnnualPlanRepository;
import com.example.demo.Model.AllAnnualPlan;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        allAnnualPlan.setApprover(null);
        allAnnualPlan.setApproveDate(null);
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

    @PostMapping(path = "approveAnnualPlan")
    public @ResponseBody JSONObject approve(@RequestParam(value="year",required = false)long year,@RequestParam(value = "approver",required = false)String approver,
                                            @RequestParam(value = "approveDate",required = false)String approveDate){
        AllAnnualPlan allAnnualPlan=allAnnualPlanRepository.findByYear(year);
        JSONObject json=new JSONObject();
        if(allAnnualPlan==null) {
            json.put("code", 210);
            json.put("msg", "失败");
            json.put("data", null);
            return json;
        }
        allAnnualPlan.setApprover(approver);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            allAnnualPlan.setApproveDate(sdf.parse(approveDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        allAnnualPlanRepository.save(allAnnualPlan);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @GetMapping(path ="getAnnualPlan")
    public @ResponseBody JSONObject getOne(@RequestParam(value="year",required = false)long year){
        JSONObject json=new JSONObject();
        if(!allAnnualPlanRepository.existsByYear(year)){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",allAnnualPlanRepository.findByYear(year));
        }
        return json;
    }
    @GetMapping(path = "getAllAnnualPlan")
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",allAnnualPlanRepository.findAll());
        return json;
    }


}
