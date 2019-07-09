package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.framework.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SelfInspectionControllerTest {

    @Autowired
    SelfInspectionController selfInspectionController=new SelfInspectionController();
    MockMvc mockMvc;
    static String fileId="none";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.selfInspectionController}).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void t1_getAll() throws Exception{
        String url = "/cma/SelfInspection/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void t2_addOne() throws Exception{
        String url = "/cma/SelfInspection/addOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("name","test01")
                .param("date","2019-7-1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void t9_deleteOne() throws Exception{
        String url = "/cma/SelfInspection/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("id","226")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void t4_getAllFile() throws Exception{
        String url = "/cma/SelfInspection/getAllFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("id","226")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    public void t3_addOneFile() throws Exception{
        File file = new File("E:/CMA/test/","1997年度01.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","1997年度01.docx","multipart/form-data",new FileInputStream(file));
        MockHttpServletRequest request=new MockHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        JSONObject res=selfInspectionController.addOneFile(firstFile,226,"test01",request);
        fileId=res.getString("data");
        Assert.assertEquals("200", res.getString("code"));
    }

    @Test
    public void t5_modifyOneFormData() throws Exception{
        String url = "/cma/SelfInspection/modifyOneFormData";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("fileId",fileId)
                .param("fileName","test02")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    public void t6_modifyOneFile() throws Exception{
        File file = new File("E:/CMA/test/","1997年度02.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","1997年度02.docx","multipart/form-data",new FileInputStream(file));
        MockHttpServletRequest request=new MockHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=selfInspectionController.modifyOneFile(firstFile,Long.parseLong(fileId),"test02",188,request);
        Assert.assertEquals(200L, res.code);
    }

    @Test
    public void t8_deleteOneFile() throws Exception{
        String url = "/cma/SelfInspection/deleteOneFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("fileId",fileId)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    public void t7_downloadFile(){
        MockHttpServletResponse response=new MockHttpServletResponse();
        String res=selfInspectionController.downloadFile(Long.parseLong(fileId),response);
        Assert.assertEquals("下载成功", res);
    }
}