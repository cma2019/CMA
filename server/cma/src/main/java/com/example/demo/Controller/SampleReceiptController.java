package com.example.demo.Controller;
//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.Model.SampleReceipt;
import com.example.demo.Repository.SampleReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/cma/SampleReceipt")
public class SampleReceiptController
{
    @Autowired
    private SampleReceiptRepository SampleReceiptRepository;
    @GetMapping(path="/getAll")
        //`获取接受单列表
    public @ResponseBody JSONObject getAll()
    {
            //调用findAll方法获取数据库中全部接受单表项，返回list
        List<SampleReceipt> res=SampleReceiptRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
            //遍历接受单列表，将信息存放在data中
        if(res.size()>0)
        {
            for(int i=0;i<res.size();i++)
            {
                JSONObject tmp = new JSONObject();
                tmp.put("sampleId", res.get(i).getSampleId());
                tmp.put("applicationUnit", res.get(i).getApplicationUnit());
                tmp.put("sampleName", res.get(i).getSampleName());
                tmp.put("version", res.get(i).getVersion());
                data.add(tmp);
            }
        }
            //列表为空
        else
        {
            code=522;
            msg="数据不存在";
            //data=null;
        }
            //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @PostMapping(path="/addOne",consumes="application/json",produces="application/json")
     /*
        用户输入各种必要信息，前端组织成json发送请求
        接受前端传输的json参数
     */
    public @ResponseBody  JSONObject addOne(@RequestBody JSONObject data)
    {
        JSONObject js= new JSONObject();
            //解析接受到的json参数，获取相应的信息
        int code=200;
        String msg="成功";
        JSONObject d=new JSONObject();
        System.out.println(data);
        String idstr=(String)data.get("sampleId");
        //System.out.println(idstr);
        String testtypestr=data.getString("testType");
        //System.out.println(testtypestr);
        String softwaretypestr=data.getString("softwareType");
        //System.out.println(softwaretypestr);
        String datestr=data.getString("receiveDate");
           // 需要做类型转换的数据做格式合法性判断
        try {
            Long.parseLong(idstr);
            Integer.parseInt(testtypestr);
            Integer.parseInt(softwaretypestr);
        }catch (NumberFormatException e){
            code=513;
            msg="某项数据错误";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
            //根据解析出来的数据填充数据库中表项的信息
        Long sampleId=Long.parseLong(idstr);
        String applicationUnit=data.getString("applicationUnit");
        String version=data.getString("version");
        String contractId=data.getString("contractId");
        int testType=Integer.parseInt(testtypestr);
        String electronicMedia=data.getString("electronicMedia");
        int softwareType=Integer.parseInt(softwaretypestr);
        String receiveUnit=data.getString("receiveUnit");
        Date receiveDate= Date.valueOf(datestr);
        String sender=data.getString("sender");
        String receiver=data.getString("receiver");
        JSONArray list =data.getJSONArray("materialList");
            //参数判空
        if(idstr.equals("")||applicationUnit.equals("")||version.equals("")||contractId.equals("")||
        testtypestr.equals("")||electronicMedia.equals("")||softwaretypestr.equals("")||
        receiveUnit.equals("")||datestr.equals("")||sender.equals("")||receiver.equals(""))
        {
            code=511;
            msg="缺少请求参数";
        }
            //确保数据不重复
        else if(SampleReceiptRepository.findBySampleId(sampleId)!=null)
        {
            code =512;
            msg="数据已存在";
        }
            //参数完整且合法，正常添加
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
            StringBuilder index=new StringBuilder();
            index.append("000000000");
             for(int i=0;i<list.size();i++)
             {
                 JSONObject tmp = (JSONObject) list.get(i);
                 /*System.out.println(list);
                 System.out.println(index);
                 System.out.println(Integer.parseInt(tmp.getString("materialId")));
                 System.out.println(tmp.getString("materialType"));*/
                 index.replace(Integer.parseInt(tmp.getString("materialId")) - 1, Integer.parseInt(tmp.getString("materialId")), tmp.getString("materialType"));
                 if(i==list.size()-1)
                 {
                     s.setOthers(tmp.getString("materialName"));
                 }
             }
             String basems=index.toString();
             s.setSoftwareSample(basems.charAt(7)-'0');
             s.setBaseMs(basems);
             SampleReceiptRepository.saveAndFlush(s);
        }
           // 返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",d);
        return js;
    }
    @PostMapping (path="/deleteOne")
       // 删除一项样品接收单表项
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        //System.out.println(sampleId);
        /*
            参数判空
         */
        if(sampleId==null||sampleId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
        }
        /*
            删除不存在的数据报错
        */
        else if(SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
        }
        /*
            参数完整且合法，正常删除
         */
        else
        {
            SampleReceiptRepository.deleteById(Long.parseLong(sampleId));
        }
         /*
            返回json，前端解析code,msg,data然后与用户交互
         */
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",null);
        return json;
    }
    @GetMapping(path="/getOne")
        //获取样品接收单中的某一表项
    public @ResponseBody JSONObject findOne(@RequestParam(value = "sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject data=new JSONObject();
        //参数判空或者为空字符串
        if(sampleId==null||sampleId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
        }
        //参数对应的数据在数据库中不存在
        else if(sampleId.equals("0")||SampleReceiptRepository.findBySampleId(Long.parseLong(sampleId))==null) {   //此样品接收登记的id不存在；

            code = 522;
            msg = "数据不存在";
        }
        //参数完整且合法，将必要信息存放在data中
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
            data.put("softwareType",s.getSoftwareType());
            data.put("receiveUnit",s.getReceiveUnit());
            data.put("receiveDate",s.getReceiveDate());
            data.put("sender",s.getSender());
            data.put("receiver",s.getReceiver());
        }
        //返回json，前端通过解析code获取请求结果，通过解析data获取需要展示的信息
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @PostMapping(path="/modifyOne",consumes="application/json",produces="application/json")
    //前端传输用户输入的信息，修改某表项
    public @ResponseBody JSONObject modify(@RequestBody JSONObject data)
    {
        //前端发送json格式请求，以json格式接受参数
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        JSONObject d= new JSONObject();
        //System.out.println(data)
        //解析接受到的json参数
        String idstr=data.getString("sampleId");
        String testtypestr=data.getString("testType");
        String sofwwaretypestr=data.getString("softwareType");
        String datestr=data.getString("receiveDate");
        // 对解析出来的参数做格式合法性判断
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
        //参数判空
        if(idstr.equals(""))
        {
            code=531;
            msg="未收到标识编号";
            js.put("code",code);
            js.put("msg",msg);
            js.put("data",d);
            return js;
        }
        //参数合法性判断
        else if((!testtypestr.equals("1")&&!testtypestr.equals("2")&&!testtypestr.equals("0"))
                ||(!sofwwaretypestr.equals("0")&&!sofwwaretypestr.equals("1")&&!sofwwaretypestr.equals("2")&&!sofwwaretypestr.equals("3")))
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
        JSONArray list =data.getJSONArray("materialList");
        //数据库中没有要修改的表项对象
        if(SampleReceiptRepository.findBySampleId(sampleId)==null)
        {
            code=532;
            msg="数据不存在";
        }
        //参数完整且合法，正常修改并更新数据库表
        else
        {
            SampleReceipt s=SampleReceiptRepository.findBySampleId(sampleId);
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
            StringBuilder index=new StringBuilder();
            index.append(s.getBaseMs());
            for(int i=0;i<list.size();i++)
            {
                JSONObject tmp = (JSONObject) list.get(i);
                System.out.println(list);
                System.out.println(index);
                System.out.println(Integer.parseInt(tmp.getString("materialId")));
                System.out.println(tmp.getString("materialType"));
                index.replace(Integer.parseInt(tmp.getString("materialId")) - 1, Integer.parseInt(tmp.getString("materialId")), tmp.getString("materialType"));
                if(i==list.size()-1)
                {
                    s.setOthers(tmp.getString("materialName"));
                }
            }
            String basems=index.toString();
            s.setSoftwareSample(basems.charAt(7)-'0');
            s.setBaseMs(basems);
            SampleReceiptRepository.saveAndFlush(s);
    }
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",d);
        return js;
    }
}
//select * from sample_receipt;
//alter table sample_receipt ENGINE =InnoDB;
