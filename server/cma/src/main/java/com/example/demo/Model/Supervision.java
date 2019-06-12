package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Supervision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supervisionId;
    private int situation;
    private String author;
    private Date createDate;
    private String approver;
    private Date approveDate;
    private String remark;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setSupervisionId(Long supervisionId) {
        supervisionId = supervisionId;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSupervisionId() {
        return supervisionId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getSituation() {
        return situation;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getApprover() {
        return approver;
    }

    public String getRemark() {
        return remark;
    }
}
