package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name="ERD_table")
public class ExternalReviewDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private long year; //这个文档所在的外审的年份
    private long fileId;    //文档编号
    private String fileName;     //文档名称
    private String dir="ExternalReviewDocument";
    int flag=0;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getYear() {
        return year;
    }

    public long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileId() {
        return fileId;
    }

    public String getDir() {
        return dir;
    }
}
