package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SupervisionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planID;
    private Long id;
    private String content;
    private String object;
    private String dateFrequency;

    public Long getPlanID()
    {
        return planID;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public Long getId()
    {
        return id;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){return content;}
    public void setObject(String object){
        this.object=object;
    }
    public String getObject(){return  object;}
    public void setDateFrequency(String dateFrequency){
        this.dateFrequency=dateFrequency;
    }
    public String getDateFrequency(){return dateFrequency;}
}
