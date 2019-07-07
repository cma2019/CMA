package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SelfInspectionDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long fileId;    //文档编号
    private Long  id;       //自查编号
    private String fileName;     //文档名称
    String dir="SelfInspectionDocument";
    //private int flag=0;

    public String getDir(){
        return dir;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    public Long getFileId() {
        return fileId;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    /*
    public int getFlag(){return flag;}
    public void setFlag(int flag){
        this.flag=flag;
    }*/
}
