package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class InternalAuditManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long year;     //内审的年份
    private Date date;   //内审管理的日期

    public Long getId() {
        return id;
    }
    public void setYear(Long year){
        this.year=year;
    }
    public Long getYear(){
        return year;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public Date getDate(){
        return date;
    }
}
