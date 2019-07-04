package com.example.demo.Controller;


import com.example.demo.framework.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Repository.SelfInspectionRepository;
import com.example.demo.Repository.SelfInspectionDocumentRepository;
import com.example.demo.Model.SelfInspection;
import com.example.demo.Model.SelfInspectionDocument;
import com.example.demo.FileControl.FileController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SelfInspection")
public class SelfInspectionController {
    @Autowired
    private SelfInspectionRepository SelfInspectionRepository;
    @Autowired
    private SelfInspectionDocumentRepository SelfInspectionDocumentRepository;
    SelfInspectionDocument sDoc=new SelfInspectionDocument();
    @GetMapping(path = "/getAll")
    public @ResponseBody JSONObject getAll(){
        List<SelfInspection> res=SelfInspectionRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("id",res.get(i).getId());
                tmp.put("name",res.get(i).getName());
                tmp.put("date",res.get(i).getDate());
                data.add(tmp);
            }
        }
        else{
            code=522;
            msg="数据不存在";
            //data=null;
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path = "/addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "name",required = false) String name,
                                           @RequestParam(value = "date",required = false) String date){
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        //JSONObject data=null;
        String year=date.substring(0,4);
        String rname=year+"年度第"+name+"自查文档集";
        SelfInspection s=new SelfInspection();
        s.setDate(java.sql.Date.valueOf(date));
        s.setName(rname);
        SelfInspectionRepository.save(s);
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false) String id){
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        //JSONObject data=null;
        try{
            Long.parseLong(id);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",null);
            return js;
        }
        SelfInspectionRepository.deleteById(Long.parseLong(id));
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    /*
    @GetMapping(path = "/getExample")
    public @ResponseBody ResponseEntity getExample() {

    }
    */
    @GetMapping(path = "/getAllFile")
    public @ResponseBody JSONObject getAllFile(@RequestParam(value = "id",required = false) String id){
        List<SelfInspectionDocument> res= SelfInspectionDocumentRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("year",res.get(i).getFileName().substring(0,4));
                tmp.put("fileId",res.get(i).getFileId());
                String entire=res.get(i).getFileName();
                System.out.println(res.get(i).getFileName());
                tmp.put("fileName",res.get(i).getFileName());
                tmp.put("file",res.get(i).getFileName()+".pdf");
                data.add(tmp);
            }
        }
        else
        {
            code=522;
            msg="数据不存在";
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @RequestMapping(path="/addOneFormData",method= RequestMethod.POST)
    @ResponseBody
    public JSONObject addOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                     @RequestParam(value = "id",required = false) String id){
        JSONObject js=new JSONObject();
        System.out.println(fileName);
        sDoc.setFileName(fileName);
        sDoc.setId(Long.parseLong(id));
        SelfInspectionDocumentRepository.save(sDoc);
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/addOneFile")
    public @ResponseBody Response addOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        FileController fileController=new FileController();
        System.out.println("?");
        System.out.println(file.getOriginalFilename());
        String[] str=file.getOriginalFilename().split("\\.");
        System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        sDoc.setFileName(sDoc.getFileName()+suffix);
        System.out.println("??");
        return  fileController.upload(file,request,sDoc.getFileName(),sDoc.getDir());
    }
    /*
    @PostMapping(path = "/modifyOneFile")
    public @ResponseBody JSONObject modifyOneFile(){

    }*/
    @PostMapping(path = "/deleteOneFile")
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false) String fileId){
        JSONObject js=new JSONObject();
        SelfInspectionDocument s=SelfInspectionDocumentRepository.findByFileId(Long.parseLong(fileId));
        FileController fileController=new FileController();
        System.out.println(s.getFileName());
        fileController.deletefile(s.getFileName(), s.getDir());
        SelfInspectionDocumentRepository.deleteById(Long.parseLong(fileId));
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @GetMapping(path = "/downloadFile")
    public @ResponseBody String downloadFile(@PathVariable("fileId") long fileId, HttpServletResponse response) {
        FileController fileController=new FileController();
        try{
            if(SelfInspectionDocumentRepository.findById(fileId)==null)
                throw new Exception("doesn't exist");
            SelfInspectionDocument temp=SelfInspectionDocumentRepository.findByFileId(fileId);
            String name=temp.getFileName();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}
//select * from self_inspection_document;
