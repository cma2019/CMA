package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StaffFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recordId;
    private long id;         //web端用listview检索，对于安卓端无实际意义。
    private String name;	//名称
    private String department;	//部门
    private String position;    //职位
    private String fileId;          //档案编号
    private String fileLocation;    //档案位置
    private String fileImage;   //档案扫描件（图片在服务器的位置）

    private int flag=0;
    private String dir="StaffFile";

    public String getDir() {
        return dir;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }


    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileImage() {
        return fileImage;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public boolean Equals(StaffFile staffFile){
        if(this.id==staffFile.getId()&&this.fileId.equals(staffFile.getFileId())&&this.fileImage.equals(staffFile.getFileImage())&&this.fileLocation.equals(staffFile.getFileLocation()))
            return true;
        else
            return false;
    }
}
