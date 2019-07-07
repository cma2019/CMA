package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.framework.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
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
        File file = new File("E:/CMA/test/","2016年度01.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","2016年度01.docx","multipart/form-data",new FileInputStream(file));
        MockHttpServletRequest request=new MockHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=internalAuditDocumentController.addOneFile(firstFile,"testFile01",2016,request);
        Assert.assertEquals(200L, res.code);
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