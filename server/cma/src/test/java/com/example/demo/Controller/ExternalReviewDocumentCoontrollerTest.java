package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.framework.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ExternalReviewDocumentCoontrollerTest {
    @Autowired
    ExternalReviewDocumentCoontroller capacityVerificationPlanController;
    MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(capacityVerificationPlanController).build();
    }

    @After
    public void after() throws Exception {
    }

    @Transactional
    @Test
    public void getAll()throws Exception{
        String url="/cma/ExternalReviewDocument/getAllFile/2021";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url,new Object[0])
                .param("year","2021")
        )       .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject= JSON.parseObject(res);
        int code= (int) jsonObject.get("code");
        Assert.assertEquals(200, (long)code);
    }
    @Test
    public void addOneFile() throws Exception{
        File file = new File("E:/CMA/Test/","143.pdf");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/Test/","143.pdf","multipart/form-data",new FileInputStream(file));
        MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=this.capacityVerificationPlanController.addFile(firstFile,request,2018);
        Assert.assertEquals(200L, res.code);
    }
}