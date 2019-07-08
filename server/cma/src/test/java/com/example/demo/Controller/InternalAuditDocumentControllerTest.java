package com.example.demo.Controller;

//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.framework.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InternalAuditDocumentControllerTest {

    @Autowired
    InternalAuditDocumentController internalAuditDocumentController=new InternalAuditDocumentController();
    MockMvc mockMvc;
    static String id="none";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.internalAuditDocumentController}).build();
    }

    @After
    public void tearDown(){
    }


    @Test
    public void t_1_addOneFile() throws Exception{
        File file = new File("E:/CMA/test/","1997年度01.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","1997年度01.docx","multipart/form-data",new FileInputStream(file));
        MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
        /*String url = "/cma/InternalAuditManagement/addOneFile";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        JSONObject res=this.internalAuditDocumentController.addOneFile(firstFile,"1997a01",1997,request);
        id=res.getString("id");
        Assert.assertEquals("200", res.getString("code"));
    }

    @Test
    @Transactional
    public void t_2_getAllFile() throws Exception{
        String url = "/cma/InternalAuditManagement/getAllFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("year","1997")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    public void t_3_modifyOneFormData() throws Exception{
        System.out.println(this.id);
        String url = "/cma/InternalAuditManagement/modifyOneFormData";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("year","1997")
                .param("fileId",this.id)
                .param("fileName","1997m01")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    public void t_4_modifyOneFile() throws Exception{
        File file = new File("E:/CMA/test/","1997年度02.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","1997年度02.docx","multipart/form-data",new FileInputStream(file));
        MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=this.internalAuditDocumentController.modifyOneFile(firstFile,Long.parseLong(this.id),1997,"1997m02",request);
        Assert.assertEquals(200L, res.code);
    }

    @Test
    @Transactional
    public void t_5_downloadFile() {
        MockHttpServletResponse response=new MockHttpServletResponse();
        String res=this.internalAuditDocumentController.downloadFile(Long.parseLong(this.id),response);
        Assert.assertEquals("下载成功", res);
    }

    @Test
    public void t_6_deleteOneFile() throws Exception{
        String url = "/cma/InternalAuditManagement/deleteOneFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("fileId",this.id)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }


    @Test
    @Transactional
    public void t_7_deleteOne() throws Exception{
        String url = "/cma/InternalAuditManagement/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("year","1997")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }
}