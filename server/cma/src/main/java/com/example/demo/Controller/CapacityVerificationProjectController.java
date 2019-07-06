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
@RequestMapping(path="/cma/CapacityVerification")
public class CapacityVerificationProjectController {
    @Autowired
    private CapacityVerificationProjectRepository CapacityVerificationProjectRepository;

    @GetMapping(path="/getAllProject")
    public @ResponseBody JSONObject getAllProject(@RequestParam(value="planId",required = false)Long planId) throws JSONException {
        JSONObject json = new JSONObject(new LinkedHashMap());
        if (CapacityVerificationProjectRepository.findAllByPlanId(planId)==null) {
            try {
                json.put("code", 500);
                json.put("msg", "查找不到");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                json.put("code", 200);
                json.put("msg", "获取成功");
                json.put("data",CapacityVerificationProjectRepository.findAllByPlanId(planId));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    @PostMapping(path = "/addOneProject")
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
        CapacityVerificationProjectRepository.save(newProject);

        JSONObject json = new JSONObject(new LinkedHashMap());
        try {
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping(path="/deleteOneProject")
    public @ResponseBody JSONObject deleteProject(@RequestParam(value="id",required=false)Long projectId){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(CapacityVerificationProjectRepository.findById(projectId)==null)
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
            CapacityVerificationProjectRepository.deleteById(projectId);
            try{
                json.put("code",200);
                json.put("msg","删除成功");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return json;
    }

    @PostMapping(path="/modifyOneProject")
    public @ResponseBody JSONObject modifyProject(@RequestParam(value="id",required=false)Long projectId,
                                               @RequestParam(value="planId",required = false)Long planId,
                                               @RequestParam(value="name",required=false)String name,
                                               @RequestParam(value="method",required=false)String method,
                                               @RequestParam(value="state",required=false)Long state,
                                               @RequestParam(value="note",required=false)String note){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(CapacityVerificationProjectRepository.findById(projectId)==null)
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

            CapacityVerificationProjectRepository.updateById(projectId,planId,name,method,state,note);
            //content,checkDate,personInCharge,state
            //System.out.println("changed object");
            try{
                json.put("code",200);
                json.put("msg","修改成功");
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

        return json;
    }

    @GetMapping(path="/getOneProject")
    public @ResponseBody JSONObject getOneProject(@RequestParam(value="id",required=false)Long projectId)throws IOException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationProject project=new CapacityVerificationProject();
        if(CapacityVerificationProjectRepository.findById(projectId)==null)
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
            json.put("data",CapacityVerificationProjectRepository.findById(projectId));
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
