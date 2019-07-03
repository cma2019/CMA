package com.example.demo.Model;

public class SelfInspectionDocument {
    private Long  id;       //自查编号
    private Long fileId;    //文档编号
    private String fileName;     //文档名称

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
}
