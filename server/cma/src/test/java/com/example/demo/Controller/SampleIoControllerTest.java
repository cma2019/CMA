package com.example.demo.Controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class SampleIoControllerTest {
    @Autowired
    SampleIoController sampleIoController;
    MockMvc mockMvc;

    public SampleIoControllerTest() {
    }

    @Before
    public void setUp()  {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.sampleIoController}).build();
    }

    @After
    public void tearDown()  {
    }

    @Test
    @Transactional
    public void addOne() throws Exception {
        String url = "/cma/SampleIo/addOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("sampleNumber","2019003")
                .param("sampleName","soft003")
                .param("sampleAmount","1")
                .param("sampleState","0")
                .param("sender","nju03")
                .param("receiver","lb03")
                .param("sendDate","2019-7-1")
                .param("obtainer","hcb03")
                .param("obtainDate","2019-7-2")
                .param("receiptId","0")
                .param("note","none03")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Test
    @Transactional
    public void deleteOne() throws Exception {
        String url = "/cma/SampleIo/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        .param("sampleIoId","126"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Test
    @Transactional
    public void findALL() throws Exception {
        String url = "/cma/SampleIo/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Test
    @Transactional
    public void findOne() throws Exception {
        String url = "/cma/SampleIo/getOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("sampleIoId","126"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }

    @Test
    @Transactional
    public void modify() throws Exception {
        String url = "/cma/SampleIo/modifyOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("sampleIoId","126")
                .param("sampleNumber","2019003")
                .param("sampleName","soft003")
                .param("sampleAmount","1")
                .param("sampleState","0")
                .param("sender","nju00")
                .param("receiver","lb00")
                .param("sendDate","2019-7-1")
                .param("obtainer","hcb00")
                .param("obtainDate","2019-7-2")
                .param("note","none00")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
    }
}
