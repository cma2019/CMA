package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class TestingInstitutionInformationControllerTest {
    @Autowired
    TestingInstitutionInformationController equipmentMaintenanceController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentMaintenanceController).build();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    @Transactional
    public void add() throws Exception {
        String url = "/cma/TestingInstitutionInformation/get";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url)
        )       .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8,11));
        Assert.assertEquals(200, (long)code);
    }
}