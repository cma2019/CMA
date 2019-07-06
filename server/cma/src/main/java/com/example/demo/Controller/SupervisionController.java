package com.example.demo.Controller;
//import com.fasterxml.jackson.databind.util.JSONPObject;
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
    @PostMapping(path="/addOne")
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "author", required = false)String author,
                      @RequestParam(value = "remark", required = false) String remark,
                      @RequestParam(value = "createDate",required = false) String createDate
                      ){
        int code;
        String msg;
        JSONObject js=new JSONObject();
        JSONObject data=new JSONObject();
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
        if(author.equals(""))
        {
            code=511;
            msg="缺少参数";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
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
    }
    @PostMapping (path="/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="supervisionId",required = false) String supervisionId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        System.out.println(supervisionId);
        SupervisionRepository.deleteById(Long.parseLong(supervisionId));
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject findALL()
    {
        List<Supervision> res= SupervisionRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for (int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("id",res.get(i).getSupervisionId());
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
    }
    @PostMapping(path="/approveOne")
    public @ResponseBody JSONObject approveOne(@RequestParam(value="supervisionId",required = false) String supervisionId,
                                            @RequestParam(value = "approver",required = false)String approver,
                                            @RequestParam(value = "approveDate",required = false)String approveDate)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        try {
            java.sql.Date.valueOf(approveDate);
            Long.parseLong(supervisionId);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
        if(approveDate.equals("")||supervisionId.equals(""))
        {
            code=511;
            msg="缺少请求参数";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
        Supervision sv= SupervisionRepository.findBySupervisionId(Long.parseLong(supervisionId));
        sv.setSituation(1);
        sv.setApprover(approver);
        sv.setApproveDate(java.sql.Date.valueOf(approveDate));
        SupervisionRepository.saveAndFlush(sv);
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;

    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modify(@RequestParam(value="supervisionId",required = false) String supervisionId,
                                           @RequestParam(value = "remark",required = false) String remark
                                           )
    {
        System.out.println(supervisionId);
        System.out.println(remark);
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        try {
            Long.parseLong(supervisionId);
        }catch (NumberFormatException E){
            code=534;
            msg="参数错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        Supervision sv=SupervisionRepository.findBySupervisionId(Long.parseLong(supervisionId));
        sv.setRemark(remark);
        SupervisionRepository.saveAndFlush(sv);
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @GetMapping(path="/executeOne")
    public @ResponseBody JSONObject executeOne(@RequestParam(value="supervisionId",required = false) String supervisionId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        Supervision sv= SupervisionRepository.findBySupervisionId(Long.parseLong(supervisionId));
        sv.setSituation(2);
        SupervisionRepository.saveAndFlush(sv);
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
}
//select * from supervision;