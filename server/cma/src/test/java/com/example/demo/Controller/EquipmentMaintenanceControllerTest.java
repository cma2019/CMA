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
public class EquipmentMaintenanceControllerTest {
    @Autowired
    EquipmentMaintenanceController equipmentMaintenanceController;
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
    public void add() throws Exception{
            String url="/cma/EquipmentMaintenance/addOne";
            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                    .param("equipmentId","12345678")
                    .param("maintenanceDate","2019-6-29")
                    .param("maintenanceContent","较差")
                    .param("maintenancePerson","唐三")
                    .param("confirmer","李四")
            )       .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print()).andReturn();

            String res = mvcResult.getResponse().getContentAsString();
            int code = Integer.parseInt(res.substring(8,11));
            Assert.assertEquals(200, (long)code);
        }
   public void getOne() throws Exception{
       String url="/cma/EquipmentMaintenance/getOne/66";
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
               .param("id","66"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print()).andReturn();
       String res = mvcResult.getResponse().getContentAsString();
       int code = Integer.parseInt(res.substring(8,11));
       Assert.assertEquals(200, (long)code);
   }
    public void getAll() throws Exception{
        String url="/cma/EquipmentMaintenance/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8,11));
        Assert.assertEquals(200, (long)code);
    }
    public void modifyOne() throws Exception{
        String url="/cma/EquipmentMaintenance/modifyOne/66";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("id,","66")
                .param("equipmentId","12345678")
                .param("maintenanceDate","2019-6-29")
                .param("maintenanceContent","较差")
                .param("maintenancePerson","唐三")
                .param("confirmer","李四")
        )       .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(8,11));
        Assert.assertEquals(200, (long)code);
    }

}