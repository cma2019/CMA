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
public class SampleIoController
{
    @Autowired
    private SampleIoRepository SampleIoRepository;
    @Autowired
    private SampleReceiveRepository SampleReceiveRepository;
    @Autowired
    private SampleReceiptRepository SampleReceiptRepository;
    @PostMapping(path="/addOne")
    // * 添加一项样品进出登记表表项
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
                      @RequestParam(value = "receiptId",required = false) String receiptId)
    {
         //* 参数列表包括所有必填信息，以String类型解析前端传过来的参数，便于做判空操作
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        // * 由于以String类型解析参数，因此要做数据合法性的判断，比如String转Date，String转Long
        try
        {
            Long.parseLong(receiptId);
            Integer.parseInt(sampleState);
            Integer.parseInt(sampleAmount);
            java.sql.Date.valueOf(sendDate);
            java.sql.Date.valueOf(obtainDate);
        }
        catch (NumberFormatException e)
        {
            code=513;
            msg="某项数据错误";
        }
       //参数判空
        if(sampleNumber== null||sampleName==null||sender==null||receiver==null||obtainer==null
        ||sampleNumber.equals("")||sampleName.equals("")||sender.equals("")||receiver.equals("")||obtainer.equals(""))
        {
            code=511;
            msg="缺少请求参数";
        }
       // 表项重复，不可添加
        else if(SampleIoRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code=512;
            msg="样品编号已存在";
        }
       //进出登记表中样品编号需要与登记表中某表项的样品编号一致
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber)==null)
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        //除样品编号外，有另外参数，在进出登记表与登记表中需要保持一致
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleAmount()!=Integer.parseInt(sampleAmount)||
                !SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleName().equals(sampleName)||
                SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleState()!=Integer.parseInt(sampleState))
        {
            code=514;
            msg="添加数据与登记表不符";
        }
       //参数长度限制
        else if(sampleNumber.length()>10||sampleName.length()>20|| sender.length()>20||
                receiver.length()>20||obtainer.length()>20|| Integer.parseInt(sampleAmount)<1||
                (Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2))
        {
            code=513;
            msg="某项数据错误";
        }
        //参数完整且合法，生成一个表项对象，根据输入参数实例化，并将对象主体存到数据库中
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
         /*
            返回格式为json，前端通过解析code可以知道运行结果，
            包括报错信息；前端通过解析data，可以获取想要的数据
         */
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        System.out.println(js);
        return js;
    }
    @PostMapping (path="/deleteOne")
     // 删除一项样品进出登记表表项
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleIoId",required = false) String sampleIoId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        //JSONObject data=null;
        //System.out.println(sampleIoId);
        /*
            参数是表项在数据库中的主键，当然也可以是样品编号，
            总之需要是唯一性数据，这里以字符串格式接受参数，
            也需要做类型转换以及数据合法性判断
         */
        if(sampleIoId==null||sampleIoId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
        }
        //如果传输的参数在数据库中没有对应数据需要报错
        else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
        }
        //传输的参数合法且完整，正常从数据库中删除对应表项
        else
        {
            SampleIoRepository.deleteById(Long.parseLong(sampleIoId));
        }
        //返回json，前端解析code,msg,data然后与用户交互
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",null);
        return json;
    }
    @GetMapping(path="/getAll")
    //获取数据库中存在的样品进出登记表完整列表
    public @ResponseBody JSONObject findALL()
    {
        //调用findAll()方法获取登记表列表
        List<SampleIO> res= SampleIoRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
       //遍历进出登记表列表，将需要的信息存放在data中
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
        //列表为空
        else
        {
            code=210;
            msg="无有效信息返回";
            //data=null;
        }
        //返回json，前端通过解析json中的code获取请求结果，前端解析json中的data获取需要展示的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @GetMapping(path="/getOne")
    //获取样品进出登记表列表中的某一表项
    public @ResponseBody JSONObject findOne(@RequestParam(value = "sampleIoId",required = false) String sampleIoId)
    {
            JSONObject json=new JSONObject();
            int code=200;
            String msg="成功";
            JSONObject data=new JSONObject();
             //前端传过来的参数为空字符串（没有判断前端手滑没传参数的情况）
            if(sampleIoId==null||sampleIoId.equals(""))
            {
                code=500;
                msg="未收到标识编号";
            }
            /*
                根据前端传输的参数在数据库的列表中搜索，
                获取不到对应数据时报错
             */
            else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null)
            {
                //此样品接收登记的id不存在；
                code=500;
                msg="数据不存在";
            }
        //参数完整且正确时，将各种必须数据放在data中
            else
           {
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
        //返回json，前端通过解析code获取请求结果，通过解析data获取需要展示的信息
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @PostMapping(path="/modifyOne")
    //用户输入需要修改的参数修改样品进出登记表中某表项
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
        //以字符串接受各种参数，对需要做类型转换的参数做合法性判断
        try
        {
            Integer.parseInt(sampleState);
            Integer.parseInt(sampleAmount);
            java.sql.Date.valueOf(sendDate);
            java.sql.Date.valueOf(obtainDate);
        }
        catch (NumberFormatException e)
        {
            code=534;
            msg="修改后数据不合法";
        }
       //参数判空
        if(sampleIoId==null||sampleIoId.equals(""))
        {
            code=531;
            msg="未收到标识编号";
         }
         /*
            通过id在列表中搜索确定需要修改的表项，
            如果根据前端传的参数id，如果找不到对应
            的表项需要报错
         */
        else if(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId))==null)
        {
            code=532;
            msg="数据不存在";
        }
       //确保样品编号唯一性
        else if(!sampleNumber.equals(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId)).getSampleNumber())&&SampleIoRepository.findBySampleNumber(sampleNumber)!=null) {
            /*System.out.println(sampleNumber);
            System.out.println(SampleIoRepository.findBySampleIoId(Long.parseLong(sampleIoId)).getSampleNumber());
            System.out.println(receiver);*/
            code = 533;
            msg = "修改后数据错误";
        }
       //确保进出登记表与样品登记表的一致性
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber)==null)
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        //确保进出登记表与样品登记表的一致性
        else if(SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleAmount()!=Integer.parseInt(sampleAmount)||
                !SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleName().equals(sampleName)||
                SampleReceiveRepository.findBySampleNumber(sampleNumber).getSampleState()!=Integer.parseInt(sampleState))
        {
            code=514;
            msg="添加数据与登记表不符";
        }
        //某些参数有长度限制
        else if(sampleNumber.length()>10||sampleName.length()>20||sender.length()>20
                ||receiver.length()>20||obtainer.length()>20||(Integer.parseInt(sampleAmount)<1||(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2)))
        {
            code=534;
            msg="修改后数据不合法";
        }
        //参数完整且合法，正常修改并更新数据库中的表项
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
        //返回json，前端解析code获取请求结果，解析data获取需要的数据
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
}
//select * from sampleio;
//alter table sampleio ENGINE =InnoDB;