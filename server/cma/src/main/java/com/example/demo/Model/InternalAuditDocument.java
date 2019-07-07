package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InternalAuditDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;    //文档编号
    private Long year; //这个文档所在的内审的年份
    private String fileName;     //文档名称
    String Dir="InternalAuditDocument";
    //int flag=0;

    public void setFileId(Long fileId){
        this.fileId=fileId;
    }
    public Long getFileId(){
        return fileId;
    }
    public void setYear(Long year){
        this.year=year;
    }
    public Long getYear(){
        return year;
    }
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    public String getFileName(){
        return fileName;
    }

    public String getDir() {
        return Dir;
    }

    /*public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }*/
}

