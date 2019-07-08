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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

/** 
* CapacityVerificationPlanController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 6, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class CapacityVerificationPlanControllerTest { 

    @Autowired
    CapacityVerificationPlanController capacityVerificationPlanController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(capacityVerificationPlanController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAllPlan() 
* 
*/ 
@Test
public void testGetAllPlan() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/getAll";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: addPlan(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "organizer", required = false) String organizer, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "note", required = false) String note) 
* 
*/ 
@Test
public void testAddPlan() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/addOne?name=名称&organizer=组织者&year=2019&note=备注";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: deletePlan(@RequestParam(value="id",required=false)Long planId) 
* 
*/ 
@Test
public void testDeletePlan() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/deleteOne?id=79";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: modifyPlan(@RequestParam(value="id",required=false)Long planId, @RequestParam(value="name",required=false)String name, @RequestParam(value="organizer",required=false)String organizer, @RequestParam(value="state",required=false)Long state, @RequestParam(value="year",required=false)String year, @RequestParam(value="note",required=false)String note) 
* 
*/ 
@Test
public void testModifyPlan() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/modifyOne?id=79&name=名称&organizer=组织者&state=1&year=2019&note=备注";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: getOnePlan(@RequestParam(value="id",required=false)Long planId) 
* 
*/ 
@Test
public void testGetOnePlan() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/getOne?id=80";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: uploadAnalysis(@RequestParam(value="id",required = false)Long id) 
* 
*/ 
@Test
public void testUploadAnalysis() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/uploadAnalysis?id=79";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(23, 26));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: addAnalysis(@RequestParam(value="file",required = false)MultipartFile file, HttpServletRequest request) 
* 
*/ 
@Test
public void testAddAnalysis() throws Exception { 
//TODO: Test goes here...
    testUploadAnalysis();
    ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.fileUpload("/cma/CapacityVerification/uploadAnalysisFile")
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
* Method: downloadAnalysis(@PathVariable("id")Long id, HttpServletResponse response)
* 
*/ 
@Test
public void testDownloadAnalysis() throws Exception {
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteAnalysis(@RequestParam(value="id",required =false)Long id)
* 
*/ 
@Test
public void testDeleteAnalysis() throws Exception {
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/deleteAnalysis?id=79";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);

} 


} 
