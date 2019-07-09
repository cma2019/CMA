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
    private long id;//唯一主键，编号
    private long year;//年份
    private String productionName;//产品名称
    private String ability;//产品能力
    private String referrence;//产品依据

    //get,set方法
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
