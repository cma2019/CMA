package com.example.demo.Model;

import java.util.Date;

public class InternalAuditManagement {
    private Long year;     //内审的年份
    private Date date;   //内审管理的日期

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
