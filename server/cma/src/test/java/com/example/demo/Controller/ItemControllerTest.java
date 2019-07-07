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
* ItemController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 7, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ItemControllerTest { 

    @Autowired
    ItemController itemController;
    MockMvc mockMvc;
@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(itemController).build();
} 

@After
public void after() throws Exception {

} 

/** 
* 
* Method: addOneItem(@RequestParam(value="year",required = false)Long year, @RequestParam(value = "productionName",required = false)String productionName, @RequestParam(value = "ability",required = false)String ability, @RequestParam(value = "referrence",required = false)String referrence) 
* 
*/ 
@Test
public void testAddOneItem() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/addOneItem?year=2019&productionName=产品名&ability=产品能力&referrence=产品依据";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: getAllItem(@RequestParam(value="year",required = false)Long year) 
* 
*/ 
@Test
public void testGetAllItem() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/getAllItem?year=2019";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: getOneItem(@RequestParam(value = "id",required = false)Long id) 
* 
*/ 
@Test
public void testGetOneItem() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/getOneItem?id=76";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: deleteOneItem(@RequestParam(value = "id",required = false)Long id) 
* 
*/ 
@Test
public void testDeleteOneItem() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/deleteOneItem?id=76";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(21, 24));
    Assert.assertEquals(200, code);
} 


} 
