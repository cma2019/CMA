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
    private long planID;
    private String object;
    private String content;
    private Date checkDate;
    private String personInCharge;
    private long state;//状态值默认为0

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
