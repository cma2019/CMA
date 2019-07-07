package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "qsmtable")
public class qsm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;//系统编号
    Long fileId;//文件编号
    String fileName;//文件名
    Byte state;//状态：0（未批准）/1（不允许）/2（批准通过）
    Byte current;//区分当前版本1与历史版本0
    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date modifyTime;//最后修改时间
    String modifier;//修改人
    String modifyContent;//修改内容
    @Transient

    String dir="qsm";

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getId() {
        return id;
    }

    @Transient
    public String getDir() {
        return dir;
    }

    public Long getFileId() {
        return fileId;
    }

    public Byte getCurrent() {
        return current;
    }

    public Byte getState() {
        return state;
    }

    public String getModifier() {
        return modifier;
    }

    public String getModifyTime() {
        return dateToStr(modifyTime);
    }

    public String getModifyContent() {
        return modifyContent;
    }

    public void setCurrent(Byte current) {
        this.current = current;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent;
    }


    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
