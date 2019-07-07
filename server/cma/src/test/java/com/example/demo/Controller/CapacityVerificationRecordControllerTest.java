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
* CapacityVerificationRecordController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 6, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class CapacityVerificationRecordControllerTest { 

    @Autowired
    CapacityVerificationRecordController capacityVerificationRecordController;
    MockMvc mockMvc;
@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(capacityVerificationRecordController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addRecord(@RequestParam(value = "projectId", required = false) Long projectId, @RequestParam(value = "date", required = false) Date date, @RequestParam(value = "methodId", required = false) String methodId, @RequestParam(value = "equipmentName", required = false) String equipmentName, @RequestParam(value = "equipmentId", required = false) String equipmentId, @RequestParam(value = "experimenter", required = false) String experimenter, @RequestParam(value = "result", required = false) String result, @RequestParam(value = "resultDeal", required = false) String resultDeal, @RequestParam(value = "note", required = false) String note) 
* 
*/ 
@Test
public void testAddRecord() throws Exception {
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/addOneRecord?projectId=92&date=2019-7-7&methodId=1&equipmentName=设备1&equipmentId=1&experimenter=实验人&result=结果&resultDeal=结果处理&note=备注";
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
* Method: deleteRecord(@RequestParam(value="id",required=false)Long recordId) 
* 
*/ 
@Test
public void testDeleteRecord() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/deleteOneRecord?id=95";
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
* Method: modifyRecord(@RequestParam(value="id",required=false)Long recordId, @RequestParam(value="projectId",required = false)Long projectId, @RequestParam(value="date",required=false)Date date, @RequestParam(value="methodId",required=false)String methodId, @RequestParam(value="equipmentName",required=false)String equipmentName, @RequestParam(value="equipmentId",required=false)String equipmentId, @RequestParam(value="experimenter",required=false)String experimenter, @RequestParam(value="result",required=false)String result, @RequestParam(value="resultDeal",required=false)String resultDeal, @RequestParam(value="note",required=false)String note) 
* 
*/ 
@Test
public void testModifyRecord() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/modifyOneRecord?id=95&projectId=92&date=2019-7-7&methodId=2&equipmentName=设备2&equipmentId=2&experimenter=实验人2&result=结果2&resultDeal=结果处理2&note=备注2";
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
* Method: getOneRecord(@RequestParam(value="id",required=false)Long recordId) 
* 
*/ 
@Test
public void testGetOneRecord() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/getOneRecord?id=95";
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
* Method: getRecordByProjectId(@RequestParam(value="projectId",required=false)Long projectId) 
* 
*/ 
@Test
public void testGetRecordByProjectId() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/CapacityVerification/getRecordByProjectId?projectId=92";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 


} 
