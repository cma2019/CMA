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
import com.example.demo.Repository.SampleIoRepository;
import com.example.demo.Repository.SampleReceiveRepository;
import com.example.demo.Repository.SampleReceiptRepository;
import com.example.demo.Model.SampleIO;
import com.example.demo.Model.SampleReceipt;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SampleIo")
public class SampleIoController {
    @Autowired
    private SampleIoRepository SampleIoRepository;
    @Autowired
    private SampleReceiveRepository SampleReceiveRepository;
    @Autowired
    private SampleReceiptRepository SampleReceiptRepository;
    @PostMapping(path="/addOne")
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "sampleNumber", required = false)String sampleNumber,
                          @RequestParam(value = "sampleName", required = false) String sampleName,
                          @RequestParam(value = "sampleAmount", required = false) String sampleAmount,
                          @RequestParam(value = "sampleState", required = false) String sampleState,
                          @RequestParam(value="sender",required = false) String sender,
                          @RequestParam(value="receiver",required = false)String receiver,
                          @RequestParam(value="note",required = false) String note,
                          @RequestParam(value="obtainer",required = false)String obtainer,
                          @RequestParam(value = "obtainDate",required = false)String obtainDate,
                          @RequestParam(value="sendDate",required = false) String sendDate,
                      @RequestParam(value = "receiptId",required = false) String receiptId){
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        try {
            Long.parseLong(receiptId);
            Integer.parseInt(sampleState);
            Integer.parseInt(sampleAmount);
            java.sql.Date.valueOf(sendDate);
            java.sql.Date.valueOf(obtainDate);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
        }
        if(sampleNumber== null||sampleName==null||sender==null||receiver==null||obtainer==null
        ||sampleNumber.equals("")||sampleName.equals("")||sender.equals("")||receiver.equals("")||obtainer.equals(""))
        {
            code=511;
            msg="缺少请求参数";
        }
        else if(SampleIoRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code=512;
            msg="样品编号已存在";
        }
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber)==null)
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleAmount()!=Integer.parseInt(sampleAmount)||
                !SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleName().equals(sampleName)||
                SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleState()!=Integer.parseInt(sampleState))
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        else if(sampleNumber.length()>10||sampleName.length()>20|| sender.length()>20||
                receiver.length()>20||obtainer.length()>20|| Integer.parseInt(sampleAmount)<1||
                (Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2))
        {
            code=513;
            msg="某项数据错误";
        }
        else
        {
            SampleIO receive=new SampleIO();
            receive.setSampleNumber(sampleNumber);
            receive.setSampleName(sampleName);
            receive.setSampleAmount(Integer.parseInt(sampleAmount));
            receive.setSampleState(Integer.parseInt(sampleState));
            receive.setSender(sender);
            receive.setReceiver(receiver);
            receive.setNote(note);
            receive.setObtainer(obtainer);
            receive.setObtainDate(java.sql.Date.valueOf(obtainDate));
            receive.setSendDate(java.sql.Date.valueOf(sendDate));
            receive.setReceiptId(Long.parseLong(receiptId));
            SampleIoRepository.saveAndFlush(receive);
            if(!receiptId.equals("0"))
            {
                SampleReceipt sr=SampleReceiptRepository.findBySampleId(Long.parseLong(receiptId));
                sr.setSampleName(sampleName);
                SampleReceiptRepository.saveAndFlush(sr);
            }
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        System.out.println(js);
        return js;
    }
    @PostMapping (path="/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleIoId",required = false) String sampleIoId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        //System.out.println(sampleIoId);
        if(sampleIoId==null||sampleIoId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
        }
        else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
        }
        else
        {
            SampleIoRepository.deleteById(Long.parseLong(sampleIoId));
        }
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",null);
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
        }
        else
        {
            code=210;
            msg="无有效信息返回";
            //data=null;
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @GetMapping(path="/getOne")
    public @ResponseBody JSONObject findOne(@RequestParam(value = "sampleIoId",required = false) String sampleIoId)
    {
            JSONObject json=new JSONObject();
            int code=200;
            String msg="成功";
            JSONObject data=new JSONObject();
            if(sampleIoId==null||sampleIoId.equals(""))
            {
                code=500;
                msg="未收到标识编号";
            }
            else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null) {   //此样品接收登记的id不存在；

            code=500;
            msg="数据不存在";
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
            data.put("receiptId",recv.getReceiptId());
        }
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modify(@RequestParam(value = "sampleIoId",required = false)String sampleIoId,
                                           @RequestParam(value = "sampleNumber", required = false)String sampleNumber,
                                           @RequestParam(value = "sampleName", required = false) String sampleName,
                                           @RequestParam(value = "sampleAmount", required = false) String sampleAmount,
                                           @RequestParam(value = "sampleState", required = false) String sampleState,
                                           @RequestParam(value="sender",required = false) String sender,
                                           @RequestParam(value="receiver",required = false)String receiver,
                                           @RequestParam(value="note",required = false) String note,
                                           @RequestParam(value="obtainer",required = false)String obtainer,
                                           @RequestParam(value = "obtainDate",required = false)String obtainDate,
                                           @RequestParam(value = "sendDate",required = false) String sendDate)
    {
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        try {
            Integer.parseInt(sampleState);
            Integer.parseInt(sampleAmount);
            java.sql.Date.valueOf(sendDate);
            java.sql.Date.valueOf(obtainDate);
        }catch (NumberFormatException e){
            code=534;
            msg="修改后数据不合法";
        }
        if(sampleIoId==null||sampleIoId.equals(""))
        {
            code=531;
            msg="未收到标识编号";
         }
        else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null)
        {
            code=532;
            msg="数据不存在";
        }
        else if(!sampleNumber.equals(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId)).getSampleNumber())&&SampleIoRepository.findBySampleNumber(sampleNumber)!=null) {
            /*System.out.println(sampleNumber);
            System.out.println(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId)).getSampleNumber());
            System.out.println(receiver);*/
            code = 533;
            msg = "修改后数据错误";
        }
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber)==null)
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleAmount()!=Integer.parseInt(sampleAmount)||
                !SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleName().equals(sampleName)||
                SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleState()!=Integer.parseInt(sampleState))
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        else if(sampleNumber.length()>10||sampleName.length()>20||sender.length()>20
                ||receiver.length()>20||obtainer.length()>20||(Integer.parseInt(sampleAmount)<1||(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2)))
        {
            code=534;
            msg="修改后数据不合法";
        }
        else
        {
            SampleIO recv= SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId));
            recv.setObtainDate(java.sql.Date.valueOf(obtainDate));
            recv.setSendDate(java.sql.Date.valueOf(sendDate));
            recv.setSampleAmount(Integer.parseInt(sampleAmount));
            recv.setSampleState(Integer.parseInt(sampleState));
            recv.setNote(note);
            recv.setObtainer(obtainer);
            recv.setReceiver(receiver);
            recv.setReceiver(recv.getReceiver());
            recv.setSender(sender);
            recv.setSampleNumber(sampleNumber);
            recv.setSampleName(sampleName);
            SampleIoRepository.saveAndFlush(recv);
            System.out.println(receiver);
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
}
//select * from sampleio;