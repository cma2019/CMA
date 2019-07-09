package com.example.demo.Controller;
import com.example.demo.Repository.TestingInstitutionResourceRepository;
import com.example.demo.Model.TestingInstitutionResource;
import com.example.demo.framework.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YXP
 * 检测机构资源管理
 */

@Controller
@RequestMapping(path="/cma/TestingInstitutionResource")
public class TestingInstitutionResourceController {
    @Autowired
    private TestingInstitutionResourceRepository TIRRepository;

    @RequestMapping(value="/get",method=RequestMethod.GET)
    @ResponseBody
    public Response getOne(){
        Response response=new Response();

        try {
            if (TIRRepository.findAll()==null)
                throw new Exception("TestingInstitutionInformation does not exist");
            TestingInstitutionResource testingInstitutionResource =TIRRepository.findAll().get(0);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(testingInstitutionResource));
            response.code=200;
            response.data=ejson;
            response.msg="成功";
        }catch(Exception e){
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }


    //修改一个
    @RequestMapping(value="/modifyOne",method = RequestMethod.POST)
    @ResponseBody
    public Response modifyOne(
            @RequestParam (value="newPlace",required=false)String newPlace,
            @RequestParam (value="nameAndAddress",required=false)String nameAndAddress,
            @RequestParam (value="outdoorsArea",required=false)double outdoorsArea,
            @RequestParam (value="stableArea",required=false)double stableArea,
            @RequestParam (value="floorSpace",required=false)double floorSpace,
            @RequestParam (value="fixedAssets",required=false) double fixedAssets,
            @RequestParam (value="seniorProfessionalTitle",required=false)Byte seniorProfessionalTitle,
            @RequestParam (value="totalNumber",required=false)Byte totalNumber,
            @RequestParam (value="primaryProfessionalTitle",required=false)Byte primaryProfessionalTitle,
            @RequestParam (value="intermediateProfessionalTitle",required=false)Byte intermediateProfessionalTitle,
            @RequestParam (value="equipmentNumber",required=false)Byte equipmentNumber
    ){
        Response response=new Response();
        try{
            if (TIRRepository.findAll()==null) {
                System.out.println("111111111111111");
                throw new Exception("TestingInstitutionInformation does not exist");
            }
            TestingInstitutionResource testingInstitutionResource =TIRRepository.findAll().get(0);
            testingInstitutionResource.setTotalNumber(totalNumber);
            testingInstitutionResource.setSeniorProfessionalTitle(seniorProfessionalTitle);
            testingInstitutionResource.setIntermediateProfessionalTitle(intermediateProfessionalTitle);
            testingInstitutionResource.setPrimaryProfessionalTitle(primaryProfessionalTitle);
            testingInstitutionResource.setFixedAssets(fixedAssets);
            testingInstitutionResource.setEquipmentNumber(equipmentNumber);
            testingInstitutionResource.setFloorSpace(floorSpace);
            testingInstitutionResource.setStableArea(stableArea);
            testingInstitutionResource.setOutdoorsArea(outdoorsArea);
            testingInstitutionResource.setNameAndAddress(nameAndAddress);
            testingInstitutionResource.setNewPlace(newPlace);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(testingInstitutionResource));
            TIRRepository.save(testingInstitutionResource);
            response.code=200;
            response.msg="成功";
            response.data=null;
        }catch(Exception e)
        {
            e.printStackTrace();
            response.code=500;
            response.msg=e.getMessage();
        }
        return response;
    }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}

