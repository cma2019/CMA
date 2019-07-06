package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.FileControl.FileController;
import com.example.demo.Model.CapacityVerificationPlan;
import com.example.demo.Repository.CapacityVerificationPlanRepository;

import com.example.demo.framework.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class CapacityVerificationPlanController {

    Long tempId;

    @Autowired
    private CapacityVerificationPlanRepository CapacityVerificationPlanRepository;

    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject getAllPlan() throws JSONException {
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(CapacityVerificationPlanRepository.findAll()==null)
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
            try{
                json.put("code",200);
                json.put("msg","获取成功");

                json.put("data",CapacityVerificationPlanRepository.findAll());
            }catch (JSONException e){
                e.printStackTrace();
            }

            /*List<CapacityVerificationPlan> planList= CapacityVerificationPlanRepository.findAll();
            JSONObject data=new JSONObject();
            JSONArray array=new JSONArray();
            for(CapacityVerificationPlan plan:planList){
                JSONObject singlePlan=new JSONObject();
                //JSONObject array=new JSONObject();
                singlePlan.put("planId",plan.getplanID());
                singlePlan.put("name",plan.getName());
                singlePlan.put("organizer",plan.getOrganizer());
                singlePlan.put("state",plan.getState());
                singlePlan.put("year",plan.getYear());
                singlePlan.put("note",plan.getNote());
                singlePlan.put("analysis",plan.getAnalysis());
                array.add(singlePlan);
                //System.out.println(array);

            }
            json.put("data",array);*/
        }
        return json;
        /*response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toString());*/
        //System.out.println(json);
        //return json.toString();
    }

    @PostMapping(path = "/addOne")
    public @ResponseBody JSONObject addPlan(@RequestParam(value = "name", required = false) String name,
                   @RequestParam(value = "organizer", required = false) String organizer,
                   @RequestParam(value = "year", required = false) String year,
                   @RequestParam(value = "note", required = false) String note){
        CapacityVerificationPlan newPlan = new CapacityVerificationPlan();
        newPlan.setName(name);
        newPlan.setOrganizer(organizer);

        newPlan.setYear(year);
        newPlan.setNote(note);
        newPlan.setState(0);

        CapacityVerificationPlanRepository.save(newPlan);

        JSONObject json = new JSONObject(new LinkedHashMap());
        try {
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping(path="/deleteOne")
    public @ResponseBody JSONObject deletePlan(@RequestParam(value="id",required=false)Long planId){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(CapacityVerificationPlanRepository.findById(planId)==null)
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
            CapacityVerificationPlanRepository.deleteById(planId);
            try{
                json.put("code",200);
                json.put("msg","删除成功");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return json;
    }

    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modifyPlan(@RequestParam(value="id",required=false)Long planId,
                                         @RequestParam(value="name",required=false)String name,
                                         @RequestParam(value="organizer",required=false)String organizer,
                                         @RequestParam(value="state",required=false)Long state,
                                         @RequestParam(value="year",required=false)String year,
                                         @RequestParam(value="note",required=false)String note){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(CapacityVerificationPlanRepository.findById(planId)==null)
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

            CapacityVerificationPlanRepository.updateById(planId,name,organizer,state,year,note);
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

    @GetMapping(path="/getOne")
    public @ResponseBody JSONObject getOnePlan(@RequestParam(value="id",required=false)Long planId)throws IOException{
        JSONObject json=new JSONObject(new LinkedHashMap());
        CapacityVerificationPlan plan=new CapacityVerificationPlan();
        if(CapacityVerificationPlanRepository.findById(planId)==null)
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
            json.put("data",CapacityVerificationPlanRepository.findById(planId));
            /*plan= CapacityVerificationPlanRepository.getOne(planId);
            JSONObject data=new JSONObject(new LinkedHashMap());
            JSONArray array=new JSONArray();
            try{
                data.put("planId",plan.getplanId());
                data.put("name",plan.getName());
                data.put("organizer",plan.getOrganizer());
                data.put("state",plan.getState());
                data.put("year",plan.getYear());
                data.put("note",plan.getNote());
                data.put("analysis",plan.getAnalysis());
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

    @PostMapping(path="/uploadAnalysis")
    public @ResponseBody
    JSONObject uploadAnalysis(@RequestParam(value="id",required = false)Long id){
        JSONObject json=new JSONObject();
        tempId=id;
        json.put("code",200);
        json.put("msg","信息获取成功");
        return json;
    }

    @PostMapping(path="/uploadAnalysisFile")
    public @ResponseBody Response addAnalysis(@RequestParam(value="file",required = false)MultipartFile file, HttpServletRequest request){
        System.out.println("Upload in");
        FileController fileController=new FileController();
        System.out.println(tempId);
        System.out.println(file.getOriginalFilename());
        String fileName=tempId+"."+fileController.getsuffix(file.getOriginalFilename());
        System.out.println(fileName);
        CapacityVerificationPlanRepository.updateAnalysis(fileName,tempId);
        CapacityVerificationPlan plan=CapacityVerificationPlanRepository.findByPlanId(tempId);
        plan.setAnalysis(fileName);
        tempId=null;
        System.out.println(plan.getAnalysis());
        System.out.println(fileName);
        return fileController.upload(file,request,plan.getAnalysis(),plan.getDir());
    }

    @RequestMapping(value="/downloadAnalysis/{id}",method=RequestMethod.GET)
    public @ResponseBody String downloadStandard(@PathVariable("id")Long id, HttpServletResponse response){
        System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(CapacityVerificationPlanRepository.findById(id)==null)
                throw new Exception("不存在");
            System.out.println(id);
            CapacityVerificationPlan temp=CapacityVerificationPlanRepository.findByPlanId(id);
            String name=temp.getAnalysis();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }

    @PostMapping(path="/deleteAnalysis")
    public @ResponseBody JSONObject deleteStandard(@RequestParam(value="id",required =false)Long id){
        JSONObject json=new JSONObject();
        if(CapacityVerificationPlanRepository.findById(id)==null){
            json.put("code",500);
            json.put("msg","文件不存在");
        }
        else
        {
            FileController fileController=new FileController();
            CapacityVerificationPlan temp=CapacityVerificationPlanRepository.findByPlanId(id);
            String fileName=temp.getAnalysis();
            fileController.deletefile(fileName,temp.getDir());
            CapacityVerificationPlanRepository.updateAnalysis(null,id);
            json.put("code",200);
            json.put("msg","删除成功");
        }
        return json;
    }
}
