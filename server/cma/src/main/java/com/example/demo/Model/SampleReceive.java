package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class SampleReceive {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long sampleId;           //样品标识编号(无实义)
    private String sampleNumber;     //**样品编号(每个样品有唯一对应编号)
    private String sampleName;       //**样品名称(一个样品编号对应唯一一个样品名称)
    private int sampleAmount;       //**样品的数量(默认为1)
    private int sampleState;        //**样品状态(0待处理，进入样品室之后(1待测，2测毕)，送出样品室之后(3已处理))注：可多次进入样品室，不过每次进入的记录保存(只需更新状态)
    private String requester;        //委托单位
    private String receiver;         //接收人
    private Date receiveDate;        //接受日期
    private String obtainer;         //领取人
    private Date obtainDate;         //领取日期
    private Long receiptId;

    public void setSampleId(long id){
        this.sampleId=id;
    }
    public Long getSampleId(){
        return this.sampleId;
    }
    public void setSampleNumber(String sampleNumber){
        this.sampleNumber=sampleNumber;
    }
    public String getSampleNumber(){
        return this.sampleNumber;
    }
    public void setSampleName(String sampleName){
        this.sampleName=sampleName;
    }
    public String getSampleName(){
        return this.sampleName;
    }
    public void setSampleAmount(int sampleAmount){
        this.sampleAmount=sampleAmount;
    }
    public int getSampleAmount(){
        return this.sampleAmount;
    }
    public void setSampleState(int sampleState){
        this.sampleState=sampleState;
    }
    public int getSampleState(){
        return this.sampleState;
    }
    public void setRequester(String requester){
        this.requester=requester;
    }
    public String getRequester(){
        return this.requester;
    }
    public void setReceiver(String receiver){
        this.receiver=receiver;
    }
    public String getReceiver(){
        return this.receiver;
    }
    public void setReceiveDate(Date receiveDate)
    {
        this.receiveDate=receiveDate;
    }
    public Date getReceiveDate(){return this.receiveDate;
    }
    public void setObtainer(String obtainer)
    {
        this.obtainer=obtainer;
    }
    public String getObtainer(){return this.obtainer;
    }
    public void setObtainDate(Date obtainDate)
    {
        this.obtainDate=obtainDate;
    }
    public Date getObtainDate(){return this.obtainDate;
    }
    public void setReceiptId(Long receiptId){
        this.receiptId=receiptId;
    }
    public Long getReceiptId(){return receiptId;}
}
