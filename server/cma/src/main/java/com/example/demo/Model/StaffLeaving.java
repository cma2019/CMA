package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;
@Entity
public class StaffLeaving {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private long Staffid;               //人员唯一编号
    private String name;          //姓名
    private String department;    //所在部门
    private String position;      //岗位
    private Date leavingDate;    //离任日期

    public void setStaffid(long staffid) {
        Staffid = staffid;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public long getStaffid() {
        return Staffid;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getLeavingDate() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.leavingDate);
    }
}
