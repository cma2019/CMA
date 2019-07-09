package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.CapacityVerificationProject;
import com.example.demo.Repository.CapacityVerificationProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lyt
 * 返回code   200→成功
 *            5xx→异常
 */
@Controller
@RequestMapping(path="/cma/CapacityVerification")//定义接口
public class CapacityVerificationProjectController {
    @Autowired
    private CapacityVerificationProjectRepository CapacityVerificationProjectRepository;

    @GetMapping(path="/getAllProject")//获取计划所有对应项目
    public @ResponseBody JSONObject getAllProject(@RequestParam(value="planId",required = false)Long planId) throws JSONException {
        JSONObject json = new JSONObject(new LinkedHashMap());
        /*if (CapacityVerificationProjectRepository.findAllByPlanId(planId).isEmpty()) {
            try {
                json.put("code", 500);
                json.put("msg", "查找不到");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {*/
            try {
                json.put("code", 200);
                json.put("msg", "获取成功");
                json.put("data",CapacityVerificationProjectRepository.findAllByPlanId(planId));//返回信息
            } catch (JSONException e) {
                e.printStackTrace();
            }
        //}
        /*List<CapacityVerificationProject> projectList= CapacityVerificationProjectRepository.findAllByPlanId(planId);
        JSONObject data=new JSONObject();
        JSONArray array=new JSONArray();
        for(CapacityVerificationProject project:projectList){
            JSONObject singleProject=new JSONObject();
            //JSONObject array=new JSONObject();
            singleProject.put("projectId",project.getprojectID());
            singleProject.put("planId",project.getplanID());
            singleProject.put("name",project.getName());
            singleProject.put("method",project.getMethod());
            singleProject.put("state",project.getState());
            singleProject.put("note",project.getNote());
            array.put(singleProject);
            //System.out.println(array);

        }
        json.put("data",array);*/

        return json;
    }

    @PostMapping(path = "/addOneProject")//添加项目
    public @ResponseBody JSONObject addProject(@RequestParam(value = "planId", required = false) Long planId,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "method", required = false) String method,
                                            @RequestParam(value = "note", required = false) String note){
        CapacityVerificationProject newProject = new CapacityVerificationProject();
        newProject.setplanId(planId);
        newProject.setName(name);
        newProject.setMethod(method);
        newProject.setNote(note);
        newProject.setState(0);
        CapacityVerificationProjectRepository.save(newProject);//保存项目
        CapacityVerificationProjectRepository.updatePlanState(0,planId);//将其对应的计划状态置为0
        JSONObject json = new JSONObject(new LinkedHashMap());
        try {
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping(path="/deleteOneProject")//删除项目
    public @ResponseBody JSONObject deleteProject(@RequestParam(value="id",required=false)Long projectId){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!CapacityVerificationProjectRepository.findById(projectId).isPresent())//是否为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {

            if(CapacityVerificationProjectRepository.findById(projectId).get().getState()==1)//如果项目状态为1，删除其对应的记录
                CapacityVerificationProjectRepository.deleteRecord(projectId);
            CapacityVerificationProjectRepository.deleteById(projectId);//删除项目
            try{
                json.put("code",200);
                json.put("msg","删除成功");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return json;
    }

    @PostMapping(path="/modifyOneProject")//修改项目
    public @ResponseBody JSONObject modifyProject(@RequestParam(value="id",required=false)Long projectId,
                                               @RequestParam(value="planId",required = false)Long planId,
                                               @RequestParam(value="name",required=false)String name,
                                               @RequestParam(value="method",required=false)String method,
                                               @RequestParam(value="state",required=false)Long state,
                                               @RequestParam(value="note",required=false)String note){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!CapacityVerificationProjectRepository.findById(projectId).isPresent())//是否为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            long tempState=CapacityVerificationProjectRepository.findById(projectId).get().getState();//记录原本状态
            CapacityVerificationProjectRepository.updateById(projectId,planId,name,method,state,note);//更新信息
            //content,checkDate,personInCharge,state
            //System.out.println("changed object");
            //long tempState=CapacityVerificationProjectRepository.findAllByPlanId(planId).get().getState();
            if(CapacityVerificationProjectRepository.findAllByPlanIdAndState(planId,0).isEmpty())//如果对应计划下所有记录状态均为1，置计划状态为1
                CapacityVerificationProjectRepository.updatePlanState(1,planId);
            if(tempState==0&&state==1)//状态由0转为1
            {
                json.put("code",300);
                json.put("msg","修改成功");
                return json;
            }
            if(tempState==1&&state==0)//状态由1转为0
            {
                CapacityVerificationProjectRepository.deleteRecord(projectId);//删除对应的记录
                CapacityVerificationProjectRepository.updatePlanState(0,planId);//将对应的计划状态记为0
            }
            try{
                json.put("code",200);
                json.put("msg","修改成功");
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

        return json;
    }

    @PostMapping(path="/modifyState")//修改状态
    public @ResponseBody JSONObject modifyState(@RequestParam(value="projectId",required=false)Long projectId,
                                                @RequestParam(value="state",required = false)Long state){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!CapacityVerificationProjectRepository.findById(projectId).isPresent())//判定为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            long tempState=CapacityVerificationProjectRepository.findById(projectId).get().getState();//记录原状态
            CapacityVerificationProjectRepository.updateProjectState(projectId,state);//更新项目状态
            //content,checkDate,personInCharge,state
            //System.out.println("changed object");
            //long tempState=CapacityVerificationProjectRepository.findAllByPlanId(planId).get().getState();
            Long planId=CapacityVerificationProjectRepository.findById(projectId).get().getplanId();//获得对应的计划id
            if(CapacityVerificationProjectRepository.findAllByPlanIdAndState(planId,0).isEmpty())//如果对应计划下所有记录状态均为1，置计划状态为1
                CapacityVerificationProjectRepository.updatePlanState(1,planId);
            if(tempState==0&&state==1)//状态由0转为1
            {
                json.put("code",300);
                json.put("msg","修改成功");
                return json;
            }
            if(tempState==1&&state==0)//状态由1转为0
            {
                CapacityVerificationProjectRepository.deleteRecord(projectId);//删除对应的记录
                CapacityVerificationProjectRepository.updatePlanState(0,planId);//将对应的计划状态记为0
            }
            try{
                json.put("code",200);
                json.put("msg","修改成功");
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

        return json;
    }

    @GetMapping(path="/getOneProject")//获取某个项目
    public @ResponseBody JSONObject getOneProject(@RequestParam(value="id",required=false)Long projectId)throws IOException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationProject project=new CapacityVerificationProject();
        if(!CapacityVerificationProjectRepository.findById(projectId).isPresent())//是否为空
        {
            try{
                json.put("code",500);
                json.put("msg","查找不到");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        else
        {
            json.put("code",200);
            json.put("msg","获取成功");
            json.put("data",CapacityVerificationProjectRepository.findById(projectId));//返回信息
            /*project= CapacityVerificationProjectRepository.getOne(projectId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("projectId",project.getprojectId());
                data.put("planId",project.getplanId());
                data.put("name",project.getName());
                data.put("method",project.getMethod());
                data.put("state",project.getState());
                data.put("note",project.getNote());
            }catch (JSONException e){
                e.printStackTrace();
            }
            //array.put(data);
            try{
                json.put("code",200);
                json.put("msg","获取成功");
                json.put("data",data);
            }catch (JSONException e){
                e.printStackTrace();
            }*/

        }

        return json;
    }
}
