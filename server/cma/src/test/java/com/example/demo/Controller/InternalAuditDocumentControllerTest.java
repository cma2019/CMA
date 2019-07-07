package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
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

    /*
    @Test
    public void deleteOne() {
    }

    @Test
    public void getAllFile() {
    }

    @Test
    public void addOneFormData() {

    }
    */

    @Test
    public void addOneFile() throws Exception{
        MockMultipartFile firstFile = null;
        File file = new File("E:/CMA/test/","2016年度01.docx");
        firstFile = new MockMultipartFile("testFile01", new FileInputStream(file));
        MockHttpServletRequestBuilder request = null;
        String url = "/cma/InternalAuditManagement/addOneFile";
        request= MockMvcRequestBuilders.fileUpload(url, new Object[0])
                .file(firstFile)
                .param("fileName","2016文档01")
                .param("year","2016");
        MvcResult result = this.mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = result.getResponse().getContentAsString();
        JSONObject js= JSONObject.parseObject(res);
        Assert.assertEquals(200L, js.getString("code"));
    }
    /*
    @Test
    public void modifyOneFormData() {
    }

    @Test
    public void modifyOneFile() {
    }

    @Test
    public void deleteOneFile() {
    }

    @Test
    public void downloadFile() {
    }*/
}