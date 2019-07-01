package com.example.demo.Controller;


import com.example.demo.Model.StaffTraining;
import com.example.demo.Repository.StaffManagementRepository;
import com.example.demo.Repository.StaffTrainingRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(path = "/cma/StaffTraining")
public class StaffTrainingController {
    @Autowired
    private StaffTrainingRepository staffTrainingRepository;
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path = "getAll")
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffTrainingRepository.findAll());
        return json;
    }

    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value="program",required = false)String program,@RequestParam(value = "trainingDate",required = false)String trainingDate,
                                           @RequestParam(value = "place",required = false)String place,@RequestParam(value = "presenter",required = false)String presenter,
                                           @RequestParam(value = "content",required = false)String content,@RequestParam(value = "note",required = false)String note){
        StaffTraining staffTraining=new StaffTraining();
        staffTraining.setContent(content);
        staffTraining.setNote(note);
        staffTraining.setPlace(place);
        staffTraining.setPresenter(presenter);
        staffTraining.setProgram(program);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            staffTraining.setTrainingDate(sdf.parse(trainingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject json=new JSONObject();
        List<StaffTraining> list=staffTrainingRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).Equals(staffTraining)){
                json.put("code",210);
                json.put("msg","失败，已存在");
                json.put("data",null);
                return json;
            }
        }
        staffTrainingRepository.save(staffTraining);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "trainingId",required = false)long trainingId){
        JSONObject json=new JSONObject();
        if(staffTrainingRepository.existsById(trainingId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else
        {
            staffTrainingRepository.deleteById(trainingId);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="program",required = false)String program,@RequestParam(value = "trainingDate",required = false)String trainingDate,
                                              @RequestParam(value = "place",required = false)String place,@RequestParam(value = "presenter",required = false)String presenter,
                                              @RequestParam(value = "content",required = false)String content,@RequestParam(value = "note",required = false)String note){
        JSONObject json=new JSONObject();
        if(staffTrainingRepository.existsById(trainingId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else{
            StaffTraining staffTraining=staffTrainingRepository.getOne(trainingId);
            if(!program.equals(""))
                staffTraining.setProgram(program);
            if(!trainingDate.equals("")){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    staffTraining.setTrainingDate(sdf.parse(trainingDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(!place.equals(""))
                staffTraining.setPlace(place);
            if(!presenter.equals(""))
                staffTraining.setPresenter(presenter);
            if(!content.equals(""))
                staffTraining.setContent(content);
            if(!note.equals(""))
                staffTraining.setNote(note);
            staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "addTrainingPeople")
    public @ResponseBody JSONObject addTrainingPeople(@RequestParam(value = "data",required = false)JSONObject data){
        JSONObject json=new JSONObject();
        String string=data.getAsString("trainingId");
        String id=data.getAsString("data");
        System.out.println("id");
        long trainingId=Long.parseLong(string);
        if(!staffTrainingRepository.existsById(trainingId)){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
        }
        else
        {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
}
