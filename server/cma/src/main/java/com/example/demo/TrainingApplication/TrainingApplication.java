package com.example.demo.TrainingApplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class TrainingApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long    id;            //培训项目唯一id
    private  String  name;          //培训项目名
    private  String  people;        //培训对象
    private  String  trainingUnit;  //培训单位
    private  long    expense;       //申请培训经费
    private  String  reason;        //申请培训理由
    private  byte    situation;     //申请状态（0未审批，1未通过，2已通过）
    private  String  department;    //申请部门
    private  Date createDate;    //申请日期
    private  String  approver;      //审核人
    private  Date    approveDate;   //审核日期

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPeople() {
        return people;
    }

    public String getTrainingUnit() {
        return trainingUnit;
    }

    public long getExpense() {
        return expense;
    }

    public String getReason() {
        return reason;
    }

    public Byte getSituation() {
        /*if(this.situation==0)
            return "未审查";
        else if(this.situation==1)
            return "未通过";
        else
            return "通过";*/
        return this.situation;
    }

    public String getDepartment() {
        return department;
    }

    public String getCreateDate() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.createDate);
    }

    public String getApprover() {
        return approver;
    }

    public String getApproveDate() {
        DateFormat df=DateFormat.getDateInstance();
        return df.format(this.approveDate);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setTrainingUnit(String trainingUnit) {
        this.trainingUnit = trainingUnit;
    }

    public void setExpense(long expense) {
        this.expense = expense;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setSituation(byte situation) throws ParseException {
        if(situation==0){
            this.setApprover("");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            this.setApproveDate(sdf.parse("2000-1-1"));
        }
        this.situation = situation;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }
    public boolean Equals(TrainingApplication t){
        if(t.getName().equals(this.name)&&t.getPeople().equals(this.people)&&t.getExpense()==this.expense&&t.getTrainingUnit().equals(this.trainingUnit)&&t.getCreateDate().equals(this.getCreateDate())
        &&t.getReason().equals(this.reason)&&t.getDepartment().equals(this.department)){
            return true;
        }
        else
            return false;
    }

}
