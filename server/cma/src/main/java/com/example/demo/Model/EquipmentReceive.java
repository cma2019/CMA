package com.example.demo.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import com.example.demo.framework.DateJsonTypeConvert;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "EQUIPMENT_RECEIVE_INFO")
public class EquipmentReceive {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private long id;
    private String name;             //设备名称
    private String model;            //型号
    private String manufacturer;              //厂家
    private String receiveSituation;           //接收情况
    private String recipient;         //接收人
    private Date receiveDate;  //接受日期
    private String equipmentSituation;   //安装调试情况
    private String acceptance;           //验收情况
    private String acceptancePerson;     //验收人
    private Date acceptanceDate;         //验收日期
    private File attachment;		     //附属文件

    public void setId(long id){this.id=id;}
    public long getId(){return this.id;}

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setModel(String model){this.model=model;}
    public String getModel(){return this.model;}

    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public String getManufacturer(){ return this.manufacturer; }

    public void setReceiveSituation(String receiveSituation){ this.receiveSituation=receiveSituation; }
    public String getReceiveSituation(){ return receiveSituation; }

    public void setAcceptance(String acceptance) { this.acceptance = acceptance; }
    public String getAcceptance(){return this.acceptance;}

    public void setAcceptanceDate(Date acceptanceDate) { this.acceptanceDate = acceptanceDate; }
    @JsonSerialize(using=DateJsonTypeConvert.class)
    public Date getAcceptance_date(){return this.acceptanceDate;}

    public void setAcceptancePerson(String acceptancePerson) { this.acceptancePerson = acceptancePerson; }
    public String getAcceptance_person(){return this.acceptancePerson;}

    public void setReceiveDate(Date receive_date) { this.receiveDate = receiveDate; }

    @JsonSerialize(using=DateJsonTypeConvert.class)
    public Date getReceiveDate(){return this.receiveDate; }

    public void setAttachment(File attachment) { this.attachment = attachment; }
    public File getAttachment(){return this.attachment;}

    public void setEquipmentSituation(String equipmentSituation) {this.equipmentSituation = equipmentSituation; }
    public String getEquipmentSituation(){return this.equipmentSituation;}

    public void setRecipient(String recipient) { this.recipient = recipient; }
    public String getRecipient(){ return this.recipient; }


}
