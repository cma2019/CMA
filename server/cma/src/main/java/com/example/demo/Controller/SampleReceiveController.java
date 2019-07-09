package com.example.demo.Controller;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import  com.example.demo.Repository.SampleReceiveRepository ;
import  com.example.demo.Repository.SampleReceiptRepository ;
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
import com.example.demo.Model.SampleReceipt;
import java.util.List;
//import java.sql.Date;

@Controller
@RequestMapping(path="/cma/SampleReceive")
public class SampleReceiveController {
    @Autowired
    private SampleReceiveRepository sampleReceiveRepository;
    @Autowired
    private SampleReceiptRepository sampleReceiptRepository;
    @PostMapping(path="/addOne")
    /*
     * 添加一项样品登记表表项
     */
    public @ResponseBody
    JSONObject addOne(@RequestParam(value = "sampleNumber", required = false)String sampleNumber,
                          @RequestParam(value = "sampleName", required = false) String sampleName,
                          @RequestParam(value = "sampleAmount", required = false) String sampleAmount,
                          @RequestParam(value = "sampleState", required = false) String sampleState,
                          @RequestParam(value="requester",required = false) String requester,
                          @RequestParam(value="receiver",required = false)String receiver,
                          @RequestParam(value="receiveDate",required = false) String receiveDate,
                          @RequestParam(value="obtainer",required = false)String obtainer,
                          @RequestParam(value = "obtainDate",required = false)String obtainDate,
                          @RequestParam(value="receiptId",required = false) String receiptId){
        //System.out.println(sampleNumber);
        //System.out.println(obtainer);
        /*
         * 参数列表包括所有必填信息，以String类型解析前端传过来的参数，便于做判空操作
         */
        JSONObject js=new JSONObject();
        int code=200;
        String msg="成功";
        /*
         * 由于以String类型解析参数，因此要做数据合法性的判断，比如String转Date，String转Long
         */
        try{
            Long.parseLong(receiptId);
            Integer.parseInt(sampleAmount);
            Integer.parseInt(sampleState);
            java.sql.Date.valueOf(receiveDate);
            java.sql.Date.valueOf(obtainDate);
        }catch(NumberFormatException e){
            code=513;
            msg="某项数据错误";
        }
        /*
            参数为空字符串或者前端传参出错没有传过来
         */
        if(sampleNumber==null||sampleName==null||requester==null||receiver==null||obtainer==null||
        sampleNumber.equals("")||sampleName.equals("")||requester.equals("")||receiver.equals("")||obtainer.equals(""))
        {
            code=511;
            msg="缺少请求参数";
        }
        /*
            样品编号是唯一的，如果数据库中已经存在，不可添加
         */
        else if(sampleReceiveRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code=512;
            msg="样品编号已存在";
        }
        /*
            参数长度有限制
         */
        else if(sampleNumber.length()>10||sampleName.length()>20||
                requester.length()>45||receiver.length()>20||obtainer.length()>20||(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1&&Integer.parseInt(sampleState)!=2))
        {
            code=513;
            msg="某项数据错误";
        }
        /*
            参数完整且合法，生成一个表项对象，根据输入参数实例化，并将对象主体存到数据库中
         */
        else
        {
            SampleReceive receive=new SampleReceive();
            receive.setSampleNumber(sampleNumber);
            receive.setSampleName(sampleName);
            receive.setSampleAmount(Integer.parseInt(sampleAmount));
            receive.setSampleState(Integer.parseInt(sampleState));
            receive.setRequester(requester);
            receive.setReceiver(receiver);
            receive.setReceiveDate(java.sql.Date.valueOf(receiveDate));
            receive.setObtainer(obtainer);
            receive.setObtainDate(java.sql.Date.valueOf(obtainDate));
            receive.setReceiptId(Long.parseLong(receiptId));
            sampleReceiveRepository.saveAndFlush(receive);
            if(!receiptId.equals("0"))
            {
                SampleReceipt sr=sampleReceiptRepository.findBySampleId(Long.parseLong(receiptId));
                sr.setSampleName(sampleName);
                sampleReceiptRepository.saveAndFlush(sr);
            }
        }
        /*
            返回格式为json，前端通过解析code可以知道运行结果，
            包括报错信息；前端通过解析data，可以获取想要的数据
         */
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
    @PostMapping (path="/deleteOne")
    /*
        删除一项样品登记表表项
     */
    public @ResponseBody JSONObject deleteOne(@RequestParam(value="sampleId",required = false) String sampleId)
    {
        JSONObject json=new JSONObject();
        int code=200;
        String msg="成功";
        /*
            参数是表项在数据库中的主键，当然也可以是样品编号，
            总之需要是唯一性数据，这里以字符串格式接受参数，
            也需要做类型转换以及数据合法性判断
         */
        try{
            Long.parseLong(sampleId);
        }catch (NumberFormatException E){
            code=500;
            msg="请求参数格式错误";
            json.put("code",code);
            json.put("msg",msg);
            json.put("data",null);
            return json;
        }
        if(sampleId.equals(""))
        {
            code=521;
            msg="未收到标识编号";
        }
        /*
            如果传输的参数在数据库中没有对应数据需要报错
         */
        else if(sampleReceiveRepository.findBySampleId(Long.parseLong(sampleId))==null) //此样品接收登记的id不在表中
        {
            code=522;
            msg="数据不存在";
        }
        /*
            传输的参数合法且完整，正常从数据库中删除对应表项
         */
        else
        {
            sampleReceiveRepository.deleteById(Long.parseLong(sampleId));
        }
        /*
            返回json，前端解析code,msg,data然后与用户交互
         */
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",null);
        return json;
    }
    @GetMapping(path="/getAll")
    /*
        获取数据库中存在的样品登记表完整列表
     */
    public @ResponseBody JSONObject findALL()
    {
        /*
            调用findAll()方法获取登记表列表
         */
        List<SampleReceive> res= sampleReceiveRepository.findAll();
        JSONObject js=new JSONObject();
        JSONArray data=new JSONArray();
        int code=200;
        String msg="成功";
        /*
            遍历登记表列表，将需要的信息存放在data中
         */
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
        }
        /*
            列表为空
         */
        else
        {
            code=210;
            msg="无有效信息返回";
            //data=null;
        }
        /*
            返回json，前端通过解析json中的code获取请求结果，前端解析json中的data获取需要展示的数据
         */
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",data);
        return js;
    }
    @GetMapping(path="/getOne")
    /*
        获取样品登记表列表中的某一表项
     */
    public @ResponseBody JSONObject findOne(@RequestParam(value = "sampleId",required = false) String  sampleId)
    {
            JSONObject json=new JSONObject();
            int code=200;
            String msg="成功";
            JSONObject data=new JSONObject();
            /*
                前端传过来的参数为空字符串（没有判断前端手滑没传参数的情况）
             */
            if(sampleId.equals(""))
            {
                code=500;
                msg="未收到标识编号";
            }
            /*
                根据前端传输的参数在数据库的列表中搜索，
                获取不到对应数据时报错
             */
            else if(sampleReceiveRepository.findBySampleId(Long.parseLong(sampleId))==null) {   //此样品接收登记的id不存在；

                code=500;
                msg="数据不存在";
            }
            /*
                参数完整且正确时，将各种必须数据放在data中
             */
            else {
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
            data.put("receiptId",recv.getReceiptId());
        }
        /*
            返回json，前端通过解析code获取请求结果，通过解析data获取需要展示的信息
         */
        json.put("code",code);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }
    @PostMapping(path="/modifyOne")
    /*
        用户输入需要修改的参数修改样品登记表中某表项
     */
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
        /*
            以字符串接受各种参数，对需要做类型转换的参数做合法性判断
         */
        try{
            Integer.parseInt(sampleAmount);
            Integer.parseInt(sampleState);
            java.sql.Date.valueOf(receiveDate);
            java.sql.Date.valueOf(obtainDate);
        }catch(NumberFormatException e){
            code =533;
            msg="修改后数据错误";
        }
        //System.out.println(sampleId.equals(""));
        /*
            通过id在列表中搜索确定需要修改的表项，
            如果根据前端传的参数id，如果找不到对应
            的表项需要报错
         */
        if(sampleReceiveRepository.findBySampleId(Integer.parseInt(sampleId))==null)
        {
            code=532;
            msg="数据不存在";
        }
        /*
            因为样品编号不可重复，如果用户修改了样品编号与
            登记表列表中某表项冲突需要报错
         */
        else if(!sampleNumber.equals( sampleReceiveRepository.findBySampleId(Integer.parseInt(sampleId)).getSampleNumber())&&sampleReceiveRepository.findBySampleNumber(sampleNumber)!=null)
        {
            code =533;
            msg="修改后数据错误";
        }
        /*
            某些参数有长度限制
         */
        else if((sampleNumber.length()>10)||(sampleName!=null&&sampleName.length()>20)||(requester!=null&&requester.length()>45)
                ||(receiver!=null&&receiver.length()>20)||(obtainer!=null&&obtainer.length()>20)||(Integer.parseInt(sampleState)!=2)&&(Integer.parseInt(sampleState)!=0&&Integer.parseInt(sampleState)!=1))
        {
            code=534;
            msg="修改后数据不合法";
        }
        /*
            参数完整且合法，正常修改并更新数据库中的表项
         */
        else
        {
            SampleReceive recv= sampleReceiveRepository.findBySampleId(Integer.parseInt(sampleId));
            //if(obtainDate!=null&&!obtainDate.equals(""))
                recv.setObtainDate(java.sql.Date.valueOf(obtainDate));
            //else
                //recv.setObtainDate(recv.getObtainDate());
            //if(receiveDate!=null&&!receiveDate.equals(""))
                recv.setObtainDate(java.sql.Date.valueOf(receiveDate));
            //else
                //recv.setObtainDate(recv.getObtainDate());
            //if(sampleState!=null&&!sampleState.equals(("")))
                recv.setSampleState(Integer.parseInt(sampleState));
            //else
                //recv.setSampleState(recv.getSampleState());
            //if(!sampleAmount.equals(("")))
                recv.setSampleAmount(Integer.parseInt(sampleAmount));
            //else
                //recv.setSampleAmount(recv.getSampleAmount());
            recv.setSampleId(Integer.parseInt(sampleId));
            //if(obtainer!=null&&!obtainer.equals(""))
                recv.setObtainer(obtainer);
            //else
                //recv.setObtainer(recv.getObtainer());
            //if(receiver!=null&&!receiver.equals(""))
                recv.setReceiver(receiver);
            //else
                //recv.setReceiver(recv.getReceiver());
            //if(requester!=null&&!requester.equals(""))
                recv.setRequester(requester);
            //else
                //recv.setRequester(recv.getRequester());
            //if(sampleNumber!=null&&!sampleNumber.equals(""))
                recv.setSampleNumber(sampleNumber);
            //else
                //recv.setSampleNumber(recv.getSampleNumber());
            //if(sampleName!=null&&!sampleName.equals(""))
                recv.setSampleName(sampleName);
            //else
                //recv.setSampleName(recv.getSampleName());
            sampleReceiveRepository.saveAndFlush(recv);
        }
        /*
            返回json，前端解析code获取请求结果，解析data获取需要的数据
         */
        js.put("code",code);
        js.put("msg",msg);
        js.put("data",null);
        return js;
    }
}
//select * from sample_receive;
//alter table sample_receive ENGINE =InnoDB;