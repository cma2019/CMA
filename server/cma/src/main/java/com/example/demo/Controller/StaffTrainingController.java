package com.example.demo.Controller;


import com.example.demo.Model.StaffTraining;
import com.example.demo.Repository.StaffManagementRepository;
import com.example.demo.Repository.StaffTrainingRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/cma/StaffTraining")
public class StaffTrainingController {
    @Autowired
    private StaffTrainingRepository staffTrainingRepository;
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path = "getAll")
    //获取所有培训记录信息
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        List<StaffTraining> list=staffTrainingRepository.findAllById(0);
        //人员id为0的是培训信息
        json.put("data",list);
        return json;
    }

    @PostMapping(path = "addOne")
    //添加某次培训记录
    public @ResponseBody JSONObject addOne(@RequestParam(value="trainingId",required = false)long trainingId,@RequestParam(value="program",required = false)String program,@RequestParam(value = "trainingDate",required = false)String trainingDate,
                                           @RequestParam(value = "place",required = false)String place,@RequestParam(value = "presenter",required = false)String presenter,
                                           @RequestParam(value = "content",required = false)String content,@RequestParam(value = "note",required = false)String note){
        StaffTraining staffTraining=new StaffTraining();
        staffTraining.setTrainingId(trainingId);
        //输入培训编号
        staffTraining.setContent(content);
        //输入培训内容
        staffTraining.setNote(note);
        //输入备注
        staffTraining.setPlace(place);
        //输入培训地点
        staffTraining.setPresenter(presenter);
        //输入主讲人
        staffTraining.setProgram(program);
        //输入培训名称
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            staffTraining.setTrainingDate(sdf.parse(trainingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //输入培训日期
        JSONObject json=new JSONObject();
        List<StaffTraining> list=staffTrainingRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals((staffTraining))){
                json.put("code",210);
                json.put("msg","已存在");
                json.put("data",null);
                return json;
            }
        }
        //判断该培训是否已存在
        staffTrainingRepository.save(staffTraining);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除培训记录
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "trainingId",required = false)long trainingId){
        JSONObject json=new JSONObject();
        if(staffTrainingRepository.existsByTrainingId(trainingId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
            //培训记录不存在
        }
        else
        {
            staffTrainingRepository.deleteAllByTrainingId(trainingId);
            //删除所有该编号的培训记录
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    //修改培训记录
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="program",required = false)String program,@RequestParam(value = "trainingDate",required = false)String trainingDate,
                                              @RequestParam(value = "place",required = false)String place,@RequestParam(value = "presenter",required = false)String presenter,
                                              @RequestParam(value = "content",required = false)String content,@RequestParam(value = "note",required = false)String note){
        JSONObject json=new JSONObject();
        if(staffTrainingRepository.existsByTrainingId(trainingId)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
            //培训记录不存在
        }
        else{
            List<StaffTraining> list=staffTrainingRepository.findAllByTrainingId(trainingId);
            for(int i=0;i<list.size();i++) {
                StaffTraining staffTraining = list.get(i);
                if (!program.equals(""))
                    //培训名称是否修改
                    staffTraining.setProgram(program);
                if (!trainingDate.equals("")) {
                    //培训日期是否修改
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        staffTraining.setTrainingDate(sdf.parse(trainingDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (!place.equals(""))
                    //培训地点是否修改
                    staffTraining.setPlace(place);
                if (!presenter.equals(""))
                    //主讲人是否修改
                    staffTraining.setPresenter(presenter);
                if (!content.equals(""))
                    //培训内容是否修改
                    staffTraining.setContent(content);
                if (!note.equals(""))
                    //备注是否修改
                    staffTraining.setNote(note);
                staffTrainingRepository.save(staffTraining);
            }
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @GetMapping(path = "getOneTraining")
    //获取某个培训信息
    public @ResponseBody JSONObject getOneTraining(@RequestParam(value = "trainingId",required = false)long trainingId){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        if(staffTrainingRepository.existsByTrainingId(trainingId)==false){
            json.put("code",210);
            json.put("msg","成功");
            json.put("data",null);
            return json;
            //该培训不存在
        }
        List<StaffTraining> list=staffTrainingRepository.findAllByTrainingId(trainingId);
        json.put("data",list.get(0));
        //返回培训记录信息
        return json;
    }
    @PostMapping(path = "addTrainingPeople")
    //为某个培训添加一个人员
    public @ResponseBody JSONObject addTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="id",required = false)long id) throws ParseException {
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingId(trainingId)){
            json.put("code",210);
            json.put("msg","无法找到该培训");
            json.put("data",null);
            //该培训不存在
        }
        else
        {
            List<StaffTraining> list=staffTrainingRepository.findAllByTrainingId(trainingId);
            if(staffManagementRepository.existsById(id)==false){
                json.put("code",210);
                json.put("msg","无法找到该人员");
                json.put("data",null);
                return json;
                //该人员不存在
            }
            for(int j=0;j<list.size();j++){
                if(id==list.get(j).getId()){
                    json.put("code",210);
                    json.put("msg","已存在该人员");
                    json.put("data",null);
                    return json;
                    //该人员已参加此次培训
                }
            }
            //staffTrainingRepository.deleteByTrainingId(trainingId);
            StaffTraining staffTraining=list.get(0);
            StaffTraining staffTraining1=new StaffTraining();
            staffTraining1.setTrainingId(trainingId);
            //输入培训id
            staffTraining1.setResult(null);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            staffTraining1.setTrainingDate(sdf.parse(staffTraining.getTrainingDate()));
            //输入培训日期
            staffTraining1.setProgram(staffTraining.getProgram());
            //输入培训名称
            staffTraining1.setPlace(staffTraining.getPlace());
            //输入培训地点
            staffTraining1.setPresenter(staffTraining.getPresenter());
            //输入主讲人
            staffTraining1.setContent(staffTraining.getContent());
            //输入培训内容
            staffTraining1.setNote(staffTraining.getNote());
            //输入备注
            staffTraining1.setId(id);
            //输入人员id
            staffTraining1.setName(staffManagementRepository.getOne(id).getName());
            //输入人员姓名
            staffTrainingRepository.save(staffTraining1);
            //staffTraining.setId(id[0]);
            //staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "addTrainingResult")
    //为某个人员的一次培训添加结果
    public @ResponseBody JSONObject addTrainingResult(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="id",required = false)long id,@RequestParam(value="result",required = false)String result){
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingIdAndId(trainingId,id)){
            json.put("code",210);
            json.put("msg","无法找到该培训");
            json.put("data",null);
            //该培训不存在
        }
        else{
            StaffTraining staffTraining=staffTrainingRepository.findByTrainingIdAndId(trainingId, id);
            if(staffTraining.getResult()!=null)
            {
                json.put("code",210);
                json.put("msg","已有结果");
                json.put("data",null);
                return json;
                //该培训已有结果
            }
            staffTraining.setResult(result);
            //输入结果
            staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
    @GetMapping(path = "getTrainingPeople")
    //获取参加某次培训的所有人员
    public @ResponseBody JSONObject getTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        List<StaffTraining> list=staffTrainingRepository.findAllByTrainingId(trainingId);
        //获取该次培训的所有信息
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==0)
                //人员id为0的是该次培训的详细信息，不是参与人员
                list.remove(i);
        }
        json.put("data",list);
        return json;
    }

    @PostMapping(path = "modifyResult")
    //修改某个人员参加某次培训的结果
    public @ResponseBody JSONObject modifyResult(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value="id",required = false)long id,@RequestParam(value="result",required = false)String result){
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingIdAndId(trainingId,id)){
            json.put("code",210);
            json.put("msg","无法找到该培训");
            json.put("data",null);
            //不存在该培训
        }
        else{
            StaffTraining staffTraining=staffTrainingRepository.findByTrainingIdAndId(trainingId, id);
            if(staffTraining.getResult()==null)
            {
                json.put("code",210);
                json.put("msg","未有结果");
                json.put("data",null);
                return json;
                //此次培训没有结果，无法修改
            }
            staffTraining.setResult(result);
            //修改结果
            staffTrainingRepository.save(staffTraining);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
    @GetMapping("getAllByStaff")
    //获取某个人员的所有培训记录
    public @ResponseBody JSONObject getAllByStaff(@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        List<StaffTraining> list=staffTrainingRepository.findAllById(id);
        //根据人员id获取培训记录
        json.put("data",list);
        return json;
    }
    @GetMapping("getOne")
    //获取某个人员参加某次培训的信息
    public @ResponseBody JSONObject getOne(@RequestParam(value = "id",required = false)long id,@RequestParam(value = "trainingId",required = false)long trainingId){
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingIdAndId(trainingId,id)){
            json.put("code",210);
            json.put("msg","无法找到该培训");
            json.put("data",null);
            //不存在该培训
        }
        else{
            StaffTraining staffTraining=staffTrainingRepository.findByTrainingIdAndId(trainingId, id);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",staffTraining);
            //返回该人员参加该次培训的信息
        }
        return json;
    }

    @PostMapping(path = "deleteTrainingPeople")
    //删除一个参加某次培训的人员
    public @ResponseBody JSONObject deleteTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId,@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        if(!staffTrainingRepository.existsByTrainingIdAndId(trainingId,id)){
            json.put("code",210);
            json.put("msg","无法找到该培训");
            json.put("data",null);
            //不存在该培训
        }
        else{
            staffTrainingRepository.deleteByTrainingIdAndId(trainingId, id);
            //删除该条记录
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
}
