package com.example.demo.Controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
    InternalAuditDocumentController InternalAuditDocumentController;
    MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.InternalAuditDocumentController}).build();
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
        File file = new File("E:/cma/test/2016年度.docx");
        firstFile = new MockMultipartFile("testFile01", new FileInputStream(file));
        String url = "/cma/InternalAuditManagement/addOneFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.fileUpload(url, new Object[0])
                .file(firstFile)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(19, 22));
        Assert.assertEquals(200L, (long)code);
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