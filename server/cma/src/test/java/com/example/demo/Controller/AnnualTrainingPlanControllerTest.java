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
* AnnualTrainingPlanController Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 16, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class AnnualTrainingPlanControllerTest {
    @Autowired
    AnnualTrainingPlanController annualTrainingPlanController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(annualTrainingPlanController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAll(@RequestParam(value = "year",required = false)long year) 
* 
*/ 
@Test
public void testGetAll() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/getAll?year=2018";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    int code=Integer.parseInt(res.substring(19,22));
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: getOne(@RequestParam(value="planId",required = false)long planId) 
* 
*/ 
@Test
public void testGetOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/getOne?planId=218";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    int code=Integer.parseInt(res.substring(24,27));
    Assert.assertEquals(210,code);
} 

/** 
* 
* Method: addOne(@RequestParam(value = "year",required = false) long year, @RequestParam(value = "trainProject",required = false)String trainProject, @RequestParam(value = "people",required = false)String people, @RequestParam(value = "method",required = false)String method, @RequestParam(value="trainingTime",required = false)long trainingTime, @RequestParam(value = "startTime",required = false)String startTime, @RequestParam(value = "endTime",required = false)String endTime, @RequestParam(value = "note",required = false)String note) 
* 
*/ 
@Test
public void testAddOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/addOne?year=2019&trainProject=前端培训&people=前端组&method=授课&trainingTime=50&startTime=2018-5-6&endTime=2018-7-6&note=无";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    int code=Integer.parseInt(res.substring(19,22));
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: deleteOne(@RequestParam(value = "planId",required = false)long planId) 
* 
*/ 
@Test
public void testDeleteOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/deleteOne?planId=7";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    int code=Integer.parseInt(res.substring(19,22));
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: modifyOne(@RequestParam(value = "planId",required = false) long planId, @RequestParam(value = "trainProject",required = false)String trainProject, @RequestParam(value = "people",required = false)String people, @RequestParam(value = "method",required = false)String method, @RequestParam(value="trainingTime",required = false)String trainingTime, @RequestParam(value = "startTime",required = false)String startTime, @RequestParam(value = "endTime",required = false)String endTime, @RequestParam(value = "note",required = false)String note) 
* 
*/ 
@Test
public void testModifyOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/AnnualTrainingPlan/modifyOne?planId=9&trainProject=&people=&method=&trainingTime=60&startTime=&endTime=&note=";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    //int status=mvcResult.getResponse().getStatus();
    //Assert.assertEquals(200,status);
    String res=mvcResult.getResponse().getContentAsString();
    int code=Integer.parseInt(res.substring(19,22));
    Assert.assertEquals(200,code);
} 

/** 
* 
* Method: setAnnualTrainingPlanRepository(AnnualTrainingPlanRepository annualTrainingPlanRepository) 
* 
*/ 
@Test
public void testSetAnnualTrainingPlanRepository() throws Exception { 
//TODO: Test goes here... 
} 


} 
