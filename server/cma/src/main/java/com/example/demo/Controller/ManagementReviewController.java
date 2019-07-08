package com.example.demo.Controller;

import com.example.demo.FileControl.FileController;
import com.example.demo.Model.ManagementFile;
import com.example.demo.Model.ManagementReview;
import com.example.demo.Model.qsm;
import com.example.demo.Repository.ManagementFileRepository;
import com.example.demo.Repository.ManagementReviewRepository;
import com.example.demo.framework.Response;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/cma/ManagementReview")
public class ManagementReviewController {
    @Autowired
    private ManagementReviewRepository managementReviewRepository;
    @Autowired
    private ManagementFileRepository managementFileRepository;

    @GetMapping(path = "getAll")
    //获取所有年份的管理评审
    public @ResponseBody
    JSONObject getAll() {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", managementReviewRepository.findAll());
        return json;
    }

    @PostMapping(path = "addOne")
    //添加某一年的管理评审信息
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "year", required = false) long year, @RequestParam(value = "date", required = false) String date) {
        JSONObject json = new JSONObject();
        ManagementReview managementReview = new ManagementReview();
        managementReview.setYear(year);
        //输入年份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            managementReview.setDate(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //输入日期
        List<ManagementReview> list = managementReviewRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getYear() == year) {
                json.put("code", 210);
                json.put("msg", "已存在");
                json.put("data", null);
                return json;
            }
        }
        //判断该年的管理评审是否已存在
        managementReviewRepository.save(managementReview);
        //保存管理评审
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除某一年的管理评审
    public @ResponseBody
    JSONObject deleteOne(@RequestParam(value = "year", required = false) long year) {
        JSONObject json = new JSONObject();
        if (managementReviewRepository.existsByYear(year) == false) {
            json.put("code", 210);
            json.put("msg", "不存在");
            json.put("data", null);
        }
        //若该年的管理评审不存在，返回信息
        else {
            managementReviewRepository.deleteByYear(year);
            //从数据库中删除
            List<ManagementFile> list = managementFileRepository.findAllByYear(year);
            //获取该年所有的文档
            for (int i = 0; i < list.size(); i++) {
                ManagementFile temp = list.get(i);
                if (temp.getFile() != null) {
                    FileController fileController = new FileController();
                    String name = temp.getFileName();
                    fileController.deletefile(name, temp.getDir());
                }
                managementFileRepository.existsByFileId(temp.getFileId());
            }
            //把该年的管理评审中的所有文档全部删除
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }

    @GetMapping("getAllFile")
    //获取某一年所有的文档信息
    public @ResponseBody
    JSONObject getAllFile(@RequestParam(value = "year", required = false) long year) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", managementFileRepository.findAllByYear(year));
        //返回所有的文档信息
        return json;
    }

    /*@PostMapping(path = "addFileData")
    public @ResponseBody
    JSONObject addOneFile(@RequestParam(value = "year", required = false) long year) {
        //JSONObject json=new JSONObject();
        JSONObject json = new JSONObject();
        //List<ManagementFile> list=managementFileRepository.findAllByYear(year);
        ManagementFile managementFile = new ManagementFile();
        managementFile.setYear(year);
        managementFile.setFlag(1);
        managementFileRepository.save(managementFile);
        managementFile.setFileName(managementFile.getFileId() + "_" + managementFile.getYear());
        managementFileRepository.save(managementFile);
        json.put("code", 200);
        json.put("msg", "收到数据");
        json.put("data", null);
        return json;
    }*/

    @GetMapping(path = "getOne")
    //获取某一个文档
    public @ResponseBody
    JSONObject getOne(@RequestParam(value = "fileId", required = false) long fileId) {
        JSONObject json = new JSONObject();
        if (managementFileRepository.existsByFileId(fileId) == false) {
            json.put("code", 210);
            json.put("msg", "不存在");
            json.put("data", null);
            //该文档不存在，返回信息
        } else {
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", managementFileRepository.findByFileId(fileId));
            //若文档存在，返回该文档的信息
        }
        return json;
    }

    @PostMapping(path = "addOneFile")
    //上传文件
    public @ResponseBody
    Response UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request,@RequestParam(value = "year", required = false) long year) {
        FileController fileController = new FileController();
        ManagementFile managementFile = new ManagementFile();
        managementFile.setYear(year);
        //输入年份
        //managementFile.setFlag(1);
        managementFileRepository.save(managementFile);
        managementFile.setFileName(managementFile.getFileId() + "_" + managementFile.getYear());
        //根据fileID和年份生成不重复的文件名
        //ManagementFile managementFile = managementFileRepository.findByFlag(1);
        managementFile.setFile(managementFile.getFileId() + "_" + managementFile.getYear() + ".doc");
        //设置服务器保存文件的名称和格式
        //managementFile.setFlag(0);
        managementFileRepository.save(managementFile);
        return fileController.upload(file, request, managementFile.getFile(), managementFile.getDir());
        //返回上传文件函数的结果
    }

    @PostMapping(path = "modifyOneFile")
    //修改某个文档
    public @ResponseBody
    JSONObject modifyOneFile(@RequestParam(value = "fileId", required = false) long fileId,@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        FileController fileController = new FileController();
        ManagementFile managementFile = managementFileRepository.findByFileId(fileId);
        if(managementFile==null){
            json.put("code", 210);
            json.put("msg", "不存在");
            json.put("data", null);
            return json;
        }
        //该文档不存在，返回信息
        String name = managementFile.getFile();
        //获取文件名
        //managementFile.setFlag(1);
        fileController.deletefile(name, managementFile.getDir());
        //根据文件名将服务器中的文档删除
        managementFileRepository.save(managementFile);
        fileController.upload(file, request, managementFile.getFile(), managementFile.getDir());
        //上传新的文档
        json.put("code", 200);
        json.put("msg", "修改成功");
        json.put("data", null);
        return json;
        //return fileController.upload(file,request,managementFile.getFileName(),managementFile.getDir());
    }

    @PostMapping(path = "deleteOneFile")
    //删除某个文档
    public @ResponseBody
    JSONObject deleteOneFile(@RequestParam(value = "fileId", required = false) long fileId) {
        FileController fileController = new FileController();
        JSONObject json = new JSONObject();
        ManagementFile managementFile = managementFileRepository.findByFileId(fileId);
        //获取数据库中的关于该文档的信息
        if(managementFile==null){
            json.put("code", 210);
            json.put("msg", "不存在");
            json.put("data", null);
            return json;
        }
        //若不存在，返回信息
        String name = managementFile.getFile();
        //获取文档名
        fileController.deletefile(name, managementFile.getDir());
        //删除该文档
        //managementFile.setFile(null);
        managementFileRepository.deleteByFileId(fileId);
        //删除数据库中该文档的信息
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", null);
        return json;
    }

    @RequestMapping(value="/downloadFile/{fileId}",method=RequestMethod.GET)
    //下载文档
    public @ResponseBody String downloadFile(@PathVariable("fileId")Long fileId, HttpServletResponse response){
        //System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(managementFileRepository.findByFileId(fileId)==null)
                throw new Exception("不存在");
            //若文件不存在，抛出异常
            //System.out.println(fileId);
            ManagementFile temp=managementFileRepository.findByFileId(fileId);
            String name=temp.getFile();
            //获取文件名
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
            //下载文档
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}
