package com.springboott.ttdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboott.ttdemo.dao.UserMapper;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.TestService;
import com.springboott.ttdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Test 服务实现类
 * </p>
 *
 * @author Sam
 * @since 2019-10-09
 */
@Service
@DS("test")
public class TestServiceImpl extends ServiceImpl<UserMapper, User> implements TestService {

    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private RestTemplate restTemplate;

    //默认事务
    @Override
    public void toDoOpenTran(Boolean error) {
        //1.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //2.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //3.获得事务状态
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            update1();
            if (error) {
                int e = 1 / 0;
            }
            // 事务提交
            transactionManager.commit(status);
        } catch (Exception e) {
            // 事务回滚
            transactionManager.rollback(status);
            System.out.println("回滚了");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transRest() {
        List<User> list = userService.list(new LambdaQueryWrapper<User>().in(User::getId, 1, 2, 3, 4, 5, 6, 7));
        System.out.println("list = " + list);
        update4();
        Map<String, String> map = new HashMap<>();
        map.put("limit", "5");
        map.put("offset", "6");
        String object = restTemplate.getForObject("http://127.0.0.1:8089/ttdemo/user?limit={limit}&offset={offset}", String.class, map);
        System.out.println("object = " + object);

        User user = new User();
        user.setUsername("大124");
        user.setAge(50);
        String postForObject = restTemplate.postForObject("http://127.0.0.1:8089/ttdemo/user", user, String.class);
        System.out.println("postForObject = " + postForObject);
    }

    //默认事务
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void update1() {
        User user = new User();
        user.setId(17);
        user.setUsername("大124");
        user.setAge(50);
        userMapper.updateById(user);
        update2();
    }

    //默认事务
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void update2() {
        User user = new User();
        user.setId(18);
        user.setUsername("小6789");
        user.setAge(50);
        userMapper.updateById(user);
    }

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)//默认事务
    public void update3() {
        User user = new User();
        user.setId(19);
        user.setUsername("张a1s");
        user.setAge(10);
        int update3 = userMapper.updateById(user);
        System.out.println("update3 = " + update3);
        update4();
        try {
            //1.使用了trycatch事务失效，update4报错 全执行修改，不会回滚update4 2.update3报错无论是否使用trycatch全部执行修改并回滚全部
            update4();
            //需要使用注入式的调用才能使用恢复springaop的事务功能 1.A无catch A报错A回滚 B报错B回滚 2.Acatch B的异常并抛出异常 A回滚B回滚
//            testService.update4();
        } catch (Exception e) {
            System.out.println("4异常 回滚");
        }
        //制造异常
//        int e = 1 / 0;
    }

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)//存在事务则运行在嵌套事务中，不存在则创建一个事务
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)//新建一个自己的事务，不论当前是否存在事务
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)//默认事务
    public void update4() {
        User user = new User();
        user.setId(20);
        user.setUsername("胡s1sd");
        user.setAge(10);
        int update4 = userMapper.updateById(user);
        System.out.println("update4 = " + update4);
        //制造异常
//        int e = 1 / 0;
    }
}
