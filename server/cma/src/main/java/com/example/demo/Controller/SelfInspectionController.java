package com.example.demo.Controller;


import com.example.demo.framework.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
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
//import java.sql.Date;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SelfInspection")
public class SelfInspectionController {
    @Autowired
    private SelfInspectionRepository SelfInspectionRepository;
    @Autowired
    private SelfInspectionDocumentRepository SelfInspectionDocumentRepository;
    /*private String add_id="0";
    private String add_name="null";
    private String modify_Id="0";
    private String modify_name="null";*/
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
            code=210;
            msg="无有效信息返回";
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
        JSONObject data=new JSONObject();
        try {
            java.sql.Date.valueOf(date);
        }catch (NumberFormatException e){
            js.put("code",513);
            js.put("msg","数据不合法");
            js.put("data",data);
            return js;
        }
        String year=date.substring(0,4);
        String rname=year+"年度第"+name+"次自查文档集";
        SelfInspection s=new SelfInspection();
        s.setDate(java.sql.Date.valueOf(date));
        s.setName(rname);
        SelfInspectionRepository.save(s);
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path = "/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false) String id){
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        try{
            Long.parseLong(id);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        List<SelfInspectionDocument> res= SelfInspectionDocumentRepository.findAllById(Long.parseLong(id));
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                SelfInspectionDocument tmp=res.get(i);
                System.out.println(tmp.getFileId()+"?");
                deleteOneFile(tmp.getFileId());
                SelfInspectionDocumentRepository.delete(tmp);
            }
        }
        SelfInspectionRepository.deleteById(Long.parseLong(id));
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
    public @ResponseBody JSONObject getAllFile(@RequestParam(value = "id",required = false) String id){
        List<SelfInspectionDocument> res= SelfInspectionDocumentRepository.findAllById(Long.parseLong(id));
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
                                     @RequestParam(value = "id",required = false) String id){
        JSONObject js=new JSONObject();
        if(fileName==null||fileName.equals(""))
        {
            js.put("code",511);
            js.put("msg","缺少请求参数");
            js.put("data",null);
            return js;
        }
        SelfInspectionDocument sdoc=new SelfInspectionDocument();
        sdoc.setFlag(1);
        sdoc.setFileName(fileName);
        sdoc.setId(Long.parseLong(id));
        SelfInspectionDocumentRepository.save(sdoc);
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }*/
    @PostMapping(path = "/addOneFile")
    public @ResponseBody Response addOneFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "id",required = false) long id,
                                             @RequestParam(value = "fileName",required = false) String fileName,
                                             HttpServletRequest request){
        //JSONObject js=new JSONObject();
        if(fileName==null||fileName.equals(""))
        {
            Response res=new Response();
            res.code=511;
            res.msg="缺少请求参数";
            res.data=null;
            return res;
        }
        FileController fileController=new FileController();
        SelfInspectionDocument sDoc=new SelfInspectionDocument();
        String[] str=file.getOriginalFilename().split("\\.");
        String suffix=str[str.length-1];
        sDoc.setFileName(fileName+"."+suffix);
        sDoc.setId(id);
        //System.out.println("???");
        SelfInspectionDocumentRepository.save(sDoc);
        //System.out.println(sDoc.getFileName());
        //System.out.println("??");
        return  fileController.upload(file,request,sDoc.getFileName(),sDoc.getDir());
    }
    @RequestMapping(path="/modifyOneFormData",method= RequestMethod.POST)
    public @ResponseBody JSONObject modifyOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                     @RequestParam(value = "fileId",required = false) String fileId){
        JSONObject js=new JSONObject();
        //System.out.println(fileName);
        SelfInspectionDocument sDoc=SelfInspectionDocumentRepository.findByFileId(Long.parseLong(fileId));
        String oldName=sDoc.getFileName();
        String path="E:/CMA/FileSystem/";
        String filepath=path+sDoc.getDir()+"/";
        File oldFile = new File(filepath, oldName);
        String[] str=oldName.split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        File newFile=new File(filepath,fileName+"."+suffix);
        if(newFile.exists())
        {
            js.put("code",514);
            js.put("msg","文件名已存在");
            js.put("data",null);
            return js;
        }
        else
            oldFile.renameTo(newFile);
        sDoc.setFileName(fileName+"."+suffix);
        SelfInspectionDocumentRepository.saveAndFlush(sDoc);
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/modifyOneFile")
    public @ResponseBody Response modifyOneFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam(value ="fileId",required = false) long fileId,
                                                @RequestParam(value = "fileName",required = false) String fileName,
                                                @RequestParam(value = "id",required = false) long id,
                                                HttpServletRequest request){
        FileController fileController=new FileController();
        SelfInspectionDocument tmp=SelfInspectionDocumentRepository.findByFileId(fileId);
        String oldName=tmp.getFileName();
        fileController.deletefile(oldName,tmp.getDir());
        String[] str=file.getOriginalFilename().split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        tmp.setId(id);
        tmp.setFileName(fileName+"."+suffix);
        SelfInspectionDocumentRepository.saveAndFlush(tmp);
        return  fileController.upload(file,request,tmp.getFileName(),tmp.getDir());
    }
    @PostMapping(path = "/deleteOneFile")
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        SelfInspectionDocument s=SelfInspectionDocumentRepository.findByFileId(fileId);
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
        SelfInspectionDocumentRepository.delete(s);
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
            if(SelfInspectionDocumentRepository.findByFileId(fileId)==null)
                throw new Exception("doesn't exist");
            SelfInspectionDocument temp=SelfInspectionDocumentRepository.findByFileId(fileId);
            String name=temp.getFileName();
            //System.out.println(name+"????");
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}
//select * from self_inspection;
//delete from self_inspection where file_id=
//alter table self_inspection ENGINE =InnoDB;
//select * from self_inspection_document;
//delete from self_inspection_document where file_id=
//alter table self_inspection_document ENGINE =InnoDB;

