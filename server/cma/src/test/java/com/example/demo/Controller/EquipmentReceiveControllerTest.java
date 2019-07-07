package com.example.demo.Controller;

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
public class EquipmentReceiveControllerTest {

    @Autowired
    EquipmentReceiveController equipmentReceiveController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentReceiveController).build();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    @Transactional
    public void testadd() throws Exception{
        String url="/cma/EquipmentReceive/add";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("name","戴尔")
                .param("model","soft003")
                .param("manufacturer","戴尔")
                .param("receiveSituation","良好")
                .param("receiveDate","2019-6-29")
                .param("acceptance","较差")
                .param("acceptanceDate","2019-7-1")
                .param("equipmentSituation","良好")
                .param("acceptancePerson","唐三")
                .param("recipient","none03")
        )       .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8,11));
        Assert.assertEquals(200, (long)code);
    }
    @Test
    @Transactional
    public void deleteOne() throws Exception{
        String url = "/cma/EquipmentReceive/deleteOne/44";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("id", "44")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8, 11));
        Assert.assertEquals(200, (long) code);
    }
    @Test
    @Transactional
    public void getAll() throws Exception{
        String url = "/cma/EquipmentReceive/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8, 11));
        Assert.assertEquals(200, (long) code);
    }
    @Test
    @Transactional
    public void modify() throws Exception {
        String url = "/cma/EquipmentReceive/modifyOne/44";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("id", "44")
                .param("name", "戴尔")
                .param("model", "soft003")
                .param("manufacturer", "戴尔")
                .param("receiveSituation", "良好")
                .param("receiveDate", "2019-6-29")
                .param("acceptance", "较差")
                .param("acceptanceDate", "2019-7-1")
                .param("equipmentSituation", "良好")
                .param("acceptancePerson", "唐三")
                .param("recipient", "none03")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8, 11));
        Assert.assertEquals(200, (long) code);
    }
}

