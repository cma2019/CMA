package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.DemoApplication;
import com.example.demo.Model.Equipment;
import com.example.demo.Repository.EquipmentRepository;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.SimpleDateFormat;

/** 
* AllAnnualPlanController Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 12, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class AllAnnualPlanControllerTest {
    @Autowired
    AllAnnualPlanController allAnnualPlanController;
    MockMvc mockMvc;


@Before
public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(allAnnualPlanController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addAnnualPlan(@RequestParam(value = "year",required = false)long year, @RequestParam(value = "author",required = false)String author, @RequestParam(value = "createDate",required = false)String createDate) 
* 
*/ 
@Test
public void testAddAnnualPlan() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/addAnnualPlan?year=2018&author=aha&createDate=2018-6-6";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
   //Assert.assertEquals(200,status);
    //JSONObject json=mvcResult.getResponse().get
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: approve(@RequestParam(value="year",required = false)long year, @RequestParam(value = "approver",required = false)String approver, @RequestParam(value = "approveDate",required = false)String approveDate) 
* 
*/ 
@Test
public void testApprove() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/approveAnnualPlan?year=2018&approver=aha&approveDate=2018-6-6";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    //JSONObject json=mvcResult.getResponse().get
    String res=mvcResult.getResponse().getContentAsString();
    JSONObject jsonObject= JSON.parseObject(res);
    int code= (int) jsonObject.get("code");
    System.out.println(code);
    Assert.assertEquals(210,code);
} 

/** 
* 
* Method: getOne(@RequestParam(value="year",required = false)long year) 
* 
*/ 
@Test
public void testGetOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/getAnnualPlan?year=2018";
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

/** 
* 
* Method: getAll() 
* 
*/ 
@Test
public void testGetAll() throws Exception {
    String url="/cma/AnnualTrainingPlan/getAllAnnualPlan";
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
