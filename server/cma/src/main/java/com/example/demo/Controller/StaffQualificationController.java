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
    public @ResponseBody
    JSONObject getAllByStaff(@RequestParam(required = false,value = "staffId")long staffId){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffQualificationRepository.findAllByStaffId(staffId));
        return json;
    }

    @PostMapping(path = "addOne")
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
        staffQualification.setFlag(1);
        staffQualificationRepository.save(staffQualification);
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",null);
        return json;
    }
    @PostMapping(path = "addOneFile")
    public @ResponseBody
    Response UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        FileController fileController = new FileController();
        StaffQualification staffQualification = staffQualificationRepository.findByFlag(1);
        staffQualification.setFlag(0);
        staffQualification.setQualificationImage(staffQualification.getStaffId()+"_"+staffQualification.getQualificationName()+".jpg");
        staffQualificationRepository.save(staffQualification);
        return fileController.upload(file, request, staffQualification.getQualificationImage(), staffQualification.getDir());
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "qualificationId",required = false)long qualificationId){
        JSONObject json=new JSONObject();
        if(staffQualificationRepository.findByQualificationId(qualificationId)==null){
            json.put("code",210);
            json.put("msg","不存在资质档案");
            json.put("data",null);
            return json;
        }
        else{
            StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
            FileController fileController = new FileController();
            //StaffFile staffFile = staffFileRepository.findById(id);
            String name = staffQualification.getQualificationImage();
            fileController.deletefile(name, staffQualification.getDir());
            //managementFile.setFile(null);
            staffQualificationRepository.deleteByQualificationId(qualificationId);
            //JSONObject json = new JSONObject();
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
            return json;
        }
    }
    @PostMapping(path = "modifyOne")
    public @ResponseBody
    JSONObject modifyOne(@RequestParam(value = "qualificationId", required = false) long qualificationId,  @RequestParam(value = "qualificationName", required = false) String qualificationName) {
        JSONObject json = new JSONObject();
        if (staffQualificationRepository.findByQualificationId(qualificationId)==null) {
            json.put("code", 210);
            json.put("msg", "资质档案不存在");
            json.put("data", null);
        } else {
            StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
            if (!qualificationName.equals(""))
                staffQualification.setQualificationName(qualificationName);
            staffQualificationRepository.save(staffQualification);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }
    @PostMapping(path = "modifyOneFile")
    public @ResponseBody
    JSONObject modifyOneFile(@RequestParam(value = "qualificationId", required = false) long qualificationId) {
        JSONObject json = new JSONObject();
        FileController fileController = new FileController();
        StaffQualification staffQualification=staffQualificationRepository.findByQualificationId(qualificationId);
        String name = staffQualification.getQualificationImage();
        staffQualification.setFlag(1);
        fileController.deletefile(name,staffQualification.getDir());
        staffQualificationRepository.save(staffQualification);
        json.put("code", 200);
        json.put("msg", "已删除原文件");
        json.put("data", null);
        return json;
        //return fileController.upload(file,request,managementFile.getFileName(),managementFile.getDir());
    }
    @RequestMapping(value="/getImage/{id}",method=RequestMethod.GET)
    public @ResponseBody String downloadFile(@PathVariable("qualificationId")long qualificationId, HttpServletResponse response){
        //System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(staffQualificationRepository.findByQualificationId(qualificationId)==null)
                throw new Exception("不存在");
            //System.out.println(fileId);
            StaffQualification temp = staffQualificationRepository.findByQualificationId(qualificationId);
            String name=temp.getQualificationImage();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
    @GetMapping(path = "getAll")
    public @ResponseBody JSONObject getAll(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","成功");
        jsonObject.put("data",staffQualificationRepository.findAll());
        return jsonObject;
    }
}