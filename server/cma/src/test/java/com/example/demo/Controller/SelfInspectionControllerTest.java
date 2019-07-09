package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.framework.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
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

//import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
//设定执行测试函数的顺序为按字典序
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/*
    不对整个类做事物回滚操作，
    因为增删改文件时，数据库的操作可以回滚，
    但是对文件的操作不能回滚
 */
public class SelfInspectionControllerTest
{

    //实例化一个controller对象
    @Autowired
    SelfInspectionController selfInspectionController=new SelfInspectionController();
    MockMvc mockMvc;
    //维护一个静态变量，用于让设计文件操作的测试共享
    static String fileId="none";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.selfInspectionController}).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    //测试getALL()方法
    public void t1_getAll() throws Exception{
        String url = "/cma/SelfInspection/getAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        //把请求的返回结果转为json
        //调用JSONObject的get方法获取code的值
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    //测试addOne()方法
    public void t2_addOne() throws Exception{
        String url = "/cma/SelfInspection/addOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("name","test01")
                .param("date","2019-7-1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    //测试deleteOne()方法
    public void t9_deleteOne() throws Exception{
        String url = "/cma/SelfInspection/deleteOne";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("id","226")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    //测试getALLFile()方法
    public void t4_getAllFile() throws Exception{
        String url = "/cma/SelfInspection/getAllFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url, new Object[0])
                .param("id","226")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    //测试addOneFile()方法
    //没有回滚，数据库中有新增记录，同时本地文件有上传
    //没有使用mockmvc模拟请求，mockmvc也有提供的模拟方法，但是比较麻烦
    //直接调用controller层的方法
    public void t3_addOneFile() throws Exception{
        File file = new File("E:/CMA/test/","1997年度01.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","1997年度01.docx","multipart/form-data",new FileInputStream(file));
        MockHttpServletRequest request=new MockHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        JSONObject res=selfInspectionController.addOneFile(firstFile,226,"test01",request);
        //设置静态变量的值为文件编号
        fileId=res.getString("data");
        Assert.assertEquals("200", res.getString("code"));
    }

    @Test
    //测试modifyOneFormData()方法
    //参数使用静态变量保持的id
    //需要本地有文件用于上传
    //本地文件的路径和名称用于填充file参数
    public void t5_modifyOneFormData() throws Exception{
        String url = "/cma/SelfInspection/modifyOneFormData";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("fileId",fileId)
                .param("fileName","test02")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    //测试modifyOneFile()方法
    //直接调用controller层的方法
    //需要本地有文件用于上传
    //本地文件的路径和名称用于填充file参数
    //id用维护的静态变量
    public void t6_modifyOneFile() throws Exception{
        File file = new File("E:/CMA/test/","1997年度02.docx");
        MockMultipartFile firstFile = new MockMultipartFile("E:/CMA/test/","1997年度02.docx","multipart/form-data",new FileInputStream(file));
        MockHttpServletRequest request=new MockHttpServletRequest();
        //String url = "/cma/InternalAuditManagement/addOneFile";
        /*MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();*/
        Response res=selfInspectionController.modifyOneFile(firstFile,Long.parseLong(fileId),"test02",188,request);
        Assert.assertEquals(200L, res.code);
    }

    @Test
    //测试deleteOneFile()接口
    public void t8_deleteOneFile() throws Exception{
        String url = "/cma/SelfInspection/deleteOneFile";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url, new Object[0])
                .param("fileId",fileId)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        JSONObject js=JSONObject.parseObject(res);
        Assert.assertEquals("200", js.getString("code"));
    }

    @Test
    @Transactional
    //测试downloadOneFile()接口
    //断言返回的字符串是否符合预期
    public void t7_downloadFile(){
        MockHttpServletResponse response=new MockHttpServletResponse();
        String res=selfInspectionController.downloadFile(Long.parseLong(fileId),response);
        Assert.assertEquals("下载成功", res);
    }
}