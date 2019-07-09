package com.example.demo.Controller;


import com.example.demo.framework.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
//import com.alibaba.fastjson.JSONObject;
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
    //获取数据库中的自查列表
    public @ResponseBody JSONObject getAll()
    {
        List<SelfInspection> res=SelfInspectionRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        //遍历列表
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
        //列表为空
        else
        {
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
    //添加一项自查表项
    public @ResponseBody JSONObject addOne(@RequestParam(value = "name",required = false) String name,
                                           @RequestParam(value = "date",required = false) String date)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //以字符串接受参数，对数据做格式合法性判断
        try {
            java.sql.Date.valueOf(date);
        }catch (NumberFormatException e){
            js.put("code",513);
            js.put("msg","数据不合法");
            js.put("data",data);
            return js;
        }
        //名字应该是不可重复的，确保数据唯一性
        if(SelfInspectionRepository.findByName(name)!=null)
        {
            js.put("code",514);
            js.put("msg","数据重复");
            js.put("data",data);
            return js;
        }
        //参数完整且合法，正常添加
        String year=date.substring(0,4);
        String rname=year+"年度第"+name+"次自查文档集";
        SelfInspection s=new SelfInspection();
        s.setDate(java.sql.Date.valueOf(date));
        s.setName(rname);
        SelfInspectionRepository.save(s);
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path = "/deleteOne")
    //删除某项自查
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false) String id)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //以字符串接受参数，做参数格式合法性判断
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
       //调用findAll方法，获取自查下的自查文档列表
        List<SelfInspectionDocument> res= SelfInspectionDocumentRepository.findAllById(Long.parseLong(id));
        //遍历自查文档列表，调用后续的deleteOneFile方法，删除自查文档
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
        //自查文档删除完成后，删除自查
        SelfInspectionRepository.delete(SelfInspectionRepository.findById(Long.parseLong(id)));
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
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
    //获取某项自查下的全部文档
    public @ResponseBody JSONObject getAllFile(@RequestParam(value = "id",required = false) String id)
    {
        JSONObject js=new JSONObject();
        //以字符串接受参数，做参数格式合法性判断
        try{
            Long.parseLong(id);
        }catch (NumberFormatException E){
            js.put("code",511);
            js.put("msg","缺少请求参数");
            js.put("data",null);
            return js;
        }
        //调用findAll方法，获取自查下的自查文档列表
        List<SelfInspectionDocument> res= SelfInspectionDocumentRepository.findAllById(Long.parseLong(id));
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        //遍历自查文档列表，将必要信息存放到data中
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
       //自查下无自查文档
        else
        {
            code=210;
            msg="无有效信息返回";
        }
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
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
    //在某项自查下添加自查文档
    public @ResponseBody
    com.alibaba.fastjson.JSONObject addOneFile(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "id",required = false) long id,
                                               @RequestParam(value = "fileName",required = false) String fileName,
                                               HttpServletRequest request)
    {
        //前端需要传递自查编号，文件名，文件
        com.alibaba.fastjson.JSONObject js=new com.alibaba.fastjson.JSONObject();
        //参数判空
        if(fileName==null||fileName.equals(""))
        {
            Response res=new Response();
            res.code=511;
            res.msg="缺少请求参数";
            res.data=null;
            js.put("code",res.code);
            js.put("msg",res.msg);
            js.put("data",res.data);
            return js;
        }
        //获取前端传递文件的后缀
        FileController fileController=new FileController();
        SelfInspectionDocument sDoc=new SelfInspectionDocument();
        String[] str=file.getOriginalFilename().split("\\.");
        String suffix=str[str.length-1];
        //将后缀拼接到文件名中
        sDoc.setFileName(fileName+"."+suffix);
        sDoc.setId(id);
        //System.out.println("???");
        //System.out.println(sDoc.getFileName());
        //System.out.println("??");
        //传递参数调用文件上传接口
        Response res=  fileController.upload(file,request,sDoc.getFileName(),sDoc.getDir());
        /*
            根据调用结果判断文件上传是否成功，
            若已经存在同名文件会报错，否则
            上传成功，并在数据库中添加记录
         */
        if(res.code==200)
        {
            SelfInspectionDocumentRepository.save(sDoc);
        }
         /*
            返回json，前端解析code获取请求结果，
            解析data获取需要的数据
         */
        js.put("code",res.code);
        js.put("msg",res.msg);
        js.put("data",sDoc.getFileId());
        return js;
    }
    @RequestMapping(path="/modifyOneFormData",method= RequestMethod.POST)
    //只修改某文档的文件名
    public @ResponseBody JSONObject modifyOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                     @RequestParam(value = "fileId",required = false) String fileId)
    {
        //前端传递参数，以字符串接受
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
        //若存在同名文件不允许重命名
        if(newFile.exists())
        {
            js.put("code",512);
            js.put("msg","文件名已存在");
            js.put("data",null);
            return js;
        }
       //不存在同名文件，调用file的rename接口进行重命名
        else
            oldFile.renameTo(newFile);
        //重命名成功，更新数据库中的自查文档记录
        sDoc.setFileName(fileName+"."+suffix);
        SelfInspectionDocumentRepository.saveAndFlush(sDoc);
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/modifyOneFile")
    /*
        修改文件（可以同时改文件和文件名，
        也可以只改文件，但是必须传递文件参数）
     */
    public @ResponseBody Response modifyOneFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam(value ="fileId",required = false) long fileId,
                                                @RequestParam(value = "fileName",required = false) String fileName,
                                                @RequestParam(value = "id",required = false) long id,
                                                HttpServletRequest request)
    {
        FileController fileController=new FileController();
        //获取数据库中修改前的文档记录
        SelfInspectionDocument tmp=SelfInspectionDocumentRepository.findByFileId(fileId);
        String oldName=tmp.getFileName();
        //调用deleteFile接口删除旧文件（避免同名文件的判断）
        fileController.deletefile(oldName,tmp.getDir());
        //获取前端传递文件的后缀
        String[] str=file.getOriginalFilename().split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        //将后缀拼接到文件名中
        tmp.setId(id);
        tmp.setFileName(fileName+"."+suffix);
        //保存新文档记录
        SelfInspectionDocumentRepository.saveAndFlush(tmp);
        //保持新文件到本地
        return  fileController.upload(file,request,tmp.getFileName(),tmp.getDir());
    }
    @PostMapping(path = "/deleteOneFile")
    //删除某项自查文档
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false) long fileId)
    {
        JSONObject js=new JSONObject();
        SelfInspectionDocument s=SelfInspectionDocumentRepository.findByFileId(fileId);
        //数据判空，如果文档记录不存在报错
        if(s==null)
        {
            js.put("code",522);
            js.put("msg","数据不存在");
            js.put("data",null);
            return js;
        }
        FileController fileController=new FileController();
        boolean res=fileController.deletefile(s.getFileName(), s.getDir());
        //如果本地文件不存在报错
        if(!res)
        {
            js.put("code",522);
            js.put("msg","文件不存在");
            js.put("data",null);
            return js;
        }
        // 如果文件和文档记录均存在，正常删除
        SelfInspectionDocumentRepository.delete(s);
         //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @GetMapping(path = "/downloadFile/{fileId}")
    //下载文件，需要自查文档编号
    public @ResponseBody String downloadFile(@PathVariable("fileId") long fileId, HttpServletResponse response)
    {
        FileController fileController=new FileController();
        //System.out.println(fileId);
        //数据库中不存在文档记录报错
        try{
            if(SelfInspectionDocumentRepository.findByFileId(fileId)==null)
                throw new Exception("doesn't exist");
            SelfInspectionDocument temp=SelfInspectionDocumentRepository.findByFileId(fileId);
            String name=temp.getFileName();
            //System.out.println(name+"????");
            //文档记录存在，调用downloadFile函数下载文件并返回
            fileController.downloadFile(response,name,temp.getDir());
            return "下载成功";
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

