package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "Certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long fileId;     //材料编号
    private String fileName;         //材料名称
    String dir="Certificate";

    public Long getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDir() {
        return dir;
    }
}
