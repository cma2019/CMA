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
* UserController Tester. 
* 
* @author <Authors name> 
* @since <pre>六月 30, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class UserControllerTest {
    MockMvc mockMvc;
    @Autowired
    UserController userController;

@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addUser(@RequestParam(value = "username", required = false)String username, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "password2", required = false) String password2) 
* 
*/ 
@Test
public void testAddUser() throws Exception { 
//TODO: Test goes here...
    String url="/cma/user/add?username=abc&email=abc@163.com&password=123456&password2=123456";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
    String res=mvcResult.getResponse().getContentAsString();
    String temp="Sign up successfully.";
    Assert.assertEquals(res,temp);
} 

/** 
* 
* Method: findUser(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) 
* 
*/ 
@Test
public void testFindUser() throws Exception { 
//TODO: Test goes here...
    String url="/cma/user/find?email=abc@163.com&password=123456";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
    String res=mvcResult.getResponse().getContentAsString();
    String temp="invalid email.";
    Assert.assertEquals(res,temp);
} 


} 
