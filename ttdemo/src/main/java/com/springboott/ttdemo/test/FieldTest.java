package com.springboott.ttdemo.test;

import com.springboott.ttdemo.TtdemoApplication;
import com.springboott.ttdemo.dao.UserMapper;
import com.springboott.ttdemo.enums.ErrorCodeEnum;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import com.springboott.ttdemo.util.ApiAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TtdemoApplication.class)
public class FieldTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @Test
    public void fieldTest() {
        //反射获取前后修改信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "用户名");
        map.put("password", "用户密码");
        map.put("age", "年龄");
        map.put("telephone", "联系电话");
        map.put("address", "地址");
        map.put("remark", "备注");
        User user = new User(); //模拟修改数据
        user.setUsername("李四");
        user.setAge(20);
        user.setAddress("广东省深圳市福田区竹子林");
        User bean = userService.getById(6); //查询原本数据
        StringBuffer followUp = new StringBuffer();
        followUp.append("此次修改变动信息：");
        try {
            Field[] newUser = user.getClass().getDeclaredFields();
            for (Field field : newUser) {
                field.setAccessible(true);//是否访问私有变量 默认false
                String oldEntity = Objects.nonNull(field.get(bean)) ? field.get(bean).toString() : "";//去null判断 防止报错
                String newEntity = Objects.nonNull(field.get(user)) ? field.get(user).toString() : "";
                if (map.containsKey(field.getName()) && !oldEntity.equals(newEntity) && !"".equals(newEntity)) {
                    followUp.append(map.get(field.getName()) + "：" + oldEntity + " → " + newEntity + "; ");
                }
            }
            System.out.println("followUp = " + followUp);
        } catch (IllegalAccessException e) {
            ApiAssert.isFalse(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("跟进失败"), true);
        }
    }
}
