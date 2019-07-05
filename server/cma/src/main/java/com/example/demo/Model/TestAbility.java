package com.example.demo.Model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@DynamicInsert
@DynamicUpdate
public class TestAbility {
    @Id
    private long year;
    private String fileName;
    String dir="TestAbility";

    public void setYear(long year) { this.year = year; }
    public long getYear(){return this.year;}

    public void setFileName(String fileName){this.fileName=fileName;}
    public String getFileName(){return this.fileName;}

    public String getDir(){return this.dir;}
}
