package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ManagementFile {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long fileId; //文件编号
    private long year;  //年份
    private String fileName; //文件名
    private String file;  //文件名及格式
    //private int flag=0;

    private String dir="managementFile"; //保存路径

    public String getDir() {
        return dir;
    }


    public void setYear(long year) {
        this.year = year;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getYear() {
        return year;
    }

    public long getFileId() {
        return fileId;
    }

    public String getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }
}
