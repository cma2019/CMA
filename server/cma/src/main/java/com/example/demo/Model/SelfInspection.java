package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class SelfInspection {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;     //自查编号
    private Date date;   //自查日期
    private String name; //自查名称

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public Date getDate(){
        return date;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
