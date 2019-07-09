package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class ManagementReview {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private long year;  //年份
    private Date date;  //日期

    public long getYear() {
        return year;
    }

    public String getDate() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.date);
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
