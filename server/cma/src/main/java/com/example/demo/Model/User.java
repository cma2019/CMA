package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private boolean []permission=new boolean[20];
    private String code;

    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return this.id;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public boolean login(String password){
        if(this.password.equals(password))
            return true;
        else
            return false;
    }

    public void setPermission(boolean[] permission) {
        this.permission = permission;
    }

    /*public boolean[] getPermission() {
        return permission;
    }*/
    public ArrayList<String> getPermission(){
        String[] permissions=new String[]{"用户管理","质量体系管理","管理评审","内审管理","自查管理","监督","期间核查","档案管理","培训管理","样品管理","设备管理","人员管理","授权签字人管理"
                ,"检测检验能力管理","能力验证管理","客户意见与投诉管理","标准管理","外部评审与上报管理","检测机构管理","系统管理"};
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            if(permission[i]==true){
                list.add(permissions[i]);
            }
        }
        return list;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
