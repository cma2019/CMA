package com.example.demo.Controller; 

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Repository.StaffTrainingRepository;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

/** 
* StaffTrainingController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 8, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StaffTrainingControllerTest {
    @Autowired
    StaffTrainingController staffTrainingController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(staffTrainingController).build();
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
    String url="/cma/StaffTraining/getAll";
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
* Method: addOne(@RequestParam(value="trainingId",required = false)long trainingId, @RequestParam(value="program",required = false)String program, @RequestParam(value = "trainingDate",required = false)String trainingDate, @RequestParam(value = "place",required = false)String place, @RequestParam(value = "presenter",required = false)String presenter, @RequestParam(value = "content",required = false)String content, @RequestParam(value = "note",required = false)String note) 
* 
*/ 
@Test
public void testAddOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/addOne?trainingId=16&program=后端培训&trainingDate=2019-8-6&place=南京大学&presenter=非洲酋长&content=后端组编程教学&note=无";
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
* Method: deleteOne(@RequestParam(value = "trainingId",required = false)long trainingId) 
* 
*/ 
@Test
public void testDeleteOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/deleteOne?trainingId=17";
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
* Method: modifyOne(@RequestParam(value = "trainingId",required = false)long trainingId, @RequestParam(value="program",required = false)String program, @RequestParam(value = "trainingDate",required = false)String trainingDate, @RequestParam(value = "place",required = false)String place, @RequestParam(value = "presenter",required = false)String presenter, @RequestParam(value = "content",required = false)String content, @RequestParam(value = "note",required = false)String note) 
* 
*/ 
@Test
public void testModifyOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/modifyOne?trainingId=16&program=&trainingDate=&place=&presenter=曹春&content=&note=";
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
* Method: getOneTraining(@RequestParam(value = "trainingId",required = false)long trainingId) 
* 
*/ 
@Test
public void testGetOneTraining() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/getOneTraining?trainingId=16";
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
* Method: addTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId, @RequestParam(value="id",required = false)long id) 
* 
*/ 
@Test
public void testAddTrainingPeople() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/addTrainingPeople?trainingId=16&id=1";
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
* Method: addTrainingResult(@RequestParam(value = "trainingId",required = false)long trainingId, @RequestParam(value="id",required = false)long id, @RequestParam(value="result",required = false)String result) 
* 
*/ 
@Test
public void testAddTrainingResult() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/addTrainingResult?trainingId=16&id=1&result=合格";
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
* Method: getTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId) 
* 
*/ 
@Test
public void testGetTrainingPeople() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/getTrainingPeople?trainingId=16&id=1";
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
* Method: modifyResult(@RequestParam(value = "trainingId",required = false)long trainingId, @RequestParam(value="id",required = false)long id, @RequestParam(value="result",required = false)String result) 
* 
*/ 
@Test
public void testModifyResult() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/modifyResult?trainingId=16&id=1&result=合格";
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
* Method: getAllByStaff(@RequestParam(value = "id",required = false)long id) 
* 
*/ 
@Test
public void testGetAllByStaff() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/getAllByStaff?id=1";
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
* Method: getOne(@RequestParam(value = "id",required = false)long id, @RequestParam(value = "trainingId",required = false)long trainingId) 
* 
*/ 
@Test
public void testGetOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/getOne?trainingId=16&id=1";
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
* Method: deleteTrainingPeople(@RequestParam(value = "trainingId",required = false)long trainingId, @RequestParam(value = "id",required = false)long id) 
* 
*/ 
@Test
public void testDeleteTrainingPeople() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffTraining/getOne?trainingId=16&id=1";
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
