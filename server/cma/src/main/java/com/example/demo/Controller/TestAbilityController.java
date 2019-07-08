package com.example.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.FileControl.FileController;
import com.example.demo.Model.TestAbility;
import com.example.demo.Repository.TestAbilityRepository;

import com.example.demo.framework.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lyt
 * 返回code   200→成功
 *            5xx→异常
 */
@Controller
@RequestMapping(path="/cma/TestAbility")
public class TestAbilityController {
    Long tempYear;
    String tempFileName;

    @Autowired
    private TestAbilityRepository TestAbilityRepository;

    @PostMapping(path="/addOne")
    public @ResponseBody JSONObject addInfo(@RequestParam(value="year",required = false)Long year,
                                            @RequestParam(value = "fileName",required = false)String fileName){
        JSONObject json=new JSONObject();//年份唯一
        tempYear=year;
        tempFileName=fileName;
        json.put("code",200);
        json.put("msg","信息添加成功");
        return json;
    }

    @PostMapping(path="/addOneFile")
    public @ResponseBody Response addFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request){
        System.out.println("add File in");
        TestAbility testAbility=new TestAbility();
        FileController fileController=new FileController();
        testAbility.setFileName(tempFileName+"."+fileController.getsuffix(file.getOriginalFilename()));
        testAbility.setYear(tempYear);
        TestAbilityRepository.save(testAbility);
        tempFileName=null;
        tempYear=null;
        return fileController.upload(file,request,testAbility.getFileName(),testAbility.getDir());
    }

    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject getAllAbility(){
        JSONObject json=new JSONObject();
        System.out.println("get All in");
        /*if(TestAbilityRepository.findAll()==null)
        {
            json.put("code",500);
            json.put("msg","无文件");
        }
        else {*/
            json.put("code", 200);
            json.put("msg","获取成功");
            json.put("data",TestAbilityRepository.findAll());
        //}
        return json;
    }

    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modifyAbility(@RequestParam(value="year",required = false)Long year,
                                                  @RequestParam(value = "fileName",required = false)String fileName){
        JSONObject json=new JSONObject();
        tempYear=year;
        tempFileName=fileName;
        System.out.println("Modify 1 in");
        json.put("code",200);
        json.put("msg","信息获取成功");
        return json;
    }

    @PostMapping(path="/modifyOneFile")
    public @ResponseBody Response modifyAbilityFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request){
        Response response=new Response();
        FileController fileController=new FileController();
        if(TestAbilityRepository.findById(tempYear)==null)
        {
            response.code=500;
            response.msg="不存在的文件";
            response.data=null;
            return response;
        }
        else
        {
            System.out.println("Modifying");
            System.out.println(tempFileName);
            System.out.println(tempYear);
            TestAbility temp = TestAbilityRepository.findByYear(tempYear);
            String fileName = temp.getFileName();
            System.out.println(fileName);
            fileController.deletefile(fileName, temp.getDir());
            //StandardManagementRepository.deleteById(fileId);
            System.out.println("delete success");
            //TODO:改后缀名
            temp.setFileName(tempFileName);
            System.out.println(temp.getFileName());
            System.out.println(temp.getYear());
            TestAbilityRepository.updateFileName(tempFileName,tempYear);
            tempYear=null;
            tempFileName=null;
            /*StandardManagementRepository.save(standard);
            standard.setFileName(standard.getFileId() + "." + fileController.getsuffix(file.getOriginalFilename()));
            StandardManagementRepository.save(standard);*/
            return fileController.upload(file, request, temp.getFileName(), temp.getDir());
        }
    }

    @RequestMapping(value="/getAnnex/{year}",method=RequestMethod.GET)
    public @ResponseBody String downloadTestAbility(@PathVariable("year")Long year, HttpServletResponse response){
        System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(TestAbilityRepository.findById(year)==null)
                throw new Exception("不存在");
            System.out.println(year);
            TestAbility temp=TestAbilityRepository.findByYear(year);
            String name=temp.getFileName();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}
