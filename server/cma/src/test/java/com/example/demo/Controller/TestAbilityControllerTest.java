package com.example.demo.Controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

/** 
* TestAbilityController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 8, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class TestAbilityControllerTest { 

    @Autowired
    TestAbilityController testAbilityController;
    MockMvc mockMvc;
@Before
public void before() throws Exception { 
    mockMvc= MockMvcBuilders.standaloneSetup(testAbilityController).build();
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addInfo(@RequestParam(value="year",required = false)Long year, @RequestParam(value = "fileName",required = false)String fileName) 
* 
*/ 
@Test
public void testAddInfo() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/addOne?year=2019&fileName=file.pdf";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(23, 26));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: addFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request) 
* 
*/ 
@Test
public void testAddFile() throws Exception { 
//TODO: Test goes here...
    testAddInfo();
    ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.fileUpload("/cma/TestAbility/addOneFile")
            .file(new MockMultipartFile("file","test.pdf","application/pdf",new FileInputStream(new File("C:/Users/user/Desktop/test.pdf")))));
    MvcResult mvcResult=resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status()
            .isOk()).andReturn();
    String res=mvcResult.getResponse().getContentAsString();
    System.out.println(res);
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: getAllAbility() 
* 
*/ 
@Test
public void testGetAllAbility() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/getAll";
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
* Method: modifyAbility(@RequestParam(value="year",required = false)Long year, @RequestParam(value = "fileName",required = false)String fileName) 
* 
*/ 
@Test
public void testModifyAbility() throws Exception { 
//TODO: Test goes here...
    String url = "/cma/TestAbility/modifyOne?year=2001&fileName=file.pdf";
    MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    String res = mvcResult.getResponse().getContentAsString();
    int code = Integer.parseInt(res.substring(23, 26));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: modifyAbilityFile(@RequestParam(value="file") MultipartFile file, HttpServletRequest request) 
* 
*/ 
@Test
public void testModifyAbilityFile() throws Exception { 
//TODO: Test goes here...
    testModifyAbility();
    ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.fileUpload("/cma/TestAbility/modifyOneFile")
            .file(new MockMultipartFile("file","test.pdf","application/pdf",new FileInputStream(new File("C:/Users/user/Desktop/test.pdf")))));
    MvcResult mvcResult=resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status()
            .isOk()).andReturn();
    String res=mvcResult.getResponse().getContentAsString();
    System.out.println(res);
    int code = Integer.parseInt(res.substring(8, 11));
    Assert.assertEquals(200, code);
} 

/** 
* 
* Method: downloadTestAbility(@PathVariable("year")Long year, HttpServletResponse response) 
* 
*/ 
@Test
public void testDownloadTestAbility() throws Exception { 
//TODO: Test goes here... 
} 


} 
