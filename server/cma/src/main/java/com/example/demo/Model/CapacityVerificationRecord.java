package com.example.demo.Model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@DynamicInsert
@DynamicUpdate
public class CapacityVerificationRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long recordId;     //能力验证记录序号
    private long projectId;//能力验证记录所对应的项目编号
    private Date date;//能力验证记录执行是时间，如2012-01-12
    private String methodID;//能力验证项目的试验方法依据的标准编号
    private String equipmentName;//仪器设备名称（由前端从设备表里获取，后端无需关联两表）
    private String equipmentId;//仪器设备的编号（同上）
    private String experimenter;//试验人员（由前端从人员表里获取，后端无需关联两表）
    private String result;//能力验证结果
    private String resultDeal;//能力验证结果处理状况
    private String note;//结果的备注

    public void setrecordId(long id){
        this.recordId=id;
    }
    public long getrecordId(){ return this.recordId; }

    public void setprojectId(long id){
        this.projectId=id;
    }
    public long getprojectId(){
        return this.projectId;
    }

    public void setDate(Date date){this.date=date;}
    public Date getDate(){return this.date;}

    public void setMethodID(String methodID){
        this.methodID=methodID;
    }
    public String getMethodID(){
        return this.methodID;
    }

    public void setEquipmentName(String equipmentName){
        this.equipmentName=equipmentName;
    }
    public String getEquipmentName(){
        return this.equipmentName;
    }

    public void setEquipmentId(String equipmentId){
        this.equipmentId=equipmentId;
    }
    public String getEquipmentId(){
        return this.equipmentId;
    }

    public void setExperimenter(String experimenter){
        this.experimenter=experimenter;
    }
    public String getExperimenter(){
        return this.experimenter;
    }

    public void setResult(String result){
        this.result=result;
    }
    public String getResult(){
        return this.result;
    }

    public void setResultDeal(String resultDeal){
        this.resultDeal=resultDeal;
    }
    public String getResultDeal(){
        return this.resultDeal;
    }

    public void setNote(String note){ this.note=note; }
    public String getNote(){
        return this.note;
    }
}
