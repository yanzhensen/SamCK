package com.springboott.ttdemo.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboott.ttdemo.TtdemoApplication;
import com.springboott.ttdemo.dao.UserMapper;
import com.springboott.ttdemo.enums.ErrorCodeEnum;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import com.springboott.ttdemo.util.ApiAssert;
import com.springboott.ttdemo.util.ApiUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TtdemoApplication.class)
public class TestPlus {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @Test
    public void t1() {
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
        /*List<User> userList1 = userMapper.selectList(new QueryWrapper<User>().lambda().in(User::getId, 1, 2, 3, 4, 5, 6));
        List<User> userList2 = userMapper.selectList(new QueryWrapper<User>().lambda().in(User::getId, 1, 2, 3));
        userList1.addAll(userList2);
        Set<User> set = new HashSet<>(userList1);
        set.forEach(System.out::println);*/
        /*List<Integer> ids = Arrays.asList(1,2,3);
        List<User> userList = new LinkedList<>();
        User u = userMapper.selectById(20);
        for (Integer id : ids) {
            System.out.println("id = " + id);
            u.setId(id);
            userList.add(u);
        }
        userList.forEach(System.out::println);*/
        List<User> ls = new LinkedList<>();
        int[] ss = new int[6];
        User u1 = userMapper.selectById(20);
        ls.add(u1);
        ss[0] = 10;
        String str = "123";
        aaa(ls, ss, str);
        System.out.println("ls = " + ls);
        for (int s : ss) {
            System.out.println("s = " + s);
        }
        System.out.println("str = " + str);
    }

    public static void aaa(List<User> ls, int[] ss, String str) {
        User u2 = new User();
        u2.setUsername("1234124sadsaf");
        ls.add(u2);
        ss[1] = 5;
        str = "yzs";
    }

    //不留全删
    @Test
    public void publicTest() {

    }

    @Test
    public void keyTest() {
        //批量主键返回
        List<User> list = new ArrayList<>();
        User u1 = new User();
        u1.setUsername("aa1");
        u1.setAge(50);
        u1.setAddress("广东深圳");
        User u2 = new User();
        u2.setUsername("aa2");
        u2.setAge(50);
        u2.setAddress("广东深圳");
        list.add(u1);
        list.add(u2);
        userService.saveBatch(list);
        list.forEach(System.out::println);
        List<Integer> ids = list.stream().map(e -> e.getId()).collect(Collectors.toList());
        System.out.println("ids = " + ids);
    }

    @Test
    public void steamTest() {
        //倒序
        List<User> list = userMapper.selectList(new LambdaQueryWrapper<User>().in(User::getId, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).orderByDesc(User::getId));
        list.forEach(System.out::println);
        System.out.println("----------------------------------");
        //变为正序 parallelStream多线程版stream 不要随便使用
        Map<Integer, User> userMap = list.parallelStream().collect(Collectors.groupingBy(User::getId,
                Collectors.collectingAndThen(Collectors.reducing((c1, c2) -> c1.getId() > c2.getId() ? c1 : c2), Optional::get)));
        for (Integer integer : userMap.keySet()) {
            System.out.println(userMap.get(integer));
        }
        System.out.println("----------------------------------");
        //倒序
        list = list.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    @Test
    public void allText() {
        /*List<User> userList1 = userMapper.selectList(new QueryWrapper<User>().lambda().in(User::getId, 1, 2, 3, 4, 5, 6));
        for (User user : userList1) {
            user.setPassword("456");
            user.setAge(40);
        }
        userList1.forEach(System.out::println);*/

        LambdaQueryWrapper<User> w1 = new LambdaQueryWrapper();
        w1.gt(User::getAge, 23);
        if (1 == 2) {
            w1.lt(User::getAge, 40);
        }
        userMapper.selectList(w1).forEach(System.out::println);


        /*QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.select("max(age) max");
        List<Map<String, Object>> uxxc = userMapper.selectMaps(queryWrapper);
        System.out.println("uxxc = " + uxxc);
        Map<String, Object> stringObjectMap = uxxc.get(0);
        System.out.println(stringObjectMap.get("max"));*/
        /*boolean update = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getId, 10)
                .set(User::getAge, 35).set(User::getTelephone,"13333456789")
                .set(User::getPassword,456).update();*/
    }


    @Test
    public void java8Tx() {
        //https://mp.weixin.qq.com/s/ogcRRWBE8CtOY5l6kcQvhg
        List<User> list = userMapper.selectList(new QueryWrapper<User>().lambda().in(User::getId, 1, 2, 3, 4, 5, 6, 2, 5, 1));
        list.forEach(s -> System.out.println("list = " + s));
        list.sort((a, b) -> a.getAge() - b.getAge());
        list.forEach(s -> System.out.println("sort = " + s));
        //流 需要接受返回数据，不会改变原实体
        list.stream().filter(s -> s.getAge() > 50).forEach(s -> System.out.println("filter = " + s));
        list.stream().distinct().forEach(s -> System.out.println("distinct = " + s));
        list.stream().map(s -> s.getUsername().toUpperCase()).forEach(s -> System.out.println("toUpperCase = " + s));

        // 找出最长的单词
        Stream<String> stream1 = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream1.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        // Optional<String> longest = stream.max((s1, s2) -> s1.length() - s2.length());
        System.out.println("longest.get() = " + longest.get());
        // 求单词长度之和
        // (参数1)初始值
        // (参数2)累加器
        // (参数3)部分和拼接器，并行执行时才会用到
        Stream<String> stream2 = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream2.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println("lengthSum = " + lengthSum);
        //将Stream规约成List
        Stream<String> stream = Stream.of("I", "love", "Collector");
        List<String> sslist = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);// 方式1
        // List<String> list = stream.collect(Collectors.toList());// 方式2
        System.out.println("sslist = " + sslist);

        Stream<String> stream3 = Stream.of("I", "love", "you", "too");
        // 转换成list集合
        List<String> strList = stream3.collect(Collectors.toList());
        System.out.println("strList = " + strList);
        // 转换成set集合
        // Set<String> set = stream3.collect(Collectors.toSet());
        // 转换成map集合
        // Map<String, Integer> map = stream3.collect(Collectors.toMap(Function.identity(), String::length));

        // 使用toMap()统计字符长度
        Stream<String> stream4 = Stream.of("I", "love", "Collector");
        List<String> list4 = stream4.collect(Collectors.toList());// 方式2
        Map<String, Integer> strLength = list4.stream().collect(Collectors.toMap(Function.identity(), str -> str.length()));
        System.out.println("strLength = " + strLength);

        // 对字符串列表进行分组
        Stream<String> stream5 = Stream.of("I", "love", "Collector");
        List<String> list5 = stream5.collect(Collectors.toList());// 方式2
        Map<Boolean, List<String>> listMap5 = list5.stream().collect(Collectors.partitioningBy(str -> str.length() > 2));
        System.out.println("listMap5 = " + listMap5);

        // 按照长度对字符串列表进行分组
        Stream<String> stream6 = Stream.of("I", "love", "Collector", "you", "Java");
        List<String> list6 = stream6.collect(Collectors.toList());// 方式2
        Map<Integer, List<String>> listMap6 = list6.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("listMap6 = " + listMap6);

        // 使用Collectors.joining()拼接字符串
        Stream<String> stream7 = Stream.of("I", "love", "Collector");
        // String joined = stream7.collect(Collectors.joining());// "IloveCollector"
        // String joined = stream7.collect(Collectors.joining(","));// "I,love,Collector"
        String joined = stream7.collect(Collectors.joining(",", "{", "}"));// "{I,love,Collector}
        System.out.println("joined = " + joined);

        // 按照年龄对员工分布组，并只保留员工的名字
        Map<Integer, List<String>> byDept = list.stream()
                .collect(Collectors.groupingBy(User::getAge,
                        Collectors.mapping(User::getUsername,// 下游收集器
                                Collectors.toList())));// 更下游的收集器
        System.out.println("byDept = " + byDept);
    }

    @Test
    public void queryWrapperText() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        Map<String, Object> param = new HashMap<>();
        param.put("username", "张三丰");
        param.put("password", "123");
        param.put("age", null);
        queryWrapper
//                .select("id,username,password,age,telephone,address,remark")//查询字段  不能跟count重复
//                .select(User.class,info->!info.getColumn().equals("address")&&!info.getColumn().equals("remark"))//排除两个字段
//                .inSql("username","select username from user where username like '五%'")//内置sql
//        .nested(wq->wq.lt("age",40).or().isNotNull("remark")).likeRight("username","五")//(age<40 or isNotNull(remark)) and username like '五%'
                .in("age", Arrays.asList(18, 30, 50)) //age in(18,30,50)
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
        queryWrapper.ge("age", 26);
        Page<User> page = new Page<User>(1, 5, true);//当前页1,每页5条, true要查询总记录数 默认true
//        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        IPage<User> iPage = userService.page(page, queryWrapper);
        System.out.println("总页面：" + iPage.getPages());
        System.out.println("总记录数：" + iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);

        IPage<Map<String, Object>> iPage2 = userMapper.selectMapsPage(page, queryWrapper);//返回Map类型 键值对
        List<Map<String, Object>> ulist = iPage2.getRecords();
        System.out.println("总页面：" + iPage2.getPages());
        System.out.println("总记录数：" + iPage2.getTotal());
        ulist.forEach(System.out::println);
    }

    @Test//LambdaQueryWrapper
    public void LambdaText() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeRight(User::getUsername, "五").and(lwq -> lwq.lt(User::getAge, 20));
        List<User> lambdaUserList = userMapper.selectList(lambdaQueryWrapper);
        lambdaUserList.forEach(System.out::println);
    }

    @Test//修改
    public void updateWrapper() {
        User uss = new User();
        uss.setId(8888);
        uss.setUsername("小明");
        System.out.println("res = " + userMapper.updateById(uss));
        boolean update = new LambdaUpdateChainWrapper<>(userMapper).set(User::getUsername, "晓东").eq(User::getId, 8888).update();
        System.out.println("update = " + update);
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
//        boolean update = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getUsername, "测试人员479e").eq(User::getAge, 51).set(User::getAge, 52).update();
//        System.out.println(update);
    }

    @Test
    public void t2() {
        happy(10000, (m) -> System.out.println("大保健花了：" + m));
    }

    @Test//mybatis 插件分页
    public void t3() {
        PageHelper.offsetPage(0, 10);
        List<User> lr = userService.list();
        PageInfo<User> userList = new PageInfo<User>(lr);
        System.out.println(userList.getList());
    }

    @Test
    public void t4() {
        System.out.println();
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }
}
