package com.springboott.ttdemo.service.impl;

import com.springboott.ttdemo.po.Product;
import com.springboott.ttdemo.dao.ProductMapper;
import com.springboott.ttdemo.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author Sam
 * @since 2019-10-09
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
