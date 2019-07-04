package com.example.demo.Controller;

import com.example.demo.Repository.CertificateRepository;
import com.example.demo.FileControl.FileController;
import com.example.demo.Model.Certificate;
import com.example.demo.framework.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(path="cma/Certificate")
public class CertificateController {
    @Autowired
    private CertificateRepository CRepository;
    @RequestMapping(path="addOne",method= RequestMethod.POST)
    @ResponseBody
    public Response addEquipment(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Certificate certificate=new Certificate();
        FileController fileController=new FileController();
        certificate.setFileName(certificate.getFileId()+".pdf");
        CRepository.save(certificate);
        certificate.setFileName(certificate.getFileId()+".pdf");
        CRepository.save(certificate);
        return fileController.upload(file,request,certificate.getFileName());
    }
    @RequestMapping(value="/getOne/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Response getOne(@PathVariable("id") long id){
        Response response=new Response();
        System.out.println("finfbyid:"+id);
        try {
            if (CRepository.findByFileId(id) == null) {
                throw new Exception("ID:"+id+" does not exist");
            }
            Certificate equipment = CRepository.findByFileId(id);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(equipment));
            response.code=200;
            response.data=ejson;
            response.msg="成功";
        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }

    @RequestMapping(value="/getAll",method=RequestMethod.GET)
    @ResponseBody
    public Response getAll(){
        Response response=new Response();
        JSONObject alljson=new JSONObject();
        Iterable<Certificate> list=CRepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<Certificate>) list).size(); i++)
        {
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<Certificate>) list).get(i)));
            jsons.add(ejson);
        }
        alljson.put("data",jsons);
        response.code=200;
        response.data=alljson;
        response.msg="成功";
        return response;
    }


    @RequestMapping(value="/deleteOne/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Response deleteOne(@PathVariable("id") long id)
    {
        Response response=new Response();
        FileController fileController=new FileController();
        try{
            if(CRepository.findById(id)==null)
                throw new Exception("doesn't exist");
            Certificate temp=CRepository.findByFileId(id);
            String name=temp.getFileName();
            CRepository.deleteById(id);
            fileController.deletefile(name,"F:/img/upload/");
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
    @RequestMapping(value="/download/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String downloadOne(@PathVariable("id") long id, HttpServletResponse response){
        FileController fileController=new FileController();
        try{
            if(CRepository.findById(id)==null)
                throw new Exception("doesn't exist");
            Certificate temp=CRepository.findByFileId(id);
            String name=temp.getFileName();
            return  fileController.downloadFile(response,name);
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}