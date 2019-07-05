package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name="ERD_table")
public class ExternalReviewDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long year; //这个文档所在的外审的年份
    private long fileId;    //文档编号
    private String fileName;     //文档名称
    private String dir="ExternalReviewDocument";
    public void setYear(long year) {
        this.year = year;
    }

    public long getYear() {
        return year;
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
