package com.springboott.ttdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.springboott.ttdemo.po.Datasource;
import com.springboott.ttdemo.dao.DatasourceMapper;
import com.springboott.ttdemo.service.DatasourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据源表 服务实现类
 * </p>
 *
 * @author Sam
 * @since 2020-04-25
 */
@Service
@DS("master")
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, Datasource> implements DatasourceService {

}
