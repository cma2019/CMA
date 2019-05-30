package com.example.demo.Model;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
@Table(name = "EQUIPMENT_RECEIVE_INFO")
public class EquipmentReceive {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private long id;
    private String name;             //设备名称
    private String model;            //型号
    private String manufacturer;              //厂家
    private String receive_situation;           //接收情况
    private String recipient;         //接收人
    private String receive_date;  //机身编号
    private String equipment_situation;   //安装调试情况
    private String acceptance;           //验收情况
    private String acceptance_person;     //验收人
    private String acceptance_date;         //验收日期
    private String attachment;		     //附属文件

    public void setId(long id){this.id=id;}
    public long getId(){return this.id;}

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setModel(String model){this.model=model;}
    public String getModel(){return this.model;}

    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public String getManufacturer(){ return this.manufacturer; }

    public void setReceive_situation(String receive_situation){ this.receive_situation=receive_situation; }
    public String getReceive_situation(){ return receive_situation; }

    public void setAcceptance(String acceptance) { this.acceptance = acceptance; }
    public String getAcceptance(){return this.acceptance;}

    public void setAcceptance_date(String acceptance_date) { this.acceptance_date = acceptance_date; }
    public String getAcceptance_date(){return this.acceptance_date;}

    public void setAcceptance_person(String acceptance_person) { this.acceptance_person = acceptance_person; }
    public String getAcceptance_person(){return this.acceptance_person;}

    public void setReceive_date(String receive_date) { this.receive_date = receive_date; }
    public String getReceive_date(){return this.receive_date; }

    public void setAttachment(String attachment) { this.attachment = attachment; }
    public String getAttachment(){return this.attachment;}

    public void setEquipment_situation(String equipment_situation) {this.equipment_situation = equipment_situation; }
    public String getEquipment_situation(){return equipment_situation;}

    public void setRecipient(String recipient) { this.recipient = recipient; }
    public String getRecipient(){ return this.recipient; }
}
