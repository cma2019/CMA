package com.example.demo.Model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicInsert
@DynamicUpdate
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long year;
    private String productionName;
    private String ability;
    private String referrence;

    public void setId(long id) { this.id = id; }
    public long getId() { return this.id; }

    public void setYear(long year) { this.year=year; }
    public long getYear() { return this.year; }

    public void setProductionName(String productionName) { this.productionName = productionName; }
    public String getProductionName(){return this.productionName;}

    public void setAbility(String ability) { this.ability = ability; }
    public String getAbility() { return this.ability; }

    public void setReferrence(String referrence) { this.referrence = referrence; }
    public String getReferrence() { return this.referrence; }
}
