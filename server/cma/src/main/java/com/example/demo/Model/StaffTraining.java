package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class StaffTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recordId;
    private long trainingId;     //培训记录id
    private String program;      //培训名称
    private Date trainingDate;   //培训日期
    private String place;        //培训地点
    private String presenter;    //主讲人
    private String content;      //培训内容
    private String note;         //备注
    private String result;       //结果

    private long id;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public String getTrainingDate() {
        DateFormat df= DateFormat.getDateInstance();
        return df.format(this.trainingDate);
    }

    public String getPlace() {
        return place;
    }

    public String getContent() {
        return content;
    }

    public String getPresenter() {
        return presenter;
    }

    public String getNote() {
        return note;
    }

    public String getProgram() {
        return program;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean Equals(StaffTraining staffTraining){
        if(staffTraining.getContent().equals(this.content)&&staffTraining.getNote().equals(this.note)&&staffTraining.getPlace().equals(this.place)&&staffTraining.getPresenter()
        .equals(this.presenter)&&staffTraining.getProgram().equals(this.program)&&staffTraining.getTrainingDate().equals(this.getTrainingDate()))
            return true;
        else
            return false;
    }
}
