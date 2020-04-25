package com.springboott.ttdemo.test;

import com.springboott.ttdemo.TtdemoApplication;
import com.springboott.ttdemo.service.TestService;
import com.springboott.ttdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 事务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TtdemoApplication.class)
public class TransactionTest {

    @Autowired
    private UserService userService;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private TestService testService;

    @Test
    public void main() {
//        testService.update1();
        testService.update3();
        //模拟循环请求
//        testService.toDoOpenTran(true);
//        testService.toDoOpenTran(false);
    }


}
