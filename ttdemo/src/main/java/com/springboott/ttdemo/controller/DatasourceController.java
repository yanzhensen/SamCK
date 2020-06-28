package com.springboott.ttdemo.controller;


import com.springboott.ttdemo.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据源表 前端控制器
 * </p>
 *
 * @author Sam
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/datasource")
public class DatasourceController {

    @Autowired
    private DatasourceService datasourceService;
}

