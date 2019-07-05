package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ERM_table")
public class ExternalReviewManagement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    private long year;     //外审的年份
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;   //外审管理的日期

    public String getDate() {
        return dateToStr(date);
    }

    public long getYear() {
        return year;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
