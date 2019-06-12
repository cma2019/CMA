package com.example.demo.Controller;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import  com.example.demo.Repository.SampleReceiveRepository ;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.Model.SampleReceive;
import java.util.List;
import java.sql.Date;

@Controller
@RequestMapping(path="/cma/SampleReceive")
public class SampleReceiveController {
    @Autowired
    private SampleReceiveRepository sampleReceiveRepository;
    private static long ID=0;
    @PostMapping(path="/addOne")
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "sampleNumber", required = false)String sampleNumber,
                          @RequestParam(value = "sampleName", required = false) String sampleName,
                          @RequestParam(value = "sampleAmount", required = false) String sampleAmount,
                          @RequestParam(value = "sampleState", required = false) String sampleState,
                          @RequestParam(value="requester",required = false) String requester,
                          @RequestParam(value="receiver",required = false)String receiver,
                          @RequestParam(value="receiveDate",required = false) String receiveDate,
                          @RequestParam(value="obtainer",required = false)String obtainer,
                          @RequestParam(value = "obtainDate",required = false)String obtainDate){
        System.out.println(sampleNumber);
        System.out.println(obtainer);
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        try{
            Integer.parseInt(sampleAmount);
            Integer.parseInt(sampleState);
            java.sql.Date.valueOf(receiveDate);
            java.sql.Date.valueOf(obtainDate);
        }catch(NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        };
        if(sampleNumber==""||sampleName==null||requester==null||receiver==null||obtainer==null)
        {
            code=511;
            msg="缺少请求参数";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
        else if(ID>0&& sampleReceiveRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code=512;
            msg="样品编号已存在";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
        else if(sampleNumber.length()>10||sampleName.length()>20||
                requester.length()>45||receiver.length()>20||obtainer.length()>20||(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2))
        {
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
            SampleReceive receive=new SampleReceive();
            receive.setSampleId(ID);
            receive.setSampleNumber(sampleNumber);
            receive.setSampleName(sampleName);
            receive.setSampleAmount(Integer.parseInt(sampleAmount));
            receive.setSampleState(Integer.parseInt(sampleState));
            receive.setRequester(requester);
            receive.setReceiver(receiver);
            receive.setReceiveDate(java.sql.Date.valueOf(receiveDate));
            receive.setObtainer(obtainer);
            receive.setObtainDate(java.sql.Date.valueOf(obtainDate));
            sampleReceiveRepository.saveAndFlush(receive);
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            ID++;
            return js;
    }
    @PostMapping (path="/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        System.out.println(sampleId);
        if(sampleId=="")
        {
            code=521;
            msg="未收到标识编号";
            json.put("code",code);
            json.put("msg",msg);
        }
        else if(sampleReceiveRepository.findBySampleId(Long.parseLong(sampleId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
            json.put("code",code);
            json.put("msg",msg);
            //json.put("data",data);
        }
        else
        {
            sampleReceiveRepository.deleteById(Long.parseLong(sampleId));
            json.put("code",code);
            json.put("msg",msg);
            //json.put("data",data);
        }
        return json;
    }
    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject findALL()
    {
        List<SampleReceive> res= sampleReceiveRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("sampleId",res.get(i).getSampleId());
                tmp.put("sampleNumber",res.get(i).getSampleNumber());
                tmp.put("sampleName",res.get(i).getSampleName());
                tmp.put("sampleAmount",res.get(i).getSampleAmount());
                tmp.put("sampleState",res.get(i).getSampleState());
                tmp.put("requester",res.get(i).getRequester());
                tmp.put("receiver",res.get(i).getReceiver());
                tmp.put("receiveDate",res.get(i).getReceiveDate().toString());
                tmp.put("obtainer",res.get(i).getObtainer());
                tmp.put("obtainDate",res.get(i).getObtainDate().toString());
                data.add(tmp);
            }
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",data);
            return js;
        }
        else
        {
            code=522;
            msg="数据不存在";
            //data=null;
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
            return js;
        }
    }
    @GetMapping(path="/getOne")
    public @ResponseBody JSONObject findOne(@RequestParam(value = "sampleId",required = false) String  sampleId)
    {
            JSONObject json=new JSONObject();
            int code=200;
            String msg="成功";
            JSONObject data=new JSONObject();
            if(sampleId=="")
            {
                code=521;
                msg="未收到标识编号";
                json.put("code",code);
                json.put("msg",msg);
                return json;
            }
            else if(sampleReceiveRepository.findBySampleId(Long.parseLong(sampleId))==null) {   //此样品接收登记的id不存在；

            code=522;
            msg="数据不存在";
            //data=null;
            json.put("code",code);
            json.put("msg",msg);
            //json.put("data",data);
            return json;
        }
        else{
            SampleReceive recv= sampleReceiveRepository.findBySampleId(Long.parseLong(sampleId));
            data.put("sampleNumber",recv.getSampleNumber());
            data.put("sampleName",recv.getSampleName());
            data.put("sampleAmount",recv.getSampleAmount());
            data.put("sampleState",recv.getSampleState());
            data.put("requester",recv.getRequester());
            data.put("receiver",recv.getReceiver());
            data.put("receiveDate",recv.getReceiveDate().toString());
            data.put("obtainer",recv.getObtainer());
            data.put("obtainDate",recv.getObtainDate().toString());
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modify(@RequestParam(value = "sampleId",required = false)String sampleId,
                                           @RequestParam(value = "sampleNumber", required = false)String sampleNumber,
                                           @RequestParam(value = "sampleName", required = false) String sampleName,
                                           @RequestParam(value = "sampleAmount", required = false) String sampleAmount,
                                           @RequestParam(value = "sampleState", required = false) String sampleState,
                                           @RequestParam(value="requester",required = false) String requester,
                                           @RequestParam(value="receiver",required = false)String receiver,
                                           @RequestParam(value="receiveDate",required = false) String receiveDate,
                                           @RequestParam(value="obtainer",required = false)String obtainer,
                                           @RequestParam(value = "obtainDate",required = false)String obtainDate)
    {
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        System.out.println(sampleNumber);
        System.out.println(sampleName);
        System.out.println(sampleId);
        System.out.println(sampleState);
        try{
            Integer.parseInt(sampleAmount);
            Integer.parseInt(sampleState);
            java.sql.Date.valueOf(receiveDate);
            java.sql.Date.valueOf(obtainDate);
        }catch(NumberFormatException e){
            code =533;
            msg="修改后数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        };
        /*if(sampleId.equals(""))
        {
            code=531;
            msg="未收到标识编号";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
            System.out.println(sampleId);
            System.out.println("?");
        }*/
        System.out.println(sampleId.equals(""));
        if(sampleReceiveRepository.findBySampleId(Integer.parseInt(sampleId))==null)
        {
            code=532;
            msg="数据不存在";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        else if(sampleNumber!= sampleReceiveRepository.findBySampleId(Integer.parseInt(sampleId)).getSampleNumber()&&sampleReceiveRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code =533;
            msg="修改后数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        else if((sampleNumber!=null&&sampleNumber.length()>10)||(sampleName!=null&&sampleName.length()>20)||(requester!=null&&requester.length()>45)
                ||(receiver!=null&&receiver.length()>20)||(obtainer!=null&&obtainer.length()>20)||(Integer.parseInt(sampleState)!=2)&&(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1))
        {
            code=534;
            msg="修改后数据不合法";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        else
        {
            SampleReceive recv= sampleReceiveRepository.findBySampleId(Integer.parseInt(sampleId));
            if(obtainDate!=null&&!obtainDate.equals(""))
                recv.setObtainDate(java.sql.Date.valueOf(obtainDate));
            else
                recv.setObtainDate(recv.getObtainDate());
            if(receiveDate!=null&&!receiveDate.equals(""))
                recv.setObtainDate(java.sql.Date.valueOf(receiveDate));
            else
                recv.setObtainDate(recv.getObtainDate());
            if(sampleState!=null&&!sampleState.equals(("")))
                recv.setSampleState(Integer.parseInt(sampleState));
            else
                recv.setSampleState(recv.getSampleState());
            if(!sampleAmount.equals(("")))
                recv.setSampleAmount(Integer.parseInt(sampleAmount));
            else
                recv.setSampleAmount(recv.getSampleAmount());
            recv.setSampleId(Integer.parseInt(sampleId));
            if(obtainer!=null&&!obtainer.equals(""))
                recv.setObtainer(obtainer);
            else
                recv.setObtainer(recv.getObtainer());
            if(receiver!=null&&!receiver.equals(""))
                recv.setReceiver(receiver);
            else
                recv.setReceiver(recv.getReceiver());
            if(requester!=null&&!requester.equals(""))
                recv.setRequester(requester);
            else
                recv.setRequester(recv.getRequester());
            if(sampleNumber!=null&&!sampleNumber.equals(""))
                recv.setSampleNumber(sampleNumber);
            else
                recv.setSampleNumber(recv.getSampleNumber());
            if(sampleName!=null&&!sampleName.equals(""))
                recv.setSampleName(sampleName);
            else
                recv.setSampleName(recv.getSampleName());
            sampleReceiveRepository.saveAndFlush(recv);
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        return js;
    }
}
//select * from sample_receive;