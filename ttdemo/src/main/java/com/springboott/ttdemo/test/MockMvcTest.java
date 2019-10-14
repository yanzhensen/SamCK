package com.springboott.ttdemo.test;

import com.springboott.ttdemo.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class MockMvcTest {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }
    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request = null;

        // 1、get查一下user列表，应该为空
        request = get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());;

//        // 2、post提交一个user
//        request = post("/user/")
//                .param("id", "1")
//                .param("username", "测试大师")
//                .param("password", "123123")
//                .param("age", "20");
//        mvc.perform(request)
//                .andExpect(content().string(equalTo("success")));
//
//        // 3、get获取user列表，应该有刚才插入的数据
//        request = get("/user/");
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("[{\"id\":1,\"username\":\"测试大师\",\"age\":20}]")));
//
//        // 4、put修改id为1的user
//        request = put("/user/1")
//                .param("username", "测试终极大师")
//                .param("age", "30");
//        mvc.perform(request)
//                .andExpect(content().string(equalTo("success")));
//
//        // 5、get一个id为1的user
//        request = get("/user/1");
//        mvc.perform(request)
//                .andExpect(content().string(equalTo("{\"id\":1,\"username\":\"测试终极大师\",\"age\":30}")));
//
//        // 6、del删除id为1的user
//        request = delete("/user/1");
//        mvc.perform(request)
//                .andExpect(content().string(equalTo("success")));
//
//        // 7、get查一下user列表，应该为空
//        request = get("/user/");
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("[]")));

    }
}
