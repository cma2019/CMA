package com.example.demo.Model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class AnnualTrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  planId;        //培训项目ID
    private long  year;          //年份
    private String trainProject; //培训项目
    private String people;       //培训对象
    private String method;       //培训方式
    private long trainingTime;   //培训课时
    private Date stratTime;      //培训开始时间
    private Date endTime;        //培训结束时间
    private String note;         //备注

    public long getPlanId() {
        return planId;
    }

    public long getYear() {
        return year;
    }

    public String getTrainProject() {
        return trainProject;
    }

    public String getPeople() {
        return people;
    }

    public String getMethod() {
        return method;
    }

    public long getTrainingTime() {
        return trainingTime;
    }

    public String getStratTime() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.stratTime);
    }

    public String getEndTime() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.endTime);
    }

    public String getNote() {
        return note;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTrainingTime(long trainingTime) {
        this.trainingTime = trainingTime;
    }

    public void setStratTime(Date stratTime) {
        this.stratTime = stratTime;
    }

    public void setTrainProject(String trainProject) {
        this.trainProject = trainProject;
    }

    public boolean Equals(AnnualTrainingPlan annualTrainingPlan){
        if(annualTrainingPlan.getYear()==this.year&&annualTrainingPlan.getPeople().equals(this.people)&&annualTrainingPlan.getTrainProject().equals(this.trainProject)&&
        annualTrainingPlan.getMethod().equals(this.method)&&annualTrainingPlan.getTrainingTime()==this.trainingTime&&annualTrainingPlan.getStratTime().equals(this.getStratTime())
        &&annualTrainingPlan.getEndTime().equals(this.getEndTime())){
            return true;
        }
        else
            return false;
    }
}
