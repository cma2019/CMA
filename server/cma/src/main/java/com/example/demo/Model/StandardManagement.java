package com.example.demo.Model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicInsert
@DynamicUpdate
public class StandardManagement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long fileId;//标准文档Id,主键
    private String filename;//文件名（带后缀）
    String dir="Standard";//存储时的路径

    //get,set方法
    public long getFileId(){return this.fileId;}
    public void setFileId(long fileId){this.fileId=fileId;}

    public String getFileName(){return this.filename;}
    public void setFileName(String fileName){this.filename=fileName;}

    //获得存储路径
    public String getDir(){return dir;}

}
