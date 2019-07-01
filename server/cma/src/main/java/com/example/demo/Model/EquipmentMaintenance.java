package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "EQUIPMENT_MAINTENANCE")
public class EquipmentMaintenance {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Long equipmentId;            //机身编号（外键）
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date maintenanceDate;          //记录日期
    private String maintenanceContent;       //维护保养内容
    private String maintenancePerson;      //维护保养人
    private String confirmer;  			  //确认人

    public EquipmentMaintenance() {
    }

    public long getId(){
        return this.id;
    }

    public String getMaintenanceDate() {
        return dateToStr(this.maintenanceDate);
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public String getMaintenanceContent() {
        return maintenanceContent;
    }

    public String getConfirmer() {
        return confirmer;
    }

    public String getMaintenancePerson() {
        return maintenancePerson;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMaintenanceContent(String maintenanceContent) {
        this.maintenanceContent = maintenanceContent;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public void setMaintenancePerson(String maintenancePerson) {
        this.maintenancePerson = maintenancePerson;
    }
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
