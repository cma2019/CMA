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
@RequestMapping(path="/cma/TestAbility")//定义接口
public class TestAbilityController {
    Long tempYear;//微信小程序传文件时无法同时传参数，保存信息用
    String tempFileName;//同上

    @Autowired
    private TestAbilityRepository TestAbilityRepository;

    @PostMapping(path="/addOne")//添加信息（传文件前）
    public @ResponseBody JSONObject addInfo(@RequestParam(value="year",required = false)Long year,
                                            @RequestParam(value = "fileName",required = false)String fileName){
        JSONObject json=new JSONObject();
        tempYear=year;//把信息存储下来
        tempFileName=fileName;
        json.put("code",200);
        json.put("msg","信息添加成功");
        return json;
    }

    @PostMapping(path="/addOneFile")//添加信息（传文件）
    public @ResponseBody Response addFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request){
        System.out.println("add File in");
        TestAbility testAbility=new TestAbility();
        FileController fileController=new FileController();
        testAbility.setFileName(tempFileName+"."+fileController.getsuffix(file.getOriginalFilename()));//设置文件名
        testAbility.setYear(tempYear);//设置年份
        TestAbilityRepository.save(testAbility);//保存信息
        tempFileName=null;//临时用数据置为空
        tempYear=null;//临时用数据置为空
        return fileController.upload(file,request,testAbility.getFileName(),testAbility.getDir());//上传文件
    }

    @GetMapping(path="/getAll")//获取所有信息
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

    @PostMapping(path="/modifyOne")//修改信息（上传文档前）
    public @ResponseBody JSONObject modifyAbility(@RequestParam(value="year",required = false)Long year,
                                                  @RequestParam(value = "fileName",required = false)String fileName){
        JSONObject json=new JSONObject();
        tempYear=year;
        tempFileName=fileName;//保存临时数据
        System.out.println("Modify 1 in");
        json.put("code",200);
        json.put("msg","信息获取成功");
        return json;
    }

    @PostMapping(path="/modifyOneFile")//修改信息（文档删除、上传）
    public @ResponseBody Response modifyAbilityFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request){
        Response response=new Response();
        FileController fileController=new FileController();
        if(!TestAbilityRepository.findById(tempYear).isPresent())//是否为空
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
            fileController.deletefile(fileName, temp.getDir());//删除已有文件
            //StandardManagementRepository.deleteById(fileId);
            System.out.println("delete success");
            //TODO:改后缀名
            //String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

            temp.setFileName(tempFileName);
            System.out.println(temp.getFileName());
            System.out.println(temp.getYear());
            TestAbilityRepository.updateFileName(tempFileName,tempYear);//上传新文件
            tempYear=null;
            tempFileName=null;//置空临时数据
            /*StandardManagementRepository.save(standard);
            standard.setFileName(standard.getFileId() + "." + fileController.getsuffix(file.getOriginalFilename()));
            StandardManagementRepository.save(standard);*/
            return fileController.upload(file, request, temp.getFileName(), temp.getDir());//上传数据
        }
    }

    @RequestMapping(value="/getAnnex/{year}",method=RequestMethod.GET)//下载年度信息
    public @ResponseBody String downloadTestAbility(@PathVariable("year")Long year, HttpServletResponse response){
        System.out.println("Download In");
        FileController fileController=new FileController();
        try{
            if(TestAbilityRepository.findById(year)==null)//是否存在
                throw new Exception("不存在");
            System.out.println(year);
            TestAbility temp=TestAbilityRepository.findByYear(year);
            String name=temp.getFileName();
            System.out.println(name);
            return  fileController.downloadFile(response,name,temp.getDir());//下载文档
        }catch(Exception e){
            e.printStackTrace();
            return "下载失败";
        }
    }
}
