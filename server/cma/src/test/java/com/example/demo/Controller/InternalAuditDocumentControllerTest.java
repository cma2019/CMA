package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.framework.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

//import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class InternalAuditDocumentControllerTest {

    @Autowired
    InternalAuditDocumentController internalAuditDocumentController=new InternalAuditDocumentController();
    MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.internalAuditDocumentController}).build();
    }

    @After
    public void tearDown(){
    }


    @Test
    @Transactional
    public void deleteOne() throws Exception{
        String url = "/cma/InternalAuditManagement/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("year","2019")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void getAllFile() throws Exception{
        String url = "/cma/InternalAuditManagement/getAllFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("year","2019")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void addOneFile() throws Exception{
        File file = new File("E:/CMA/test/","2016年度01.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","2016年度01.docx","multipart/form-data",new FileInputStream(file));
        MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=this.internalAuditDocumentController.addOneFile(firstFile,"2016f01",2016,request);
        Assert.assertEquals(200L, res.code);
    }

    @Test
    @Transactional
    public void modifyOneFormData() throws Exception{
        String url = "/cma/InternalAuditManagement/modifyOneFormData";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("year","2019")
                .param("fileId","162")
                .param("fileName","2019f01")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void modifyOneFile() throws Exception{
        File file = new File("E:/CMA/test/","2016年度01.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","2016年度01.docx","multipart/form-data",new FileInputStream(file));
        MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=this.internalAuditDocumentController.modifyOneFile(firstFile,162,2019,"testF01",request);
        Assert.assertEquals(200L, res.code);
    }

    @Test
    @Transactional
    public void deleteOneFile() throws Exception{
        String url = "/cma/InternalAuditManagement/deleteOneFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("fileId","163")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void downloadFile() {
        MockHttpServletResponse response=new MockHttpServletResponse();
        String res=this.internalAuditDocumentController.downloadFile(162,response);
        Assert.assertEquals("下载成功", res);
    }
}