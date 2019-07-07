package com.example.demo.Controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

/** 
* CapacityVerificationProjectController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 6, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class CapacityVerificationProjectControllerTest {

    @Autowired
    CapacityVerificationProjectController capacityVerificationProjectController;
    MockMvc mockMvc;
@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(capacityVerificationProjectController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAllProject(@RequestParam(value="planId",required = false)Long planId) 
* 
*/ 
@Test
public void testGetAllProject() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/getAllProject?planId=80";
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
* Method: addProject(@RequestParam(value = "planId", required = false) Long planId, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "method", required = false) String method, @RequestParam(value = "note", required = false) String note) 
* 
*/ 
@Test
public void testAddProject() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/addOneProject?planId=80&name=名称&method=方法&note=备注";
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
* Method: deleteProject(@RequestParam(value="id",required=false)Long projectId) 
* 
*/ 
@Test
public void testDeleteProject() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/deleteOneProject?id=92";
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
* Method: modifyProject(@RequestParam(value="id",required=false)Long projectId, @RequestParam(value="planId",required = false)Long planId, @RequestParam(value="name",required=false)String name, @RequestParam(value="method",required=false)String method, @RequestParam(value="state",required=false)Long state, @RequestParam(value="note",required=false)String note) 
* 
*/ 
@Test
public void testModifyProject() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/modifyOneProject?id=92&planId=80&name=test&method=test&state=1&note=test";
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
* Method: getOneProject(@RequestParam(value="id",required=false)Long projectId) 
* 
*/ 
@Test
public void testGetOneProject() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/getOneProject?id=92";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 


} 
