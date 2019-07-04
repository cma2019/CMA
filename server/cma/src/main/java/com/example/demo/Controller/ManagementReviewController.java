package com.example.demo.Controller;

import com.example.demo.Model.ManagementFile;
import com.example.demo.Model.ManagementReview;
import com.example.demo.Repository.ManagementFileRepository;
import com.example.demo.Repository.ManagementReviewRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/cma/ManagementReview")
public class ManagementReviewController {
    @Autowired
    private ManagementReviewRepository managementReviewRepository;
    @Autowired
    private ManagementFileRepository managementFileRepository;

    @GetMapping(path = "getAll")
    public @ResponseBody
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",managementReviewRepository.findAll());
        return json;
    }

    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "year",required = false)long year,@RequestParam(value = "date",required = false)String date){
        JSONObject json=new JSONObject();
        ManagementReview managementReview=new ManagementReview();
        managementReview.setYear(year);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            managementReview.setDate(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<ManagementReview> list=managementReviewRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getYear()==year){
                json.put("code",210);
                json.put("msg","已存在");
                json.put("data",null);
                return json;
            }
        }
        managementReviewRepository.save(managementReview);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "year",required = false)long year){
        JSONObject json=new JSONObject();
        if(managementReviewRepository.existsByYear(year)==false){
            json.put("code",210);
            json.put("msg","不存在");
            json.put("data",null);
        }
        else{
            managementReviewRepository.deleteByYear(year);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
         return json;
    }
    @GetMapping("getAllFile")
    public @ResponseBody JSONObject getAllFile(@RequestParam(value = "year",required = false)long year){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",managementFileRepository.findAllByYear(year));
        return json;
    }
    @PostMapping(path = "addOneFile")
    public @ResponseBody JSONObject addOneFile(@RequestParam(value = "year",required = false)long year,@RequestParam(value = "fileName",required = false)String fileName){
        JSONObject json=new JSONObject();
        List<ManagementFile> list=managementFileRepository.findAllByYear(year);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getFileName()==fileName)
            {
                json.put("code",210);
                json.put("msg","已存在");
                json.put("data",null);
                return json;
            }
        }
        ManagementFile managementFile=new ManagementFile();
        managementFile.setYear(year);
        managementFile.setFileName(fileName);
        managementFileRepository.save(managementFile);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
    @PostMapping(path = "UpLoad")
    public @ResponseBody JSONObject UpLoad(File file){
        JSONObject json=new JSONObject();
        return json;
    }
}
