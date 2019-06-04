package com.example.demo.framework;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJsonTypeConvert extends JsonSerializer<Date> {
    //用于序列化字符串(例如转换为json格式)
    @Override
    public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        //自己定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //将数据以json格式输出
        arg1.writeString(sdf.format(arg0));
    }
}


