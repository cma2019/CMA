package com.example.demo.Controller;


import com.example.demo.framework.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
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
//import java.sql.Date;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping(path="/cma/InternalAuditManagement")
public class InternalAuditDocumentController {
    @Autowired
    private InternalAuditManagementRepository InternalAuditManagementRepository;
    @Autowired
    private InternalAuditDocumentRepository InternalAuditDocumentRepository;
    /*private long add_year=0;
    private String add_name="null";
    private long modify_year=0;
    private String modify_name="null";
    private long modify_id=0;*/
    @PostMapping(path = "/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "year",required = false) long year){
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
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
        js.put("data",data);
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
                //System.out.println(res.get(i).getFileName());
                tmp.put("fileName",entire.substring(0,entire.indexOf(".")));
                tmp.put("file",res.get(i).getFileName());
                data.add(tmp);
            }
        }
        else
        {
            code=210;
            msg="无有效信息返回";
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    /*
    @RequestMapping(path="/addOneFormData",method= RequestMethod.POST)
    public @ResponseBody JSONObject addOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                     @RequestParam(value = "year",required = false) long year){
        JSONObject js=new JSONObject();
        System.out.println(fileName);
        InternalAuditDocument iDoc=new InternalAuditDocument();
        iDoc.setFlag(1);
        iDoc.setYear(year);
        iDoc.setFileName(fileName);
        InternalAuditDocumentRepository.save(iDoc);
        /*
        if(InternalAuditDocumentRepository.findByYear(year)!=null)
        {
            js.put("code",500);
            js.put("msg","年份重复");
            js.put("data",null);
            return js;
        }
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }*/
    @PostMapping(path = "/addOneFile")
    public @ResponseBody
    com.alibaba.fastjson.JSONObject addOneFile(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "fileName",required = false) String fileName,
                                               @RequestParam(value = "year",required = false) long year,
                                               HttpServletRequest request)
    {
        FileController fileController=new FileController();
        InternalAuditDocument iDoc=new InternalAuditDocument();
        System.out.println(file.getOriginalFilename());
        String[] str=file.getOriginalFilename().split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        iDoc.setYear(year);
        iDoc.setFileName(fileName+"."+suffix);
        System.out.println(year);
        System.out.println(iDoc.getFileName());
        InternalAuditDocumentRepository.save(iDoc);
        //System.out.println(sDoc.getFileName());
        Response res= fileController.upload(file,request,iDoc.getFileName(),iDoc.getDir());
        com.alibaba.fastjson.JSONObject js=new com.alibaba.fastjson.JSONObject();
        js.put("code",res.code);
        js.put("msg",res.msg);
        js.put("id",iDoc.getFileId());
        return js;
    }
    @RequestMapping(path="/modifyOneFormData",method= RequestMethod.POST)
    public @ResponseBody JSONObject modifyOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                        @RequestParam(value = "year",required = false) long year,
                                        @RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        InternalAuditDocument iDoc=InternalAuditDocumentRepository.findByFileId(fileId);
        String oldName=iDoc.getFileName();
        String path="E:/CMA/FileSystem/";
        String filepath=path+iDoc.getDir()+"/";
        File oldFile = new File(filepath, oldName);
        String[] str=oldName.split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        File newFile=new File(filepath,fileName+"."+suffix);
        if(newFile.exists())
        {
            js.put("code",512);
            js.put("msg","文件名已存在");
            js.put("data",null);
            return js;
        }
        else
            oldFile.renameTo(newFile);
        iDoc.setYear(year);
        iDoc.setFileName(fileName+"."+suffix);
        InternalAuditDocumentRepository.saveAndFlush(iDoc);
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/modifyOneFile")
    public @ResponseBody Response modifyOneFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam(value = "fileId",required = false) long fileId,
                                                @RequestParam(value = "year",required = false) long year,
                                                @RequestParam(value = "fileName",required = false) String fileName,
                                                HttpServletRequest request){
        FileController fileController=new FileController();
        InternalAuditDocument tmp= InternalAuditDocumentRepository.findByFileId(fileId);
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
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        tmp.setFileName(fileName+"."+suffix);
        tmp.setYear(year);
        InternalAuditDocumentRepository.saveAndFlush(tmp);
        return  fileController.upload(file,request,tmp.getFileName(),tmp.getDir());
    }
    @PostMapping(path = "/deleteOneFile")
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        InternalAuditDocument s=InternalAuditDocumentRepository.findByFileId(fileId);
        if(s==null)
        {
            js.put("code",522);
            js.put("msg","数据不存在");
            js.put("data",null);
            return js;
        }
        FileController fileController=new FileController();
        boolean res=fileController.deletefile(s.getFileName(), s.getDir());
        if(!res)
        {
            js.put("code",522);
            js.put("msg","文件不存在");
            js.put("data",null);
            return js;
        }
        InternalAuditDocumentRepository.delete(s);
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @GetMapping(path = "/downloadFile/{fileId}")
    public @ResponseBody String downloadFile(@PathVariable("fileId") long fileId, HttpServletResponse response) {
        FileController fileController=new FileController();
        //System.out.println(fileId);
        try{
            if(InternalAuditDocumentRepository.findByFileId(fileId)==null)
                throw new Exception("doesn't exist");
            InternalAuditDocument temp=InternalAuditDocumentRepository.findByFileId(fileId);
            String name=temp.getFileName();
            System.out.println(name+"????");
            fileController.downloadFile(response,name,temp.getDir());
            return "下载成功";
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}
//select * from internal_audit_document;
//delete from internal_audit_document where file_id=
//alter table internal_audit_document ENGINE =InnoDB;

