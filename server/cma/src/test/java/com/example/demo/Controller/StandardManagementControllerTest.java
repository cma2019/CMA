package com.example.demo.Controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

/** 
* StandardManagementController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 8, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StandardManagementControllerTest { 

    @Autowired
    StandardManagementController standardManagementController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(standardManagementController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addStandard(@RequestParam(value="file")MultipartFile file, HttpServletRequest request) 
* 
*/ 
@Test
public void testAddStandard() throws Exception { 
//TODO: Test goes here...
    ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.fileUpload("/cma/StandardManagement/addOne")
            .file(new MockMultipartFile("file","test.pdf","application/pdf",new FileInputStream(new File("C:/Users/user/Desktop/test.pdf")))));
    MvcResult mvcResult=resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status()
            .isOk()).andReturn();
    String res=mvcResult.getResponse().getContentAsString();
    System.out.println(res);
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: getAllStandard() 
* 
*/ 
@Test
public void testGetAllStandard() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/StandardManagement/getAll";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: getOneStandard(@RequestParam(value="fileId")Long fileId) 
* 
*/ 
@Test
public void testGetOneStandard() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/StandardManagement/getOne?fileId=72";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: downloadStandard(@PathVariable("fileId")Long fileId, HttpServletResponse response) 
* 
*/ 
@Test
public void testDownloadStandard() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteStandard(@RequestParam(value="fileId",required =false)Long fileId) 
* 
*/ 
@Test
public void testDeleteStandard() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/StandardManagement/deleteOne?fileId=72";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: modifyStandard(@RequestParam(value="fileId",required = false)Long fileId, @RequestParam(value="file")MultipartFile file, HttpServletRequest request) 
* 
*/ 
@Test
public void testModifyStandard() throws Exception { 
//TODO: Test goes here...
    ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.fileUpload("/cma/StandardManagement/modifyOne")
            .file(new MockMultipartFile("file","test.pdf","application/pdf",new FileInputStream(new File("C:/Users/user/Desktop/test.pdf")))).param("fileId","72"));
    MvcResult mvcResult=resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status()
            .isOk()).andReturn();
    String res=mvcResult.getResponse().getContentAsString();
    System.out.println(res);
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 


} 
