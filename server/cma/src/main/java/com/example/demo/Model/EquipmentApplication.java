package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
@Table(name = "EQUIPMENT_APPLICATION")
public class EquipmentApplication {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String applicant;        	 //申请人
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applicationDate;  	 //申请日期
    private String applicationPurpose;   //申请用途
    private Byte equipmentUse;    //服务器/测试机 (0 全不选/1 服务器/2 测试机/3 全选)
    private String equipmentNumber;   //设备编号，从现有设备里选。
    private String softwareInfo;         //所申请的软件的信息
    private String auditor;              //审核人
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date auditDate;            //审核时间
    private String auditOpinion;          //审核意见

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getEquipmentUse() {
        return equipmentUse;
    }

    public String getApplicationDate() {
        return dateToStr(applicationDate);
    }

    public String getAuditDate() {
        return dateToStr(auditDate);
    }

    public Long getId() {
        return id;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getApplicationPurpose() {
        return applicationPurpose;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public String getSoftwareInfo() {
        return softwareInfo;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setApplicationPurpose(String applicationPurpose) {
        this.applicationPurpose = applicationPurpose;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public void setEquipmentUse(Byte equipmentUse) {
        this.equipmentUse = equipmentUse;
    }

    public void setSoftwareInfo(String softwareInfo) {
        this.softwareInfo = softwareInfo;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
