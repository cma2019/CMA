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
public class CapacityVerificationProject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long projectId;     //能力验证项目序号
    private long planId;//能力验证项目所对应的计划编号
    private String name;//能力验证项目的名称
    private String method;//能力验证项目的试验方法
    private long state;   //0即未完成，1即完成变成了记录
    private String note;//该项目的备注

    //set,get方法
    public void setprojectId(long id){
        this.projectId=id;
    }
    public long getprojectId(){ return this.projectId; }

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

    public void setMethod(String method){
        this.method=method;
    }
    public String getMethod(){
        return this.method;
    }

    public void setState(long state){ this.state=state; }
    public long getState(){
        return this.state;
    }

    public void setNote(String note){
        this.note=note;
    }
    public String getNote(){
        return this.note;
    }

}
