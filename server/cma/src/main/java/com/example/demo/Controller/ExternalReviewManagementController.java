package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.EquipmentUse;
import com.example.demo.Model.ExternalReviewManagement;
import com.example.demo.framework.Response;
import com.example.demo.Repository.ExternalReviewManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="cma/ExternalReviewManagement")
public class ExternalReviewManagementController {
    @Autowired
    ExternalReviewManagementRepository ERMRepository;
    @RequestMapping(path="addOne",method = RequestMethod.POST)
    @ResponseBody
    public Response addOne(
        @RequestParam(value="year",required = false)long year,
        @RequestParam(value = "date",required = false)Date date)
    {
         ExternalReviewManagement externalReviewManagement=new ExternalReviewManagement();
         Response response=new Response();
         externalReviewManagement.setDate(date);
         externalReviewManagement.setYear(year);
         ERMRepository.save(externalReviewManagement);
         response.code=200;
         response.msg="成功";
         response.data=null;
         return response;
    }
    @RequestMapping(path="getAll",method = RequestMethod.GET)
    @ResponseBody
    public Response getAll(){
        Response response=new Response();
        JSONObject alljson=new JSONObject();
        Iterable<ExternalReviewManagement> list=ERMRepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<ExternalReviewManagement>) list).size(); i++){
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<ExternalReviewManagement>) list).get(i)));
            jsons.add(ejson);
        }
        response.data=jsons;
        response.code=200;
        response.msg="成功";
        return  response;
    }
    @RequestMapping(path="deleteOne/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response deleteOne(@PathVariable("id")long id)
    {
        Response response=new Response();
        try{
            if(ERMRepository.findById(id)==null)
                throw new Exception("不存在");
            ERMRepository.deleteById(id);
            response.data=null;
            response.msg="成功";
            response.code=200;

        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
