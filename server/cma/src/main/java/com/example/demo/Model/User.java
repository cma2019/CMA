package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Array;

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
    public String getPassword(){
        return this.password;
    }

    public void setPermission(boolean[] permission) {
        this.permission = permission;
    }

    public boolean[] getPermission() {
        return permission;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
