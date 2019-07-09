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
    //添加年度计划
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
        //给单个年度计划赋值
        allAnnualPlan.setApprover(null);
        allAnnualPlan.setApproveDate(null);
        //初始化批准人和批准日期为空
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
        //比较是否已存在相同的计划
        allAnnualPlanRepository.save(allAnnualPlan);
        //保存计划
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "approveAnnualPlan")
    //批准年度计划
    public @ResponseBody JSONObject approve(@RequestParam(value="year",required = false)long year,@RequestParam(value = "approver",required = false)String approver,
                                            @RequestParam(value = "approveDate",required = false)String approveDate){
        AllAnnualPlan allAnnualPlan=allAnnualPlanRepository.findByYear(year);
        //通过年份查找年度计划
        JSONObject json=new JSONObject();
        if(allAnnualPlan==null) {
            json.put("code", 210);
            json.put("msg", "失败");
            json.put("data", null);
            return json;
        }
        //未找到该计划，返回信息
        allAnnualPlan.setApprover(approver);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            allAnnualPlan.setApproveDate(sdf.parse(approveDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        allAnnualPlanRepository.save(allAnnualPlan);
        //找到该计划，输入批准人和批准日期，并保存
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @GetMapping(path ="getAnnualPlan")
    //获取某一年的年度计划
    public @ResponseBody JSONObject getOne(@RequestParam(value="year",required = false)long year){
        JSONObject json=new JSONObject();
        if(!allAnnualPlanRepository.existsByYear(year)){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        //年度计划不存在，返回信息
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",allAnnualPlanRepository.findByYear(year));
        }
        //年度计划存在，返回该年度计划的详细内容
        return json;
    }
    @GetMapping(path = "getAllAnnualPlan")
    //查看所有年度计划
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",allAnnualPlanRepository.findAll());
        //返回所有年度计划的信息
        return json;
    }


}
