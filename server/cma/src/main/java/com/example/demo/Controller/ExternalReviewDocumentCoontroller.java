package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.ExternalReviewDocument;
import com.example.demo.framework.Response;
import com.example.demo.FileControl.FileController;
import com.example.demo.Repository.ExternalReviewDocumentRepository;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path="cma/ExternalReviewDocument")
public class ExternalReviewDocumentCoontroller {
    @Autowired
    ExternalReviewDocumentRepository ERDRepository;
    @RequestMapping(path="getAllFile",method = RequestMethod.GET)
    @ResponseBody
    public Response getAllFile()
    {
        Response response=new Response();
        Iterable<ExternalReviewDocument>list=ERDRepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i=0;i< ((List<ExternalReviewDocument>)list).size();i++)
        {
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<ExternalReviewDocument>) list).get(i)));
            jsons.add(ejson);
        }
        response.code=200;
        response.data=jsons;
        response.msg="成功";
        return response;
    }
    @RequestMapping(path="addOneFormData",method = RequestMethod.POST)
    @ResponseBody
    public Response addOneFormData(@RequestParam(value ="year" ,required = false)long year)
    {
        ExternalReviewDocument externalReviewDocument=new ExternalReviewDocument();
        Response response=new Response();
        externalReviewDocument.setYear(year);
        externalReviewDocument.setFlag(1);
        ERDRepository.save(externalReviewDocument);
        externalReviewDocument.setFileId(externalReviewDocument.getId());
        externalReviewDocument.setFileName(externalReviewDocument.getId()+".pdf");
        ERDRepository.save(externalReviewDocument);
        response.code=200;
        response.data=null;
        response.msg="收到数据";
        return response;
    }
    @RequestMapping(path="addOneFile",method = RequestMethod.POST)
    @ResponseBody
    public  Response addOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        ExternalReviewDocument temp=ERDRepository.findByFlag(1);
        
    }


}
