package com.example.demo.Controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
public class SampleReceiveControllerTest {
    @Autowired
    SampleReceiveController SampleReceiveController;
    MockMvc mockMvc;

    public SampleReceiveControllerTest() {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.SampleReceiveController}).build();
    }

    @After
    public void tearDown(){
    }

    @Transactional
    @Rollback(true)
    @Test
    public void addOne() throws Exception {
        String url = "/cma/SampleReceive/addOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("sampleNumber","2019004")
                .param("sampleName","soft004")
                .param("sampleAmount","1")
                .param("sampleState","0")
                .param("requester","nju04")
                .param("receiver","lb04")
                .param("receiveDate","2019-7-1")
                .param("obtainer","hcb04")
                .param("obtainDate","2019-7-2")
                .param("receiptId","0")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    public void deleteOne() throws Exception {
        String url = "/cma/SampleReceive/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("sampleId","122")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    public void findALL() throws Exception {
        String url = "/cma/SampleReceive/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                //.param("sampleId","0")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    public void findOne() throws Exception {
        String url = "/cma/SampleReceive/getOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("sampleId","122")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Transactional
    @Test
    public void modify() throws Exception {
        String url = "/cma/SampleReceive/modifyOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("sampleNumber","2019001")
                .param("sampleName","soft001")
                .param("sampleAmount","1")
                .param("sampleState","0")
                .param("requester","nju01")
                .param("receiver","lb01")
                .param("receiveDate","2019-7-1")
                .param("obtainer","hcb01")
                .param("obtainDate","2019-7-2")
                .param("sampleId","122")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }
}
