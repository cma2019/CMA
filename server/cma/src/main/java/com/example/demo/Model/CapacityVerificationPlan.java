package com.example.demo.Model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicInsert
@DynamicUpdate
public class CapacityVerificationPlan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long planId;     //能力验证计划序号
    private String name;//能力验证计划名称
    private String organizer;//能力验证项目的组织方
    private long state;   //0即未完成，1的话是他设计的所有项目均执行成记录才成为记录变成1
    private String year;  //参加年度
    private String note;//计划的备注
    private String analysis;

    public void setplanId(long id){
        this.planId=id;
    }
    public long getplanId(){
        return this.planId;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    public void setOrganizer(String organizer){
        this.organizer=organizer;
    }
    public String getOrganizer(){
        return this.organizer;
    }

    public void setState(long state){
        this.state=state;
    }
    public long getState(){
        return this.state;
    }

    public void setYear(String year){
        this.year=year;
    }
    public String getYear(){
        return this.year;
    }

    public void setNote(String note){
        this.note=note;
    }
    public String getNote(){
        return this.note;
    }

    public void setAnalysis(String analysis){this.analysis=analysis;}
    public String getAnalysis(){return this.analysis;}
}
