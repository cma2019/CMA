package com.example.demo.Controller; 

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

/** 
* StaffQualificationController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 8, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StaffQualificationControllerTest {
    @Autowired
    StaffQualificationController staffQualificationController;
    MockMvc mockMvc;
@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(staffQualificationController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAllByStaff(@RequestParam(required = false,value = "staffId")long staffId) 
* 
*/ 
@Test
public void testGetAllByStaff() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffQualification/getAllByStaff?staffId=52";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam(required = false,value = "staffId")long staffId, @RequestParam(required = false,value="qualificationName")String qualificationName) 
* 
*/ 
@Test
public void testUpLoad() throws Exception { 
//TODO: Test goes here...
    File file = new File("E:/CMA/test/","test.jpg");
    MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","test.jpg","multipart/form-data",new FileInputStream(file));
    MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
    //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
    net.minidev.json.JSONObject jsonObject=this.staffQualificationController.UpLoad(firstFile,request,2018,"test");
    Assert.assertEquals(210, jsonObject.get("code"));
} 

/** 
* 
* Method: deleteOne(@RequestParam(value = "qualificationId",required = false)long qualificationId) 
* 
*/ 
@Test
public void testDeleteOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffQualification/deleteOne?qualificationId=52";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(210,code);
} 

/** 
* 
* Method: modifyOne(@RequestParam(value = "qualificationId", required = false) long qualificationId, @RequestParam(value = "qualificationName", required = false) String qualificationName) 
* 
*/ 
@Test
public void testModifyOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffQualification/modifyOne?qualificationId=52&qualificationName=sdf";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(210,code);
} 

/** 
* 
* Method: modifyOneFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam(value = "qualificationId", required = false) long qualificationId) 
* 
*/ 
@Test
public void testModifyOneFile() throws Exception { 
//TODO: Test goes here...
    File file = new File("E:/CMA/test/","test.jpg");
    MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","test.jpg","multipart/form-data",new FileInputStream(file));
    MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
    //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
    net.minidev.json.JSONObject jsonObject=this.staffQualificationController.modifyOneFile(firstFile,request,52);
    Assert.assertEquals(210, jsonObject.get("code"));
} 

/** 
* 
* Method: downloadFile(@PathVariable("qualificationId")long qualificationId, HttpServletResponse response) 
* 
*/ 
@Test
public void testDownloadFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAll() 
* 
*/ 
@Test
public void testGetAll() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffQualification/getAll";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: getOne(@RequestParam(value = "qualificationId",required = false)long qualificationId) 
* 
*/ 
@Test
public void testGetOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffQualification/getOne?qualificationId=52";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(210,code);
} 


} 
