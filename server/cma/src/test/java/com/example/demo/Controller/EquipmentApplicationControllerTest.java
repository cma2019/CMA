package com.example.demo.Controller;

import static org.junit.Assert.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.Model.AllAnnualPlan;
import com.example.demo.Repository.AllAnnualPlanRepository;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class EquipmentApplicationControllerTest {
    @Autowired
    EquipmentController equipmentController;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
    }

    @After
    public void after() throws Exception {
    }
    @Test
    @Transactional
    public void testEquipmentAdd() throws Exception {
//TODO: Test goes here...
        String url="/cma/Equipment/add?name=13&model=1111&cpu=1&memory=1&hardDisk=1&equipmentNumber=1&application=1&state=1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        //int status=mvcResult.getResponse().getStatus();
        //Assert.assertEquals(200,status);
        //JSONObject json=mvcResult.getResponse().get
        String res=mvcResult.getResponse().getContentAsString();
        System.out.println(res);
        int code=Integer.parseInt(res.substring(8,11));
        Assert.assertEquals(200,code);
    }
    @Test
    @Transactional
    public void testGetAll() throws Exception {
        String url="/cma/Equipment/getAll";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        //int status=mvcResult.getResponse().getStatus();
        //Assert.assertEquals(200,status);
        String res=mvcResult.getResponse().getContentAsString();
        System.out.println(res);
        System.out.println(res.substring(8,11));
        int code=Integer.parseInt(res.substring(8,11));
        Assert.assertEquals(200,code);
    }
    @Test
    @Transactional
    public void deleteOne()throws Exception{
        String url="/cma/Equipment/deleteOne/76";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url,new Object[0])
        .param("id","76"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject= JSON.parseObject(res);
        int code= (int) jsonObject.get("code");
        Assert.assertEquals(200, (long)code);
    }
}