package com.springboott.ttdemo.controller;

import com.baomidou.dynamic.datasource.DynamicDataSourceCreator;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Set;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author Sam
 * @since 2020-04-25
 */
@Api(tags = {"动态增减数据源"})
@RestController
@RequestMapping("/datasources")
@Validated
public class DynamicDatasourceController {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private DynamicDataSourceCreator dataSourceCreator;

    @ApiOperation(value = "查询数据源")
    @GetMapping
    public Set<String> now() {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        return ds.getCurrentDataSources().keySet();
    }

//    @ApiOperation(value = "添加数据源", notes = "<br />请求参数示例：<br /><a href=\"https://pic-gongkai.fangzhizun.com/FrDhuUAO8TZXtEBLz4dixgKIQTzB8430.txt\" target=\"_blank\">请求参数示例</a><br />")
    @ApiOperation(value = "添加数据源", notes = "<br />请求参数示例：<br />" +
            "{\"pollName\":\"test2\",\"driverClassName\":\"com.mysql.cj.jdbc.Driver\",\"url\":\"jdbc:mysql://127.0.0.1:3306/demo_xy?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false\",\"username\":\"root\",\"password\":\"root\"}")
    @PostMapping
    public Set<String> add(@RequestBody DataSourceProperty dataSourceProperty) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dataSourceProperty.getPollName(), dataSource);
        return ds.getCurrentDataSources().keySet();
    }

    @ApiOperation(value = "删除数据源")
    @DeleteMapping
    public void remove(String name) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(name);
    }

}
