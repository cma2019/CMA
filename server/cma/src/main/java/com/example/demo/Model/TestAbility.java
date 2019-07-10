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
    private long year;//年份作为唯一主键
    private String fileName;//文件名
    String dir="TestAbility";//路径

    //get,set方法
    public void setYear(long year) { this.year = year; }
    public long getYear(){return this.year;}

    public void setFileName(String fileName){this.fileName=fileName;}
    public String getFileName(){return this.fileName;}

    //返回保存路径
    public String getDir(){return this.dir;}
}
