package com.example.demo.Controller;
import com.example.demo.Repository.qsmRepository;
import com.example.demo.FileControl.FileController;
import com.example.demo.Model.qsm;
import com.example.demo.framework.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="cma/Certificate")
public class qsmController {
    @Autowired
    private qsmRepository QRepository;
    MultipartFile mfile;
    HttpServletRequest mrequest;

    @RequestMapping(path="addOneFormData",method= RequestMethod.POST)
    @ResponseBody
    public Response addOneFormData(
            @RequestParam (value="state",required=false)Byte state,
            @RequestParam (value="current",required=false)Byte current,
            @RequestParam (value="modifyTime",required=false) Date modifyTime,
            @RequestParam (value="modifier",required=false)String modifier,
            @RequestParam (value="modifyContent",required=false)String modifyContent
    ){
        FileController fileController=new FileController();
            qsm Qsm=new qsm();
            Qsm.setState(state);
            Qsm.setCurrent(current);
            Qsm.setModifyTime(modifyTime);
            Qsm.setModifier(modifier);
            Qsm.setModifyContent(modifyContent);
            Qsm.setFileName(Qsm.getId()+".pdf");
            Qsm.setFileId( Qsm.getId());
            QRepository.save(Qsm);
            return   fileController.upload(mfile,mrequest,Qsm.getFileName(),Qsm.getDir());
    }
    @RequestMapping(path="addOneFile",method= RequestMethod.POST)
    @ResponseBody
    public void addEquipment(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        mfile=file;
        mrequest=request;
    }
    @RequestMapping(value="/getCurrent",method=RequestMethod.GET)
    @ResponseBody
    public Response getOne(){
        Response response=new Response();
        Iterable<qsm> list=QRepository.findAll();
        for(int i = 0; i<((List<qsm>) list).size(); i++)
        {
            if(((List<qsm>) list).get(i).getCurrent()==1) {
                JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<qsm>) list).get(i)));
                response.code=200;
                response.data=ejson;
                response.msg="成功";
            }
        }
        return response;
    }

    @RequestMapping(value="/getAllHistory",method=RequestMethod.GET)
    @ResponseBody
    public Response getAll(){
        Response response=new Response();
        JSONObject alljson=new JSONObject();
        Iterable<qsm> list=QRepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<qsm>) list).size(); i++)
        {
            if(((List<qsm>) list).get(i).getCurrent()==0) {
                JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<qsm>) list).get(i)));
                jsons.add(ejson);
            }
        }
        alljson.put("data",jsons);
        response.code=200;
        response.data=alljson;
        response.msg="成功";
        return response;
    }
    @RequestMapping(value="/getFileById/{id}",method=RequestMethod.GET)
    @ResponseBody
    public  String getFile(@PathVariable("id") long id,HttpServletResponse response)
    {
        FileController fileController=new FileController();
        try{
            if(QRepository.findById(id)==null)
                throw new Exception("doesn't exist");
            qsm temp=QRepository.findById(id);
            String name=temp.getFileName();
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
    @RequestMapping(value="/deleteOne/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Response deleteOne(@PathVariable("id") long id)
    {
        Response response=new Response();
        FileController fileController=new FileController();
        try{
            if(QRepository.findById(id)==null)
                throw new Exception("doesn't exist");
            qsm temp=QRepository.findById(id);
            String name=temp.getFileName();
            QRepository.deleteById(id);
            fileController.deletefile(name, temp.getDir());
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
    @RequestMapping(value="/Approve/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Response Approve(@PathVariable("id") long id,@RequestParam (value="state",required=false)Byte state){

        Response response=new Response();
        try {
            if (QRepository.findById(id) == null)
                throw new Exception("doesn't exist");
            qsm temp = QRepository.findById(id);
            temp.setState(state);
            QRepository.save(temp);
            response.code=200;
            response.data=null;
            response.msg="成功";

        }catch (Exception e)
        {
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
            response.data=null;
        }
        return response;
    }
    @RequestMapping(value="/getApprove",method = RequestMethod.GET)
    @ResponseBody
    public Response getApprove(){
        Response response=new Response();
        JSONObject alljson=new JSONObject();
        Iterable<qsm> list=QRepository.findAll();
        JSONArray jsons=new JSONArray();
        for(int i = 0; i<((List<qsm>) list).size(); i++)
        {
            if(((List<qsm>) list).get(i).getState()==0) {
                JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(((List<qsm>) list).get(i)));
                jsons.add(ejson);
            }
        }
        alljson.put("data",jsons);
        response.code=200;
        response.data=alljson;
        response.msg="成功";
        return response;
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
