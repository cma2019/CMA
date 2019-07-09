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
public class IntermediateChecksRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long recordId;      //期间核查记录的唯一编号
    private long planId;      //对应期间核查计划的编号
    private String object;    //核查对象
    private Date checkDate;   //核查时间
    private String processRecord; //核查过程记录
    private String processRecordPerson; //核查过程记录人
    private Date processRecordDate; //核查过程记录时间
    private String resultRecord;  //核查结论记录
    private String resultRecordPerson; //核查结论记录人
    private Date resultRecordDate; //核查结论记录时间
    private String note;    //备注

    //提供get,set方法
    public void setRecordId(long recordId){this.recordId=recordId;}
    public long getRecordId(){return this.recordId;}

    public void setPlanId(long planId){
        this.planId=planId;
    }
    public long getPlanId(){
        return this.planId;
    }

    public void setObject(String object){
        this.object=object;
    }
    public String getObject(){
        return this.object;
    }

    public void setCheckDate(Date date){
        this.checkDate=date;
    }
    public Date getCheckDate(){
        return this.checkDate;
    }

    public void setProcessRecord(String processRecord){
        this.processRecord=processRecord;
    }
    public String getProcessRecord(){
        return this.processRecord;
    }

    public void setProcessRecordPerson(String processRecordPerson){
        this.processRecordPerson=processRecordPerson;
    }
    public String getProcessRecordPerson(){
        return this.processRecordPerson;
    }

    public void setProcessRecordDate(Date processRecordDate){
        this.processRecordDate=processRecordDate;
    }
    public Date getProcessRecordDate(){
        return this.processRecordDate;
    }

    public void setResultRecord(String resultRecord){
        this.resultRecord=resultRecord;
    }
    public String getResultRecord(){
        return this.resultRecord;
    }

    public void setResultRecordPerson(String resultRecordPerson){
        this.resultRecordPerson=resultRecordPerson;
    }
    public String getResultRecordPerson(){
        return this.resultRecordPerson;
    }

    public void setResultRecordDate(Date resultRecordDate){
        this.resultRecordDate=resultRecordDate;
    }
    public Date getResultRecordDate(){
        return this.resultRecordDate;
    }

    public void setNote(String note){
        this.note=note;
    }
    public String getNote(){
        return this.note;
    }
}
