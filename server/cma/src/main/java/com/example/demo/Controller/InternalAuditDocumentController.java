package com.example.demo.Controller;


import com.example.demo.framework.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Repository.InternalAuditManagementRepository;
import com.example.demo.Repository.InternalAuditDocumentRepository;
import com.example.demo.Model.InternalAuditManagement;
import com.example.demo.Model.InternalAuditDocument;
import com.example.demo.FileControl.FileController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/InternalAuditManagement")
public class InternalAuditDocumentController {
    @Autowired
    private InternalAuditManagementRepository InternalAuditManagementRepository;
    @Autowired
    private InternalAuditDocumentRepository InternalAuditDocumentRepository;
    long add_year=0;
    String add_name="null";
    long modify_year=0;
    String modify_name="null";
    long modify_id=0;
    @PostMapping(path = "/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "year",required = false) long year){
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        //JSONObject data=null;
        /*
        try{
            Long.parseLong(year);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",null);
            return js;
        }*/
        List<InternalAuditDocument> res= InternalAuditDocumentRepository.findAllByYear(year);
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                InternalAuditDocument tmp=res.get(i);
                System.out.println(tmp.getFileId()+"?");
                deleteOneFile(tmp.getFileId());
                InternalAuditDocumentRepository.delete(tmp);
            }
        }
        InternalAuditManagement iam=InternalAuditManagementRepository.findByYear(year);
        InternalAuditManagementRepository.delete(iam);
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
    public @ResponseBody JSONObject getAllFile(@RequestParam(value = "year",required = false) long year){
        List<InternalAuditDocument> res= InternalAuditDocumentRepository.findAllByYear(year);
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("year",res.get(i).getYear());
                tmp.put("fileId",res.get(i).getFileId());
                String entire=res.get(i).getFileName();
                System.out.println(res.get(i).getFileName());
                tmp.put("fileName",entire.substring(0,entire.indexOf(".")));
                tmp.put("file",res.get(i).getFileName());
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
                                     @RequestParam(value = "year",required = false) long year){
        JSONObject js=new JSONObject();
        System.out.println(fileName);
        add_name=fileName;
        add_year=year;
        /*
        if(InternalAuditDocumentRepository.findByYear(year)!=null)
        {
            js.put("code",500);
            js.put("msg","年份重复");
            js.put("data",null);
            return js;
        }*/
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/addOneFile")
    public @ResponseBody Response addOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        /*
        if(InternalAuditDocumentRepository.findByYear(add_year)!=null)
        {
            Response res=new Response();
            res.code=500;
            res.msg="年份重复";
            res.data=null;
            return res;
        }
        */
        FileController fileController=new FileController();
        InternalAuditDocument sDoc=new InternalAuditDocument();
        sDoc.setYear(add_year);
        System.out.println("?");
        System.out.println(file.getOriginalFilename());
        String[] str=file.getOriginalFilename().split("\\.");
        System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        sDoc.setFileName(add_name+"."+suffix);
        InternalAuditDocumentRepository.save(sDoc);
        System.out.println(sDoc.getFileName());
        System.out.println("??");
        return  fileController.upload(file,request,sDoc.getFileName(),sDoc.getDir());
    }
    @RequestMapping(path="/modifyOneFormData",method= RequestMethod.POST)
    @ResponseBody
    public JSONObject modifyOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                        @RequestParam(value = "year",required = false) long year,
                                        @RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        System.out.println(fileName);
        modify_year=year;
        modify_name=fileName;
        modify_id=fileId;
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/modifyOneFile")
    public @ResponseBody Response modifyOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        FileController fileController=new FileController();
        InternalAuditDocument tmp=InternalAuditDocumentRepository.findByFileId(modify_id);
        /*if(InternalAuditDocumentRepository.findByYear(modify_year)!=null)
        {
            Response res=new Response();
            res.code=500;
            res.msg="修改后年份重复";
            res.data=null;
            return res;
        }*/
        String oldName=tmp.getFileName();
        fileController.deletefile(oldName,tmp.getDir());
        String[] str=file.getOriginalFilename().split("\\.");
        System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        tmp.setFileName(modify_name+"."+suffix);
        InternalAuditDocumentRepository.saveAndFlush(tmp);
        return  fileController.upload(file,request,tmp.getFileName(),tmp.getDir());
    }
    @PostMapping(path = "/deleteOneFile")
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        System.out.println(fileId);
        InternalAuditDocument s=InternalAuditDocumentRepository.findByFileId(fileId);
        FileController fileController=new FileController();
        System.out.println(s.getFileName());
        fileController.deletefile(s.getFileName(), s.getDir());
        InternalAuditDocumentRepository.delete(s);
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @GetMapping(path = "/downloadFile/{fileId}")
    public @ResponseBody String downloadFile(@PathVariable("fileId") long fileId, HttpServletResponse response) {
        FileController fileController=new FileController();
        System.out.println(fileId);
        try{
            if(InternalAuditDocumentRepository.findByFileId(fileId)==null)
                throw new Exception("doesn't exist");
            InternalAuditDocument temp=InternalAuditDocumentRepository.findByFileId(fileId);
            String name=temp.getFileName();
            System.out.println(name+"????");
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}

