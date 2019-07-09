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
    /*
        删除某项内审
     */
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
        /*
            调用findAll获取某项内审记录下的内审文档列表
         */
        List<InternalAuditDocument> res= InternalAuditDocumentRepository.findAllByYear(year);
        /*
            遍历list，删除内审文档
         */
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                InternalAuditDocument tmp=res.get(i);
                System.out.println(tmp.getFileId()+"?");
                /*
                    调用删除deleteOneFile接口删除文件
                 */
                deleteOneFile(tmp.getFileId());
                /*
                    删除文档记录
                 */
                InternalAuditDocumentRepository.delete(tmp);
            }
        }
        /*
            删除内审记录
         */
        InternalAuditManagement iam=InternalAuditManagementRepository.findByYear(year);
        InternalAuditManagementRepository.delete(iam);
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
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
    /*
        获取某项内审下的全部内审文档
     */
    public @ResponseBody JSONObject getAllFile(@RequestParam(value = "year",required = false) long year){
        /*
            调用findAll方法获取内审文档列表
         */
        List<InternalAuditDocument> res= InternalAuditDocumentRepository.findAllByYear(year);
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        /*
            遍历内审文档列表，将必要信息保存在data
         */
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
        /*
            内审文档列表为空
         */
        else
        {
            code=210;
            msg="无有效信息返回";
        }
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        System.out.println(js);
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
    /*
        在某项内审下添加一项内审文档
     */
    public @ResponseBody
    com.alibaba.fastjson.JSONObject addOneFile(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "fileName",required = false) String fileName,
                                               @RequestParam(value = "year",required = false) long year,
                                               HttpServletRequest request)
    {
         /*
            前端需要传递内审编号，文件名，文件
         */
        FileController fileController=new FileController();
        InternalAuditDocument iDoc=new InternalAuditDocument();
        com.alibaba.fastjson.JSONObject js=new com.alibaba.fastjson.JSONObject();
        //System.out.println(file.getOriginalFilename());
        /*
            获取文件后缀
         */
        String[] str=file.getOriginalFilename().split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        iDoc.setYear(year);
        /*
            将后缀拼接到文件名中
         */
        iDoc.setFileName(fileName+"."+suffix);
        //System.out.println(year);
        //System.out.println(iDoc.getFileName());
        //System.out.println(sDoc.getFileName());
        /*
            调用upload方法，上传文件，同名文件会报错
         */
        Response res= fileController.upload(file,request,iDoc.getFileName(),iDoc.getDir());
        /*
            上传成功，添加文档记录
         */
        if(res.code==200)
        {
            InternalAuditDocumentRepository.save(iDoc);
        }
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
        js.put("code",res.code);
        js.put("msg",res.msg);
        js.put("id",iDoc.getFileId());
        System.out.println(js);
        return js;
    }
    @RequestMapping(path="/modifyOneFormData",method= RequestMethod.POST)
    /*
        重命名文档
     */
    public @ResponseBody JSONObject modifyOneFormData(@RequestParam(value = "fileName",required = false) String fileName,
                                        @RequestParam(value = "year",required = false) long year,
                                        @RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        InternalAuditDocument iDoc=InternalAuditDocumentRepository.findByFileId(fileId);
        String oldName=iDoc.getFileName();
        /*
            文件路径
         */
        String path="E:/CMA/FileSystem/";
        String filepath=path+iDoc.getDir()+"/";
        File oldFile = new File(filepath, oldName);
        /*
            获取原文件的后缀
         */
        String[] str=oldName.split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        /*
            将后缀拼接到新文件名
         */
        File newFile=new File(filepath,fileName+"."+suffix);
        /*
            若同名文件存在，不可重命名
         */
        if(newFile.exists())
        {
            js.put("code",512);
            js.put("msg","文件名已存在");
            js.put("data",null);
            return js;
        }
        /*
            正常重命名
         */
        else
            oldFile.renameTo(newFile);
        iDoc.setYear(year);
        iDoc.setFileName(fileName+"."+suffix);
        /*
            重命名成功，更新文档记录
         */
        InternalAuditDocumentRepository.saveAndFlush(iDoc);
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @PostMapping(path = "/modifyOneFile")
    /*
        修改文件（可以同时改文件和文件名，也可以只改文件，但是必须传递文件参数）
     */
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
        /*
            删除旧文件
         */
        fileController.deletefile(oldName,tmp.getDir());
        /*
            获取旧文档记录中的后缀
         */
        String[] str=file.getOriginalFilename().split("\\.");
        //System.out.println(str[str.length-1]);
        String suffix=str[str.length-1];
        /*
            将后缀拼接到新文件的文件名中
         */
        tmp.setFileName(fileName+"."+suffix);
        tmp.setYear(year);
        /*
            更新文档记录
         */
        InternalAuditDocumentRepository.saveAndFlush(tmp);
        /*
            调用upload方法，更新本地文件
         */
        return  fileController.upload(file,request,tmp.getFileName(),tmp.getDir());
    }
    @PostMapping(path = "/deleteOneFile")
    /*
        删除某项内审文档
     */
    public @ResponseBody JSONObject deleteOneFile(@RequestParam(value = "fileId",required = false) long fileId){
        JSONObject js=new JSONObject();
        InternalAuditDocument s=InternalAuditDocumentRepository.findByFileId(fileId);
        /*
            文档记录不存在，无法删除
         */
        if(s==null)
        {
            js.put("code",522);
            js.put("msg","数据不存在");
            js.put("data",null);
            return js;
        }
        FileController fileController=new FileController();
        boolean res=fileController.deletefile(s.getFileName(), s.getDir());
        /*
            文件不存在，无法删除
         */
        if(!res)
        {
            js.put("code",522);
            js.put("msg","文件不存在");
            js.put("data",null);
            return js;
        }
        /*
            文件删除，文档记录删除
         */
        InternalAuditDocumentRepository.delete(s);
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
        js.put("code",200);
        js.put("msg","成功");
        js.put("data",null);
        return js;
    }
    @GetMapping(path = "/downloadFile/{fileId}")
    /*
        下载文件，需要内审文档编号
     */
    public @ResponseBody String downloadFile(@PathVariable("fileId") long fileId, HttpServletResponse response) {
        FileController fileController=new FileController();
         /*
            数据库中不存在文档记录报错
         */
        //System.out.println(fileId);
        try{
            if(InternalAuditDocumentRepository.findByFileId(fileId)==null)
                throw new Exception("doesn't exist");
            InternalAuditDocument temp=InternalAuditDocumentRepository.findByFileId(fileId);
            String name=temp.getFileName();
             /*
                文档记录存在，调用downloadFile函数下载文件并返回
             */
            //System.out.println(name+"????");
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

