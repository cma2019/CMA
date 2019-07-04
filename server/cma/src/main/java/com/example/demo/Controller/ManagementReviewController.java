package com.example.demo.Controller;

import com.example.demo.FileControl.FileController;
import com.example.demo.Model.ManagementFile;
import com.example.demo.Model.ManagementReview;
import com.example.demo.Repository.ManagementFileRepository;
import com.example.demo.Repository.ManagementReviewRepository;
import com.example.demo.framework.Response;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    private MultipartFile mfile;
    private HttpServletRequest mrequest;

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
            List<ManagementFile> list=managementFileRepository.findAllByYear(year);
            for(int i=0;i<list.size();i++){
                ManagementFile temp=list.get(i);
                if(temp.getFile()!=null){
                    FileController fileController=new FileController();
                    String name=temp.getFileName();
                    fileController.deletefile(name, temp.getDir());
                }
                managementFileRepository.existsByFileId(temp.getFileId());
            }
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
    @PostMapping(path = "addFileData")
    public @ResponseBody Response addOneFile(@RequestParam(value = "year",required = false)long year,@RequestParam(value = "fileName",required = false)String fileName){
        //JSONObject json=new JSONObject();
        List<ManagementFile> list=managementFileRepository.findAllByYear(year);
        ManagementFile managementFile=new ManagementFile();
        managementFile.setYear(year);
        managementFile.setFileName(fileName);
        FileController fileController=new FileController();
        managementFile.setFile(fileName+".doc");
        managementFileRepository.save(managementFile);
        return fileController.upload(mfile,mrequest,managementFile.getFileName(),managementFile.getDir());
    }
    @GetMapping(path = "getOne")
    public  @ResponseBody JSONObject getOne(@RequestParam(value = "fileId",required = false)long fileId){
        JSONObject json=new JSONObject();
        if(managementFileRepository.existsByFileId(fileId)==false){
            json.put("code",210);
            json.put("msg","不存在");
            json.put("data",null);
        }
        else
        {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",managementFileRepository.findByFileId(fileId));
        }
        return json;
    }
    @PostMapping(path = "addOneFile")
    public @ResponseBody void UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        mfile=file;
        mrequest=request;
    }
    @PostMapping(path = "modifyOneFile")
    public @ResponseBody Response modifyOneFile(@RequestParam(value = "fileId",required = false)long fileId){
        FileController fileController=new FileController();
        ManagementFile managementFile=managementFileRepository.findByFileId(fileId);
        String name=managementFile.getFileName();
        fileController.deletefile(name, managementFile.getDir());
        return fileController.upload(mfile,mrequest,managementFile.getFileName(),managementFile.getDir());
    }
    @PostMapping(path = "deleteOneFile")
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false)long fileId){
        FileController fileController=new FileController();
        ManagementFile managementFile=managementFileRepository.findByFileId(fileId);
        String name=managementFile.getFileName();
        fileController.deletefile(name, managementFile.getDir());
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
}
