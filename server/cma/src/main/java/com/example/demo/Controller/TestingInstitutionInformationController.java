package com.example.demo.Controller;
import com.example.demo.Repository.TestingInstitutionInformationRepository;
import com.example.demo.Model.TestingInstitutionInformation;
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
 * 检测机构信息
 */
@Controller
@RequestMapping(path="/cma/TestingInstitutionInformation")
public class TestingInstitutionInformationController {
    @Autowired
    private TestingInstitutionInformationRepository TIIRepository;

    @RequestMapping(value="/get",method=RequestMethod.GET)
    @ResponseBody
    public Response getOne(){
        Response response=new Response();

        try {
            if (TIIRepository.findAll()==null)
                throw new Exception("TestingInstitutionInformation does not exist");
            TestingInstitutionInformation testingInstitutionInformation =TIIRepository.findAll().get(0);
            JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(testingInstitutionInformation));
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
    @RequestMapping(value="/modifyOne",method = RequestMethod.POST)
    @ResponseBody
    public Response modifyOne(
            @RequestParam (value="testingInstitutionName",required=false)String testingInstitutionName,
            @RequestParam (value="testingInstitutionAddress",required=false)String testingInstitutionAddress,
            @RequestParam (value="postcode",required=false)String postcode,
            @RequestParam (value="fax",required=false)String fax,
            @RequestParam (value="email",required=false)String email,
            @RequestParam (value="tiPeopleInCharge",required=false)String tiPeopleInCharge,
            @RequestParam (value="tiPicPosition",required=false)String tiPicPosition,
            @RequestParam (value="tiPicTelephone",required=false)String tiPicTelephone,
            @RequestParam (value="tiPicMobilephone",required=false)String tiPicMobilephone,
            @RequestParam (value="liaison",required=false)String liaison,
            @RequestParam (value="liaisonPosition",required=false)String liaisonPosition,
            @RequestParam (value="liaisonTelephone",required=false)String liaisonTelephone,
            @RequestParam (value="liaisonMobilephone",required=false)String liaisonMobilephone,
            @RequestParam (value="socialCreditCode",required=false)String socialCreditCode,
            @RequestParam (value="legalEntityBelongedName",required=false)String legalEntityBelongedName,
            @RequestParam (value="legalEntityBelongedAddress",required=false)String legalEntityBelongedAddress,
            @RequestParam (value="lebPeopleInCharge",required=false)String lebPeopelInCharge,
            @RequestParam (value="lebPicPosition",required=false)String lebPicPosition,
            @RequestParam (value="lebPicTelephone",required=false)String lebPicTelephone,
            @RequestParam (value="lebSocialCreditCode",required=false)String lebSocialCreditCode,
            @RequestParam (value="competentDepartmentName",required=false)String competentDepartmentName,
            @RequestParam (value="competentDepartmentAddress",required=false)String competentDepartmentAddress,
            @RequestParam (value="cdPeopleInCharge",required=false)String cdPeopleInCharge,
            @RequestParam (value="cdPicPosition",required=false)String cdPicPosition,
            @RequestParam (value="cdPicTelephone",required=false)String cdPicTelephone,
            @RequestParam (value="characteristic",required=false)Byte characteristic,
            @RequestParam (value="legalEntity",required=false)Byte legalEntity
           ){
        Response response=new Response();
        try{
            if (TIIRepository.findAll()==null) {
                throw new Exception("TestingInstitutionInformation does not exist");
            }
            TestingInstitutionInformation  testingInstitutionInformation =TIIRepository.findAll().get(0);
            testingInstitutionInformation.setCdPeopleInCharge(cdPeopleInCharge);
            testingInstitutionInformation.setCdPicPosition(cdPicPosition);
            testingInstitutionInformation.setCdPicTelephone(cdPicTelephone);
            testingInstitutionInformation.setCharacteristic(characteristic);
            testingInstitutionInformation.setCompetentDepartmentAddress(competentDepartmentAddress);
            testingInstitutionInformation.setCompetentDepartmentName(competentDepartmentName);
             testingInstitutionInformation.setEmail(email);
            testingInstitutionInformation.setFax(fax);
            testingInstitutionInformation.setLebPicPosition(lebPicPosition);
            testingInstitutionInformation.setLebPeopleInCharge(lebPeopelInCharge);
            testingInstitutionInformation.setLebPicTelephone(lebPicTelephone);
            testingInstitutionInformation.setLebSocialCreditCode(lebSocialCreditCode);
            testingInstitutionInformation.setLegalEntity(legalEntity);
            testingInstitutionInformation.setTestingInstitutionAddress(testingInstitutionAddress);
            testingInstitutionInformation.setTestingInstitutionName(testingInstitutionName);
            testingInstitutionInformation.setLegalEntityBelongedAddress(legalEntityBelongedAddress);
            testingInstitutionInformation.setLiaison(liaison);
            testingInstitutionInformation.setLegalEntityBelongedName(legalEntityBelongedName);
            testingInstitutionInformation.setLiaisonPosition(liaisonPosition);
            testingInstitutionInformation.setLiaisonTelephone(liaisonTelephone);
            testingInstitutionInformation.setLiaisonMobilephone(liaisonMobilephone);
            testingInstitutionInformation.setTiPeopleInCharge(tiPeopleInCharge);
            testingInstitutionInformation.setTiPicMobilephone(tiPicMobilephone);
            testingInstitutionInformation.setLebPicPosition(tiPicPosition);
            testingInstitutionInformation.setTiPicTelephone(tiPicTelephone);
            testingInstitutionInformation.setPostcode(postcode);
            testingInstitutionInformation.setSocialCreditCode(socialCreditCode);
           JSONObject ejson = JSONObject.parseObject(JSONObject.toJSONString(testingInstitutionInformation));
            TIIRepository.save(testingInstitutionInformation);
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