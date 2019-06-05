package com.example.demo.Controller;
//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.example.demo.Model.SampleReceipt;
import com.example.demo.Repository.SampleReceiptRepository;
import net.minidev.json.JSONArray;
//import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SampleReceipt")
public class SampleReceiptController {
    @Autowired
    private SampleReceiptRepository SampleReceiptRepository;
    @PostMapping(path="/addOne")
    public @ResponseBody
    JSONObject addone(@RequestParam(value="data",required = false)JSONObject data){

        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject d=new JSONObject();
        System.out.println(data);
        String idstr=data.getString("sampleId");
        System.out.println(data);
        String testtypestr=data.getString("testType");
        String sofwwaretypestr=data.getString("softwareType");
        String datestr=data.getString("receiveDate");
        try {
            Long.parseLong(idstr);
            Integer.parseInt(testtypestr);
            Integer.parseInt(sofwwaretypestr);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        Long sampleId=Long.parseLong(idstr);
        String applicationUnit=data.getString("applicationUnit");
        String version=data.getString("version");
        String contractId=data.getString("contractId");
        int testType=Integer.parseInt(testtypestr);
        String electronicMedia=data.getString("electronicMedia");
        int softwareType=Integer.parseInt(sofwwaretypestr);
        String receiveUnit=data.getString("receiveUnit");
        Date receiveDate=java.sql.Date.valueOf(datestr);
        String sender=data.getString("sender");
        String receiver=data.getString("receiver");
        JSONArray list =(JSONArray)data.get("materialList");
        if(idstr==""||applicationUnit==""||version==""||contractId==""||
        testtypestr==""||electronicMedia==""||sofwwaretypestr==""||
        receiveUnit==""||datestr==""||sender==""||receiver=="")
        {
            code=511;
            msg="缺少请求参数";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        else
        {
            SampleReceipt s=new SampleReceipt();
            s.setSampleId(sampleId);
            s.setApplicationUnit(applicationUnit);
            s.setContractId(contractId);
            s.setElectronicMedia(electronicMedia);
            s.setTestType(testType);
            s.setSoftwareType(softwareType);
            s.setReceiveDate(receiveDate);
            s.setVersion(version);
            s.setSender(sender);
            s.setReceiveUnit(receiveUnit);
            s.setReceiver(receiver);
             int basems=0;
             for(int i=0;i<list.size()-1;i++)
             {
                    JSONObject tmp=JSONObject.fromObject(list.get(i));
                    basems=basems*10+Integer.parseInt(tmp.getString("materialType"));
             }
            s.setBaseMs(basems+"");
            SampleReceiptRepository.saveAndFlush(s);
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
    }
    @PostMapping (path="/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=null;
        System.out.println(sampleId);
        if(sampleId==null||sampleId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
        }
        else if(SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
        }
        else
        {
            SampleReceiptRepository.deleteById(Long.parseLong(sampleId));
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
        }
        return json;
    }
    /*@GetMapping(path="/getAll")
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
    }*/
    @GetMapping(path="/getOne")
    public @ResponseBody JSONObject findOne(@RequestParam(value = "sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        if(sampleId==null||sampleId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
        else if(SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId))==null) {   //此样品接收登记的id不存在；

            code=522;
            msg="数据不存在";
            //data=null;
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
        else{
            SampleReceipt s= SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId));
            data.put("sampleName",s.getSampleName());
            data.put("applicationUnit",s.getApplicationUnit());
            data.put("version",s.getVersion());
            data.put("contractId",s.getContractId());
            data.put("testType",s.getTestType());
            data.put("electronicMedia",s.getElectronicMedia());
            JSONArray list=new JSONArray();
            for(int i=0;i<9;i++)
            {
                JSONObject tmp=new JSONObject();
                if(s.getBaseMs().charAt(i)!='0')
                {
                    tmp.put("materialId",i+1);
                    tmp.put("materialType",s.getBaseMs().charAt(i));
                    if(s.getBaseMs().charAt(i)-'0'>8)
                    {
                        tmp.put("materialName",s.getOthers());
                    }
                     list.add(tmp);
                }
            }
            data.put("data",list);
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",data);
            return json;
        }
    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modify(@RequestParam(value = "data",required = false) JSONObject data)
    {
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject d=new JSONObject();
        d=null;
        String idstr=data.getString("sampleId");
        String testtypestr=data.getString("testType");
        String sofwwaretypestr=data.getString("softwareType");
        String datestr=data.getString("receiveDate");
        try {
            Long.parseLong(idstr);
            Integer.parseInt(testtypestr);
            Integer.parseInt(sofwwaretypestr);
        }catch (NumberFormatException e){
            code=534;
            msg="修改后数据不合法";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        if(idstr==""||idstr==null)
        {
            code=531;
            msg="未收到标识编号";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        else if((testtypestr!="1"&&testtypestr!="2"&&testtypestr!="0")
                ||(sofwwaretypestr!="0"&&sofwwaretypestr!="1"&&sofwwaretypestr!="2"&&sofwwaretypestr!="3"))
        {
            code=534;
            msg="修改后数据不合法";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        Long sampleId=Long.parseLong(idstr);
        String applicationUnit=data.getString("applicationUnit");
        String version=data.getString("version");
        String contractId=data.getString("contractId");
        int testType=Integer.parseInt(testtypestr);
        String electronicMedia=data.getString("electronicMedia");
        int softwareType=Integer.parseInt(sofwwaretypestr);
        String receiveUnit=data.getString("receiveUnit");
        Date receiveDate=java.sql.Date.valueOf(datestr);
        String sender=data.getString("sender");
        String receiver=data.getString("receiver");
        JSONArray list=(JSONArray)data.get("materialList");
        if(SampleReceiptRepository.findBySampleId(sampleId)==null)
        {
            code=532;
            msg="数据不存在";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        else
        {
            SampleReceipt s=new SampleReceipt();
            s.setSampleId(sampleId);
            s.setApplicationUnit(applicationUnit);
            s.setContractId(contractId);
            s.setElectronicMedia(electronicMedia);
            s.setTestType(testType);
            s.setSoftwareType(softwareType);
            s.setReceiveDate(receiveDate);
            s.setVersion(version);
            s.setSender(sender);
            s.setReceiveUnit(receiveUnit);
            s.setReceiver(receiver);
            int basems=0;
            if(list!=null||list.size()!=0)
            {
                for(int i=0;i<list.size();i++)
                {
                    JSONObject tmp=JSONObject.fromObject(list.get(i));
                    basems+=basems*10+Integer.parseInt(tmp.getString("materialType"));
                }
            }
            s.setBaseMs(basems+"");
            SampleReceiptRepository.saveAndFlush(s);
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
    }
    }
}
//select * from sampleio;
