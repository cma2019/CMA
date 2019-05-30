package com.example.demo.StaffManagement;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
public class StaffManagement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;                 //人员唯一编号
    private String name;             //名称
    private int gender;           //性别(男0 女1)
    private String department;       //部门
    private String position;         //职位
    private String title;          	 //职称
    private String degree;           //文化程度
    private String graduationSchool; //毕业院校
    private String graduationMajor;  //毕业专业
    private Date graduationDate;   //毕业时间
    private int workingYears;     //从事工作年限

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        if(this.gender==0)
            return "男";
        else
            return "女";
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

    public String getDegree() {
        return degree;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public String getGraduationMajor() {
        return graduationMajor;
    }

    public String getGraduationDate() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.graduationDate);
    }

    public int getWorkingYears() {
        return workingYears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public void setGraduationMajor(String graduationMajor) {
        this.graduationMajor = graduationMajor;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public void setWorkingYears(int workingYears) {
        this.workingYears = workingYears;
    }

    public  boolean Eqauls(StaffManagement staffManagement){
        if(staffManagement.getName().equals(this.name)&&staffManagement.getGender().equals(this.getGender())&&staffManagement.getDepartment().equals(this.department)&&staffManagement.getPosition().equals(this.position)
        &&staffManagement.getTitle().equals(this.title)&&staffManagement.getDegree().equals(this.degree)&&staffManagement.getGraduationSchool().equals(this.graduationSchool)&&staffManagement.getGraduationMajor().equals(this.graduationMajor)
        &&staffManagement.getGraduationDate().equals(this.getGraduationDate())&&staffManagement.getWorkingYears()==this.workingYears)
            return true;
        else
            return false;

    }
}
