package com.example.demo.Controller;

import com.example.demo.FileControl.FileController;
import com.example.demo.Model.ManagementFile;
import com.example.demo.Model.StaffFile;
import com.example.demo.Model.StaffManagement;
import com.example.demo.Model.StaffQualification;
import com.example.demo.Repository.StaffManagementRepository;
import com.example.demo.Repository.StaffQualificationRepository;
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

@Controller
@Transactional
@RequestMapping(path = "cma/StaffQualification")
public class StaffQualificationController {
    @Autowired
    private StaffQualificationRepository staffQualificationRepository;
    @Autowired
    private StaffManagementRepository staffManagementRepository;

    @GetMapping(path = "getAllByStaff")
    //获取某个人员所有的资质档案
    public @ResponseBody
    JSONObject getAllByStaff(@RequestParam(required = false,value = "staffId")long staffId){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffQualificationRepository.findAllByStaffId(staffId));
        //返回该人员所有的资质信息
        return json;
    }

    /*@PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(required = false,value = "staffId")long staffId, @RequestParam(required = false,value="qualificationName")String qualificationName){
        JSONObject json=new JSONObject();
        StaffQualification staffQualification=new StaffQualification();
        if(staffManagementRepository.existsById(staffId)==false){
            json.put("code",210);
            json.put("msg","不存在人员");
            json.put("data",null);
            return json;
        }
        StaffManagement staffManagement=staffManagementRepository.getOne(staffId);
        staffQualification.setStaffId(staffId);
        staffQualification.setDepartment(staffManagement.getDepartment());
        staffQualification.setName(staffManagement.getName());
        staffQualification.setPosition(staffManagement.getPosition());
        staffQualification.setQualificationName(qualificationName);
        //staffQualification.setFlag(1);
        staffQualificationRepository.save(staffQualification);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }*/
    @PostMapping(path = "addOneFile")
    //添加一个资质文件
    public @ResponseBody
    JSONObject UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request,
            @RequestParam(required = false,value = "staffId")long staffId, @RequestParam(required = false,value="qualificationName")String qualificationName) {
        JSONObject json=new JSONObject();
        StaffQualification staffQualification=new StaffQualification();
        if(staffManagementRepository.existsById(staffId)==false){
            json.put("code",210);
            json.put("msg","不存在人员");
            json.put("data",null);
            return json;
            //改人员不在人员列表中
        }
        StaffManagement staffManagement=staffManagementRepository.getOne(staffId);
        //获取该人员信息
        staffQualification.setStaffId(staffId);
        //输入人员id
        staffQualification.setDepartment(staffManagement.getDepartment());
        //输入部门
        staffQualification.setName(staffManagement.getName());
        //输入姓名
        staffQualification.setPosition(staffManagement.getPosition());
        //输入职位
        staffQualification.setQualificationName(qualificationName);
        //输入资质名称
        //staffQualification.setFlag(1);
        staffQualificationRepository.save(staffQualification);
        FileController fileController = new FileController();
        //StaffQualification staffQualification = staffQualificationRepository.findByFlag(1);
        //staffQualification.setFlag(0);
        staffQualification.setQualificationImage(staffQualification.getStaffId()+"_"+staffQualification.getQualificationName()+".jpg");
        //生成资质文件名称和格式
        staffQualificationRepository.save(staffQualification);
        fileController.upload(file, request, staffQualification.getQualificationImage(), staffQualification.getDir());
        //上传文件
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }

    @PostMapping(path = "deleteOne")
    //删除某个资质档案
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "qualificationId",required = false)long qualificationId){
        JSONObject json=new JSONObject();
        if(staffQualificationRepository.findByQualificationId(qualificationId)==null){
            json.put("code",210);
            json.put("msg","不存在资质档案");
            json.put("data",null);
            return json;
            //该资质档案不存在
        }
        else{
            StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
            //获取该资质档案
            FileController fileController = new FileController();
            //StaffFile staffFile = staffFileRepository.findById(id);
            String name = staffQualification.getQualificationImage();
            //获取资质档案文件名
            fileController.deletefile(name, staffQualification.getDir());
            //删除文件
            //managementFile.setFile(null);
            staffQualificationRepository.deleteByQualificationId(qualificationId);
            //删除数据库中的该条信息
            //JSONObject json = new JSONObject();
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
            return json;
        }
    }
    @PostMapping(path = "modifyOne")
    //修改某个资质档案的信息
    public @ResponseBody
    JSONObject modifyOne(@RequestParam(value = "qualificationId", required = false) long qualificationId,  @RequestParam(value = "qualificationName", required = false) String qualificationName) {
        JSONObject json = new JSONObject();
        if (staffQualificationRepository.findByQualificationId(qualificationId)==null) {
            json.put("code", 210);
            json.put("msg", "资质档案不存在");
            json.put("data", null);
            //资质档案不存在
        } else {
            StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
            //获取资质档案信息
            if (!qualificationName.equals(""))
                //资质名称是否修改
                staffQualification.setQualificationName(qualificationName);
            staffQualificationRepository.save(staffQualification);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }
    @PostMapping(path = "modifyOneFile")
    //修改资质档案的文件
    public @ResponseBody
    JSONObject modifyOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,@RequestParam(value = "qualificationId", required = false) long qualificationId) {
        JSONObject json = new JSONObject();
        FileController fileController = new FileController();
        StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
        if(staffQualification==null)
        {
            json.put("code", 210);
            json.put("msg", "不存在");
            json.put("data", null);
            return json;
            //该资质档案不存在
        }
        String name = staffQualification.getQualificationImage();
        //获取文件名
        //staffQualification.setFlag(1);
        fileController.deletefile(name,staffQualification.getDir());
        //删除文件
        staffQualification.setQualificationImage(staffQualification.getStaffId()+"_"+staffQualification.getQualificationName()+".jpg");
        //生成新文件名
        staffQualificationRepository.save(staffQualification);
        fileController.upload(file, request, staffQualification.getQualificationImage(), staffQualification.getDir());
        //上传新文件
        json.put("code", 200);
        json.put("msg", "修改成功");
        json.put("data", null);
        return json;
        //return fileController.upload(file,request,managementFile.getFileName(),managementFile.getDir());
    }
    @RequestMapping(value="/getImage/{qualificationId}",method=RequestMethod.GET)
    //下载资质档案文件
    public @ResponseBody String downloadFile(@PathVariable("qualificationId")long qualificationId, HttpServletResponse response){
        //System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(staffQualificationRepository.findByQualificationId(qualificationId)==null)
                throw new Exception("不存在");
            //资质档案不存在，抛出异常
            //System.out.println(fileId);
            StaffQualification temp = staffQualificationRepository.findByQualificationId(qualificationId);
            //获取资质档案信息
            String name=temp.getQualificationImage();
            //获取文件名
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
            //下载文件
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
    @GetMapping(path = "getAll")
    //获取所有资质档案信息
    public @ResponseBody JSONObject getAll(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","成功");
        jsonObject.put("data",staffQualificationRepository.findAll());
        return jsonObject;
    }

    @GetMapping(path = "getOne")
    //获取某个资质档案信息
    public @ResponseBody JSONObject getOne(@RequestParam(value = "qualificationId",required = false)long qualificationId){
        JSONObject json=new JSONObject();
        if(staffQualificationRepository.findByQualificationId(qualificationId)==null){
            json.put("code", 210);
            json.put("msg", "资质档案不存在");
            json.put("data", null);
            //资质档案不存在
        }
        else{
            StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",staffQualification);
            //返回资质档案信息
        }
        return json;
    }
}