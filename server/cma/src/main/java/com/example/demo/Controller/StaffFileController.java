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
    public @ResponseBody
    JSONObject getAll() {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", staffFileRepository.findAll());
        return json;
    }

    @GetMapping(path = "getOne")
    public @ResponseBody
    JSONObject getOne(@RequestParam(value = "staffId", required = false) long staffId) {
        JSONObject json = new JSONObject();
        if (staffFileRepository.existsByStaffId(staffId)==false) {
            json.put("code", 210);
            json.put("msg", "档案不存在");
            json.put("data", null);
        }
        else {
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", staffFileRepository.findByStaffId(staffId));
        }
        return json;
    }

    /*@PostMapping(path = "addOne")
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) {
        StaffFile staffFile = new StaffFile();
        JSONObject json = new JSONObject();
        if (!staffManagementRepository.existsById(staffId)) {
            json.put("code", 210);
            json.put("msg", "人员不存在");
            json.put("data", null);
        } else {
            StaffManagement staffManagement = staffManagementRepository.getOne(staffId);
            staffFile.setStaffId(staffId);
            staffFile.setName(staffManagement.getName());
            staffFile.setDepartment(staffManagement.getDepartment());
            staffFile.setPosition(staffManagement.getPosition());
            staffFile.setFileId(fileId);
            staffFile.setFileLocation(fileLocation);
            staffFile.setFlag(1);
            List<StaffFile> list = staffFileRepository.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStaffId()==staffId) {
                    json.put("code", 210);
                    json.put("msg", "失败,已存在");
                    json.put("data", null);
                    return json;
                }
            }
            staffFileRepository.save(staffFile);
            json.put("code", 200);
            json.put("msg", "收到数据");
            json.put("data", null);
        }
        return json;
    }*/

    @PostMapping(path = "addOneFile")
    public @ResponseBody JSONObject UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                           @RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) {
        StaffFile staffFile = new StaffFile();
        JSONObject json = new JSONObject();
        if (!staffManagementRepository.existsById(staffId)) {
            json.put("code", 210);
            json.put("msg", "人员不存在");
            json.put("data", null);
        } else {
            StaffManagement staffManagement = staffManagementRepository.getOne(staffId);
            staffFile.setStaffId(staffId);
            staffFile.setName(staffManagement.getName());
            staffFile.setDepartment(staffManagement.getDepartment());
            staffFile.setPosition(staffManagement.getPosition());
            staffFile.setFileId(fileId);
            staffFile.setFileLocation(fileLocation);
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
            FileController fileController = new FileController();
            //StaffFile staffFile = staffFileRepository.findByFlag(1);
            staffFile.setFileImage(staffFile.getStaffId() + "_" + staffFile.getFileId() + ".jpg");
            //staffFile.setFlag(0);
            staffFileRepository.save(staffFile);
            fileController.upload(file, request, staffFile.getFileImage(), staffFile.getDir());
            staffFileRepository.save(staffFile);
            json.put("code", 200);
            json.put("msg", "收到数据");
            json.put("data", null);
        }
        return json;
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody
    JSONObject deleteOne(@RequestParam(value = "staffId", required = false) long staffId) {
        JSONObject json = new JSONObject();
        if (staffFileRepository.existsByStaffId(staffId) == false) {
            json.put("code", 210);
            json.put("msg", "档案不存在");
            json.put("data", null);
            return json;
        }
        FileController fileController = new FileController();
        StaffFile staffFile = staffFileRepository.findByStaffId(staffId);
        String name = staffFile.getFileImage();
        fileController.deletefile(name, staffFile.getDir());
        //managementFile.setFile(null);
        staffFileRepository.deleteByStaffId(staffId);
        //JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "成功");
        json.put("data", null);
        return json;
    }

    @PostMapping(path = "modifyOne")
    public @ResponseBody
    JSONObject modifyOne(@RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) {
        JSONObject json = new JSONObject();
        if (staffFileRepository.existsByStaffId(staffId) == false) {
            json.put("code", 210);
            json.put("msg", "档案不存在");
            json.put("data", null);
        } else {
            StaffFile staffFile = staffFileRepository.findByStaffId(staffId);
            if (!fileId.equals("")) {
                staffFile.setFileId(fileId);
            }
            if (!fileLocation.equals(""))
                staffFile.setFileLocation(fileLocation);
            staffFileRepository.save(staffFile);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }

    @PostMapping(path = "modifyOneFile")
    public @ResponseBody JSONObject modifyOneFile(@RequestParam(value = "staffId", required = false) long staffId,@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        FileController fileController = new FileController();
        StaffFile staffFile = staffFileRepository.findByStaffId(staffId);
        String name = staffFile.getFileImage();
        //managementFile.setFlag(1);
        fileController.deletefile(name, staffFile.getDir());
        staffFile.setFileImage(staffFile.getStaffId() + "_" + staffFile.getFileId() + ".jpg");
        staffFileRepository.save(staffFile);
        fileController.upload(file, request, staffFile.getFileImage(), staffFile.getDir());
        json.put("code", 200);
        json.put("msg", "修改成功");
        json.put("data", null);
        return json;
        //return fileController.upload(file,request,managementFile.getFileName(),managementFile.getDir());
    }
    @RequestMapping(value="/getImage/{staffId}",method=RequestMethod.GET)
    public @ResponseBody String downloadFile(@PathVariable("staffId")long staffId, HttpServletResponse response){
        //System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(staffFileRepository.findByStaffId(staffId)==null)
                throw new Exception("不存在");
            //System.out.println(fileId);
            StaffFile temp = staffFileRepository.findByStaffId(staffId);
            String name=temp.getFileImage();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}