package com.example.demo.Controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.Model.SampleReceipt;
import com.example.demo.Repository.SampleReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/cma/SampleReceipt")
public class SampleReceiptController {
    @Autowired
    private SampleReceiptRepository SampleReceiptRepository;
    @GetMapping(path="/getAll")
    public @ResponseBody JSONObject getAll(){
        List<SampleReceipt> res=SampleReceiptRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++) {
                JSONObject tmp = new JSONObject();
                tmp.put("sampleId", res.get(i).getSampleId());
                tmp.put("applicationUnit", res.get(i).getApplicationUnit());
                tmp.put("sampleName", res.get(i).getSampleName());
                tmp.put("version", res.get(i).getVersion());
                data.add(tmp);
            }
        }
        else
        {
            code=522;
            msg="数据不存在";
            //data=null;
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path="/addOne",consumes="application/json",produces="application/json")
    public @ResponseBody  JSONObject addOne(@RequestBody JSONObject data){
        JSONObject js= new JSONObject();

        int code=200;
        String msg="成功";
        JSONObject d=new JSONObject();
        System.out.println(data);
        System.out.println("?");
        String idstr=(String)data.get("sampleId");
        System.out.println(idstr);
        String testtypestr=data.getString("testType");
        System.out.println(testtypestr);
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
        Date receiveDate= Date.valueOf(datestr);
        String sender=data.getString("sender");
        String receiver=data.getString("receiver");
        JSONArray list =data.getJSONArray("materialList");
        if(idstr==""||applicationUnit==""||version==""||contractId==""||
        testtypestr==""||electronicMedia==""||sofwwaretypestr==""||
        receiveUnit==""||datestr==""||sender==""||receiver=="")
        {
            code=511;
            msg="缺少请求参数";
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
             StringBuilder index=new StringBuilder("000000000");
             for(int i=0;i<list.size();i++)
             {
                 JSONObject tmp = (JSONObject) list.get(i);
                 System.out.println(tmp.getString("materialId"));
                 System.out.println(tmp.getString("materialType"));
                 System.out.println(index);
                 index.replace(Integer.parseInt(tmp.getString("materialId")) - 1, Integer.parseInt(tmp.getString("materialId")), tmp.getString(" materialType"));
                 if(i==list.size()-1)
                 {
                     s.setOthers(tmp.getString("materialName"));
                 }
             }
             String basems=index.toString();
             s.setBaseMs(basems);
             SampleReceiptRepository.saveAndFlush(s);
        }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",d);
        return js;
    }
    @PostMapping (path="/deleteOne")
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        System.out.println(sampleId);
        if(sampleId==null||sampleId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
        }
        else if(SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
        }
        else
        {
            SampleReceiptRepository.deleteById(Long.parseLong(sampleId));
        }
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",null);
        return json;
    }
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
        }
        else if(sampleId=="0"||SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId))==null) {   //此样品接收登记的id不存在；

            code=522;
            msg="数据不存在";
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
            System.out.println(s.getBaseMs());
            for(int i=0;i<9;i++)
            {
                JSONObject tmp=new JSONObject();
                if(s.getBaseMs().charAt(i)!='0')
                {
                    tmp.put("materialId",i+1);
                    tmp.put("materialType",s.getBaseMs().charAt(i));
                    if(i==8)
                    {
                        tmp.put("materialName",s.getOthers());
                    }
                     list.add(tmp);
                    data.put("data",list);
                }
            }
        }
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @PostMapping(path="/modifyOne")
    public @ResponseBody JSONObject modify(@RequestParam(value = "data",required = false) JSONObject data)
    {
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject d= new JSONObject();
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
        if(idstr=="")
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
                    JSONObject tmp=(JSONObject)list.get(i);
                    basems+=(int)(Integer.parseInt(tmp.getString("materialType"))*Math.pow(10,Integer.parseInt(tmp.getString("materialId"))));
                }
            }
            s.setBaseMs(basems+"");
            SampleReceiptRepository.saveAndFlush(s);
    }
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",d);
        return js;
    }
    /*@PostMapping(path="addReceive")
    public @ResponseBody JSONObject addReceive(@RequestParam(value = "sampleNumber", required = false)String sampleNumber,
                                               @RequestParam(value = "sampleName", required = false) String sampleName,
                                               @RequestParam(value = "sampleAmount", required = false) String sampleAmount,
                                               @RequestParam(value = "sampleState", required = false) String sampleState,
                                               @RequestParam(value="requester",required = false) String requester,
                                               @RequestParam(value="receiver",required = false)String receiver,
                                               @RequestParam(value="receiveDate",required = false) String receiveDate,
                                               @RequestParam(value="obtainer",required = false)String obtainer,
                                               @RequestParam(value = "obtainDate",required = false)String obtainDate,
                                               @RequestParam(value="receiptId",required = false) String receiptId)
    {
        JSONObject js=new JSONObject();
        return js;

    }*/
}
