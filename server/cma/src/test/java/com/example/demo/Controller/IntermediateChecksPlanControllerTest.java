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
* IntermediateChecksPlanController Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 6, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class IntermediateChecksPlanControllerTest {

    @Autowired
    IntermediateChecksPlanController intermediateChecksPlanController;
    MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(intermediateChecksPlanController).build();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addPlan(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="object",required=false)String object, @RequestParam(value="content",required=false)String content, @RequestParam(value="checkDate",required=false) Date checkDate, @RequestParam(value="personInCharge",required=false)String personInCharge)
     */
    @Test
    public void testAddPlan() throws Exception {
//TODO: Test goes here...
        String url = "/cma/IntermediateChecksPlan/addOne?object=期间检查计划1&content=检查&checkDate=2019-7-6&personInCharge=lyt";
        MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(21, 24));
        Assert.assertEquals(200, code);
    }

    /**
     * Method: deletePlan(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="planId",required=false)Long planId)
     */
    @Test
    public void testDeletePlan() throws Exception {
//TODO: Test goes here...
        String url = "/cma/IntermediateChecksPlan/deleteOne?planId=17";
        MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(21, 24));
        Assert.assertEquals(200, code);
    }

    /**
     * Method: modifyPlan(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="planId",required=false)Long planId, @RequestParam(value="object",required=false)String object, @RequestParam(value="content",required=false)String content, @RequestParam(value="checkDate",required=false)Date checkDate, @RequestParam(value="personInCharge",required=false)String personInCharge, @RequestParam(value="state",required=false)byte state)
     */
    @Test
    public void testModifyPlan() throws Exception {
//TODO: Test goes here...
        String url = "/cma/IntermediateChecksPlan/modifyOne?planId=17&object=对象&content=内容&checkDate=2019-7-6&personInCharge=lyt&state=1";
        MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.post(url)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(21, 24));
        Assert.assertEquals(200, code);
    }

    /**
     * Method: getOne(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="planId",required=false)Long planId)
     */
    @Test
    public void testGetOne() throws Exception {
//TODO: Test goes here...
        String url = "/cma/IntermediateChecksPlan/getOne?planId=24";
        MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(21, 24));
        Assert.assertEquals(200, code);
    }

    /**
     * Method: getAll(HttpServletRequest request, HttpServletResponse response)
     */
    @Test
    public void testGetAll() throws Exception {
//TODO: Test goes here...
        String url = "/cma/IntermediateChecksPlan/getAll";
        MvcResult mvcResult = mockMvc.perform((MockMvcRequestBuilders.get(url)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        int code = Integer.parseInt(res.substring(21, 24));
        Assert.assertEquals(200, code);
    }


}

