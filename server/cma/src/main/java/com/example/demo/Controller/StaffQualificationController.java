package com.example.demo.Controller;

import com.example.demo.Model.StaffManagement;
import com.example.demo.Repository.StaffManagementRepository;
import com.example.demo.Repository.StaffQualificationRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    JSONObject getAllByStaff(@RequestParam(required = false,value = "id")long id){
        JSONObject json=new JSONObject();
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",staffQualificationRepository.findAllById(id));
        return json;
    }

    @PostMapping(path = "addOne")
    public @ResponseBody JSONObject addOne(@RequestParam(required = false,value = "id")long id, @RequestParam(required = false,value="qualificationName")String qualificationName,
                                    @RequestParam(required = false,value = "qualificationImage")File qualificationImage){
        JSONObject json=new JSONObject();
        return json;
    }
}
