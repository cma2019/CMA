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
    public @ResponseBody JSONObject addOne(@RequestParam(value="trainingId",required = false)long trainingId,@RequestParam(value="program",required = false)String program,@RequestParam(value = "trainingDate",required = false)String trainingDate,
                                           @RequestParam(value = "place",required = false)String place,@RequestParam(value = "presenter",required = false)String presenter,
                                           @RequestParam(value = "content",required = false)String content,@RequestParam(value = "note",required = false)String note){
        StaffTraining staffTraining=new StaffTraining();
        staffTraining.setTrainingId(trainingId);
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

    /*@PostMapping(path = "deleteOne")
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
    }*/

    @PostMapping(path = "addTrainingPeople")
    public @ResponseBody JSONObject addTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="id",required = false)long[] id) throws ParseException {
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingId(trainingId)){
            json.put("code",210);
            json.put("msg","失败,无法找到该培训");
            json.put("data",null);
        }
        else
        {
            StaffTraining staffTraining=staffTrainingRepository.findByTrainingId(trainingId);
            for(int i=0;i<id.length;i++){
                if(staffManagementRepository.existsById(id[i])==false){
                    json.put("code",210);
                    json.put("msg","失败,无法找到id为"+id[i]+"的人员");
                    json.put("data",null);
                    return json;
                }
            }
            staffTrainingRepository.deleteByTrainingId(trainingId);
            for(int i=0;i<id.length;i++){
                StaffTraining staffTraining1=new StaffTraining();
                staffTraining1.setTrainingId(trainingId);
                staffTraining1.setResult(staffTraining.getResult());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                staffTraining1.setTrainingDate(sdf.parse(staffTraining.getTrainingDate()));
                staffTraining1.setProgram(staffTraining.getProgram());
                staffTraining1.setPlace(staffTraining.getPlace());
                staffTraining1.setPresenter(staffTraining.getPresenter());
                staffTraining1.setContent(staffTraining.getContent());
                staffTraining1.setNote(staffTraining.getNote());
                staffTraining1.setId(id[i]);
                staffTrainingRepository.save(staffTraining1);
            }
            //staffTraining.setId(id[0]);
            //staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "addTrainingResult")
    public @ResponseBody JSONObject addTrainingResult(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="id",required = false)long id,@RequestParam(value="result",required = false)String result){
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingIdAndId(trainingId,id)){
            json.put("code",210);
            json.put("msg","失败,无法找到该培训");
            json.put("data",null);
        }
        else{
            StaffTraining staffTraining=staffTrainingRepository.findByTrainingIdAndId(trainingId, id);
            staffTraining.setResult(result);
            staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
    @GetMapping(path = "getTrainingPeople")
    public @ResponseBody JSONObject getTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffTrainingRepository.findAllByTrainingId(trainingId));
        return json;
    }

    @PostMapping(path = "modifyResult")
    public @ResponseBody JSONObject modifyResult(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="id",required = false)long id,@RequestParam(value="result",required = false)String result){
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingIdAndId(trainingId,id)){
            json.put("code",210);
            json.put("msg","失败,无法找到该培训");
            json.put("data",null);
        }
        else{
            StaffTraining staffTraining=staffTrainingRepository.findByTrainingIdAndId(trainingId, id);
            staffTraining.setResult(result);
            staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
}
