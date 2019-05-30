package com.example.demo.Model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *@author yxp
 * 仪器设备
 * 仪器设备信息
 */

@Entity
@Table(name = "EQUIPMENT_INFO")
public class Equipment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;             //设备名称
    private String model;            //型号
    private String cpu;              //CPU
    private String memory;           //内存
    private String hardDisk;         //硬盘
    private String equipmentNumber;  //机身编号
    private String application;      //用途
    private Byte state;              //使用情况（准用/停用）1/0

    public void setId(long id){this.id=id;}
    public long getId(){return this.id;}

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setModel(String model){this.model=model;}
    public String getModel(){return this.model;}

    public void setMemory(String memory){this.memory=memory;}
    public String getMemory(){return this.memory;}

    public void setCpu(String cpu){this.cpu=cpu;}
    public String getCpu(){return this.cpu;}

    public void setHardDisk(String hardDisk){this.hardDisk=hardDisk;}
    public String getHardDisk(){return this.hardDisk;}

    public void setEquipmentNumber(String equipmentNumber){this.equipmentNumber=equipmentNumber;}
    public String getEquipmentNumber(){return this.equipmentNumber;}

    public void setApplication(String application) { this.application = application; }
    public String getApplication(){return this.application;}

    public void setState(Byte state){this.state=state;}
    public Byte getState(){return this.state;}
}
