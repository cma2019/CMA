package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class SampleReceiptControllerTest
{
    @Autowired
    SampleReceiptController sampleReceiptController;
    MockMvc mockMvc;

    public SampleReceiptControllerTest() {
    }

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.sampleReceiptController}).build();
    }

    @After
    public void tearDown(){
    }

    @Transactional
    @Test
    //测试addOne()接口
    public void addOne() throws Exception {
        String url = "/cma/SampleReceipt/addOne";
        //请求参数的格式为json，所以先构造参数例子
        JSONObject input=new JSONObject();
        JSONArray data=new JSONArray();
        for(int i=5;i<10;i++)
        {
            JSONObject tmp=new JSONObject();
            tmp.put("materialId",i);
            tmp.put("materialType",i/3);
            if(i==9)
                tmp.put("materialName","《附加材料》");
            data.add(tmp);
        }
        input.put("sampleId","2");
        input.put("applicationUnit","nju02");
        input.put("version","2.0.1");
        input.put("contractId","2019002");
        input.put("testType",1);
        input.put("electronicMedia","cd01");
        input.put("materialList",data);
        input.put("softwareType",1);
        input.put("receiveUnit","nju_test01");
        input.put("receiveDate","2019-7-6");
        input.put("sender","hcb01");
        input.put("receiver","lb01");
        String requestJson=JSONObject.toJSONString(input);
        //json格式的请求参数传入方式需要设定contenType和content
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0]).contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        System.out.println(requestJson);
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    //测试deleteOne接口
    public void deleteOne() throws Exception {
        String url = "/cma/SampleReceipt/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("sampleId","1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    //测试getAll（）接口
    public void findALL() throws Exception {
        String url = "/cma/SampleReceipt/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                //.param("sampleId","")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    //测试getOne()接口
    public void findOne() throws Exception {
        String url = "/cma/SampleReceipt/getOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("sampleId","1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    //测试modifyOne()接口
    public void modify() throws Exception {
        String url = "/cma/SampleReceipt/modifyOne";
        JSONObject input=new JSONObject();
        JSONArray data=new JSONArray();
        for(int i=5;i<10;i++)
        {
            JSONObject tmp=new JSONObject();
            tmp.put("materialId",i);
            tmp.put("materialType",i/3);
            if(i==9)
                tmp.put("materialName","《附加材料》");
            data.add(tmp);
        }
        input.put("sampleId","1");
        input.put("applicationUnit","nju02");
        input.put("version","2.0.1");
        input.put("contractId","2019002");
        input.put("testType",1);
        input.put("electronicMedia","cd01");
        input.put("materialList",data);
        input.put("softwareType",1);
        input.put("receiveUnit","nju_test01");
        input.put("receiveDate","2019-7-6");
        input.put("sender","hcb01");
        input.put("receiver","lb01");
        String requestJson=JSONObject.toJSONString(input);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0]).contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }
}
