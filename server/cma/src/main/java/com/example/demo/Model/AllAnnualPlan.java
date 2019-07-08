package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class AllAnnualPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long year;           //年度计划年份
    private String author;       //编写人
    private Date createDate;     //编写日期
    private String approver;     //批准人
    private Date approveDate;    //批准日期

    public long getYear() {
        return year;
    }

    public String getApproveDate() {
        if(this.approveDate==null)
            return null;
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.approveDate);
    }

    public String getApprover() {
        return approver;
    }

    public String getCreateDate() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.createDate);
    }

    public String getAuthor() {
        return author;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean Equals(AllAnnualPlan allAnnualPlan){
        if(this.year==allAnnualPlan.getYear()&&this.author.equals(allAnnualPlan.getAuthor())&&this.getCreateDate().equals(allAnnualPlan.getCreateDate()))
            return true;
        else
            return false;
    }
}
