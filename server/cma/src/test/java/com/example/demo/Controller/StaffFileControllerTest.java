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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;

/** 
* StaffFileController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 8, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StaffFileControllerTest {
    @Autowired
    StaffFileController staffFileController;
    MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc= MockMvcBuilders.standaloneSetup(staffFileController).build();
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
    String url="/cma/StaffFile/getAll";
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
* Method: getOne(@RequestParam(value = "staffId", required = false) long staffId) 
* 
*/ 
@Test
public void testGetOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffFile/getOne?staffId=1";
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
* Method: UpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) 
* 
*/ 
@Test
public void testUpLoad() throws Exception { 
//TODO: Test goes here...
    File file = new File("E:/CMA/test/","test.jpg");
    MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","test.jpg","multipart/form-data",new FileInputStream(file));
    MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
    //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
    net.minidev.json.JSONObject jsonObject=this.staffFileController.UpLoad(firstFile,request,2018,"test","place1");
    Assert.assertEquals(210, jsonObject.get("code"));
} 

/** 
* 
* Method: deleteOne(@RequestParam(value = "staffId", required = false) long staffId) 
* 
*/ 
@Test
public void testDeleteOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffFile/deleteOne?staffId=1";
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
* Method: modifyOne(@RequestParam(value = "staffId", required = false) long staffId, @RequestParam(value = "fileId", required = false) String fileId, @RequestParam(value = "fileLocation", required = false) String fileLocation) 
* 
*/ 
@Test
public void testModifyOne() throws Exception { 
//TODO: Test goes here...
    String url="/cma/StaffFile/modifyOne?staffId=1&fileId=sfd&fileLocation=sdf";
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
* Method: modifyOneFile(@RequestParam(value = "staffId", required = false) long staffId, @RequestParam("file") MultipartFile file, HttpServletRequest request) 
* 
*/ 
@Test
public void testModifyOneFile() throws Exception { 
//TODO: Test goes here...
    File file = new File("E:/CMA/test/","test.jpg");
    MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","test.jpg","multipart/form-data",new FileInputStream(file));
    MockMultipartHttpServletRequest request=new MockMultipartHttpServletRequest();
    //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
    net.minidev.json.JSONObject jsonObject=this.staffFileController.modifyOneFile(2018,firstFile,request);
    Assert.assertEquals(210, jsonObject.get("code"));
} 

/** 
* 
* Method: downloadFile(@PathVariable("staffId")long staffId, HttpServletResponse response) 
* 
*/ 
@Test
public void testDownloadFile() throws Exception { 
//TODO: Test goes here... 
} 


} 
