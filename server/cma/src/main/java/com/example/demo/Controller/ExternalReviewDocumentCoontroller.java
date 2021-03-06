package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.ExternalReviewDocument;
import com.example.demo.framework.Response;
import com.example.demo.FileControl.FileController;
import com.example.demo.Repository.ExternalReviewDocumentRepository;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @author YXP
 * 外部审查文档
 */
@Controller
@RequestMapping(path="cma/ExternalReviewDocument")
public class ExternalReviewDocumentCoontroller {
    @Autowired
    ExternalReviewDocumentRepository ERDRepository;
    @RequestMapping(path="/getAllFile/{year}",method = RequestMethod.GET)
    @ResponseBody
    public Response getAllFile(@PathVariable("year")long year)
    {
        Response response=new Response();
        Iterable<ExternalReviewDocument>list=ERDRepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i=0;i< ((List<ExternalReviewDocument>)list).size();i++)
        {
            if((((List<ExternalReviewDocument>) list).get(i)).getYear()==year) {
                JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<ExternalReviewDocument>) list).get(i)));
                jsons.add(ejson);
            }
        }
        response.code=200;
        response.data=jsons;
        response.msg="成功";
        return response;
    }
    @RequestMapping(path="/addOneFormData",method = RequestMethod.POST)
    @ResponseBody
    public Response addOneFormData(@RequestParam(value ="year" ,required = false)long year)
    {
        ExternalReviewDocument externalReviewDocument=new ExternalReviewDocument();
        Response response=new Response();
        externalReviewDocument.setYear(year);
        ERDRepository.save(externalReviewDocument);
        externalReviewDocument.setFileId(externalReviewDocument.getId());
        externalReviewDocument.setFileName(externalReviewDocument.getId()+".pdf");
        ERDRepository.save(externalReviewDocument);
        response.code=200;
        response.data=null;
        response.msg="收到数据";
        return response;
    }
    @RequestMapping(path="/addFile",method = RequestMethod.POST)
    @ResponseBody
    public  Response addFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                             @RequestParam(value ="year" ,required = false)long year){
        FileController fileController=new FileController();
        ExternalReviewDocument externalReviewDocument=new ExternalReviewDocument();
        externalReviewDocument.setYear(year);
        ERDRepository.save(externalReviewDocument);
        externalReviewDocument.setFileId(externalReviewDocument.getId());
        externalReviewDocument.setFileName(externalReviewDocument.getId()+".pdf");
        ERDRepository.save(externalReviewDocument);
        return  fileController.upload(file,request,externalReviewDocument.getFileName(),externalReviewDocument.getDir());
    }
    @RequestMapping(path="/addOneFile",method = RequestMethod.POST)
    @ResponseBody
    public  Response addOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        FileController fileController=new FileController();
        if(ERDRepository.findByFlag(1)==null)
            System.out.println("未找到");
        ExternalReviewDocument temp=ERDRepository.findByFlag(1);
        System.out.println(temp.getId());
        temp.setFlag(0);
        ERDRepository.save(temp);
        return  fileController.upload(file,request,temp.getFileName(),temp.getDir());
    }
    @RequestMapping(path="/modifyFormData/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response modifyFormData(
            @PathVariable("id")long id,
            @RequestParam(value="fileId",required =false)Long fileId,
            @RequestParam(value = "year",required = false)long year
    ){
        Response response=new Response();
        try{
            if(ERDRepository.findById(id)==null)
                throw new Exception("doesn't exist");
            ExternalReviewDocument temp=ERDRepository.findById(id);
            temp.setFileId(fileId);
            temp.setYear(year);
            temp.setFlag(1);
            ERDRepository.save(temp);
            response.code=200;
            response.msg="收到数据";
            response.data=null;
        }catch (Exception e){
            e.printStackTrace();
            response.msg="查找失败";
            response.code=500;
            response.data=null;
        }
        return response;
    }

    /**
     * 修改文件，首先需要查询旧文件，在服务器删除旧文件后上传新文件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(path="/modifyFile",method = RequestMethod.POST)
    @ResponseBody
    public Response modifyFormData(@RequestParam("file") MultipartFile file, HttpServletRequest request
    ){
        FileController fileController=new FileController();
        Response response=new Response();
        try{
            if(ERDRepository.findByFlag(1)==null)
                throw new Exception("doesn't exist");
            ExternalReviewDocument temp=ERDRepository.findByFlag(1);
            temp.setFlag(0);
            String name=temp.getFileName();
            ERDRepository.save(temp);
            if (!fileController.deletefile(name, temp.getDir()))
                throw new Exception("旧文件删除失败");
            return  fileController.upload(file,request,temp.getFileName(),temp.getDir());
        }catch (Exception e){
            e.printStackTrace();
            response.msg=e.getMessage();
            response.code=500;
            response.data=null;
            return response;
        }
    }
    /*@RequestMapping(value="/deleteOne/{year}",method=RequestMethod.POST)
    @ResponseBody
    public Response deleteOne(@PathVariable("year") long year)
    {
        Response response=new Response();
        FileController fileController=new FileController();
        try {
            int count =0;
            Iterable<ExternalReviewDocument>list=ERDRepository.findAll();
            for(int i=0;i< ((List<ExternalReviewDocument>)list).size();i++)
            {
                if((((List<ExternalReviewDocument>) list).get(i)).getYear()==year) {
                    count++;
                    ExternalReviewDocument temp=((List<ExternalReviewDocument>) list).get(i);
                    String name = temp.getFileName();
                    ERDRepository.deleteById((((List<ExternalReviewDocument>) list).get(i).getId()));
                    if (!fileController.deletefile(name, temp.getDir()))
                        throw new Exception("删除失败");
                }
                if(count==0)
                {
                    throw new Exception("删除失败，未找到该年份记录");
                }
            }
            response.data = null;
            response.msg = "成功";
            response.code = 200;
        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }*/

    /**
     * 删除数据和文档
     * @param id
     * @return
     */
    @RequestMapping(value="/deleteOne/{id}",method=RequestMethod.POST)
    @ResponseBody
    public Response deleteOneFile(@PathVariable("id") long id)
    {
        Response response=new Response();
        FileController fileController=new FileController();
        try {
            if(ERDRepository.findById(id)==null)
                throw new Exception("未找到该记录");
            ExternalReviewDocument temp=ERDRepository.findById(id);
            String name = temp.getFileName();
            ERDRepository.deleteById(temp.getId());
            response.data = null;
            response.msg = "数据删除成功";
            response.code = 200;
            boolean F=fileController.deletefile(name, temp.getDir());
            if (!F){
                throw new Exception("数据删除成功，文档删除失败");
            } else if(F){
                response.data = null;
                response.msg = "数据删除成功,文档删除成功";
                response.code = 200;
            }
        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }
    @RequestMapping(value = "/downloadFile/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  String getFile(@PathVariable("id") long id,HttpServletRequest request, HttpServletResponse response)
    {
        FileController fileController=new FileController();
        try{
            if(ERDRepository.findById(id)==null)
                throw new Exception("doesn't exist");
            ExternalReviewDocument temp=ERDRepository.findById(id);
            String name=temp.getFileName();
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
    @RequestMapping(value = "/getOneFile/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Response getOneFile(@PathVariable("id")long id)
    {
        Response response=new Response();
        try{
            if(ERDRepository.findById(id)==null)
                throw new Exception("文档不存在");
            ExternalReviewDocument temp=ERDRepository.findById(id);
            JSONObject json=JSONObject.parseObject(JSONObject.toJSONString(temp));
            response.code=200;
            response.data=json;
            response.msg="成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            response.code=500;
            response.data=null;
            response.msg=e.getMessage();
        }
        return response;
    }
}