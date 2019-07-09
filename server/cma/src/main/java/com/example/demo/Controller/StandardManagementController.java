package com.example.demo.Controller;

import com.example.demo.FileControl.FileController;
import com.example.demo.Repository.StandardManagementRepository;
import com.example.demo.framework.Response;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.StandardManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lyt
 * 返回code   200→成功
 *            5xx→异常
 */
@Controller
@RequestMapping(path="/cma/StandardManagement")//接口定义
public class StandardManagementController {

    @Autowired
    private StandardManagementRepository StandardManagementRepository;

    @PostMapping(path="/addOne")//添加标准
    public @ResponseBody Response addStandard(@RequestParam(value="file")MultipartFile file, HttpServletRequest request){
        StandardManagement standard=new StandardManagement();
        FileController fileController=new FileController();
        System.out.println(file.getOriginalFilename());//OriginalFilename里自带后缀名
        System.out.println(file.getName());
        standard.setFileName(file.getOriginalFilename());
        StandardManagementRepository.save(standard);//存到数据库后获得Id用来命名文件
        standard.setFileName(standard.getFileId()+"."+fileController.getsuffix(file.getOriginalFilename()));
        StandardManagementRepository.save(standard);
        return fileController.upload(file,request,standard.getFileName(),standard.getDir());//调用FileController的上传方法
    }

    @GetMapping(path="/getAll")//返回所有标准
    public @ResponseBody JSONObject getAllStandard(){
        JSONObject json=new JSONObject();
        System.out.println("get All in");
        /*if(StandardManagementRepository.findAll()==null){
            json.put("code",500);
            json.put("msg","无文件");
        }
        else
        {*/
            json.put("code",200);
            json.put("msg","获得成功");
            json.put("data",StandardManagementRepository.findAll());
        //}
        return json;

    }

    @GetMapping(path="/getOne")//返回单个标准
    public @ResponseBody JSONObject getOneStandard(@RequestParam(value="fileId")Long fileId){
        JSONObject json=new JSONObject();
        System.out.println("get One in");
        System.out.println(fileId);
        if(StandardManagementRepository.findAll()==null){//←判断方法要修改
            json.put("code",500);
            json.put("msg","无文件");
        }
        else
        {
            json.put("code",200);
            json.put("msg","获得成功");
            json.put("data",StandardManagementRepository.findById(fileId));
        }
        return json;
    }

    @RequestMapping(value="/downloadFile/{fileId}",method=RequestMethod.GET)//←由于前端（微信小程序）发送数据的特殊性，下载文件的接口设计为如此
    public @ResponseBody String downloadStandard(@PathVariable("fileId")Long fileId, HttpServletResponse response){
        System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(StandardManagementRepository.findById(fileId)==null)
                throw new Exception("不存在");
            System.out.println(fileId);
            StandardManagement temp=StandardManagementRepository.findByFileId(fileId);
            String name=temp.getFileName();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());//调用FileController的下载方法
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }

    @PostMapping(path="/deleteOne")//删除
    public @ResponseBody JSONObject deleteStandard(@RequestParam(value="fileId",required =false)Long fileId){
        JSONObject json=new JSONObject();
        if(StandardManagementRepository.findById(fileId)==null){//判断方法要改动
            json.put("code",500);
            json.put("msg","文件不存在");
        }
        else
        {
            FileController fileController=new FileController();
            StandardManagement temp=StandardManagementRepository.findByFileId(fileId);
            String fileName=temp.getFileName();
            fileController.deletefile(fileName,temp.getDir());//调用FileController的删除方法
            StandardManagementRepository.deleteById(fileId);
            json.put("code",200);
            json.put("msg","删除成功");

        }
        return json;
    }

    @PostMapping(path="/modifyOne")//修改
    public @ResponseBody Response modifyStandard(@RequestParam(value="fileId",required = false)Long fileId,
                                                   @RequestParam(value="file")MultipartFile file, HttpServletRequest request){
        Response response=new Response();
        FileController fileController=new FileController();
        if(StandardManagementRepository.findById(fileId)==null){//判断是否为空，判断方法参考期间核查
            response.code=500;
            response.msg="不存在的文件";
            response.data=null;
            return response;
        }
        else {//删除原文件后上传新文件
            StandardManagement temp = StandardManagementRepository.findByFileId(fileId);
            String fileName = temp.getFileName();
            fileController.deletefile(fileName, temp.getDir());
            //StandardManagementRepository.deleteById(fileId);
            System.out.println("delete success");//删除完成
            //TODO:改后缀名
            //String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//修改时可能文件类型改变，后缀名可能不传进来，要记住原来的后缀

            StandardManagement standard = new StandardManagement();
            standard.setFileName(fileName);
            /*StandardManagementRepository.save(standard);
            standard.setFileName(standard.getFileId() + "." + fileController.getsuffix(file.getOriginalFilename()));
            StandardManagementRepository.save(standard);*/
            return fileController.upload(file, request, standard.getFileName(), standard.getDir());//上传新文件
        }
    }

}
