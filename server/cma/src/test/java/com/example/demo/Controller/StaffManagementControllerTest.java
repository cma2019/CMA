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
* StaffManagementController Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 16, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StaffManagementControllerTest {
    @Autowired
    StaffManagementController staffManagementController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(staffManagementController).build();
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
    String url="/cma/StaffManagement/getAll";
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
* Method: addOne(@RequestParam(value="name",required = false)String name, @RequestParam(value = "gender",required = false)int gender, @RequestParam(value = "department",required = false)String department, @RequestParam(value = "position",required = false)String position, @RequestParam(value="title",required = false)String title, @RequestParam(value = "degree",required = false)String degree, @RequestParam(value = "graduationSchool",required = false)String graduationSchool, @RequestParam(value = "graduationMajor",required = false)String graduationMajor, @RequestParam(value = "graduationDate",required = false)String graduationDate, @RequestParam(value = "workingYears",required = false)int workingYears) 
* 
*/ 
@Test
public void testAddOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffManagement/addOne?name=Judy&gender=1&department=财务部&position=部员&title=导师&degree=硕士研究生&graduationSchool=河南大学&graduationMajor=会计&graduationDate=2006-6-28&workingYears=8";
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
* Method: get(@RequestParam(value = "id",required = false)long id) 
* 
*/ 
@Test
public void testGet() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffManagement/getOne?id=23";
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
* Method: deleteOne(@RequestParam(value = "id",required = false)Long id) 
* 
*/ 
@Test
public void testDeleteOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffManagement/deleteOne?id=23";
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
* Method: modifyOne(@RequestParam(value="id",required = false)long id, @RequestParam(value="name",required = false)String name, @RequestParam(value = "gender",required = false)String gender, @RequestParam(value = "department",required = false)String department, @RequestParam(value = "position",required = false)String position, @RequestParam(value="title",required = false)String title, @RequestParam(value = "degree",required = false)String degree, @RequestParam(value = "graduationSchool",required = false)String graduationSchool, @RequestParam(value = "graduationMajor",required = false)String graduationMajor, @RequestParam(value = "graduationDate",required = false)String graduationDate, @RequestParam(value = "workingYears",required = false)String workingYears) 
* 
*/ 
@Test
public void testModifyOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffManagement/modifyOne?id=24&name=Kate&gender=&department=&position=&title=&degree=博士研究生&graduationSchool=&graduationMajor=&graduationDate=&workingYears=";
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


} 
