package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;

//import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
//回滚类中各测试方法对数据库的事物操作
public class SupervisionRecordControllerTest {

    @Autowired
    SupervisionRecordController supervisionRecordController=new SupervisionRecordController();
    MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.supervisionRecordController}).build();
    }

    @After
    public void tearDown()  {
    }

    @Test
    //测试getAll()方法
    public void getAll() throws Exception{
        String url = "/cma/SupervisionRecord/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("planId","170")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    //测试addOne()方法
    public void addOne() throws Exception{
        String url = "/cma/SupervisionRecord/addOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("planId","170")
                .param("department","d1")
                .param("supervisor","s1")
                .param("superviseDate","2019-7-1")
                .param("supervisedPerson","s1")
                .param("record","r1")
                .param("conclusion","c1")
                .param("operator","o1")
                .param("recordDate","2019-7-2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    //测试modifyOne()方法
    public void modifyOne() throws Exception{
        String url = "/cma/SupervisionRecord/modifyOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("recordId","171")
                .param("department","d1")
                .param("supervisor","s1")
                .param("superviseDate","2019-7-1")
                .param("supervisedPerson","s1")
                .param("record","r1")
                .param("conclusion","c1")
                .param("operator","o1")
                .param("recordDate","2019-7-2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    //测试deleteOne()方法
    public void deleteOne() throws Exception{
        String url = "/cma/SupervisionRecord/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("recordId","171")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }
}