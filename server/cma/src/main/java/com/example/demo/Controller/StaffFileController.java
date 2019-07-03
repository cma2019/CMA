package com.example.demo.Controller;

import com.example.demo.Model.StaffFile;
import com.example.demo.Model.StaffManagement;
import com.example.demo.Repository.StaffFileRepository;
import com.example.demo.Repository.StaffManagementRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    JSONObject getAll(){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffFileRepository.findAll());
        return json;
    }

    @GetMapping(path = "getOne")
    public @ResponseBody JSONObject getOne(@RequestParam(value="id",required = false)long id){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(!staffFileRepository.existsById(id)){
            json.put("code",210);
            json.put("msg","档案不存在");
            json.put("data",null);
        }
        else {
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",staffFileRepository.findById(id));
        }
        return json;
    }

    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(value = "id",required = false)long id,@RequestParam(value = "fileId",required = false) String fileId,@RequestParam(value = "fileLocation",required = false)String fileLocation,
                                           @RequestParam(value = "fileImage",required = false)String fileImage){
        StaffFile staffFile=new StaffFile();
        JSONObject json=new JSONObject();
        if(!staffManagementRepository.existsById(id)){
            json.put("code",210);
            json.put("msg","人员不存在");
            json.put("data",null);
        }
        else {
            StaffManagement staffManagement = staffManagementRepository.getOne(id);
            staffFile.setId(id);
            staffFile.setName(staffManagement.getName());
            staffFile.setDepartment(staffManagement.getDepartment());
            staffFile.setPosition(staffManagement.getPosition());
            staffFile.setFileId(fileId);
            staffFile.setFileImage(fileImage);
            staffFile.setFileLocation(fileLocation);
            List<StaffFile> list = staffFileRepository.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).Equals(staffFile)) {
                    json.put("code", 210);
                    json.put("msg", "失败,已存在");
                    json.put("data", null);
                    return json;
                }
            }
            staffFileRepository.save(staffFile);
            json.put("code", 200);
            json.put("msg", "成功");
            json.put("data", null);
        }
        return json;
    }

    @PostMapping(path = "deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value = "id",required = false)long id){
        JSONObject json=new JSONObject();
        if(staffFileRepository.existsById(id)==false){
            json.put("code",210);
            json.put("msg","档案不存在");
            json.put("data",null);
        }
        else{
            staffFileRepository.deleteById(id);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }
}
