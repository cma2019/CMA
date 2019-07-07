package com.example.demo.Controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

/** 
* IntermediateChecksRecordController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 6, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class IntermediateChecksRecordControllerTest { 

    @Autowired
    IntermediateChecksRecordController intermediateChecksRecordController;
    MockMvc mockMvc;

    @Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(intermediateChecksRecordController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAllRecord(HttpServletRequest request, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetAllRecord() throws Exception { 
//TODO: Test goes here... 
    String url = "/cma/IntermediateChecksRecord/getAll";
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
* Method: getOneByRecordId(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="recordId",required=false)Long recordId) 
* 
*/ 
@Test
public void testGetOneByRecordId() throws Exception { 
//TODO: Test goes here... 
    String url = "/cma/IntermediateChecksRecord/getOneByRecordId?recordId=27";
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
* Method: addRecord(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "planId", required = false) Long planId, @RequestParam(value = "object", required = false) String object, @RequestParam(value = "checkDate", required = false) Date checkDate, @RequestParam(value = "processRecord", required = false) String processRecord, @RequestParam(value = "processRecordPerson", required = false) String processRecordPerson, @RequestParam(value = "processRecordDate", required = false) Date processRecordDate, @RequestParam(value = "resultRecord", required = false) String resultRecord, @RequestParam(value = "resultRecordPerson", required = false) String resultRecordPerson, @RequestParam(value = "resultRecordDate", required = false) Date resultRecordDate, @RequestParam(value = "note", required = false) String note) 
* 
*/ 
@Test
public void testAddRecord() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/IntermediateChecksRecord/addOne?planId=25&object=对象&checkDate=2019-5-30&processRecord=处理记录&processRecordPerson=处理人&processRecordDate=2019-7-7&resultRecord=结果记录&resultRecordPerson=结果记录人&resultRecordDate=2019-7-9&note=test1";
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
* Method: deleteRecord(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="recordId",required=false)Long recordId) 
* 
*/ 
@Test
public void testDeleteRecord() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/IntermediateChecksRecord/deleteOne?recordId=86";
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
* Method: modifyRecord(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="recordId",required=false)Long recordId, @RequestParam(value = "planId", required = false) Long planId, @RequestParam(value = "object", required = false) String object, @RequestParam(value = "checkDate", required = false) Date checkDate, @RequestParam(value = "processRecord", required = false) String processRecord, @RequestParam(value = "processRecordPerson", required = false) String processRecordPerson, @RequestParam(value = "processRecordDate", required = false) Date processRecordDate, @RequestParam(value = "resultRecord", required = false) String resultRecord, @RequestParam(value = "resultRecordPerson", required = false) String resultRecordPerson, @RequestParam(value = "resultRecordDate", required = false) Date resultRecordDate, @RequestParam(value = "note", required = false) String note) 
* 
*/ 
@Test
public void testModifyRecord() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/IntermediateChecksRecord/modifyOne?recordId=86&planId=17&object=对象1&checkDate=2019-5-30&processRecord=处理记录2&processRecordPerson=处理人3&processRecordDate=2019-7-7&resultRecord=结果记录4&resultRecordPerson=结果记录人5&resultRecordDate=2019-7-9&note=test1";
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
* Method: getOneByPlanId(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="planId",required=false)Long planId) 
* 
*/ 
@Test
public void testGetOneByPlanId() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/IntermediateChecksRecord/getOneByPlanId?planId=17";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 


} 
