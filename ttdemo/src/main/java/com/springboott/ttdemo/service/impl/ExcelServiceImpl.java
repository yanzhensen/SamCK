package com.springboott.ttdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboott.ttdemo.dao.UserMapper;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.ExcelService;
import com.springboott.ttdemo.util.excel.ExcelData;
import com.springboott.ttdemo.util.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@DS("test")
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize) {
        log.info("导出数据开始。。。。。。");
        //查询数据并赋值给ExcelData
        List<User> userList = userMapper.selectList(Wrappers.emptyWrapper());
        List<List<String>> list = new LinkedList<>();
        for (User user : userList) {
            List<String> addList = Arrays.asList(String.valueOf(user.getId()), String.valueOf(user.getUsername()), String.valueOf(user.getPassword()), String.valueOf(user.getAge()));
            list.add(addList);
        }
        //表头赋值
        List<String> head = Arrays.asList("序列", "用户名", "密码", "年龄");
        ExcelData data = new ExcelData();
        data.setHead(head);
        data.setData(list);
        data.setFileName(fileName);
        //实现导出
        try {
            ExcelUtil.exportExcel(request, response, data);
            log.info("导出数据结束。。。。。。");
            return true;
        } catch (Exception e) {
            log.info("导出数据失败。。。。。。");
            return false;
        }
    }

    @Override
    public Boolean importExcel(String fileName) {
        log.info("导入数据开始。。。。。。");
        try {
            List<Object[]> list = ExcelUtil.importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                User user = new User();
                user.setId((Integer) list.get(i)[0]);
                user.setUsername((String) list.get(i)[1]);
                user.setPassword((String) list.get(i)[2]);
                user.setAge((Integer) list.get(i)[3]);
                userMapper.insert(user);
            }
            log.info("导入数据结束。。。。。。");
            return true;
        } catch (Exception e) {
            log.info("导入数据失败。。。。。。");
            e.printStackTrace();
        }
        return false;
    }
}
