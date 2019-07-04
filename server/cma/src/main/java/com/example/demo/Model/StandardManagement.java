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
    private long fileId;
    private String filename;
    String dir="Standard";

    public long getFileId(){return this.fileId;}
    public void setFileId(long fileId){this.fileId=fileId;}

    public String getFileName(){return this.filename;}
    public void setFileName(String fileName){this.filename=fileName;}

    public String getDir(){return dir;}

}
