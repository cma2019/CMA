package com.example.demo.Controller;


import com.example.demo.Model.AnnualTrainingPlan;
import com.example.demo.Repository.AnnualTrainingPlanRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/cma/AnnualTrainingPlan")
public class AnnualTrainingPlanController {
    @Autowired
    private AnnualTrainingPlanRepository annualTrainingPlanRepository;

    @GetMapping(path = "getAll")
    //获取某一年的所有培训计划
    public @ResponseBody JSONObject getAll(@RequestParam(value = "year",required = false)long year){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",annualTrainingPlanRepository.findAllByYear(year));
        //返回该年的所有培训计划信息
        return json;
    }

    @GetMapping(path = "getOne")
    //获取某一年的某一次培训计划
    public @ResponseBody JSONObject getOne(@RequestParam(value="planId",required = false)long planId){
        //输入计划编号
        JSONObject json=new JSONObject();
        if(annualTrainingPlanRepository.existsById(planId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        //若不存在，返回失败信息
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",annualTrainingPlanRepository.findById(planId));
            //计划存在，返回该次计划的信息
        }
        return json;
    }
    @PostMapping(path = "addOne")
    //添加一次年度培训计划
    public @ResponseBody JSONObject addOne(@RequestParam(value = "year",required = false) long year,@RequestParam(value = "trainProject",required = false)String trainProject,
                                           @RequestParam(value = "people",required = false)String people,@RequestParam(value = "method",required = false)String method,
                                           @RequestParam(value="trainingTime",required = false)long trainingTime,@RequestParam(value = "startTime",required = false)String startTime,
                                           @RequestParam(value = "endTime",required = false)String endTime,@RequestParam(value = "note",required = false)String note) throws ParseException {
        AnnualTrainingPlan annualTrainingPlan=new AnnualTrainingPlan();
        annualTrainingPlan.setYear(year);
        //输入年份
        annualTrainingPlan.setTrainProject(trainProject);
        //输入项目名称
        annualTrainingPlan.setPeople(people);
        //输入培训人员姓名
        annualTrainingPlan.setMethod(method);
        //输入授课方式
        annualTrainingPlan.setTrainingTime(trainingTime);
        //输入培训时长
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        annualTrainingPlan.setStartTime(sdf.parse(startTime));
        //开始时间
        annualTrainingPlan.setEndTime(sdf.parse(endTime));
        //结束时间
        annualTrainingPlan.setNote(note);
        //输入备注
        JSONObject json=new JSONObject();
        List<AnnualTrainingPlan> list=annualTrainingPlanRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).Equals(annualTrainingPlan))
            {
                json.put("code",210);
                json.put("msg","已存在");
                json.put("data",null);
                return json;
            }
        }
        //判断是否已存在相同的培训计划
        annualTrainingPlanRepository.save(annualTrainingPlan);
        //保存培训计划
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除某一次培训计划
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "planId",required = false)long planId){
        JSONObject json=new JSONObject();
        if(annualTrainingPlanRepository.existsById(planId)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
        }
        //不存在该计划，返回失败信息
        else{
            annualTrainingPlanRepository.deleteById(planId);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        //存在，返回成功
        return json;
    }

    @PostMapping(path = "modifyOne")
    //修改某次计划，除了planId都是可选项
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "planId",required = false) long planId,@RequestParam(value = "trainProject",required = false)String trainProject,
            @RequestParam(value = "people",required = false)String people,@RequestParam(value = "method",required = false)String method,
            @RequestParam(value="trainingTime",required = false)String trainingTime,@RequestParam(value = "startTime",required = false)String startTime,
            @RequestParam(value = "endTime",required = false)String endTime,@RequestParam(value = "note",required = false)String note) throws ParseException {
        JSONObject json=new JSONObject();
        if(annualTrainingPlanRepository.existsById(planId)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
        }
        //不存在该次计划
        else {
            AnnualTrainingPlan annualTrainingPlan = annualTrainingPlanRepository.getOne(planId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!trainProject.equals(""))
                //可选项若不为空，则修改他的值
                annualTrainingPlan.setTrainProject(trainProject);
            if (!people.equals(""))
                annualTrainingPlan.setPeople(people);
            if (!method.equals(""))
                annualTrainingPlan.setMethod(method);
            if (!trainingTime.equals(""))
                annualTrainingPlan.setTrainingTime(Long.parseLong(trainingTime));
            if (!startTime.equals("")) {
                annualTrainingPlan.setStartTime(sdf.parse(startTime));
            }
            if (!endTime.equals(""))
                annualTrainingPlan.setEndTime(sdf.parse(endTime));
            if (!note.equals(""))
                annualTrainingPlan.setNote(note);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
            annualTrainingPlanRepository.save(annualTrainingPlan);
        }
        return json;
    }

    public void setAnnualTrainingPlanRepository(AnnualTrainingPlanRepository annualTrainingPlanRepository) {
        this.annualTrainingPlanRepository = annualTrainingPlanRepository;
    }
}
