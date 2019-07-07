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
* StaffAuthorizationController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 7, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StaffAuthorizationControllerTest {
    @Autowired
    StaffAuthorizationController staffAuthorizationController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(staffAuthorizationController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAll() 
* 
*/ 
@Test
public void testGetAll() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffAuthorization/getAll";
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
* Method: getOne(@RequestParam(value="authorizationId",required = false)long authorizationId) 
* 
*/ 
@Test
public void testGetOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffAuthorization/getOne?authorizationId=25";
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
* Method: addOne(@RequestParam(value = "id",required = false) long id, @RequestParam(value = "authorizerId",required = false)long authorizerId, @RequestParam(value = "content",required = false)String content, @RequestParam(value = "authorizerDate",required = false)String authorizerDate) 
* 
*/ 
@Test
public void testAddOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffAuthorization/addOne?id=2&authorizerId=1&content=培训授权&authorizerDate=2019-7-1";
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
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: deleteOne(@RequestParam(value = "authorizationId",required = false)long authorizationId) 
* 
*/ 
@Test
public void testDeleteOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffAuthorization/deleteOne?authorizationId=3";
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
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: modifyOne(@RequestParam(value = "authorizationId",required = false)long authorizationId, @RequestParam(value = "id",required = false) String id, @RequestParam(value = "authorizerId",required = false)String authorizerId, @RequestParam(value = "content",required = false)String content, @RequestParam(value = "authorizerDate",required = false)String authorizerDate) 
* 
*/ 
@Test
public void testModifyOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffAuthorization/modifyOne?authorizationId=2&id=3&authorizerId=1&content=培训授权&authorizerDate=2019-7-1";
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
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: getallbystaff(@RequestParam(required = false,value = "id")long id) 
* 
*/ 
@Test
public void testGetallbystaff() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffAuthorization/getByAllStaff?id=25";
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


} 
