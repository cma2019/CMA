package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ManagementFile {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long fileId;
    private long year;
    private String fileName;
    private String file;

    private String dir="managementFile";

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
