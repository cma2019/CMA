package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class StaffAuthorization {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long authorizationId;    //授权记录id
    private String content;          //授权内容

    private long authorizerId;     //授权人id     //授权人也应该从现有人员里选择
    private String authorizerName;   //授权人名称
    private Date authorizerDate;     //授权时间

    private long id;                  //被授权人id
    private String name;             //被授权人名称
    private String department;       //被授权人部门
    private String position;         //被授权人职位

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorizerDate(Date authorizerDate) {
        this.authorizerDate = authorizerDate;
    }

    public void setAuthorizerId(long authorizerId) {
        this.authorizerId = authorizerId;
    }

    public void setAuthorizerName(String authorizerName) {
        this.authorizerName = authorizerName;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPosition() {
        return position;
    }

    public String getAuthorizerDate() {
        DateFormat df= DateFormat.getDateInstance();
        return df.format(this.authorizerDate);
    }

    public long getAuthorizationId() {
        return authorizationId;
    }

    public long getAuthorizerId() {
        return authorizerId;
    }

    public String getAuthorizerName() {
        return authorizerName;
    }
}
