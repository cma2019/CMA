package com.example.demo.Controller;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.example.demo.Model.SupervisionPlan;
import com.example.demo.Repository.SupervisionPlanRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.Repository.SupervisionRepository;
import com.example.demo.Model.Supervision;
//import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/Supervision")
public class SupervisionController {
    @Autowired
    private SupervisionRepository SupervisionRepository;
    @Autowired
    private SupervisionPlanRepository SupervisionPlanRepository;
    @Autowired
    private SupervisionPlanController supervisionPlanController=new SupervisionPlanController();
    @PostMapping(path="/addOne")
   //新增一项监督
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "author", required = false)String author,
                      @RequestParam(value = "remark", required = false) String remark,
                      @RequestParam(value = "createDate",required = false) String createDate
                      )
    {
        int code;
        String msg;
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
        /*
            以字符串获取前端参数，做参数格式合法性判断
         */
        try {
            java.sql.Date.valueOf(createDate);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        /*
            参数判空
         */
        if(author.equals(""))
        {
            code=511;
            msg="缺少参数";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        /*
            参数完整且合法，正常添加
         */
        else
        {
            code=200;
            msg="成功";
            Supervision sv=new Supervision();
            sv.setCreateDate(java.sql.Date.valueOf(createDate));
            sv.setAuthor(author);
            sv.setApproveDate(java.sql.Date.valueOf("1999-9-9"));
            sv.setApprover("null");
            sv.setRemark(remark);
            sv.setSituation(0);
            SupervisionRepository.save(sv);
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
    }
    @PostMapping (path="/deleteOne")
    //删除一项监督
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="id",required = false) String id)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
       //参数格式合法性判断
        try {
            Long.parseLong(id);
        }catch (NumberFormatException E){
            json.put("code",500);
            json.put("msg","缺少请求参数");
            json.put("data",null);
        }
        JSONObject data=new JSONObject();
        //System.out.println(supervisionId);
       //调用findALL获取某项监督下的监督计划列表
        List<SupervisionPlan>res=SupervisionPlanRepository.findAllById(Long.parseLong(id));
        //遍历监督计划列表，逐项删除
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                supervisionPlanController.deleteOne(res.get(i).getPlanID()+"");
            }
        }
       //删除监督
        SupervisionRepository.deleteById(Long.parseLong(id));
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @GetMapping(path="/getAll")
   //获取监督列表
    public @ResponseBody JSONObject findALL()
    {
        //调用findAll方法，获取监督列表
        List<Supervision> res= SupervisionRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        //将必要信息存放在data
        if(res.size()>0)
        {
            for (int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("id",res.get(i).getId());
                tmp.put("situation",res.get(i).getSituation());
                tmp.put("author",res.get(i).getAuthor());
                tmp.put("createDate",res.get(i).getCreateDate());
                tmp.put("approver",res.get(i).getApprover());
                tmp.put("approveDate",res.get(i).getApproveDate());
                tmp.put("remark",res.get(i).getRemark());
                data.add(tmp);
            }
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //监督列表为空
        else
        {
            code=210;
            msg="无有效信息返回";
            //data=null;
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
            return js;
        }
       // 返回json，前端解析code获取请求结果，解析data获取需要的数据
    }
    @PostMapping(path="/approveOne")
   //批准一项监督
    public @ResponseBody JSONObject approveOne(@RequestParam(value="id",required = false) String id,
                                            @RequestParam(value = "approver",required = false)String approver,
                                            @RequestParam(value = "approveDate",required = false)String approveDate)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        //确保参数合法
        try {
            java.sql.Date.valueOf(approveDate);
            Long.parseLong(id);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
       //确保参数非空
        if(approveDate.equals("")||id.equals(""))
        {
            code=511;
            msg="缺少请求参数";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
       //参数完整且正确，更新监督信息
        Supervision sv= SupervisionRepository.findById(Long.parseLong(id));
        sv.setSituation(1);
        sv.setApprover(approver);
        sv.setApproveDate(java.sql.Date.valueOf(approveDate));
        SupervisionRepository.saveAndFlush(sv);
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;

    }
    @PostMapping(path="/modifyOne")
    //修改监督
    public @ResponseBody JSONObject modify(@RequestParam(value="id",required = false) String id,
                                           @RequestParam(value = "remark",required = false) String remark
                                           )
    {
        //System.out.println(supervisionId);
        //System.out.println(remark);
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        //确保参数合法
        try {
            Long.parseLong(id);
        }catch (NumberFormatException E){
            code=534;
            msg="参数错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        //参数合法且完整，更新监督
        Supervision sv=SupervisionRepository.findById(Long.parseLong(id));
        sv.setRemark(remark);
        SupervisionRepository.saveAndFlush(sv);
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path="/executeOne")
    //执行监督
    public @ResponseBody JSONObject executeOne(@RequestParam(value="id",required = false) String id)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        Supervision sv= SupervisionRepository.findById(Long.parseLong(id));
        sv.setSituation(2);
        SupervisionRepository.saveAndFlush(sv);
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
}
//select * from supervision;
//alter table supervision ENGINE =InnoDB;