package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class SupervisionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recordId;
    private Long planId;
    private String department;
    private String supervisor;
    private java.sql.Date superviseDate;
    private String supervisedPerson	;
    private String record;
    private String conclusion;
    private String operator;
    private java.sql.Date recordDate;

    public Long getPlanId()
    {
        return planId;
    }
    public void setPlanId(Long planId)
    {
        this.planId=planId;
    }
    public Long getRecordId()
    {
        return recordId;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public String getDepartment(){return department;}
    public void setSupervisor(String supervisor){
        this.supervisor=supervisor;
    }
    public String getSupervisor(){return  supervisor;}
    public void setSuperviseDate(java.sql.Date superviseDate){
        this.superviseDate=superviseDate;
    }
    public Date getSuperviseDate(){
        return superviseDate;
    }
    public void setSupervisedPerson(String supervisedPerson	){
        this.supervisedPerson	=supervisedPerson	;
    }
    public String getSupervisedPerson(){return supervisedPerson	;}
    public String getRecord(){return record;}
    public void setRecord(String record){
        this.record=record;
    }
    public void setConclusion(String conclusion){
        this.conclusion=conclusion;
    }
    public String getConclusion(){return conclusion;}
    public String getOperator(){return operator;}
    public void setOperator(String operator){
        this.operator=operator;
    }
    public void setRecordDate(java.sql.Date recordDate){
        this.recordDate=recordDate;
    }
    public Date getRecordDate(){
        return recordDate;
    }
}
