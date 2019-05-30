package com.example.demo.Controller;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import com.example.demo.Repository.SampleIoRepository;
import com.example.demo.Model.SampleIO;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SampleIo")
public class SampleIoController {
    @Autowired
    private SampleIoRepository SampleIoRepository;
    private static long ID=0;
    @PostMapping(path="/addOne")
    public @ResponseBody JSONObject addReceive(@RequestParam(value = "sampleNumber", required = false)String sampleNumber,@RequestParam(value = "sampleName", required = false) String sampleName,
                                               @RequestParam(value = "sampleAmount", required = false) String sampleAmount, @RequestParam(value = "sampleState", required = false) String sampleState,
                                               @RequestParam(value="sender",required = false) String sender, @RequestParam(value="receiver",required = false)String receiver,@RequestParam(value="note",required = false) String note,
                                               @RequestParam(value="obtainer",required = false)String obtainer,@RequestParam(value = "obtainDate",required = false)String obtainDate,@RequestParam(value="sendDate",required = false) String sendDate){
        System.out.println(sampleNumber);
        System.out.println(sampleName);
        System.out.println(sender);
        System.out.println(receiver);
        System.out.println(obtainer);
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        try {
            Integer.parseInt(sampleState);
            Integer.parseInt(sampleAmount);
            java.sql.Date.valueOf(sendDate);
            java.sql.Date.valueOf(obtainDate);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
        if(sampleNumber== null||sampleName==null||sender==null||receiver==null||obtainer==null)
        {
            code=511;
            msg="缺少请求参数";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
        else if(ID>0&& SampleIoRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code=512;
            msg="样品编号已存在";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
        else if(sampleNumber.length()>10||sampleName.length()>20|| sender.length()>20||
                receiver.length()>20||obtainer.length()>20|| Integer.parseInt(sampleAmount)<1||
                (Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2))
        {
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            return js;
        }
            SampleIO receive=new SampleIO();
            receive.setSampleIoId(ID);
            receive.setSampleNumber(sampleNumber);//3
            receive.setSampleName(sampleName);//s1
            receive.setSampleAmount(Integer.parseInt(sampleAmount));//1
            receive.setSampleState(Integer.parseInt(sampleState));//2
            receive.setSender(sender);//nju
            receive.setReceiver(receiver);//bl
            receive.setNote(note);//
            receive.setObtainer(obtainer);//hcb
            receive.setObtainDate(java.sql.Date.valueOf(obtainDate));//2019-6-2
            receive.setSendDate(java.sql.Date.valueOf(sendDate));//2019-1-9
            SampleIoRepository.saveAndFlush(receive);
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data","null");
            ID++;
            return js;
    }
    @PostMapping (path="/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleIoId",required = false) String sampleIoId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        System.out.println(sampleIoId);
        if(sampleIoId==null||sampleIoId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
            json.put("code",code);
            json.put("msg",msg);
        }
        else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
            json.put("code",code);
            json.put("msg",msg);
            //json.put("data",data);
        }
        else
        {
            SampleIoRepository.deleteById(Long.parseLong(sampleIoId));
            json.put("code",code);
            json.put("msg",msg);
            //json.put("data",data);
        }
        return json;
    }
    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject findALL()
    {
        List<SampleIO> res= SampleIoRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for (int i=0;i<res.size();i++)
            {
                JSONObject tmp=new JSONObject();
                tmp.put("sampleIoId",res.get(i).getSampleIoId());
                tmp.put("sampleNumber",res.get(i).getSampleNumber());
                tmp.put("sampleName",res.get(i).getSampleName());
                tmp.put("sampleAmount",res.get(i).getSampleAmount());
                tmp.put("sampleState",res.get(i).getSampleState());
                tmp.put("sender",res.get(i).getSender());
                tmp.put("receiver",res.get(i).getReceiver());
                tmp.put("sendDate",res.get(i).getSendDate().toString());
                tmp.put("obtainer",res.get(i).getObtainer());
                tmp.put("obtainDate",res.get(i).getObtainDate().toString());
                tmp.put("note",res.get(i).getNote());
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
    public @ResponseBody JSONObject findReceiveId(@RequestParam(value = "sampleIoId",required = false) String sampleIoId)
    {
            JSONObject json=new JSONObject();
            int code=200;
            String msg="成功";
            JSONObject data=new JSONObject();
            if(sampleIoId==null||sampleIoId.equals(""))
            {
                code=521;
                msg="未收到标识编号";
                json.put("code",code);
                json.put("msg",msg);
                return json;
            }
            else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null) {   //此样品接收登记的id不存在；

            code=522;
            msg="数据不存在";
            //data=null;
            json.put("code",code);
            json.put("msg",msg);
            //json.put("data",data);
            return json;
        }
        else{
            SampleIO recv= SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId));
            data.put("sampleNumber",recv.getSampleNumber());
            data.put("sampleName",recv.getSampleName());
            data.put("sampleAmount",recv.getSampleAmount());
            data.put("sampleState",recv.getSampleState());
            data.put("sender",recv.getSender());
            data.put("receiver",recv.getReceiver());
            data.put("sendDate",recv.getSendDate().toString());
            data.put("obtainer",recv.getObtainer());
            data.put("obtainDate",recv.getObtainDate().toString());
            data.put("note",recv.getNote());
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modify(@RequestParam(value = "sampleIoId",required = false)String sampleIoId,@RequestParam(value = "sampleNumber", required = false)String sampleNumber,@RequestParam(value = "sampleName", required = false) String sampleName,
                                           @RequestParam(value = "sampleAmount", required = false) String sampleAmount,@RequestParam(value = "sampleState", required = false) String sampleState,@RequestParam(value="sender",required = false) String sender,
                                           @RequestParam(value="receiver",required = false)String receiver,@RequestParam(value="note",required = false) String note,
                                           @RequestParam(value="obtainer",required = false)String obtainer,@RequestParam(value = "obtainDate",required = false)String obtainDate,@RequestParam(value = "sendDate",required = false) String sendDate)
    {
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        System.out.println(sampleNumber);
        System.out.println(sampleName);
        System.out.println(sampleIoId);
        System.out.println(sampleState);
        System.out.println(obtainDate);
        try {
            Integer.parseInt(sampleState);
            Integer.parseInt(sampleAmount);
            java.sql.Date.valueOf(sendDate);
            java.sql.Date.valueOf(obtainDate);
        }catch (NumberFormatException e){
            code=534;
            msg="修改后数据不合法";
            js.put("code",code);
            js.put("msg",msg);
            return js;
            //js.put("data",data);
        }
        if(sampleIoId==null||sampleIoId.equals(""))
        {
            code=531;
            msg="未收到标识编号";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
         }
        else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null)
        {
            code=532;
            msg="数据不存在";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        else if((sampleNumber!=null&&!sampleNumber.equals(""))&& SampleIoRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code =533;
            msg="修改后数据错误";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        else if(sampleNumber.length()>10||sampleName.length()>20||sender.length()>20
                ||receiver.length()>20||obtainer.length()>20||(Integer.parseInt(sampleAmount)<1||(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2)))
        {
            code=534;
            msg="修改后数据不合法";
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        else
        {
            SampleIO recv= SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId));
            if(!obtainDate.equals(""))
                recv.setObtainDate(java.sql.Date.valueOf(obtainDate));
            else
                recv.setObtainDate(recv.getObtainDate());
            if(!sendDate.equals(""))
                recv.setSendDate(java.sql.Date.valueOf(sendDate));
            else
                recv.setSendDate(recv.getSendDate());
            if(!sampleAmount.equals(""))
                recv.setSampleAmount(Integer.parseInt(sampleAmount));
            else
                recv.setSampleAmount(recv.getSampleAmount());
            if(!sampleState.equals(""))
                recv.setSampleState(Integer.parseInt(sampleState));
            else
                recv.setSampleAmount(recv.getSampleAmount());
            if(!note.equals(""))
                recv.setNote(note);
            else
                recv.setNote(recv.getNote());
            recv.setSampleIoId(Long.parseLong(sampleIoId));
            if(!obtainer.equals(""))
                recv.setObtainer(obtainer);
            else
                recv.setObtainer(recv.getObtainer());
            if(!receiver.equals(""))
                recv.setReceiver(receiver);
            else
                recv.setReceiver(recv.getReceiver());
            if(!sender.equals(""))
                recv.setSender(sender);
            else
                recv.setSender(recv.getSender());
            if(!sampleNumber.equals(""))
                recv.setSampleNumber(sampleNumber);
            else
                recv.setSampleNumber(recv.getSampleNumber());
            if(!sampleName.equals(""))
                recv.setSampleName(sampleName);
            else
                recv.setSampleName(recv.getSampleName());
            SampleIoRepository.saveAndFlush(recv);
            js.put("code",code);
            js.put("msg",msg);
            //js.put("data",data);
        }
        return js;
    }
}
//select * from sampleio;