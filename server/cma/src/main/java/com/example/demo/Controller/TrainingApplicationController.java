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
    //获取所有培训申请
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",trainingApplicationRepository.findAll());
        return json;
    }

    @GetMapping(path = "getOne")
    //获取一次培训申请信息
    public @ResponseBody JSONObject getOne(@RequestParam(value="id",required = false)long id){
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","失败,无法找到");
            json.put("data",null);
            //不存在该次培训申请
        }
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",trainingApplicationRepository.findById(id));
            //获取该次培训申请信息
        }
        return json;
    }
    @PostMapping(path = "addOne")
    //添加一次培训申请
    public @ResponseBody JSONObject addOne(@RequestParam(value = "name",required = false) String name,@RequestParam(value = "people",required = false)String people,
                                           @RequestParam(value = "trainingUnit",required = false)String trainingUnit,@RequestParam(value = "expense",required = false)long expense,
    @RequestParam(value="reason",required = false)String reason,@RequestParam(value = "department",required = false)String department,@RequestParam(value = "createDate",required = false)String createDate) throws ParseException {
        TrainingApplication trainingApplication=new TrainingApplication();
        trainingApplication.setName(name);
        //输入培训项目名
        trainingApplication.setPeople(people);
        //输入培训对象
        trainingApplication.setTrainingUnit(trainingUnit);
        //输入培训单位
        trainingApplication.setExpense(expense);
        //输入开销
        trainingApplication.setReason(reason);
        //输入培训理由
        trainingApplication.setDepartment(department);
        //输入申请部门
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        trainingApplication.setCreateDate(sdf.parse(createDate));
        //输入申请日期
        trainingApplication.setSituation((byte) 0);
        //初始化为未审核
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
        //判断该培训申请是否存在
        trainingApplicationRepository.save(trainingApplication);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除某次培训申请
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
            //该培训申请不存在
        }
        else{
            trainingApplicationRepository.deleteById(id);
            //删除该培训申请
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    //修改某次培训申请
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "id",required = false)long id,@RequestParam(value = "name",required = false) String name,@RequestParam(value = "people",required = false)String people,
                                              @RequestParam(value = "trainingUnit",required = false)String trainingUnit,@RequestParam(value = "expense",required = false)String expense,
                                              @RequestParam(value="reason",required = false)String reason,@RequestParam(value = "department",required = false)String department,@RequestParam(value = "createDate",required = false)String createDate) throws ParseException {
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
            //该培训申请不存在
        }
        else{
            TrainingApplication trainingApplication=trainingApplicationRepository.getOne(id);
            //获取某次培训申请信息
            if(trainingApplication.getSituation()==2)
            {
                json.put("code",210);
                json.put("msg","不可修改");
                json.put("data",null);
                return json;
                //申请已通过，不可修改
            }
            if(!name.equals(""))
                //培训名称是否修改
                trainingApplication.setName(name);
            if(!people.equals(""))
                //培训对象是否修改
                trainingApplication.setPeople(people);
            if(!trainingUnit.equals(""))
                //培训单位是否修改
                trainingApplication.setTrainingUnit(trainingUnit);
            if(!expense.equals(""))
                //培训开销是否修改
                trainingApplication.setExpense(Integer.parseInt(expense));
            if(!reason.equals(""))
                //培训理由是否修改
                trainingApplication.setReason(reason);
            if(!department.equals(""))
                //申请部门是否修改
                trainingApplication.setDepartment(department);
            if(!createDate.equals("")){
                //申请日期是否修改
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                trainingApplication.setCreateDate(sdf.parse(createDate));
            }
            if(trainingApplication.getSituation()==1){
                trainingApplication.setSituation((byte) 0);
                //若审核未通过，修改后变为未审核状态
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
    //审核一次申请
    public @ResponseBody JSONObject aprroveOne(@RequestParam(value = "id",required = false)long id,@RequestParam(value = "situation",required = false)int situation
            ,@RequestParam(value="approver",required = false)String approver,@RequestParam(value = "approveDate",required = false)String approveDate) throws ParseException {
        JSONObject json=new JSONObject();
        if(trainingApplicationRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","无法找到");
            json.put("data",null);
            //该申请不存在
        }
        else{
            TrainingApplication trainingApplication=trainingApplicationRepository.getOne(id);
            //获取该次申请的信息
            if(trainingApplication.getSituation()==0){
                if(situation==0){
                    json.put("code",210);
                    json.put("msg","审查错误");
                    json.put("data",null);
                    return json;
                    //审核结果不可为未审核
                }
                trainingApplication.setSituation((byte) situation);
                //输入审核结果
                trainingApplication.setApprover(approver);
                //输入审核人姓名
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                trainingApplication.setApproveDate(sdf.parse(approveDate));
                //输入审核日期
                trainingApplicationRepository.save(trainingApplication);
                json.put("code",200);
                json.put("msg","成功");
                json.put("data",null);
            }
            else{
                json.put("code",210);
                json.put("msg","已审查");
                json.put("data",null);
                //审核状态不为未审核，则不可审核
            }
        }
        return json;
    }
}
