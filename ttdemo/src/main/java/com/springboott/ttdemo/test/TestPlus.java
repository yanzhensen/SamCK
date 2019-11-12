package com.springboott.ttdemo.test;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
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

import java.util.*;
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
//        User u1 =  userMapper.selectById(3);
//        System.out.println(u1);
//        User u2 = userService.getById(1);
//        System.out.println(u2);
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
//        List<User> lsUser = userService.list();
//        System.out.println(lsUser.size());

        //使用pageHelp进行分页
//        PageHelper.startPage(1,5);
//        QueryWrapper<User> wrapperUser = new QueryWrapper<>();
//        wrapperUser.select("*")
//                .and(i->i.gt("id",10)).and(i->i.lt("id",30));
//                .eq("username","测f28c2307")
//                .or().like("age",1).and(i -> i.le("age",10));
//                .between("id",2,50);
//        List<User> los = userMapper.selectList(wrapperUser);
//        PageInfo<User> page = new PageInfo<User>(los);
//        for (User user : page.getList()) {
//            System.out.println(user);
//        }
        //默认调用toString  增强型lambda表达式
        /*los.forEach(System.out::println);
        los.forEach(i->System.out.println(i));
        los.forEach(i->{
            int id = i.getId();
            int age = i.getAge();
            String username = i.getUsername();
            System.out.println(id+"  "+username+"  "+age);
        });*/
        List<User> userList1 = userMapper.selectList(new QueryWrapper<User>().lambda().in(User::getId, 1, 2, 3, 4, 5, 6));
        List<User> userList2 = userMapper.selectList(new QueryWrapper<User>().lambda().in(User::getId, 1, 2, 3));
        userList1.addAll(userList2);
        Set<User> set = new HashSet<>(userList1);
        set.forEach(System.out::println);
    }

    @Test
    public void queryWrapperText() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        Map<String,Object> param = new HashMap<>();
        param.put("username","张三丰");
        param.put("password","123");
        param.put("age",null);
        queryWrapper
//                .select("id,username,password,age,telephone,address,remark")//查询字段  不能跟count重复
//                .select(User.class,info->!info.getColumn().equals("address")&&!info.getColumn().equals("remark"))//排除两个字段
//                .inSql("username","select username from user where username like '五%'")//内置sql
//        .nested(wq->wq.lt("age",40).or().isNotNull("remark")).likeRight("username","五")//(age<40 or isNotNull(remark)) and username like '五%'
                .in("age", Arrays.asList(18,30,50)) //age in(18,30,50)
//                .allEq((k,v)->!k.equals("username"),param)//key过滤
//                .allEq(param,param.get("age")!=null)//值过 滤需要判断类型
//                .select("avg(age) avg_age","min(age) min_age","max(age) max_age").groupBy("age").having("sum(age)<{0}",500)//selectMaps
        ;
        List<User> uxxx = userMapper.selectList(queryWrapper);
        uxxx.forEach(System.out::println);
//        List<Map<String, Object>> uxxc = userMapper.selectMaps(queryWrapper);//Maps
//        uxxc.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);//总记录数
        System.out.println("count = " + count);

        List<User> uxxcc = userMapper.selectAll(queryWrapper);//自定义方法查询
        uxxcc.forEach(System.out::println);
    }

    @Test//mybatis plus自带分页
    public void PageText() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.ge("age",26);
        Page<User> page = new Page<User>(1,5,true);//当前页1,每页5条, true要查询总记录数 默认true
        IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
        System.out.println("总页面：" + iPage.getPages());
        System.out.println("总记录数：" + iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);

        IPage<Map<String, Object>> iPage2 = userMapper.selectMapsPage(page,queryWrapper);//返回Map类型 键值对
        List<Map<String, Object>> ulist = iPage2.getRecords();
        System.out.println("总页面：" + iPage2.getPages());
        System.out.println("总记录数：" + iPage2.getTotal());
        ulist.forEach(System.out::println);
    }
    @Test//LambdaQueryWrapper
    public void LambdaText() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeRight(User::getUsername,"五").and(lwq->lwq.lt(User::getAge,20));
        List<User> lambdaUserList = userMapper.selectList(lambdaQueryWrapper);
        lambdaUserList.forEach(System.out::println);
    }
    @Test//修改
    public void updateWrapper() {
        /*
        UpdateWrapper<User> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("username","测试人员479e").eq("age",48);
        User user = new User();
        user.setAge(49);
        user.setPassword("456");
        int rows1 = userMapper.update(user, updateWrapper1);
        System.out.println("影响行数："+rows1);
        */
        /*
        UpdateWrapper<User> updateWrapper2 = new UpdateWrapper<>();
        updateWrapper2.eq("username","测试人员479e").eq("age",49).set("age",50);
        int rows2 = userMapper.update(null, updateWrapper2);
        System.out.println("影响行数："+rows2);
        */

        //updateLambda
        /*
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getUsername,"测试人员479e").eq(User::getAge,50).set(User::getAge,51);
        int rows3 = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("影响行数："+rows3);
        */
        //LambdaUpdateChainWrapper
        boolean update = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getUsername, "测试人员479e").eq(User::getAge, 51).set(User::getAge, 52).update();
        System.out.println(update);
    }
    @Test
    public void t2() {
        happy(10000,(m) -> System.out.println("大保健花了："+m));
    }
    @Test//mybatis 插件分页
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
