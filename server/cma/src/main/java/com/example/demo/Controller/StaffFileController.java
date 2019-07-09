package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.FileControl.FileController;
import com.example.demo.Model.ManagementFile;
import com.example.demo.Model.StaffFile;
import com.example.demo.Model.StaffManagement;
import com.example.demo.Repository.StaffFileRepository;
import com.example.demo.Repository.StaffManagementRepository;
import com.example.demo.framework.Response;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/cma/StaffFile")
public class StaffFileController {
    @Autowired
    private StaffFileRepository staffFileRepository;
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path = "getAll")
    //获取所有的人员档案
    public @ResponseBody
    JSONObject getAll() {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", staffFileRepository.findAll());
        return json;
    }

    @GetMapping(path = "getOne")
    //获取某个人员的人员档案
    public @ResponseBody
    JSONObject getOne(@RequestParam(value = "staffId", required = false) long staffId) {
        JSONObject json = new JSONObject();
        if (staffFileRepository.existsByStaffId(staffId)==false) {
            json.put("code", 210);
            json.put("msg", "档案不存在");
            json.put("data", null);
            //若该人员的人员档案不存在，返回信息
        }
        else {
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", staffFileRepository.findByStaffId(staffId));
            //返回该人员的档案信息
        }
        return json;
    }

    @PostMapping(path = "addOneFile")
    //添加某个人员的档案
    public @ResponseBody JSONObject UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                           @RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) {
        StaffFile staffFile = new StaffFile();
        JSONObject json = new JSONObject();
        if (!staffManagementRepository.existsById(staffId)) {
            json.put("code", 210);
            json.put("msg", "人员不存在");
            json.put("data", null);
            //若该人员不在人员列表中，返回信息
        } else {
            StaffManagement staffManagement = staffManagementRepository.getOne(staffId);
            //从人员列表中获取该人员的信息
            staffFile.setStaffId(staffId);
            //输入人员id
            staffFile.setName(staffManagement.getName());
            //输入人员姓名
            staffFile.setDepartment(staffManagement.getDepartment());
            //输入人员部门
            staffFile.setPosition(staffManagement.getPosition());
            //输入人员职位
            staffFile.setFileId(fileId);
            //输入档案id
            staffFile.setFileLocation(fileLocation);
            //输入档案位置
            //staffFile.setFlag(1);
            List<StaffFile> list = staffFileRepository.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStaffId()==staffId) {
                    json.put("code", 210);
                    json.put("msg", "失败,已存在");
                    json.put("data", null);
                    return json;
                }
            }
            //判断该人员档案是否存在
            FileController fileController = new FileController();
            //StaffFile staffFile = staffFileRepository.findByFlag(1);
            staffFile.setFileImage(staffFile.getStaffId() + "_" + staffFile.getFileId() + ".jpg");
            //生成文件名和文件格式
            //staffFile.setFlag(0);
            staffFileRepository.save(staffFile);
            fileController.upload(file, request, staffFile.getFileImage(), staffFile.getDir());
            //保存文件
            staffFileRepository.save(staffFile);
            json.put("code", 200);
            json.put("msg", "收到数据");
            json.put("data", null);
        }
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除某个人员的档案
    public @ResponseBody
    JSONObject deleteOne(@RequestParam(value = "staffId", required = false) long staffId) {
        JSONObject json = new JSONObject();
        if (staffFileRepository.existsByStaffId(staffId) == false) {
            json.put("code", 210);
            json.put("msg", "档案不存在");
            json.put("data", null);
            return json;
            //该人员档案不存在，返回信息
        }
        FileController fileController = new FileController();
        StaffFile staffFile = staffFileRepository.findByStaffId(staffId);
        //获取该人员的人员档案
        String name = staffFile.getFileImage();
        //获取人员档案文件名
        fileController.deletefile(name, staffFile.getDir());
        //删除人员档案
        //managementFile.setFile(null);
        staffFileRepository.deleteByStaffId(staffId);
        //从数据库中删除人员档案的相关信息
        //JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", null);
        return json;
    }

    @PostMapping(path = "modifyOne")
    //修改某个人员档案的信息
    public @ResponseBody
    JSONObject modifyOne(@RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) {
        JSONObject json = new JSONObject();
        if (staffFileRepository.existsByStaffId(staffId) == false) {
            json.put("code", 210);
            json.put("msg", "档案不存在");
            json.put("data", null);
            //该人员档案不存在，返回信息
        } else {
            StaffFile staffFile = staffFileRepository.findByStaffId(staffId);
            //获取该次档案信息
            if (!fileId.equals("")) {
                //判断是否修改档案id
                staffFile.setFileId(fileId);
            }
            if (!fileLocation.equals(""))
                //判断是否修改档案位置
                staffFile.setFileLocation(fileLocation);
            staffFileRepository.save(staffFile);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }

    @PostMapping(path = "modifyOneFile")
    //修改某个人员档案的档案文件
    public @ResponseBody JSONObject modifyOneFile(@RequestParam(value = "staffId", required = false) long staffId,@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        FileController fileController = new FileController();
        StaffFile staffFile = staffFileRepository.findByStaffId(staffId);
        if(staffFile==null){
            json.put("code", 210);
            json.put("msg", "不存在");
            json.put("data", null);
            return json;
            //该档案不存在，返回信息
        }
        String name = staffFile.getFileImage();
        //获取档案文件名
        //managementFile.setFlag(1);
        fileController.deletefile(name, staffFile.getDir());
        //删除原来的档案文件
        staffFile.setFileImage(staffFile.getStaffId() + "_" + staffFile.getFileId() + ".jpg");
        //生成新的文件名和文件格式
        staffFileRepository.save(staffFile);
        fileController.upload(file, request, staffFile.getFileImage(), staffFile.getDir());
        //上传一个新文件
        json.put("code", 200);
        json.put("msg", "修改成功");
        json.put("data", null);
        return json;
        //return fileController.upload(file,request,managementFile.getFileName(),managementFile.getDir());
    }
    @RequestMapping(value="/getImage/{staffId}",method=RequestMethod.GET)
    //下载档案文件
    public @ResponseBody String downloadFile(@PathVariable("staffId")long staffId, HttpServletResponse response){
        //System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(staffFileRepository.findByStaffId(staffId)==null)
                throw new Exception("不存在");
            //档案不存在，抛出异常
            //System.out.println(fileId);
            StaffFile temp = staffFileRepository.findByStaffId(staffId);
            //获取档案信息
            String name=temp.getFileImage();
            //获取文件名
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
            //下载文件
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}