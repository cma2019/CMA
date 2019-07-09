package com.example.demo.Model;

import com.sun.javafx.beans.IDProperty;
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
public class IntermediateChecksPlan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long planID;//设计划id为主键，自增
    private String object;//对象
    private String content;//内容
    private Date checkDate;//日期
    private String personInCharge;//负责人
    private long state;//状态值默认为0

    //给每个元素提供get,set方法
    public void setPlanId(long id){
        this.planID=id;
    }
    public long getPlanId(){
        return this.planID;
    }

    public void setObject(String object){
        this.object=object;
    }
    public String getObject(){
        return this.object;
    }

    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return this.content;
    }

    public void setCheckDate(Date date){
        this.checkDate=date;
    }
    public Date getCheckDate(){
        return this.checkDate;
    }

    public void setPersonInCharge(String personInCharge){
        this.personInCharge=personInCharge;
    }
    public String getPersonInCharge(){
        return this.personInCharge;
    }

    public void setState(long state){
        this.state=state;
    }
    public long getState(){
        return this.state;
    }
}
