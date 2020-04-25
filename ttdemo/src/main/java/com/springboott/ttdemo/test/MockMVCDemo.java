package com.springboott.ttdemo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboott.ttdemo.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCDemo {
    @Autowired
    private MockMvc mockMvc;

    //ObjectMapper是一个可以重复使用的对象
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void courseListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
                .andDo(print())
                //返回相应的MvcResult
                .andReturn();
    }

    @Test
    public void createTest() throws Exception {
        String json = "{\"username\":\"Mock测试\",\"age\":40,\"telephone\":\"13550012345\"}";
        //将json格式字符串转换成Course对象里的属性值
        User user = mapper.readValue(json, User.class);
        System.out.println("user = " + user);
        //perform,执行一个RequestBuilders请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
        mockMvc.perform(
                //构造一个post请求
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        //使用writeValueAsString()方法来获取对象的JSON字符串表示
                        .content(mapper.writeValueAsString(user)))
                //andExpect，添加ResultMathcers验证规则，验证控制器执行完成后结果是否正确，【这是一个断言】
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //假定返回的结果中，"username" 值为 "Mock测试2",如果不是的话，会抛出异常java.lang.AssertionError，并给出期望值和实际值
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("成功"))
                //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
                .andDo(print())
                //返回相应的MvcResult
                .andReturn();
    }
}
