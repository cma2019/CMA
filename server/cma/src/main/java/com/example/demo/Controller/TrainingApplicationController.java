package com.example.demo.Controller;

import com.example.demo.Model.TrainingApplication;
import com.example.demo.Repository.TrainingApplicationRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(path="/cma/TrainingApplication")
public class TrainingApplicationController {
    @Autowired
    private TrainingApplicationRepository trainingApplicationRepository;

    @GetMapping(path = "getAll")
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",trainingApplicationRepository.findAll());
        return json;
    }

    @GetMapping(path = "getOne")
    public @ResponseBody JSONObject getOne(@RequestParam(value="id",required = false)long id){
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",trainingApplicationRepository.findById(id));
        }
        return json;
    }
    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "name",required = false) String name,@RequestParam(value = "people",required = false)String people,
                                           @RequestParam(value = "trainingUnit",required = false)String trainingUnit,@RequestParam(value = "expense",required = false)long expense,
    @RequestParam(value="reason",required = false)String reason,@RequestParam(value = "department",required = false)String department,@RequestParam(value = "createDate",required = false)String createDate) throws ParseException {
        TrainingApplication trainingApplication=new TrainingApplication();
        trainingApplication.setName(name);
        trainingApplication.setPeople(people);
        trainingApplication.setTrainingUnit(trainingUnit);
        trainingApplication.setExpense(expense);
        trainingApplication.setReason(reason);
        trainingApplication.setDepartment(department);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        trainingApplication.setCreateDate(sdf.parse(createDate));
        trainingApplication.setSituation((byte) 0);
        JSONObject json=new JSONObject();
        List<TrainingApplication> list=trainingApplicationRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).Equals(trainingApplication))
            {
                json.put("code",210);
                json.put("msg","已存在");
                json.put("data",null);
                return json;
            }
        }
        trainingApplicationRepository.save(trainingApplication);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
        }
        else{
            trainingApplicationRepository.deleteById(id);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "id",required = false)long id,@RequestParam(value = "name",required = false) String name,@RequestParam(value = "people",required = false)String people,
                                              @RequestParam(value = "trainingUnit",required = false)String trainingUnit,@RequestParam(value = "expense",required = false)String expense,
                                              @RequestParam(value="reason",required = false)String reason,@RequestParam(value = "department",required = false)String department,@RequestParam(value = "createDate",required = false)String createDate) throws ParseException {
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
        }
        else{
            TrainingApplication trainingApplication=trainingApplicationRepository.getOne(id);
            if(!name.equals(""))
                trainingApplication.setName(name);
            if(!people.equals(""))
                trainingApplication.setPeople(people);
            if(!trainingUnit.equals(""))
                trainingApplication.setTrainingUnit(trainingUnit);
            if(!expense.equals(""))
                trainingApplication.setExpense(Long.parseLong(expense));
            if(!reason.equals(""))
                trainingApplication.setReason(reason);
            if(!department.equals(""))
                trainingApplication.setDepartment(department);
            if(!createDate.equals("")){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                trainingApplication.setCreateDate(sdf.parse(createDate));
            }
            if(trainingApplication.getSituation()==1){
                trainingApplication.setSituation((byte) 0);
                //trainingApplication.setCreateDate(null);
                //trainingApplication.setApproveDate(null);
                //trainingApplication.setApprover(null);
            }
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
            trainingApplicationRepository.save(trainingApplication);
        }
        return json;
    }
    @PostMapping("approveOne")
    public @ResponseBody JSONObject aprroveOne(@RequestParam(value = "id",required = false)long id,@RequestParam(value = "situation",required = false)int situation
            ,@RequestParam(value="approver",required = false)String approver,@RequestParam(value = "approveDate",required = false)String approveDate) throws ParseException {
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
        }
        else{
            TrainingApplication trainingApplication=trainingApplicationRepository.getOne(id);
            if(trainingApplication.getSituation()==0){
                if(situation==0){
                    json.put("code",210);
                    json.put("msg","审查错误");
                    json.put("data",null);
                    return json;
                }
                trainingApplication.setSituation((byte) situation);
                trainingApplication.setApprover(approver);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                trainingApplication.setApproveDate(sdf.parse(approveDate));
                trainingApplicationRepository.save(trainingApplication);
                json.put("code",200);
                json.put("msg","成功");
                json.put("data",null);
            }
            else{
                json.put("code",210);
                json.put("msg","已审查");
                json.put("data",null);
            }
        }
        return json;
    }
}
