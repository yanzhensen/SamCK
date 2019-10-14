package com.springboott.ttdemo.test;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboott.ttdemo.TtdemoApplication;
import com.springboott.ttdemo.dao.UserMapper;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TtdemoApplication.class)
public class TestPlus {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Test
    public void t1(){
        User u1 =  userMapper.selectById(3);
        System.out.println(u1);
        User u2 = userService.getById(1);
        System.out.println(u2);
//        for (int i = 0; i < 100; i++) {
//            StringBuffer sbu= new StringBuffer();
//            for (int j = 0; j < 9; j++) {
//                sbu.append((int)(Math.random()*10));
//            }
//            User u3 = new User();
//            String as = UUID.randomUUID().toString().substring(0,8);
//            u3.setUsername("测"+as);
//            u3.setPassword("123");
//            u3.setAge((int)(Math.random()*100));
//            u3.setAddress("测试地址"+as);
//            u3.setTelephone("13"+ sbu);
//            u3.setRemark("测试备注"+as);
//            userService.saveOrUpdate(u3);
//        }
        List<User> lsUser = userService.list();
        System.out.println(lsUser.size());

        //使用pageHelp进行分页
//        PageHelper.startPage(1,5);
        QueryWrapper<User> wrapperUser = new QueryWrapper<>();
        wrapperUser.select("*")
                .and(i->i.gt("id",10)).and(i->i.lt("id",30));
//                .eq("username","测f28c2307")
//                .or().like("age",1).and(i -> i.le("age",10));
//                .between("id",2,50);
        List<User> los = userMapper.selectList(wrapperUser);
//        PageInfo<User> page = new PageInfo<User>(los);
//        for (User user : page.getList()) {
//            System.out.println(user);
//        }
        //默认调用toString  增强型lambda表达式
        los.forEach(System.out::println);
        los.forEach(i->System.out.println(i));
        los.forEach(i->{
            int id = i.getId();
            int age = i.getAge();
            String username = i.getUsername();
            System.out.println(id+"  "+username+"  "+age);
        });
    }

    @Test
    public void t2() {
        happy(10000,(m) -> System.out.println("大保健花了："+m));
    }
    @Test
    public void t3() {
        PageHelper.offsetPage(0,10);
        List<User> lr = userService.list();
        PageInfo<User> userList = new PageInfo<User>(lr);
        System.out.println(userList.getList());
    }
    @Test
    public void t4() {
        System.out.println();
    }

    public void happy(double  money, Consumer<Double> con) {
        con.accept(money);
    }
}
