package com.example.demo.Controller;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.Repository.SelfInspectionRepository;
import com.example.demo.Repository.SelfInspectionDocumentRepository;
import com.example.demo.Model.SelfInspection;
import com.example.demo.Model.SelfInspectionDocument;
import com.example.demo.FileControl.FileController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SelfInspection")
public class SelfInspectionController {
    @Autowired
    private SelfInspectionRepository SelfInspectionRepository;
    @Autowired
    private SelfInspectionDocumentRepository SelfInspectionDocumentRepository;
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
    /*
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
    @PostMapping(path = "/addOneFile")
    public @ResponseBody JSONObject addOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        SelfInspectionDocument sDoc=new SelfInspectionDocument();
        FileController fileController=new FileController();
        sDoc.setFileName(sDoc.getFileName());
    }
    @PostMapping(path = "/modifyOneFile")
    public @ResponseBody JSONObject modifyOneFile(){

    }
    @PostMapping(path = "/deleteOneFile")
    public @ResponseBody JSONObject deleteOneFile(){

    }
    @GetMapping(path = "/downloadFile")
    public @ResponseBody ResponseEntity downloadFile() {

    }*/
}
