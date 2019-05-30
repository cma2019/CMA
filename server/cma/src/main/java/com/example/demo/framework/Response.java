package com.example.demo.framework;

import com.alibaba.fastjson.JSON;

public class Response {
    public int code;
    public String msg;
    public JSON data;


    public Response()
    {
     //   this.code = null;
        this.data = null;
        this.msg = null;
    }

    public Response(int code, JSON data, String message)
    {
        this.code= code;
        this.data = data;
        this.msg = message;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
