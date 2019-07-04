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
@RequestMapping(path="/cma/StandardManagement")
public class StandardManagementController {

    @Autowired
    private StandardManagementRepository StandardManagementRepository;

    @PostMapping(path="/addOne")
    public @ResponseBody Response addStandard(@RequestParam(value="file")MultipartFile file, HttpServletRequest request){
        StandardManagement standard=new StandardManagement();
        FileController fileController=new FileController();
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        standard.setFileName(file.getOriginalFilename());
        StandardManagementRepository.save(standard);
        standard.setFileName(standard.getFileId()+"."+fileController.getsuffix(file.getOriginalFilename()));
        StandardManagementRepository.save(standard);
        return fileController.upload(file,request,standard.getFileName(),standard.getDir());
    }

    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject getAllStandard(){
        JSONObject json=new JSONObject();
        System.out.println("get All in");
        if(StandardManagementRepository.findAll()==null){
            json.put("code",500);
            json.put("msg","无文件");
        }
        else
        {
            json.put("code",200);
            json.put("msg","获得成功");
            json.put("data",StandardManagementRepository.findAll());
        }
        return json;

    }

    @GetMapping(path="/getOne")
    public @ResponseBody JSONObject getOneStandard(@RequestParam(value="fileId")Long fileId){
        JSONObject json=new JSONObject();
        System.out.println("get One in");
        System.out.println(fileId);
        if(StandardManagementRepository.findAll()==null){
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

    @RequestMapping(value="/downloadFile/{fileId}",method=RequestMethod.GET)
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
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }

    /*@PostMapping(path="/deleteOne")
    public @ResponseBody*/

}
