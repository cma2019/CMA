package com.example.demo.Controller;


//import com.example.demo.framework.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Repository.InternalAuditManagementRepository;
import com.example.demo.Model.InternalAuditManagement;
//import com.example.demo.FileControl.FileController;
//import org.springframework.web.multipart.MultipartFile;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/InternalAuditManagement")
public class InternalAuditManagementController
{
    @Autowired
    private InternalAuditManagementRepository InternalAuditManagementRepository;
    /*
    String add_id="0";
    String add_name="null";
    String modify_Id="0";
    String modify_name="null";
    */
    @GetMapping(path = "/getAll")
    //获取内审列表
    public @ResponseBody JSONObject getAll()
    {
        //调用findAll方法，获取内审list
        List<InternalAuditManagement> res=InternalAuditManagementRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
       //遍历内审list，将必要信息存放在data中
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("year",res.get(i).getYear());
                tmp.put("date",res.get(i).getDate());
                data.add(tmp);
            }
        }
        //数据库中无内审记录
        else
        {
            code=210;
            msg="无有效信息返回";
            //data=null;
        }
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path = "/addOne")
    //添加一项内审表项
    public @ResponseBody JSONObject addOne(@RequestParam(value = "year",required = false) String year,
                                           @RequestParam(value = "date",required = false) String date)
    {
        int code=200;
        String msg="成功";
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        //以字符串接受参数，做参数格式合法性判断
        try
        {
            Long.parseLong(year);
            java.sql.Date.valueOf(date);
        }
        catch (NumberFormatException e)
        {
            code=513;
            msg="数据不合法";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //年份不能重复，确保数据唯一性
        if(InternalAuditManagementRepository.findByYear(Long.parseLong(year))!=null)
        {
            code=514;
            msg="年份重复";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //参数完整且合法时正常添加内审记录
        InternalAuditManagement iam=new InternalAuditManagement();
        iam.setDate(java.sql.Date.valueOf(date));
        iam.setYear(Long.parseLong(year));
        InternalAuditManagementRepository.save(iam);
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
}
//select * from internal_audit_management;
//delete from internal_audit_management where file_id=
//alter table internal_audit_management ENGINE =InnoDB;
