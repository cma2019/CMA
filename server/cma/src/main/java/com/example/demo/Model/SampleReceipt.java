package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class SampleReceipt {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long sampleId;            //样品标识编号(无实义)

    private String applicationUnit;   //申报单位
    private String sampleName;        //**软件产品名称(即样品名称)
    private String version;           //版本号
    private String contractId;        //合同编号
    private int testType;            //测试类型(0登记检测,1确认检测,2验收检测)
    private String electronicMedia;   //电子媒介
    //对于这个材料清单列表，我推荐对这于材料单独创建一个类，然后这个地方使用材料类的数组
    private String baseMs;     //基础材料编号为1-7；
    //以上括号中的均是0：没有该材料 1：只有电子文档 2：只有书面文档 3：两种文档都有
    //对应于《用户手册》《计算机软件产品登记检测申请表》《材料交接单》《软件产品功能列表》
    //《软件名称与版本号确认单》《受测软件产品简介》 《自主产权保证书》
    private int softwareSample;      //软件样品一套 0：无 1：有电子档
    private String others;            //其他材料
    private int softwareType;        //软件类型 0：系统软件 1：支持软件 2：应用软件 3：其它软件
    private String receiveUnit;       //接收单位
    private Date receiveDate;         //接收日期
    private String sender;            //报送人
    private String receiver;          //受理人

    public void setSampleId(Long id){this.sampleId=id;}
    public Long getSampleId(){return this.sampleId;}
    public void setSampleName(String name){this.sampleName=name;}
    public String getSampleName(){return this.sampleName;}
    public void setApplicationUnit(String applicationUnit){this.applicationUnit=applicationUnit;}
    public String getApplicationUnit(){return this.applicationUnit;}
    public void setVersion(String version){this.version=version;}
    public String getVersion(){return this.version;}
    public void setContractId(String contractId){this.contractId=contractId;}
    public String getContractId(){return this.contractId;}
    public void setTestType(int testType){this.testType=testType;}
    public int getTestType(){return this.testType;}
    public void setElectronicMedia(String electronicMedia){this.electronicMedia=electronicMedia;}
    public String getElectronicMedia(){return this.electronicMedia;}
    public void setBaseMs(String list){this.baseMs=list;}
    public String getBaseMs(){return this.baseMs;}
    public void setSoftwareSample(int softwareSample){this.softwareSample=softwareSample;}
    public int getSoftwareSample(){return this.softwareSample;}
    public void setOthers(String others){this.others=others;}
    public String getOthers(){return this.others;}
    public void setSoftwareType(int softwareType){this.softwareType=softwareType;}
    public int getSoftwareType(){return this.softwareType;}
    public void setReceiveUnit(String receiveUnit){this.receiveUnit=receiveUnit;}
    public String getReceiveUnit(){return this.receiveUnit;}
    public void setReceiveDate(Date receiveDate){this.receiveDate=receiveDate;}
    public Date getReceiveDate(){return this.receiveDate;}
    public void setSender(String sender){ this.sender=sender;}
    public String getSender(){return this.sender;}
    public void setReceiver(String receiver){this.receiver=receiver;}
    public String getReceiver(){return this.receiver;}
}

