package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StaffQualification {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long qualificationId;    //资质证明id
    private String qualificationName;     //资质名称
    private String qualificationImage;    //资质证书扫描件
    private long id;                 //人员id
    private String name;             //人员名称
    private String department;       //人员部门
    private String position;         //人员职位

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQualificationImage(String qualificationImage) {
        this.qualificationImage = qualificationImage;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
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

    public long getId() {
        return id;
    }

    public String getQualificationImage() {
        return qualificationImage;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public long getQualificationId() {
        return qualificationId;
    }
}
