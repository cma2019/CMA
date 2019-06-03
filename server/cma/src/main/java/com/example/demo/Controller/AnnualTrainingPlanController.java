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
    public @ResponseBody JSONObject getAll(@RequestParam(value = "year",required = false)long year){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",annualTrainingPlanRepository.findAllByYear(year));
        return json;
    }

    @GetMapping(path = "getOne")
    public @ResponseBody JSONObject getOne(@RequestParam(value="planId",required = false)long planId){
        JSONObject json=new JSONObject();
        if(annualTrainingPlanRepository.existsById(planId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",annualTrainingPlanRepository.findById(planId));
        }
        return json;
    }
    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "year",required = false) long year,@RequestParam(value = "trainProject",required = false)String trainProject,
                                           @RequestParam(value = "people",required = false)String people,@RequestParam(value = "method",required = false)String method,
                                           @RequestParam(value="trainingTime",required = false)long trainingTime,@RequestParam(value = "startTime",required = false)String startTime,
                                           @RequestParam(value = "endTime",required = false)String endTime,@RequestParam(value = "note",required = false)String note) throws ParseException {
        AnnualTrainingPlan annualTrainingPlan=new AnnualTrainingPlan();
        annualTrainingPlan.setYear(year);
        annualTrainingPlan.setTrainProject(trainProject);
        annualTrainingPlan.setPeople(people);
        annualTrainingPlan.setMethod(method);
        annualTrainingPlan.setTrainingTime(trainingTime);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        annualTrainingPlan.setStratTime(sdf.parse(startTime));
        annualTrainingPlan.setEndTime(sdf.parse(endTime));
        annualTrainingPlan.setNote(note);
        JSONObject json=new JSONObject();
        List<AnnualTrainingPlan> list=annualTrainingPlanRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).Equals(annualTrainingPlan))
            {
                json.put("code",210);
                json.put("msg","失败,已存在");
                json.put("data",null);
                return json;
            }
        }
        annualTrainingPlanRepository.save(annualTrainingPlan);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "planId",required = false)long planId){
        JSONObject json=new JSONObject();
        if(annualTrainingPlanRepository.existsById(planId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else{
            annualTrainingPlanRepository.deleteById(planId);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "planId",required = false) long planId,@RequestParam(value = "trainProject",required = false)String trainProject,
            @RequestParam(value = "people",required = false)String people,@RequestParam(value = "method",required = false)String method,
            @RequestParam(value="trainingTime",required = false)String trainingTime,@RequestParam(value = "startTime",required = false)String startTime,
            @RequestParam(value = "endTime",required = false)String endTime,@RequestParam(value = "note",required = false)String note) throws ParseException {
        JSONObject json=new JSONObject();
        if(annualTrainingPlanRepository.existsById(planId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else {
            AnnualTrainingPlan annualTrainingPlan = annualTrainingPlanRepository.getOne(planId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!trainProject.equals(""))
                annualTrainingPlan.setTrainProject(trainProject);
            if (!people.equals(""))
                annualTrainingPlan.setPeople(people);
            if (!method.equals(""))
                annualTrainingPlan.setMethod(method);
            if (!trainingTime.equals(""))
                annualTrainingPlan.setTrainingTime(Long.parseLong(trainingTime));
            if (!startTime.equals("")) {
                annualTrainingPlan.setStratTime(sdf.parse(startTime));
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
