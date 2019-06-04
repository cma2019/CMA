package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "EQUIPMENT_USE")
public class EquipmentUse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Long equipmentId;      //机身编号（外键）
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date useDate;          //使用日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date openDate;         //开机时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date closeDate;        //关机时间
    private String sampleNumber;     //样品编号
    private String testProject;      //测试项目
    private String beforeUse;        //仪器使用前状况
    private String afterUse;        //仪器使用后状况
    private String user;            //使用人
    private String remark;          //备注

    public void setId(long id){this.id=id;}
    public long getId() { return id; }

    public void setEquipmentId(Long equipmentId){this.equipmentId=equipmentId;}
    public Long getEquipmentId() { return this.equipmentId; }

    public void setUseDate(Date useDate){this.useDate=useDate;}
    public String getUseDate() {return dateToStr(this.useDate); }

    public void setCloseDate(Date closeDate) { this.closeDate = closeDate; }
    public String getCloseDate() { return dateToStr(this.closeDate); }

    public void setAfterUse(String afterUse) { this.afterUse = afterUse; }
    public String getAfterUse() { return afterUse; }

    public void setOpenDate(Date openDate) { this.openDate = openDate; }
    public String getOpenDate() { return dateToStr(this.openDate); }

    public void setBeforeUse(String beforeUse) { this.beforeUse = beforeUse; }
    public String getBeforeUse() { return beforeUse; }

    public void setSampleNumber(String sampleNumber) { this.sampleNumber = sampleNumber; }
    public String getSampleNumber() { return sampleNumber; }

    public void setRemark(String remark) { this.remark = remark; }
    public String getRemark() { return remark; }

    public void setTestProject(String testProject) { this.testProject = testProject; }
    public String getTestProject() { return testProject; }

    public void setUser(String user) {this.user = user; }
    public String getUser() { return user; }
    public static String dateToStr(java.util.Date dateDate) {
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String dateString = formatter.format(dateDate);
       return dateString;
      }
}
